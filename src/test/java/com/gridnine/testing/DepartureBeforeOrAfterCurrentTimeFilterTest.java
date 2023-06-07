package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartureBeforeOrAfterCurrentTimeFilterTest {

    @Test
    @DisplayName("Should return flights with departure time before the current time when isBefore is true")
    public void filterFlightsBeforeCurrentTime() {
        LocalDateTime currentDateTime = LocalDateTime.of(1234, 1, 1, 12, 0);
        LocalDateTime dep1 = LocalDateTime.of(1234, 1, 1, 10, 0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1, 1, 11, 0);
        Segment segment1 = new Segment(dep1, arr1);
        Flight flight1 = new Flight(List.of(segment1));

        LocalDateTime dep2 = LocalDateTime.of(1234, 1, 1, 13, 0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1, 1, 14, 0);
        Segment segment2 = new Segment(dep2, arr2);
        Flight flight2 = new Flight(List.of(segment2));

        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Create filter
        DepartureBeforeOrAfterCurrentTimeFilter filter =
                new DepartureBeforeOrAfterCurrentTimeFilter(currentDateTime, true);

        // Call method under test
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Verify results
        assertEquals(1, filteredFlights.size());
        assertTrue(filteredFlights.contains(flight2));
    }

    @Test
    @DisplayName("Should return flights with departure time after the current time when isBefore is false")
    void filterFlightsWhenIsBeforeFalse() {
        LocalDateTime currentDateTime = LocalDateTime.of(1234, 1, 1, 12, 0);
        LocalDateTime dep1 = LocalDateTime.of(1234, 1, 1, 10, 0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1, 1, 11, 0);
        Segment segment1 = new Segment(dep1, arr1);
        Flight flight1 = new Flight(List.of(segment1));

        LocalDateTime dep2 = LocalDateTime.of(1234, 1, 1, 13, 0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1, 1, 14, 0);
        Segment segment2 = new Segment(dep2, arr2);
        Flight flight2 = new Flight(List.of(segment2));

        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Create filter
        DepartureBeforeOrAfterCurrentTimeFilter filter =
                new DepartureBeforeOrAfterCurrentTimeFilter(currentDateTime, false);

        // Call method under test
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Verify results
        assertEquals(1, filteredFlights.size());
        assertTrue(filteredFlights.contains(flight1));
    }

    @Test
    @DisplayName("Should return excluded flights")
    public void getExcludedFlights() {
        LocalDateTime currentDateTime = LocalDateTime.of(1235 ,1,1,12,0);
        LocalDateTime dep1 = LocalDateTime.of(1234, 1,1,10,0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1,1,11,0);
        Segment segment1 = new Segment(dep1 ,arr1);
        Flight flight1 = new Flight(List.of(segment1));

        LocalDateTime dep2 = LocalDateTime.of(1234, 1,1,13,0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1,1,14,0);
        Segment segment2 = new Segment(dep2,arr2);
        Flight flight2 = new Flight(List.of(segment2));

        List<Flight> flights = Arrays.asList(flight1 ,flight2);

        // Create filter
        DepartureBeforeOrAfterCurrentTimeFilter filter =
                new DepartureBeforeOrAfterCurrentTimeFilter(currentDateTime,true);

        // Call method under test
        filter.filterFlights(flights);

        // Verify results
        assertEquals(2, filter.getExcludedFlights().size());
    }
}