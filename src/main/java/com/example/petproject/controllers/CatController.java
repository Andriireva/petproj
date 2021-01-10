package com.example.petproject.controllers;

import com.example.petproject.domain.Cat;
import com.example.petproject.dto.CatDTO;
import com.example.petproject.exeptions.ResourceNotFoundException;
import com.example.petproject.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/cats")
@AllArgsConstructor
public class CatController {

  private final CatService catService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CatDTO create(@RequestBody CatDTO catDTO) {
    return catService.createCat(catDTO);
  }

  @GetMapping
  public List<CatDTO> getCats(
        @RequestParam(required = false) String text,
        @RequestParam(required = false) String sortField
        ) {
    return catService.findAll(text, sortField);
  }

  @GetMapping(path = "/{id}")
  public CatDTO getCat(@PathVariable Long id) {
    return catService.get(id);
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable Long id) {
    catService.deleteCat(id);
  }

  @PutMapping(path = "/{id}")
  public CatDTO update(@PathVariable Long id, @RequestBody CatDTO catDTO) {
    return catService.update(id, catDTO);
  }

}
