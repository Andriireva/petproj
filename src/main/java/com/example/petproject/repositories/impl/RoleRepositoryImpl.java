package com.example.petproject.repositories.impl;

import com.example.petproject.domain.Role;
import com.example.petproject.repositories.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

  @Override public List<Role> getRoles(Long id) {
//    return Arrays.asList(new Role(4L, "ADMIN"), new Role(5L, "DEFAULT"));
    return Arrays.asList(new Role(5L, "DEFAULT"));
  }
}
