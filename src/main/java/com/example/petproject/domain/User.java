package com.example.petproject.domain;

import java.util.Objects;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String password;

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                name.equals(user.name) &&
                password.equals(user.password) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}
