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
  private final KittyRepository kittyRepository;

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

  public CatDTO get(Long id) {
    Cat cat = catRepository.getOne(id).orElseThrow(() -> new ResourceNotFoundException("Cat id with id " + id + " is not found"));
    List<Kitty> kitties = kittyRepository.getAllByCatID(id);
    CatDTO catDTO = CatDTO.convertToDTO(cat);
    catDTO.setKitties(kitties.stream().map(kitty -> KittyDTO.convertToDTO(kitty)).collect(Collectors.toList()));
    return catDTO;
  };

  @Transactional
  public void deleteCat(Long id) {
    catRepository.getOne(id).ifPresentOrElse(cat -> {
      kittyRepository.deleteAllBy(id);
      catRepository.delete(id);
    }, () -> {
      throw new ResourceNotFoundException("Cat id with id " + id + " is not found");
    });
  }

  @SneakyThrows
  public List<Cat> getPAges() {
    return catRepository.getPaginatedData(5, 10);
  }

}
