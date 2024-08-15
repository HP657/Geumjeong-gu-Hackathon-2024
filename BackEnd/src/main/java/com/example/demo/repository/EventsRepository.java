package com.example.demo.repository;

import com.example.demo.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Long> {
    List<Events> findByRegion(String region);
}
