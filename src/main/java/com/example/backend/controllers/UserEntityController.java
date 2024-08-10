package com.example.backend.controllers;

import com.example.backend.models.UserEntity;
import com.example.backend.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/superuser")
public class UserEntityController {
    @Autowired
    public UserEntityService userEntityService;

    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable("id") Long id) {
        return userEntityService.findById(id);
    }

    @PostMapping("/")
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return userEntityService.createUser(userEntity);
    }
}
