package com.flight.logbook_server.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserStatistics {
    private int totalFlights;
    private long totalFlightTimeMinutes;
    private String longestDistance;
}
