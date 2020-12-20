package com.example.petproject.repositories.impl;

import com.example.petproject.domain.User;
import com.example.petproject.repositories.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private static final RowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

  private final JdbcTemplate jdbcTemplate;

  public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override public Optional<User> getByName(String username) {
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject("select * from users where name = ?",
            ROW_MAPPER, username));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }
}
