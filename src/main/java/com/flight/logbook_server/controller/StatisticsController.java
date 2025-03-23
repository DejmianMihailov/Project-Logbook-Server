package com.flight.logbook_server.controller;

import com.flight.logbook_server.dto.UserStatistics;
import com.flight.logbook_server.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<UserStatistics> getUserStatistics(Principal principal) {
        String username = principal.getName();
        UserStatistics statistics = flightService.getUserStatistics(username);
        return ResponseEntity.ok(statistics);
    }
}