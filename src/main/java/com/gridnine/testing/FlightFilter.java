package com.gridnine.testing;

import java.util.List;

/**
 * This interface defines a method for filtering flights.
 */
public interface FlightFilter {

    /**
     * Filters a list of flights based on custom criteria.
     *
     * @param flights The list of flights to be filtered.
     * @return A list of flights that meet the filter criteria.
     */
    List<Flight> filterFlights(List<Flight> flights);
}