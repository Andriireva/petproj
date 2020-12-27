package com.example.petproject.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kitties")
public class Kitty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String color;

  @ManyToOne()
  @JoinColumn(name = "cat_id")
  private Cat cat;

  public Kitty(String name, String color) {
    this.name = name;
    this.color = color;
  }

  public Kitty(Long id, String name, String color, Long catId) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  @Override public String toString() {
    return "Kitty{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", color='" + color + '\'' +
          '}';
  }
}
