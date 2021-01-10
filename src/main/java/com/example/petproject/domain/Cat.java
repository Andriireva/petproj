package com.example.petproject.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cats")
public class Cat extends AbstractAuditDomain {
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
