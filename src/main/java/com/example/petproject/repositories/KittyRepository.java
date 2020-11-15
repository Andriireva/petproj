package com.example.petproject.repositories;

import com.example.petproject.domain.Kitty;

import java.util.Optional;

public interface KittyRepository {
  Optional<Kitty> getOne(Long id);

  Optional<Kitty> create(Long catId, Kitty cat);

  Optional<Kitty> update(Long id, Kitty cat);

  void delete(Long id);
}
