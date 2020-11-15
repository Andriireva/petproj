package com.example.petproject.domain;

import java.util.Objects;

public class Cat {
  private Long id;
  private String name;
  private String color;
  private Boolean multiColor;

  public Cat() {
  }

  public Cat(String name, String color, Boolean multiColor) {
    this.name = name;
    this.color = color;
    this.multiColor = multiColor;
  }

  public Cat(Long id, String name, String color, Boolean multiColor) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.multiColor = multiColor;
  }

  @Override
  public String toString() {
    return "Cat{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", color='" + color + '\'' +
          ", multiColor=" + multiColor +
          '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Cat cat = (Cat) o;
    return Objects.equals(id, cat.id) &&
          Objects.equals(name, cat.name) &&
          Objects.equals(color, cat.color) &&
          Objects.equals(multiColor, cat.multiColor);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name, color, multiColor);
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
}
