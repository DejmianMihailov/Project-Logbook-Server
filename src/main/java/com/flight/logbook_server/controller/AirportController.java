package com.flight.logbook_server.controller;

import com.flight.logbook_server.model.Airport;
import com.flight.logbook_server.repository.AirportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // GET /api/airports
    @GetMapping
    public List<Airport> getAllAirports() {
        // Връщате директно Entity или DTO – в прост случай Entity е ОК
        return airportRepository.findAll();
    }

    // GET /api/airports/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable Long id) {
        return airportRepository.findById(id)
                .map(airport -> ResponseEntity.ok(airport))
                .orElse(ResponseEntity.notFound().build());
    }
}