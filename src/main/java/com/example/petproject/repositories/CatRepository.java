package com.example.petproject.repositories;

import com.example.petproject.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

//  Optional<Cat> getOne(Long id);
//
//  Optional<Cat> create(Cat cat);
//
//  Optional<Cat> update(Long id, Cat cat);
//
//  void delete(Long id);
}
