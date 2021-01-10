package com.example.petproject.repositories;

import com.example.petproject.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

  List<Cat> findAllByColorContains(String partColor);

  List<Cat> findAllByCreatedDateAfter(Instant date);

  Optional<Cat> findByColor(String color);

  List<Cat> findAllByColorContainsOrNameContains(String color, String name);

  @Query(nativeQuery = true,
        value = "select * from cats where color like '%?%'")
  List<Cat> findByVeryImportantQuery(String partColor);

//  List<Cat> find

//  Optional<Cat> getOne(Long id);
//
//  Optional<Cat> create(Cat cat);
//
//  Optional<Cat> update(Long id, Cat cat);
//
//  void delete(Long id);
}
