package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the FlightFilter interface and filters flights based on their departure time.
 */
public class DepartureBeforeOrAfterCurrentTimeFilter implements FlightFilter {

    private final LocalDateTime currentDateTime;
    private final boolean isBefore;
    private final List<Flight> excludedFlights;

    /**
     * Constructor for DepartureBeforeOrAfterCurrentTimeFilter.
     *
     * @param currentDateTime The current date and time.
     * @param isBefore A boolean value indicating whether to filter flights that depart before or after the current date and time.
     */
    public DepartureBeforeOrAfterCurrentTimeFilter(LocalDateTime currentDateTime, boolean isBefore) {
        this.currentDateTime = currentDateTime;
        this.isBefore = isBefore;
        this.excludedFlights = new ArrayList<>();
    }

    /**
     * Filters flights based on their departure time.
     *
     * @param flights The list of flights to be filtered.
     * @return A list of flights that depart before or after the current date and time depending on the value of isBefore.
     */
    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    boolean isValid = isBefore ? flight.getSegments().get(0).getDepartureDate().isAfter(currentDateTime)
                            : flight.getSegments().get(0).getDepartureDate().isBefore(currentDateTime);
                    if (!isValid) {
                        excludedFlights.add(flight);
                    }
                    return isValid;
                })
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of excluded flights.
     *
     * @return The list of excluded flights.
     */
    public List<Flight> getExcludedFlights() {
        return new ArrayList<>(excludedFlights);
    }
}
