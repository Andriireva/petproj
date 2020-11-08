package com.example.petproject.events;

import com.example.petproject.PetprojectApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

  @EventListener(ApplicationReadyEvent.class)
  public void applicationReady() {
    LOGGER.info("====== Application is ready =====");
  }
}
