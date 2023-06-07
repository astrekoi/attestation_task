package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrivalBeforeOrAfterDepartureFilterTest {

    @Test
    @DisplayName("Should filter flights with arrival after departure when isBefore is false")
    void filterFlightsWithArrivalAfterDepartureWhenIsBeforeIsFalse() {
        LocalDateTime dep1 = LocalDateTime.of(1234, 1, 1, 10, 0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1, 1, 12, 0);
        Segment segment1 = new Segment(dep1, arr1);

        LocalDateTime dep2 = LocalDateTime.of(1234, 1, 1, 13, 0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1, 1, 11, 0);
        Segment segment2 = new Segment(dep2, arr2);

        Flight flight1 = new Flight(List.of(segment1));
        Flight flight2 = new Flight(List.of(segment2));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Create the filter
        ArrivalBeforeOrAfterDepartureFilter filter =
                new ArrivalBeforeOrAfterDepartureFilter(false);

        // Filter the flights
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Check that the correct flights were filtered out
        assertEquals(1, filteredFlights.size());
        assertEquals(flight2, filteredFlights.get(0));
    }

    @Test
    @DisplayName("Should return an empty list when no flights match the filter criteria")
    void filterFlightsReturnsEmptyListWhenNoFlightsMatchCriteria() {
        LocalDateTime dep1 = LocalDateTime.of(1234, 1, 1, 10, 0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1, 1, 9, 0);
        Segment segment1 = new Segment(dep1, arr1);

        LocalDateTime dep2 = LocalDateTime.of(1234, 1, 1, 13, 0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1, 1, 12, 0);
        Segment segment2 = new Segment(dep2, arr2);

        LocalDateTime dep3 = LocalDateTime.of(1234, 1, 1, 16, 0);
        LocalDateTime arr3 = LocalDateTime.of(1234, 1, 1, 15, 0);
        Segment segment3 = new Segment(dep3, arr3);

        Flight flight1 = new Flight(List.of(segment1));
        Flight flight2 = new Flight(List.of(segment2));
        Flight flight3 = new Flight(List.of(segment3));

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3);

        // Create the filter
        ArrivalBeforeOrAfterDepartureFilter filter =
                new ArrivalBeforeOrAfterDepartureFilter(true);

        // Call the method under test
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Verify the result
        assertTrue(filteredFlights.isEmpty());
    }

    @Test
    @DisplayName("Should filter flights with arrival before departure when isBefore is true")
    void filterFlightsWithArrivalBeforeDepartureWhenIsBeforeIsTrue() {// Create test data
        LocalDateTime dep1 = LocalDateTime.of(1234, 1, 1, 10, 0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1, 1, 11, 0);
        LocalDateTime dep2 = LocalDateTime.of(1234, 1, 1, 12, 0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1, 1, 11, 0);
        Segment segment1 = new Segment(dep1, arr1);
        Segment segment2 = new Segment(dep2, arr2);
        List<Segment> segments1 = List.of(segment1);
        List<Segment> segments2 = Arrays.asList(segment1, segment2);
        Flight flight1 = new Flight(segments1);
        Flight flight2 = new Flight(segments2);
        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Create filter
        ArrivalBeforeOrAfterDepartureFilter filter =
                new ArrivalBeforeOrAfterDepartureFilter(true);

        // Filter flights
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Verify filtered flights
        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));

        // Verify excluded flights
        List<Flight> excludedFlights = filter.getExcludedFlights();
        assertEquals(1, excludedFlights.size());
        assertEquals(flight2, excludedFlights.get(0));
    }

    @Test
    @DisplayName("Should add excluded flights to the excludedFlights list")
    void filterFlightsAddsExcludedFlightsToExcludedFlightsList() {
        LocalDateTime dep1 = LocalDateTime.of(1234, 1, 1, 10, 0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1, 1, 12, 0);
        Segment segment1 = new Segment(dep1, arr1);

        LocalDateTime dep2 = LocalDateTime.of(1234, 1, 1, 13, 0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1, 1, 15, 0);
        Segment segment2 = new Segment(dep2, arr2);

        LocalDateTime dep3 = LocalDateTime.of(1234, 1, 1, 16, 0);
        LocalDateTime arr3 = LocalDateTime.of(1234, 1, 1, 14, 0);
        Segment segment3 = new Segment(dep3, arr3);

        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment3));

        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Create the filter
        ArrivalBeforeOrAfterDepartureFilter filter =
                new ArrivalBeforeOrAfterDepartureFilter(false);

        // Call the method under test
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Verify the results
        assertEquals(1, filteredFlights.size());
        assertEquals(flight2, filteredFlights.get(0));
    }

    @Test
    @DisplayName("Should return all flights when all flights match the filter criteria")
    void filterFlightsReturnsAllFlightsWhenAllFlightsMatchCriteria() {
        LocalDateTime dep1 = LocalDateTime.of(1234, 1, 1, 10, 0);
        LocalDateTime arr1 = LocalDateTime.of(1234, 1, 1, 12, 0);
        Segment segment1 = new Segment(dep1, arr1);

        LocalDateTime dep2 = LocalDateTime.of(1234, 1, 1, 14, 0);
        LocalDateTime arr2 = LocalDateTime.of(1234, 1, 1, 16, 0);
        Segment segment2 = new Segment(dep2, arr2);

        List<Segment> segments1 = Arrays.asList(segment1, segment2);
        Flight flight1 = new Flight(segments1);

        LocalDateTime dep3 = LocalDateTime.of(1234, 1, 1, 18, 0);
        LocalDateTime arr3 = LocalDateTime.of(1234, 1, 1, 20, 0);
        Segment segment3 = new Segment(dep3, arr3);

        LocalDateTime dep4 = LocalDateTime.of(1234, 1, 1, 22, 0);
        LocalDateTime arr4 = LocalDateTime.of(1234, 1, 2, 0, 0);
        Segment segment4 = new Segment(dep4, arr4);

        List<Segment> segments2 = Arrays.asList(segment3, segment4);
        Flight flight2 = new Flight(segments2);

        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Create the filter
        ArrivalBeforeOrAfterDepartureFilter filter =
                new ArrivalBeforeOrAfterDepartureFilter(true);

        // Call the method under test
        List<Flight> filteredFlights = filter.filterFlights(flights);

        // Verify the result
        assertEquals(flights, filteredFlights);
    }
}