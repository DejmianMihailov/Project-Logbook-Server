package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.FlightTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightTimeRepository extends JpaRepository<FlightTime, Long> {
}
