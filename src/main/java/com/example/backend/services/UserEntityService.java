package com.example.backend.services;

import com.example.backend.models.UserEntity;
import com.example.backend.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public UserEntity updateUser(UserEntity user, Long id) {
        UserEntity userEntityOptional = userEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user with ID " + id + " not found."));

        userEntityOptional.setUsername(user.getUsername());
        userEntityOptional.setPassword(encryptPassword(user.getPassword()));
        userEntityOptional.setEmail(user.getEmail());

        return userEntityRepository.save(userEntityOptional);
    }

    @Override
    public UserEntity findById(Long id) {
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
        return password;
    }
}
