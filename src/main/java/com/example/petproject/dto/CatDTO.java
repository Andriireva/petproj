package com.example.petproject.dto;

import com.example.petproject.domain.Cat;
import com.example.petproject.domain.Kitty;

import java.util.List;
import java.util.Objects;

public class CatDTO {

  private Long id;
  private String name;
  private String color;
  private Boolean multiColor;
  private String medicalCardNumber;
  private List<KittyDTO> kitties;

  public CatDTO() {
  }

  public CatDTO(Long id, String name, String color, Boolean multiColor, List<KittyDTO> kitties) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.multiColor = multiColor;
    this.kitties = kitties;
  }

  public CatDTO(Long id, String name, String color, Boolean multiColor) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.multiColor = multiColor;
  }

  public static Cat convertToDomain(CatDTO catDto) {
    return new Cat(catDto.getName(), catDto.getColor(), catDto.getMultiColor());
  }

  public static CatDTO convertToDTO(Cat createdCat) {
    return new CatDTO(createdCat.getId(), createdCat.getName(), createdCat.getColor(), createdCat.getMultiColor());
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Boolean getMultiColor() {
    return multiColor;
  }

  public void setMultiColor(Boolean multiColor) {
    this.multiColor = multiColor;
  }

  public List<KittyDTO> getKitties() {
    return kitties;
  }

  public void setKitties(List<KittyDTO> kitties) {
    this.kitties = kitties;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CatDTO catDTO = (CatDTO) o;
    return Objects.equals(id, catDTO.id) &&
          Objects.equals(name, catDTO.name) &&
          Objects.equals(color, catDTO.color) &&
          Objects.equals(multiColor, catDTO.multiColor) &&
          Objects.equals(kitties, catDTO.kitties);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name, color, multiColor, kitties);
  }
}
