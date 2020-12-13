package com.example.petproject.controllers;

import com.example.petproject.dto.CatDTO;
import com.example.petproject.service.CatService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/cats")
public class CatController {

  private final CatService catService;

  public CatController(CatService catService) {
    this.catService = catService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CatDTO create(@RequestBody CatDTO catDTO) {
    return catService.createCat(catDTO);
  }


  @GetMapping
  public List<CatDTO> getCats() {
    return Collections.emptyList();
  }

  @GetMapping(path = "/{id}")
  public CatDTO getCat(@PathVariable Long id) {
//  public ResponseEntity<CatDTO> getCat(@PathVariable Long id) {
//    try {
//      return ResponseEntity.ok(catService.get(id));
//    } catch (ResourceNotFoundException e) {
//      return ResponseEntity.notFound().build();
//    }
    return catService.get(id);
  }
}
