package com.example.petproject.dto;

import com.example.petproject.domain.Kitty;

import java.util.Objects;

public class KittyDTO {
  private Long id;
  private String name;
  private String color;

  public KittyDTO() {
  }

  public KittyDTO(String name, String color) {
    this.name = name;
    this.color = color;
  }

  public KittyDTO(Long id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  public static Kitty convertToDomain(KittyDTO kittyDTO) {
    return new Kitty(kittyDTO.getName(), kittyDTO.getColor());
  }

  public static KittyDTO convertToDTO(Kitty createdKitty) {
    return new KittyDTO(createdKitty.getId(), createdKitty.getName(), createdKitty.getColor());
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


  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    KittyDTO kittyDTO = (KittyDTO) o;
    return Objects.equals(id, kittyDTO.id) &&
          Objects.equals(name, kittyDTO.name) &&
          Objects.equals(color, kittyDTO.color);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name, color);
  }
}
