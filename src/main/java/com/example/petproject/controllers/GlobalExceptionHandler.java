package com.example.petproject.controllers;

import com.example.petproject.exeptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ResourceNotFoundException.class})
//  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<HttpErrorBody> handlerResoruceNotFound(ResourceNotFoundException exception) {
    return ResponseEntity.status(404).body(new HttpErrorBody(exception.getMessage()));
  }

}
