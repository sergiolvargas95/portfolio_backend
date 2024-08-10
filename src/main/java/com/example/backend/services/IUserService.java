package com.example.backend.services;

import com.example.backend.models.UserEntity;

import java.util.Optional;

public interface IUserService {
    UserEntity createUser(UserEntity user);

    UserEntity updateUser(UserEntity user, Long id);

    UserEntity findById(Long id);

    Optional<UserEntity> findByUsername(String name);
}
