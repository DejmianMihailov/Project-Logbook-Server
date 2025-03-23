package com.flight.logbook_server.service;

import com.flight.logbook_server.dto.UserStatistics;
import com.flight.logbook_server.model.*;
import com.flight.logbook_server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightLogRepository flightLogRepository;
    private final AircraftRepository aircraftRepository;
    private final FlightTimeRepository flightTimeRepository;
    private final TakeoffRepository takeoffRepository;
    private final LandingRepository landingRepository;
    private final OperationalConditionRepository operationalConditionRepository;
    private final PilotFunctionRepository pilotFunctionRepository;
    private final RemarksRepository remarksRepository;

    /**
     * Add a new flight to the system.
     *
     * @param flightLog The flight log object containing all necessary information.
     */
    public void addFlight(FlightLog flightLog) {
        // Validate departure and arrival airports
        if (flightLog.getDepartureAirport() == null || flightLog.getDepartureAirport().isEmpty()) {
            throw new RuntimeException("Departure airport cannot be null or empty.");
        }
        if (flightLog.getArrivalAirport() == null || flightLog.getArrivalAirport().isEmpty()) {
            throw new RuntimeException("Arrival airport cannot be null or empty.");
        }

        // Calculate distance (mock example; replace with real logic if needed)
        flightLog.setDistance(calculateDistance(flightLog.getDepartureAirport(), flightLog.getArrivalAirport()));

        // Calculate total flight time
        if (flightLog.getDepartureTime() != null && flightLog.getArrivalTime() != null) {
            long totalTimeMinutes = Duration.between(flightLog.getDepartureTime(), flightLog.getArrivalTime()).toMinutes();
            flightLog.setTotalFlightTime(totalTimeMinutes);
        } else {
            throw new RuntimeException("Departure and Arrival times must be provided.");
        }

        // Validate and save Aircraft
        Aircraft aircraft = flightLog.getAircraft();
        if (aircraft != null) {
            Optional<Aircraft> existingAircraft = aircraftRepository.findByRegistration(aircraft.getRegistration());
            if (existingAircraft.isPresent()) {
                flightLog.setAircraft(existingAircraft.get());
            } else {
                Aircraft savedAircraft = aircraftRepository.save(aircraft);
                flightLog.setAircraft(savedAircraft);
            }
        }

        // Validate and save FlightTime
        FlightTime flightTime = flightLog.getFlightTime();
        if (flightTime != null) {
            flightTime = flightTimeRepository.save(flightTime);
            flightLog.setFlightTime(flightTime);
        } else {
            throw new RuntimeException("FlightTime cannot be null.");
        }

        // Validate and save Takeoff
        Takeoff takeoff = flightLog.getTakeoff();
        if (takeoff != null) {
            takeoff = takeoffRepository.save(takeoff);
            flightLog.setTakeoff(takeoff);
        } else {
            throw new RuntimeException("Takeoff cannot be null.");
        }

        // Validate and save Landing
        Landing landing = flightLog.getLanding();
        if (landing != null) {
            landing = landingRepository.save(landing);
            flightLog.setLanding(landing);
        } else {
            throw new RuntimeException("Landing cannot be null.");
        }

        // Validate and save OperationalCondition (optional)
        OperationalCondition operationalCondition = flightLog.getOperationalCondition();
        if (operationalCondition != null) {
            operationalCondition = operationalConditionRepository.save(operationalCondition);
            flightLog.setOperationalCondition(operationalCondition);
        }

        // Validate and save PilotFunction
        PilotFunction pilotFunction = flightLog.getPilotFunction();
        if (pilotFunction != null) {
            if (pilotFunction.getRole() == null || pilotFunction.getRole().isEmpty()) {
                throw new RuntimeException("Pilot Role cannot be null or empty.");
            }
            pilotFunction = pilotFunctionRepository.save(pilotFunction);
            flightLog.setPilotFunction(pilotFunction);
        } else {
            throw new RuntimeException("PilotFunction cannot be null.");
        }

        // Validate and save Remarks (optional)
        Remarks remarks = flightLog.getRemarks();
        if (remarks != null) {
            remarks = remarksRepository.save(remarks);
            flightLog.setRemarks(remarks);
        }

        // Save FlightLog
        flightLogRepository.save(flightLog);
    }

    /**
     * Calculate distance between departure and arrival airports (mock implementation).
     *
     * @param departureAirport The departure airport code.
     * @param arrivalAirport   The arrival airport code.
     * @return The distance in kilometers.
     */
    private int calculateDistance(String departureAirport, String arrivalAirport) {
        // Replace this mock logic with real distance calculation
        return (departureAirport.hashCode() + arrivalAirport.hashCode()) % 1000;
    }

    /**
     * Retrieve all flights for a specific user.
     *
     * @param username The username of the user.
     * @return A list of flights.
     */
    public List<FlightLog> getFlightsByUsername(String username) {
        return flightLogRepository.findByUsername(username);
    }

    /**
     * Retrieve user statistics based on flights.
     *
     * @param username The username of the user.
     * @return The statistics object.
     */
    public UserStatistics getUserStatistics(String username) {
        List<FlightLog> flights = flightLogRepository.findByUsername(username);

        if (flights == null || flights.isEmpty()) {
            return new UserStatistics(0, 0L, "0 km");
        }

        int totalFlights = flights.size();
        long totalFlightTimeMinutes = flights.stream()
                .filter(flight -> flight.getTotalFlightTime() != null)
                .mapToLong(FlightLog::getTotalFlightTime)
                .sum();

        int longestDistance = flights.stream()
                .filter(flight -> flight.getDistance() != null)
                .mapToInt(FlightLog::getDistance)
                .max()
                .orElse(0);

        return new UserStatistics(totalFlights, totalFlightTimeMinutes, longestDistance + " km");
    }

    /**
     * Retrieve a specific flight by its ID.
     *
     * @param id The ID of the flight.
     * @return The flight log object.
     */
    public FlightLog getFlightById(Long id) {
        Optional<FlightLog> flightLog = flightLogRepository.findById(id);
        return flightLog.orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    /**
     * Delete a flight by its ID.
     *
     * @param id The ID of the flight.
     */
    public void deleteFlight(Long id) {
        if (flightLogRepository.existsById(id)) {
            flightLogRepository.deleteById(id);
        } else {
            throw new RuntimeException("Flight not found");
        }
    }
}
