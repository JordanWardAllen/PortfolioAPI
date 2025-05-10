package com.portfolioAPI.portfolioapi.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolioAPI.portfolioapi.DTO.UserDTO;
import com.portfolioAPI.portfolioapi.Mapper.UserMapper;
import com.portfolioAPI.portfolioapi.Service.AsyncService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.io.IOException;
import java.util.*;

@Repository
@Slf4j
public class RedisRepositoryImpl {

    private final UnifiedJedis jedisClient = new UnifiedJedis("redis://localhost:6379");
    private final UserMapper userMapper;
    private final AsyncService asyncService;

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public RedisRepositoryImpl(UserMapper userMapper, AsyncService asyncService) {
        this.userMapper = userMapper;
        this.asyncService = asyncService;
    }

    @PostConstruct
    public void init() {
        try {
            // Enable notifications on a fresh Jedis instance
            try (Jedis pubSubJedis = new Jedis("localhost", 6379)) {
                pubSubJedis.configSet("notify-keyspace-events", "KExg$");

                JedisPubSub listener = new JedisPubSub() {
                    @Override
                    public void onPMessage(String pattern, String channel, String msg) {
                        log.info("Pattern: {} | Channel: {} | Event: {}", pattern, channel, msg);
                    }
                };
                new Thread(() -> pubSubJedis.psubscribe(listener, "__keyspace@0__:*"),
                        "RedisKeyspaceSubscriber").start(); // :contentReference[oaicite:12]{index=12}
            }
        } catch (Exception ex) {
            log.error("Failed to initialize Redis Pub/Sub listener", ex);
        }
    }

    public void saveUser(UserDTO user) {
        try {
            jedisClient.set(user.getName(), objectMapper.writeValueAsString(userMapper.mapUserDTOToUserEntity(user)));
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public void createUser(UserDTO user) {
        if (!findUserByName(user.getName()).isPresent()) {
            saveUser(user);
        } else {
            throw new NoSuchElementException(user.getName() + " does not exist.");
        }

    }

    public void deleteUser(String name) {
        jedisClient.del(name);

    }

    public Optional<UserDTO> findUserByName(String name) {
        try {
            String value = jedisClient.get(name);
            if(value != null) {
                return Optional.of(objectMapper.convertValue(objectMapper.readTree(value), UserDTO.class));
            }
            return Optional.empty();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserDTO> findAll() {
        Map<String, UserDTO> result = new HashMap<>();

        // Efficiently iterate keys with SCAN
        String cursor = "0";
        do {
            ScanResult<String> scan = jedisClient.scan(cursor, new ScanParams().match("*").count(100));
            List<String> keys = scan.getResult();
            if (!keys.isEmpty()) {
                // Batch-fetch values
                List<String> values = jedisClient.mget(keys.toArray(new String[0]));
                for (int i = 0; i < keys.size(); i++) { ;
                    try {
                        UserDTO userDTO = objectMapper.convertValue(objectMapper.readTree(values.get(i)), UserDTO.class);
                        result.put(keys.get(i), userDTO);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
            cursor = scan.getCursor();
        } while (!"0".equals(cursor));

        return result.values().stream().toList();
    }
}
