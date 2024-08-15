package com.example.demo.scheduler;

import com.example.demo.service.CrawlEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyResetScheduler {

    @Autowired
    private CrawlEvents crawlEvents;

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyEvents() {
        crawlEvents.getEvents();
    }
}