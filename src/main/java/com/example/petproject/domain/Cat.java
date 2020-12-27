package com.example.petproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String color;
  private Boolean multiColor;
  @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
  private List<Kitty> kitties;

  public Cat(String name, String color, Boolean multiColor) {
    this.name = name;
    this.color = color;
    this.multiColor = multiColor;
  }

  @Override public String toString() {
    return "Cat{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", color='" + color + '\'' +
          ", multiColor=" + multiColor +
          '}';
  }
}
