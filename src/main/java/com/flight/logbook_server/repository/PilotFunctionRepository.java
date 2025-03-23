package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.PilotFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotFunctionRepository extends JpaRepository<PilotFunction, Long> {}

