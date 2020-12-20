package com.example.petproject.repositories.impl;

import com.example.petproject.domain.Role;
import com.example.petproject.repositories.RoleRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

  private static final RowMapper<Role> ROW_MAPPER = new BeanPropertyRowMapper<>(Role.class);

  private final JdbcTemplate jdbcTemplate;

  public RoleRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Role> getRoles(Long userId) {
    return jdbcTemplate.query("select r.id, r.name from roles r\n"
          + "inner join users_roles ur on r.id = ur.role_id\n"
          + "where ur.user_id = ?", ROW_MAPPER, userId);
  }
}
