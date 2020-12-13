package com.example.petproject.repositories.impl;

import com.example.petproject.domain.User;
import com.example.petproject.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private static Map<String, User> users = new HashMap<>();
  static {
    users.put("admin", new User(1L, "admin", "$2y$08$8UVfC5KuETxNRlDIQ2/00e7aTohpbLAczXdXnnHw1MN3ipPRJ3rzO"));
    users.put("dima", new User(2L, "dima", "$2y$08$8UVfC5KuETxNRlDIQ2/00e7aTohpbLAczXdXnnHw1MN3ipPRJ3rzO"));
  }

  @Override public Optional<User> getByName(String username) {
    if (users.containsKey(username)) {
      return Optional.of(users.get(username));
    } else {
      return Optional.empty();
    }
  }
}
