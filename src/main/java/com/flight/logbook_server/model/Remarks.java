package com.flight.logbook_server.model;



import jakarta.persistence.*;

@Entity
@Table(name = "remarks")
public class Remarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = true)
    private String comment;

    // Getters and Setters
}
