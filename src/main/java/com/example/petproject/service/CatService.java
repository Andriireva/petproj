package com.example.petproject.service;

import com.example.petproject.client.FBCat;
import com.example.petproject.client.PetClinicClient;
import com.example.petproject.domain.Cat;
import com.example.petproject.domain.Kitty;
import com.example.petproject.dto.CatDTO;
import com.example.petproject.dto.KittyDTO;
import com.example.petproject.exeptions.ResourceNotFoundException;
import com.example.petproject.repositories.CatRepository;
import com.example.petproject.repositories.KittyRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.petproject.dto.KittyDTO.convertToDomain;

@Component
public class CatService {

  private final CatRepository catRepository;
  private final KittyRepository kittyRepository;
//  private final PetClinicClient petClinicClient;

  public CatService(CatRepository catRepository, KittyRepository kittyRepository) {
    this.catRepository = catRepository;
    this.kittyRepository = kittyRepository;
  }

  public CatDTO createCat(CatDTO catDto) {

    var createdCat = catRepository.create(CatDTO.convertToDomain(catDto)).get();
    var convertDtoCat = CatDTO.convertToDTO(createdCat);
    convertDtoCat.setKitties(new ArrayList<>());

    if (!CollectionUtils.isEmpty(catDto.getKitties())) {
      catDto.getKitties().forEach(kittyDTO -> {

        var createdKitty = kittyRepository.create(createdCat.getId(), convertToDomain(kittyDTO)).get();
        var convertedKittyDTO = KittyDTO.convertToDTO(createdKitty);
        convertDtoCat.getKitties().add(convertedKittyDTO);

      });
    }

    return convertDtoCat;
  }

  @Transactional
  public void deleteCat(Long id) {
    catRepository.getOne(id).ifPresentOrElse(cat -> {
      kittyRepository.deleteAllBy(id);
      catRepository.delete(id);
    }, () -> {
      throw new ResourceNotFoundException("Cat id with id " + id + " is not found");
    });
  }

}
