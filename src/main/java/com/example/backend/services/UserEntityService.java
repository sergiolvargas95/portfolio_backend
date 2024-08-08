package com.example.backend.services;

import com.example.backend.models.UserEntity;
import com.example.backend.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserEntityService implements IUserService {
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;

    }

    @Override
    public UserEntity createUser(UserEntity user) {
        if(userEntityRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already taken.");
        }
        
        user.setPassword(encryptPassword(user.getPassword()));

        return userEntityRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userEntityRepository.save(user);
    }

    @Override
    public UserEntity findUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userEntityRepository.findById(id);
        return userEntityOptional.orElse(null);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userEntityRepository.findByUsername(username);
    }

    private String encryptPassword(String password) {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //return encoder.encode(password);
    }
}
