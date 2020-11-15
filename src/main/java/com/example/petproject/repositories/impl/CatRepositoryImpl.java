package com.example.petproject.repositories.impl;

import com.example.petproject.domain.Cat;
import com.example.petproject.repositories.CatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Optional;

@Component
public class CatRepositoryImpl implements CatRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(CatRepositoryImpl.class);
  private static final BeanPropertyRowMapper<Cat> CAT_BEAN_PROPERTY_ROW_MAPPER = new BeanPropertyRowMapper<>(Cat.class);

  private final String applicationName;
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public CatRepositoryImpl(String applicationName,
        JdbcTemplate jdbcTemplate) {
    this.applicationName = applicationName;
    this.jdbcTemplate = jdbcTemplate;
    LOGGER.info("==== CatRepositoryImpl constructor is called =====");
    LOGGER.info("Application name: " + this.applicationName);
  }

  @Override
  public Optional<Cat> getOne(Long id) {
    try {
      var cat = jdbcTemplate.queryForObject("select * from cats where id = ?",
            CAT_BEAN_PROPERTY_ROW_MAPPER, id);
      return Optional.of(cat);
    } catch (EmptyResultDataAccessException e) {
      LOGGER.debug("EmptyResultDataAccessException: ", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<Cat> create(Cat cat) {
    LOGGER.debug("creating new cat with properties {}, {}", cat, "another info");
    LOGGER.info("creating new cat");
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps =
            connection.prepareStatement("insert into cats(name, color, multi_color) values (?, ?, ?)",
                  new String[] { "id" });
      ps.setString(1, cat.getName());
      ps.setString(2, cat.getColor());
      ps.setBoolean(3, cat.getMultiColor());
      return ps;
    }, keyHolder);
    long catId = keyHolder.getKey().longValue();
    return getOne(catId);
  }

  @Override
  public Optional<Cat> update(Long id, Cat cat) {
    jdbcTemplate.update("update cats set name = ?, "
                + "color = ?, "
                + "multi_color = ? "
                + "where id = ?",
          cat.getName(), cat.getColor(), cat.getMultiColor(), id);
    return getOne(id);
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM cats where id = ?", id);
  }
}
