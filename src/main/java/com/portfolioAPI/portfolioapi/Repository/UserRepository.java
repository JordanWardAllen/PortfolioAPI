package com.portfolioAPI.portfolioapi.Repository;

import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
//public interface UserRepository extends JpaRepository<UserEntity, Long> {
//
//    UserEntity findUserById(Long id);
//
//    UserEntity findUserByEmail(String email);
//
//}


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserById(Long id);
    UserEntity findUserByEmail(String email);
    Optional<UserEntity> findUserByName(String name);
    Optional<UserEntity> findUserByEmailAndPassword(String email, String password);

}

