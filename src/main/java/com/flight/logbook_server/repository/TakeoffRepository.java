package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.Takeoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeoffRepository extends JpaRepository<Takeoff, Long> {}
