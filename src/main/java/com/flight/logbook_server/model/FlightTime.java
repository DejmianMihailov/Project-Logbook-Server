package com.flight.logbook_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "flight_time")
@Getter
@Setter
public class FlightTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "multi_pilot_time", nullable = false)
    private int multiPilotTime; // Минутен формат

    @Column(name = "single_pilot_time", nullable = false)
    private int singlePilotTime; // Минутен формат

    @Column(name = "total_flight_time", nullable = false)
    private int totalFlightTime; // Автоматично изчислявано

    /**
     * Изчислява общото време в минути като сума от multiPilotTime и singlePilotTime.
     * @return Общо време в минути
     */
    public int getTotalMinutes() {
        return multiPilotTime + singlePilotTime;
    }

    /**
     * Актуализира totalFlightTime при всяка промяна на multiPilotTime или singlePilotTime.
     */
    @PrePersist
    @PreUpdate
    public void calculateTotalFlightTime() {
        this.totalFlightTime = getTotalMinutes();
    }
}
