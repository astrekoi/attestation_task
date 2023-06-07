package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("************************************");

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current time: " + currentTime.format(fmt));
        System.out.println("************************************");

        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("All flights:");
        flights.forEach(System.out::println);
        System.out.println("************************************");

        flights = departureBeforeCurrentTime(flights);
        System.out.println("************************************");

        flights = arrivalBeforeDeparture(flights);
        System.out.println("************************************");

        flights = groundTimeExceedsTwoHours(flights);
        System.out.println("************************************");

        System.out.println("All flights after filters:");
        flights.forEach(System.out::println);
        System.out.println("************************************");
    }

    /**
     * Filter flights that depart before the current time.
     *
     * @param flights The list of flights to be filtered.
     * @return A new list containing only the flights from the input list that depart before the current time.
     */
    public static List<Flight> departureBeforeCurrentTime(List<Flight> flights) {
        DepartureBeforeOrAfterCurrentTimeFilter departureBeforeCurrentTimeFilter = new DepartureBeforeOrAfterCurrentTimeFilter(LocalDateTime.now(), true);

        List<Flight> departureBeforeCurrentTimeFlights = departureBeforeCurrentTimeFilter.filterFlights(flights);

        System.out.println("Filtered flights up to the current time:");
        departureBeforeCurrentTimeFlights.forEach(System.out::println);
        System.out.println("-------------------------");

        System.out.println("Excluded Flights:");
        departureBeforeCurrentTimeFilter.getExcludedFlights().forEach(System.out::println);

        return departureBeforeCurrentTimeFlights;
    }

    /**
     * Filter flights that have segments with arrival date before departure date.
     *
     * @param flights The list of flights to be filtered.
     * @return A new list containing only the flights from the input list that have at least one segment
     */
    public static List<Flight> arrivalBeforeDeparture(List<Flight> flights) {
        ArrivalBeforeOrAfterDepartureFilter arrivalBeforeDepartureFilter = new ArrivalBeforeOrAfterDepartureFilter(true);

        List<Flight> arrivalBeforeDepartureFlights = arrivalBeforeDepartureFilter.filterFlights(flights);

        System.out.println("Filtered flights with segments with arrival date earlier than departure date:");
        arrivalBeforeDepartureFlights.forEach(System.out::println);
        System.out.println("-------------------------");

        System.out.println("Excluded Flights:");
        arrivalBeforeDepartureFilter.getExcludedFlights().forEach(System.out::println);

        return arrivalBeforeDepartureFlights;
    }

    /**
     * Filter flights that have a total ground time exceeding 2 hours.
     *
     * @param flights The list of flights to be filtered.
     * @return A new list containing only the flights from the input list that have a total ground time
     */
    public static List<Flight> groundTimeExceedsTwoHours(List<Flight> flights) {
        GroundTimeExceedsHoursFilter groundTimeExceedsHoursFilter = new GroundTimeExceedsHoursFilter(2);

        List<Flight> groundTimeExceedsHoursFlights = groundTimeExceedsHoursFilter.filterFlights(flights);

        System.out.println("Filtered flights if the total time spent on the ground exceeds two hours:");
        groundTimeExceedsHoursFlights.forEach(System.out::println);
        System.out.println("-------------------------");

        System.out.println("Excluded Flights:");
        groundTimeExceedsHoursFilter.getExcludedFlights().forEach(System.out::println);

        return groundTimeExceedsHoursFlights;
    }
}

