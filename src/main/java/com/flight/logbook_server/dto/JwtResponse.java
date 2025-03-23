package com.flight.logbook_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private String email;
    private boolean enabled;
    private String role;
}
