package com.example.petproject.events;

import com.example.petproject.domain.Cat;
import com.example.petproject.repositories.CatRepository;
import com.example.petproject.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

  private final CatRepository catRepository;
  private final ApplicationService applicationService;

  public ApplicationReadyEventListener(CatRepository catRepository,
        ApplicationService applicationService) {
    this.catRepository = catRepository;
    this.applicationService = applicationService;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void applicationReady() {
    LOGGER.info("====== Application is ready =====");
    LOGGER.info("====== Application is name {} =====", applicationService.getName());
//
//    LOGGER.info("Cat with id 1: " + catRepository.getOne(1L));

    var newCat = catRepository.create(new Cat("MyNewCatname", "Black", false));
    LOGGER.info("New created cat: " + newCat);

    var updatedCat = catRepository.update(newCat.get().getId(), new Cat("Updateteadname", "updateCOloer", true));
    LOGGER.info("updated cat: " + updatedCat);

    LOGGER.info("Deleting cat with id: " + updatedCat.get().getId());
    catRepository.delete(updatedCat.get().getId());

    var deletedCat = catRepository.getOne(updatedCat.get().getId());
    LOGGER.info("Deleted cat: " + deletedCat);
  }
}
