package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the FlightFilter interface and filters flights based on their arrival and departure times.
 */
public class ArrivalBeforeOrAfterDepartureFilter implements FlightFilter {

    private final boolean isBefore;
    private final List<Flight> excludedFlights;

    /**
     * Constructor for ArrivalBeforeOrAfterDepartureFilter.
     *
     * @param isBefore A boolean value indicating whether to filter flights that arrive before or after their departure time.
     */
    public ArrivalBeforeOrAfterDepartureFilter(boolean isBefore) {
        this.isBefore = isBefore;
        this.excludedFlights = new ArrayList<>();
    }

    /**
     * Filters flights based on their arrival and departure times.
     *
     * @param flights The list of flights to be filtered.
     * @return A list of flights that arrive before or after their departure time depending on the value of isBefore.
     */
    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    boolean isValid = flight.getSegments().stream()
                            .noneMatch(segment -> isBefore ? segment.getArrivalDate().isBefore(segment.getDepartureDate())
                                    : segment.getArrivalDate().isAfter(segment.getDepartureDate()));
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
