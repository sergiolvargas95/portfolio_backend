package com.example.backend.repositories;

import com.example.backend.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String name);
}
