package com.flight.logbook_server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "landing")
public class Landing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_landing", nullable = true)
    private int dayLanding;

    @Column(name = "night_landing", nullable = true)
    private int nightLanding;

    // Getters and Setters
}
