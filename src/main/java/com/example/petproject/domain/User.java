package com.example.petproject.domain;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String password;

    public User(Long id) {
        this.id = id;
    }
}
