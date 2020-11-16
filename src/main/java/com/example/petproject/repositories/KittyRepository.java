package com.example.petproject.repositories;

import com.example.petproject.domain.Kitty;

import java.util.Optional;

public interface KittyRepository extends Paginated<Kitty> {
  Optional<Kitty> getOne(Long id);

  Optional<Kitty> create(Long catId, Kitty kitty);

  Optional<Kitty> update(Long id, Kitty kitty);

  void delete(Long id);

  void deleteAllBy(Long catId);
}
