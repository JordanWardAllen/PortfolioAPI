package com.portfolioAPI.portfolioapi.Mapper;

import com.portfolioAPI.portfolioapi.DTO.UserDTO;
import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserMapper {

    public UserEntity mapUserDTOToUserEntity (UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setName(userDTO.getName());
        userEntity.setAlias(userDTO.getAlias());
        return userEntity;
    }

    public UserDTO mapUserEntityToUserDTO (UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setName(userEntity.getName());
        userDTO.setAlias(userEntity.getAlias());
        return userDTO;
    }


    public Iterable<UserDTO> mapUserDTOToUserEntityIterator (Iterable<UserEntity> userEntityIterable) {
        List<UserDTO> userDTOList = new ArrayList<>();
        userEntityIterable.forEach(userEntity -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setPassword(userEntity.getPassword());
            userDTO.setName(userEntity.getName());
            userDTO.setAlias(userEntity.getAlias());
            userDTOList.add(userDTO);
        });

        return userDTOList;

    }
}
