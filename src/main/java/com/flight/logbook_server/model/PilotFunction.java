package com.flight.logbook_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pilot_function")
@Getter
@Setter
public class PilotFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pilot_role", nullable = false)
    private String role; // Полето, което ще съдържа ролята на пилота

    @Column(name = "pilot_time", nullable = false)
    private int time; // Примерно поле за времето на пилота
}
