package com.example.petproject.controllers;

import com.example.petproject.dto.KittyDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cats/{id}/kitties")
public class KittyController {


  @PostMapping
  public KittyDTO createKitty(@PathVariable("id") Long catId,
        @RequestBody KittyDTO kittyDTO) {
    return new KittyDTO();
  }
}
