package org.gp.dataservice.cassandra.controller;

import com.datastax.driver.core.utils.UUIDs;
import org.gp.dataservice.cassandra.model.Event;
import org.gp.dataservice.cassandra.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/events")
public class EventServiceController {

  @Autowired
  EventRepository eventRepository;

  @GetMapping
  public ResponseEntity<List<Event>> getAllEvents(
      @RequestParam(required = false) String type) {

    try {
      List<Event> events = new ArrayList<Event>();

      if (type == null)
        eventRepository.findAll().forEach(events::add);
      else
        eventRepository.findByTypeContaining(type).forEach(events::add);

      if (events.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(events, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Event> getEventById(@PathVariable("id") UUID id) {

    Optional<Event> eventData = eventRepository.findById(id);

    if (eventData.isPresent()) {
      return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Event> createTutorial(@RequestBody Event event) {

    try {
      Event _event = eventRepository.save(new Event(UUIDs.timeBased(),
          event.getType(), event.getData()));
      return new ResponseEntity<>(_event, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @GetMapping("/tutorials/published")
  // public ResponseEntity<List<Event>> findByPublished() {
  //
  // try {
  // List<Event> tutorials = eventRepository.findByPublished(true);
  //
  // if (tutorials.isEmpty()) {
  // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  // }
  // return new ResponseEntity<>(tutorials, HttpStatus.OK);
  // } catch (Exception e) {
  // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }

}
