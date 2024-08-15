package com.example.demo.api;

import com.example.demo.dto.Response;
import com.example.demo.entity.Events;
import com.example.demo.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventsApiController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/seoul")
    public ResponseEntity<Response<List<Events>>> seoulEvnets() {
        return eventsService.seoulEvents().toResponseEntity();
    }
}
