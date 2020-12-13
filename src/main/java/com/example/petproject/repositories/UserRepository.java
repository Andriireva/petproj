package com.example.petproject.repositories;

import com.example.petproject.domain.User;

import java.util.Optional;

public interface UserRepository {
  Optional<User> getByName(String username);
}
