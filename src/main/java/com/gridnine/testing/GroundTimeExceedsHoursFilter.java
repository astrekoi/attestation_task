package com.gridnine.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class implements the FlightFilter interface and filters flights based on their ground time.
 */
public class GroundTimeExceedsHoursFilter implements FlightFilter {

    private final long maxGroundTime;
    private final List<Flight> excludedFlights;

    /**
     * Constructor for GroundTimeExceedsHoursFilter.
     *
     * @param maxGroundTime The maximum ground time allowed for a flight in hours.
     */
    public GroundTimeExceedsHoursFilter(long maxGroundTime) {
        this.maxGroundTime = maxGroundTime;
        this.excludedFlights = new ArrayList<>();
    }

    /**
     * Filters flights based on their ground time.
     *
     * @param flights The list of flights to be filtered.
     * @return A list of flights that have a ground time less than or equal to the maximum ground time allowed.
     */
    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() <= 1) {
                        return true;
                    }
                    long totalGroundTime = IntStream.range(0, segments.size() - 1)
                            .mapToLong(i -> Duration.between(segments.get(i).getArrivalDate(),
                                    segments.get(i + 1).getDepartureDate()).toHours())
                            .sum();
                    boolean isValid = totalGroundTime <= maxGroundTime;
                    if (!isValid) {
                        excludedFlights.add(flight);
                    }
                    return isValid;
                })
                .collect(Collectors.toList());
    }

    /**
     * Returns a copy of the list of excluded flights.
     *
     * @return A copy of the list of excluded flights.
     */
    public List<Flight> getExcludedFlights() {
        return new ArrayList<>(excludedFlights);
    }
}
