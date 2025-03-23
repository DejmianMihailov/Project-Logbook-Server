package com.flight.logbook_server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight_log")
@Getter
@Setter
public class FlightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматично генериране на стойности
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "total_flight_time")
    private Long totalFlightTime;

    @Column(name = "flight_duration", nullable = false)
    private int flightDuration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "distance", nullable = true)
    private Integer distance; // Разстояние на полета в километри

    @Column(name = "pilot_name", nullable = false)
    private String pilotName;


    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;

    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "flight_time_id", nullable = false)
    private FlightTime flightTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "takeoff_id", nullable = false)
    private Takeoff takeoff;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "landing_id", nullable = false)
    private Landing landing;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operational_condition_id", nullable = true)
    private OperationalCondition operationalCondition;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pilot_function_id", nullable = false)
    private PilotFunction pilotFunction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remarks_id", nullable = true)
    private Remarks remarks;


}
