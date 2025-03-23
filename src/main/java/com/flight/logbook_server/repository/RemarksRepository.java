package com.flight.logbook_server.repository;

import com.flight.logbook_server.model.Remarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarksRepository extends JpaRepository<Remarks, Long> {}