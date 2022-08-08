package com.portfolioAPI.portfolioapi.Repository;

import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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

    UserEntity findUserById(Long id);
    UserEntity findUserByEmail(String email);
    UserEntity findUserByName(String name);
    UserEntity findUserByEmailAndPassword(String email, String password);

}

