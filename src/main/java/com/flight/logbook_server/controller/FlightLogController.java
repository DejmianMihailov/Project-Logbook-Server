package com.flight.logbook_server.controller;

import com.flight.logbook_server.model.FlightLog;
import com.flight.logbook_server.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class FlightLogController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<String> addFlightWithAuth(@RequestBody FlightLog flightLog, Authentication authentication) {
        String username = authentication.getName();
        flightLog.setUsername(username);

        if (flightLog.getDepartureAirport() == null || flightLog.getDepartureAirport().isEmpty()) {
            return ResponseEntity.badRequest().body("Departure airport is required.");
        }

        if (flightLog.getArrivalAirport() == null || flightLog.getArrivalAirport().isEmpty()) {
            return ResponseEntity.badRequest().body("Arrival airport is required.");
        }

        flightService.addFlight(flightLog);
        return ResponseEntity.ok("Flight added successfully");
    }

    @PostMapping("/manual")
    public ResponseEntity<String> addFlightWithoutAuth(@RequestBody FlightLog flightLog) {
        try {
            flightService.addFlight(flightLog);
            return ResponseEntity.ok("Flight added successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<FlightLog>> getUserFlights(Principal principal) {
        String username = principal.getName();
        List<FlightLog> userFlights = flightService.getFlightsByUsername(username);
        return ResponseEntity.ok(userFlights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightLog> getFlightById(@PathVariable Long id) {
        try {
            FlightLog flightLog = flightService.getFlightById(id);
            return ResponseEntity.ok(flightLog);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.ok("Flight deleted successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
