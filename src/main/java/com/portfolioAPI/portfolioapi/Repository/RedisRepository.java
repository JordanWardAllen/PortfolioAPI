//package com.portfolioAPI.portfolioapi.Repository;
//
//import com.portfolioAPI.portfolioapi.DTO.UserDTO;
//import com.portfolioAPI.portfolioapi.Entity.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface RedisRepository extends JpaRepository<UserDTO, String> {
//
//    Optional<UserDTO> findUserById(Long id);
//    UserDTO findUserByEmail(String email);
//    Optional<UserDTO> findUserByName(String name);
//    Optional<UserDTO> findUserByEmailAndPassword(String email, String password);
//
//}
