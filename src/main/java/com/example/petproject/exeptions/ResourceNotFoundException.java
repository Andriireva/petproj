package com.example.petproject.exeptions;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String s) {
    super(s);
  }
}
