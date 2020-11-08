package com.example.petproject.repositories.impl;

import com.example.petproject.repositories.CatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatRepositoryImpl implements CatRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(CatRepositoryImpl.class);

  private final String applicationName;


  @Autowired
  public CatRepositoryImpl(String applicationName) {
    this.applicationName = applicationName;
    LOGGER.info("==== CatRepositoryImpl constructor is called =====");
    LOGGER.info("Application name: " + this.applicationName);
  }

  @Override
  public String getFamCatName() {
    return "Snow";
  }
}
