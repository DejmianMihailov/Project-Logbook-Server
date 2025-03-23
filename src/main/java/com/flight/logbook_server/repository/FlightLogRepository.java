package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.FlightLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightLogRepository extends JpaRepository<FlightLog, Long> {

    @Query("SELECT SUM(f.flightTime.totalFlightTime) FROM FlightLog f")
    Integer findTotalFlightTime();
    List<FlightLog> findByUsername(String username);
}

