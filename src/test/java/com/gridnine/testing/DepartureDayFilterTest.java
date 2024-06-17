package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест для  фильтра DepartureDayFilterTest.
 */

class DepartureDayFilterTest {

    /**
     * Тест для соответствующей даты вылета.
     */
    @Test
    @DisplayName("Departure day filter match test")
    void testDepartureDayFilterMatch() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 10, 26, 10, 0);
        DepartureDayFilter filter = new DepartureDayFilter(departureDate);
        Flight flight = new Flight(List.of(new Segment(departureDate, LocalDateTime.of(2024, 10, 26, 12, 0, 0))));
        assertTrue(filter.test(flight));
    }

    /**
     * Тест для несоответствия даты вылета.
     */
    @Test
    @DisplayName("Departure day filter mismatch test")
    void testDepartureDayFilterMismatch() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 10, 26, 10, 0);
        DepartureDayFilter filter = new DepartureDayFilter(departureDate);
        Flight flight = new Flight(List.of(new Segment(LocalDateTime.of(2024, 10, 27, 10, 0, 0), LocalDateTime.of(2024, 10, 27, 12, 0, 0))));
        assertFalse(filter.test(flight));
    }
}