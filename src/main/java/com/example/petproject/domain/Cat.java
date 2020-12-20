package com.example.petproject.domain;

import lombok.*;

import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Value
public class Cat {
  private Long id;
  private String name;
  private String color;
  private Boolean multiColor;

  public Cat(String name, String color, Boolean multiColor) {
    this.name = name;
    this.color = color;
    this.multiColor = multiColor;
  }
}
