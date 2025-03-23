package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.OperationalCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationalConditionRepository extends JpaRepository<OperationalCondition, Long> {}
