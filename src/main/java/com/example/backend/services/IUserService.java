package com.example.backend.services;

import com.example.backend.models.UserEntity;
import org.apache.catalina.User;

import java.util.Optional;

public interface IUserService {
    UserEntity createUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    UserEntity findUserById(Long id);

    Optional<UserEntity> findByUsername(String name);
}
