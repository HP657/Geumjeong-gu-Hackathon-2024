package com.example.demo.api;

import com.example.demo.dto.Response;
import com.example.demo.entity.Events;
import com.example.demo.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventsApiController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/{eventId}")
    public ResponseEntity<Response<Optional<Events>>> eventIdEvent(@PathVariable("eventId") Long eventId) {
        return eventsService.getEventIdByEvent(eventId).toResponseEntity();
    }

    @GetMapping("/seoul")
    public ResponseEntity<Response<List<Events>>> seoulEvents() {
        return eventsService.getEventsByRegion("서울시").toResponseEntity();
    }

    @GetMapping("/busan")
    public ResponseEntity<Response<List<Events>>> busanEvents() {
        return eventsService.getEventsByRegion("부산시").toResponseEntity();
    }

    @GetMapping("/daegu")
    public ResponseEntity<Response<List<Events>>> daeguEvents() {
        return eventsService.getEventsByRegion("대구시").toResponseEntity();
    }

    @GetMapping("/incheon")
    public ResponseEntity<Response<List<Events>>> incheonEvents() {
        return eventsService.getEventsByRegion("인천시").toResponseEntity();
    }

    @GetMapping("/gwangju")
    public ResponseEntity<Response<List<Events>>> gwangjuEvents() {
        return eventsService.getEventsByRegion("광주시").toResponseEntity();
    }

    @GetMapping("/daejeon")
    public ResponseEntity<Response<List<Events>>> daejeonEvents() {
        return eventsService.getEventsByRegion("대전시").toResponseEntity();
    }

    @GetMapping("/ulsan")
    public ResponseEntity<Response<List<Events>>> ulsanEvents() {
        return eventsService.getEventsByRegion("울산시").toResponseEntity();
    }

    @GetMapping("/sejong")
    public ResponseEntity<Response<List<Events>>> sejongEvents() {
        return eventsService.getEventsByRegion("세종시").toResponseEntity();
    }

    @GetMapping("/gyeonggi")
    public ResponseEntity<Response<List<Events>>> gyeonggiEvents() {
        return eventsService.getEventsByRegion("경기도").toResponseEntity();
    }

    @GetMapping("/gangwon")
    public ResponseEntity<Response<List<Events>>> gangwonEvents() {
        return eventsService.getEventsByRegion("강원도").toResponseEntity();
    }

    @GetMapping("/chungbuk")
    public ResponseEntity<Response<List<Events>>> chungbukEvents() {
        return eventsService.getEventsByRegion("충청북도").toResponseEntity();
    }

    @GetMapping("/chungnam")
    public ResponseEntity<Response<List<Events>>> chungnamEvents() {
        return eventsService.getEventsByRegion("충청남도").toResponseEntity();
    }

    @GetMapping("/jeonbuk")
    public ResponseEntity<Response<List<Events>>> jeonbukEvents() {
        return eventsService.getEventsByRegion("전라북도").toResponseEntity();
    }

    @GetMapping("/jeonnam")
    public ResponseEntity<Response<List<Events>>> jeonnamEvents() {
        return eventsService.getEventsByRegion("전라남도").toResponseEntity();
    }

    @GetMapping("/gyeongbuk")
    public ResponseEntity<Response<List<Events>>> gyeongbukEvents() {
        return eventsService.getEventsByRegion("경상북도").toResponseEntity();
    }

    @GetMapping("/gyeongnam")
    public ResponseEntity<Response<List<Events>>> gyeongnamEvents() {
        return eventsService.getEventsByRegion("경상남도").toResponseEntity();
    }

    @GetMapping("/jeju")
    public ResponseEntity<Response<List<Events>>> jejuEvents() {
        return eventsService.getEventsByRegion("제주도").toResponseEntity();
    }
}
