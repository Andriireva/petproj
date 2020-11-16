package com.example.petproject.repositories.impl;

import com.example.petproject.domain.Kitty;
import com.example.petproject.repositories.KittyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class KittyRepositoryImpl implements KittyRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(CatRepositoryImpl.class);
  private static final BeanPropertyRowMapper<Kitty> KITTY_BEAN_PROPERTY_ROW_MAPPER = new BeanPropertyRowMapper<Kitty>(Kitty.class);

  private final JdbcTemplate jdbcTemplate;

  public KittyRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Kitty> getOne(Long id) {
    try {
      var kitty = jdbcTemplate.queryForObject("select * from cats where id = ?",
            KITTY_BEAN_PROPERTY_ROW_MAPPER, id);
      return Optional.of(kitty);
    } catch (EmptyResultDataAccessException e) {
      LOGGER.debug("EmptyResultDataAccessException: ", e);
      return Optional.empty();
    }
  }

  @Override
  public Optional<Kitty> create(Long catId, Kitty kitty) {
    LOGGER.debug("creating new Kitty with properties {}", kitty);
    LOGGER.info("creating new kitty");
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps =
            connection.prepareStatement("insert into kitty(name, color, cat_id) values (?, ?, ?)",
                  new String[] { "id" });
      ps.setString(1, kitty.getName());
      ps.setString(2, kitty.getColor());
      ps.setLong(3, kitty.getCatId());
      return ps;
    }, keyHolder);
    long kittyId = keyHolder.getKey().longValue();
    return getOne(kittyId);
  }

  @Override
  public Optional<Kitty> update(Long id, Kitty kitty) {
    jdbcTemplate.update("update cats set name = ?, "
                + "color = ?, "
                + "where id = ?",
          kitty.getName(), kitty.getColor(), id);
    return getOne(id);
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM kitties where id = ?", id);
  }

  @Override
  public List<Kitty> getPaginatedData(int pageNumber, int pageSize) {
    return Collections.emptyList();
  }

  @Override
  public void deleteAllBy(Long catId) {
    jdbcTemplate.update("DELETE FROM kitties where cat_id = ?", catId);
  }
}
