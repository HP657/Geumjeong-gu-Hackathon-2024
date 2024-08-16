package com.example.demo.service;

import com.example.demo.dto.Response;
import com.example.demo.entity.Events;
import com.example.demo.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    public Response<List<Events>> getEventsByRegion(String region) {
        return new Response<>(eventsRepository.findByRegion(region), HttpStatus.OK);
    }

    public Response<Optional<Events>> getEventIdByEvent(Long eventId) {
        return new Response<>(eventsRepository.findById(eventId), HttpStatus.OK);
    }
}
