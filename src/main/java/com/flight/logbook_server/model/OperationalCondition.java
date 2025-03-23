package com.flight.logbook_server.model;


import jakarta.persistence.*;

@Entity
@Table(name = "operational_condition")
public class OperationalCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "night_flight_time", nullable = true)
    private int nightFlightTime;

    // Getters and Setters
}
