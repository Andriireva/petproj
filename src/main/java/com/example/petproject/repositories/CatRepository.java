package com.example.petproject.repositories;

import com.example.petproject.domain.Cat;

import java.util.Optional;

public interface CatRepository extends Paginated<Cat> {

  Optional<Cat> getOne(Long id);

  Optional<Cat> create(Cat cat);

  Optional<Cat> update(Long id, Cat cat);

  void delete(Long id);
}
