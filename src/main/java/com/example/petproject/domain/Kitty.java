package com.example.petproject.domain;

import java.util.Objects;

public class Kitty {
  private Long id;
  private String name;
  private String color;
  private Long catId;

  public Kitty() {
  }

  public Kitty(String name, String color) {
    this.name = name;
    this.color = color;
  }

  public Kitty(Long id, String name, String color, Long catId) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.catId = catId;
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

  public Long getCatId() {
    return catId;
  }

  public void setCatId(Long catId) {
    this.catId = catId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Kitty kitty = (Kitty) o;
    return Objects.equals(id, kitty.id) &&
          Objects.equals(name, kitty.name) &&
          Objects.equals(color, kitty.color) &&
          Objects.equals(catId, kitty.catId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, color, catId);
  }

  @Override public String toString() {
    return "Kitty{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", color='" + color + '\'' +
          ", catId=" + catId +
          '}';
  }
}
