package com.example.SWP391.controller;

import com.example.SWP391.entity.Event;
import com.example.SWP391.payload.Request.InsertEventRequest;
import com.example.SWP391.service.EventService;
import com.example.SWP391.service.imp.EventServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api-events")
public class EventController {
    @Autowired
    private EventServiceImp eventServiceimp;
    @Autowired
    private EventService eventService;



    @GetMapping
    public List<Event> getAllEvent(){

        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventDetailsById(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public ResponseEntity<?> insertEvent(InsertEventRequest request) {
        eventServiceimp.insertEvent(request);
        System.out.println("kiem tra a: " + request.getDescription());

        return new ResponseEntity<>("Insert Event", OK);
    }
}
