package com.qa.springboot.dvd.repository;

import com.qa.springboot.dvd.model.UserModel;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<UserModel> findByName(String name);
}
