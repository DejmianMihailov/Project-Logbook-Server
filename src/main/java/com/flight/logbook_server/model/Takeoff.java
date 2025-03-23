package com.flight.logbook_server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "takeoff")
public class Takeoff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_takeoff", nullable = true)
    private int dayTakeoff;

    @Column(name = "night_takeoff", nullable = true)
    private int nightTakeoff;

    // Getters and Setters
}
