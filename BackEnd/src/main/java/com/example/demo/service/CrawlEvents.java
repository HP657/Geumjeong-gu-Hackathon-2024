package com.example.demo.service;

import com.example.demo.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CrawlEvents {
    @Autowired
    private RestTemplate restTemplate;

    public Response<String> getEvents() {
        String url = "http://localhost:5000/api/crawl";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return new Response<>(response.getBody(), HttpStatus.OK);
    }
}
