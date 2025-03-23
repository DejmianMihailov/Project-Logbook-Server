package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.Landing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandingRepository extends JpaRepository<Landing, Long> {}