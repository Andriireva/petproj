package com.example.petproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationService {

  private final String name;

  public ApplicationService(@Value("${myapplication.name}") String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
