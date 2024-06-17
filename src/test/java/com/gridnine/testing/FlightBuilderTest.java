package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Тест для  фильтра FlightBuilder.
 */
class FlightBuilderTest {

    /**
     * Создание списков полетов
     */
    @Test
    @DisplayName("Create flight")
    void testCreateFlights() {
        List<Flight> flights = FlightBuilder.createFlights();
        assertEquals(6, flights.size());
    }

    /**
     * Создать рейс с нечетным количеством рейсов
     */
    @Test
    @DisplayName("Create flight with odd number of dates")
    void testCreateFlightWithOddNumberOfDates() {
        assertThrows(IllegalArgumentException.class, () -> FlightBuilder.createFlight(LocalDateTime.now()));
    }

    /**
     * Рейсы с сегментами, начинающимися раньше
     */
    @Test
    @DisplayName("Flights with a segment starting before now")
    void testFilterFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        List<Flight> flights = FlightBuilder.createFlights();

        List<SegmentRule> rules = Arrays.asList(
                new SegmentRule() {
                    @Override
                    public boolean test(Flight flight) {
                        for (Segment segment : flight.getSegments()) {
                            if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
        );

        List<Flight> filteredFlights = FlightBuilder.filterFlights(flights, rules);
        assertEquals(1, filteredFlights.size());
        assertEquals(flights.get(2), filteredFlights.get(0));
    }

    /**
     * Фильтрация рейсов с несколькими правилами
     */
    @Test
    @DisplayName("Filter Flights With Multiple Rules")
    void testFilterFlightsWithMultipleRules() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        List<Flight> flights = FlightBuilder.createFlights();

        SegmentRule rule1 = new SegmentRule() {
            @Override
            public boolean test(Flight flight) {
                for (Segment segment : flight.getSegments()) {
                    if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                        return true;
                    }
                }
                return false;
            }
        };

        SegmentRule rule2 = new SegmentRule() {
            @Override
            public boolean test(Flight flight) {
                for (Segment segment : flight.getSegments()) {
                    if (segment.getArrivalDate().isAfter(threeDaysFromNow)) {
                        return true;
                    }
                }
                return false;
            }
        };

        List<SegmentRule> rules = Arrays.asList(rule1, rule2);
        List<Flight> filteredFlights = FlightBuilder.filterFlights(flights, rules);
        assertEquals(2, filteredFlights.size());
        assertEquals(flights.get(2), filteredFlights.get(0));
        assertEquals(flights.get(5), filteredFlights.get(1));
    }

}