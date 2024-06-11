package com.example.SWP391.service;

import com.example.SWP391.entity.Event;
import com.example.SWP391.entity.EventDetails;
import com.example.SWP391.entity.EventImage;
import com.example.SWP391.payload.Request.InsertEventRequest;
import com.example.SWP391.repository.EventDetailsRepository;
import com.example.SWP391.repository.EventImageRepository;
import com.example.SWP391.repository.EventRepository;
import com.example.SWP391.repository.EventScheduleRepository;
import com.example.SWP391.service.imp.EventServiceImp;
import com.example.SWP391.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EventService implements EventServiceImp {
    @Autowired
    private FileServiceImp fileServiceimp;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventImageRepository eventImageRepository;
    @Autowired
    private EventDetailsRepository eventDetailsRepository;
    @Autowired
    private EventScheduleRepository eventScheduleRepository;


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public boolean insertEvent(InsertEventRequest request) {
        boolean isCopySuccess = fileServiceimp.saveFile(request.getFile());

        if(isCopySuccess){
            Event eventEntity = new Event();
            eventEntity.setDescription(request.getDescription());
            eventEntity.setName(request.getEventName());
            eventEntity.setTimestart(request.getTimeStart());
            eventEntity.setTimeend(request.getTimeEnd());
            eventEntity.setPrice(request.getPrice());

            Event eventSaved = eventRepository.save(eventEntity);

            EventImage eventImage = new EventImage();
            eventImage.setName(request.getFile().getOriginalFilename());
            eventImage.setEvent(eventSaved);
            eventImageRepository.save(eventImage);

            EventDetails eventDetails = new EventDetails();
            eventDetails.setClient(request.getClient());
            eventDetails.setDate(request.getDate());
            eventDetails.setTime(request.getTime());
            eventDetails.setLocation(request.getLocation());
            eventDetails.setEvent(eventSaved);
            eventDetails.setDuration(request.getDuration());
            eventDetailsRepository.save(eventDetails);




        }
        return false;
    }
}
