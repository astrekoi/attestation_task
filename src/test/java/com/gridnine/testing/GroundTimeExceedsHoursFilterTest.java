package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroundTimeExceedsHoursFilterTest {

    @Test
    @DisplayName("Should return an empty list when the input flights list is empty")
    void filterFlightsWhenInputListIsEmpty() {
        GroundTimeExceedsHoursFilter filter = new GroundTimeExceedsHoursFilter(2);

        // Call the method under test with an empty list
        List<Flight> result = filter.filterFlights(new ArrayList<>());

        // Assert that the result is an empty list
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return the same list when all flights have only one segment")
    void filterFlightsWhenAllFlightsHaveOneSegment() {
        List<Flight> flights = Arrays.asList(
                new Flight(List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)))),
                new Flight(List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(3)))),
                new Flight(List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(4))))
        );

        // Create filter instance
        GroundTimeExceedsHoursFilter filter = new GroundTimeExceedsHoursFilter(5);

        // Call the method under test
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Verify the result
        assertEquals(flights, filteredFlights);
    }
}