package com.example.petproject.service;

import com.example.petproject.domain.Cat;
import com.example.petproject.domain.Kitty;
import com.example.petproject.dto.CatDTO;
import com.example.petproject.dto.KittyDTO;
import com.example.petproject.exeptions.ResourceNotFoundException;
import com.example.petproject.repositories.CatRepository;
import com.example.petproject.repositories.KittyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.petproject.dto.KittyDTO.convertToDomain;

@Component
@RequiredArgsConstructor
public class CatService {

  private final CatRepository catRepository;

  public CatDTO createCat(CatDTO catDto) {
    Cat catToBeSaved = CatDTO.convertToDomain(catDto);
    List<Kitty> kittyList = catDto.getKitties()
          .stream()
          .map(kittyDTO -> {
            Kitty kitty = convertToDomain(kittyDTO);
            kitty.setCat(catToBeSaved);
            return kitty;
          })
          .collect(Collectors.toList());
    catToBeSaved.setKitties(kittyList);
    var createdCat = catRepository.save(catToBeSaved);

    var convertDtoCat = CatDTO.convertToDTO(createdCat);
    convertDtoCat.setKitties(createdCat.getKitties()
          .stream()
          .map(kitty -> KittyDTO.convertToDTO(kitty))
          .collect(Collectors.toList()));
    return convertDtoCat;
  }

  public CatDTO get(Long id) {
    Cat cat = catRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Cat id with id " + id + " is not found"));
    CatDTO catDTO = CatDTO.convertToDTO(cat);
    catDTO.setKitties(cat.getKitties().stream().map(kitty -> KittyDTO.convertToDTO(kitty)).collect(Collectors.toList()));
    return catDTO;
  };

  @Transactional
  public void deleteCat(Long id) {
    catRepository.findById(id).ifPresentOrElse(cat -> catRepository.delete(cat), () -> {
      throw new ResourceNotFoundException("Cat id with id " + id + " is not found");
    });
  }
//
//  @SneakyThrows
//  public List<Cat> getPAges() {
//    return catRepository.getPaginatedData(5, 10);
//  }

}
