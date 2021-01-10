package com.example.petproject.service;

import com.example.petproject.domain.Cat;
import com.example.petproject.domain.Kitty;
import com.example.petproject.dto.CatDTO;
import com.example.petproject.dto.KittyDTO;
import com.example.petproject.exeptions.ResourceNotFoundException;
import com.example.petproject.repositories.CatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.petproject.dto.KittyDTO.convertToDomain;

@Component
@RequiredArgsConstructor
@Slf4j
public class CatService {

  private final CatRepository catRepository;

  public CatDTO createCat(CatDTO catDto) {

    log.info("Creating user");
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
  }

  @Transactional
  public void deleteCat(Long id) {
    catRepository.findById(id).ifPresentOrElse(cat -> catRepository.delete(cat), () -> {
      throw new ResourceNotFoundException("Cat id with id " + id + " is not found");
    });
  }

  @Transactional
  public CatDTO update(Long id, CatDTO catDTO) {
    Cat catToBeSaved = catRepository
          .findById(id)
          .map(cat -> {
            cat.setName(catDTO.getName());
            cat.setColor(catDTO.getColor());
            cat.setMultiColor(catDTO.getMultiColor());
            return cat;
          })
          .orElseThrow(() -> new ResourceNotFoundException("Cat id with id " + id + " is not found"));
    Cat updatedCat = catRepository.save(catToBeSaved);
    CatDTO updatedCatDTO = CatDTO.convertToDTO(updatedCat);
    updatedCatDTO.setKitties(updatedCat.getKitties().stream().map(kitty -> KittyDTO.convertToDTO(kitty)).collect(Collectors.toList()));
    return updatedCatDTO;
  }

  public List<CatDTO> findAll(String text, String sortField) {
    List<Cat> cats;
    if (StringUtils.isEmpty(text)) {
      cats = catRepository.findAll();
    } else {
//      cats = catRepository.findAllByColorContains(text);
      ExampleMatcher exampleMatcher = ExampleMatcher
            .matchingAny()
            .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
      cats = catRepository.findAll(
            Example.of(new Cat(text, text, null), exampleMatcher),
            Sort.by(Sort.Direction.DESC, sortField));
    }
    return cats
          .stream()
          .map(cat -> {
            CatDTO catDTO = CatDTO.convertToDTO(cat);
            catDTO.setKitties(cat.getKitties().stream().map(kitty -> KittyDTO.convertToDTO(kitty)).collect(Collectors.toList()));
            return catDTO;
          })
          .collect(Collectors.toList());
  }
  //
  //  @SneakyThrows
  //  public List<Cat> getPAges() {
  //    return catRepository.getPaginatedData(5, 10);
  //  }

}
