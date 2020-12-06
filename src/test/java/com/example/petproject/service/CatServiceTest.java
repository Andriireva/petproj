package com.example.petproject.service;

import com.example.petproject.domain.Cat;
import com.example.petproject.domain.Kitty;
import com.example.petproject.dto.CatDTO;
import com.example.petproject.dto.KittyDTO;
import com.example.petproject.exeptions.ResourceNotFoundException;
import com.example.petproject.repositories.CatRepository;
import com.example.petproject.repositories.KittyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatServiceTest {

  @InjectMocks
  private CatService catService;

  @Mock
  private CatRepository catRepository;

  @Mock
  private KittyRepository kittyRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void createCat_catDtoWithoutKitties_shouldReturnNewCat() {
    Cat cat = new Cat();
    String msNorris = "Ms Norris";
    cat.setName(msNorris);
    String color = "Yellow";
    cat.setColor(color);
    boolean multiColor = true;
    cat.setMultiColor(multiColor);
    long generatedCatId = 55L;
    when(catRepository.create(cat))
          .thenAnswer(invocation -> {
            ((Cat) invocation.getArgument(0)).setId(generatedCatId);
            return of(invocation.getArgument(0));
          });

    CatDTO catDto = new CatDTO();
    catDto.setName(msNorris);
    catDto.setColor(color);
    catDto.setMultiColor(multiColor);
    CatDTO createdCat = catService.createCat(catDto);

    assertNotNull(createdCat);
    assertNotNull(createdCat.getId());
    assertEquals("Ms Norris", createdCat.getName());
    assertEquals("Yellow", createdCat.getColor());
    assertEquals(Boolean.TRUE, createdCat.getMultiColor());
    assertNotNull(createdCat.getKitties());
    assertEquals(0, createdCat.getKitties().size());
    verify(catRepository).create(any(Cat.class));
    verify(kittyRepository, never()).create(eq(generatedCatId), any(Kitty.class));
  }

  @Test
  void createCat_catDtoWithKitties_shouldReturnNewCat() {
    Cat cat = new Cat();
    String msNorris = "Ms Norris";
    cat.setName(msNorris);
    String color = "Yellow";
    cat.setColor(color);
    boolean multiColor = true;
    cat.setMultiColor(multiColor);
    long generatedCatId = 55L;
    when(catRepository.create(cat))
          .thenAnswer(invocation -> {
            ((Cat) invocation.getArgument(0)).setId(generatedCatId);
            return of(invocation.getArgument(0));
          });
    when(kittyRepository.create(eq(generatedCatId), any(Kitty.class)))
          .thenAnswer(invocation -> {
            ((Kitty) invocation.getArgument(1)).setId(66L);
            return of(invocation.getArgument(1));
          });

    CatDTO catDto = new CatDTO();
    catDto.setName(msNorris);
    catDto.setColor(color);
    catDto.setMultiColor(multiColor);
    catDto.setKitties(Arrays.asList(new KittyDTO("Kittyone", "white"),
          new KittyDTO("Kittytwo", "white")));
    CatDTO createdCat = catService.createCat(catDto);

    assertNotNull(createdCat);
    assertNotNull(createdCat.getId());
    assertEquals("Ms Norris", createdCat.getName());
    assertEquals("Yellow", createdCat.getColor());
    assertEquals(Boolean.TRUE, createdCat.getMultiColor());
    assertNotNull(createdCat.getKitties());
    assertEquals(2, createdCat.getKitties().size());
    verify(catRepository).create(any(Cat.class));
    verify(kittyRepository, times(2)).create(eq(generatedCatId), any(Kitty.class));
  }


  @Test
  void deleteCat_existedCat_shouldExecuteDeleteMethods() {
    Long catToDelete = 77L;
    when(catRepository.getOne(catToDelete)).thenReturn(of(new Cat()));

    catService.deleteCat(catToDelete);

    verify(catRepository).delete(catToDelete);
    verify(kittyRepository).deleteAllBy(catToDelete);
  }

  @Test
  void deleteCat_notExistedCat_shouldThrowResourceNotFoundException() {
    Long catToDelete = 77L;
    when(catRepository.getOne(catToDelete)).thenReturn(Optional.empty());

    assertThrows(NullPointerException.class, () -> catService.deleteCat(catToDelete));

    verify(catRepository, never()).delete(catToDelete);
    verify(kittyRepository, never()).deleteAllBy(catToDelete);
  }

}