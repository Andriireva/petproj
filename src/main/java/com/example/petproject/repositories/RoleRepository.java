package com.example.petproject.repositories;

import com.example.petproject.domain.Role;

import java.util.List;

public interface RoleRepository {
  List<Role> getRoles(Long id);
}
