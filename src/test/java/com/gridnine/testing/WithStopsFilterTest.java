package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WithStopsFilterTest {
    /**
     * Проверьте, что фильтр возвращает true для рейса с остановками.
     */
    @Test
    @DisplayName("Test that the filter returns true for a flight with stops")
    void testWithStops1() {
        LocalDateTime departureDate1 = LocalDateTime.of(2024, 10, 26, 10, 0);
        LocalDateTime arrivalDate1 = LocalDateTime.of(2024, 10, 26, 12, 0);
        LocalDateTime departureDate2 = LocalDateTime.of(2024, 10, 26, 14, 0);
        LocalDateTime arrivalDate2 = LocalDateTime.of(2024, 10, 26, 16, 0);
        LocalDateTime departureDate3 = LocalDateTime.of(2024, 10, 26, 18, 0);
        LocalDateTime arrivalDate3 = LocalDateTime.of(2024, 10, 26, 20, 0);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(departureDate1, arrivalDate1));
        segments.add(new Segment(departureDate2, arrivalDate2));
        segments.add(new Segment(departureDate3, arrivalDate3));
        Flight flight = new Flight(segments);

        WithStopsFilter filter = new WithStopsFilter();

        assertTrue(filter.test(flight));
    }

    /**
     * Проверьте, что фильтр возвращает false для рейса без остановок.
     */
    @Test
    @DisplayName("Test that the filter returns false for a flight without stops")
    void testNoStops() {
        LocalDateTime departureDate1 = LocalDateTime.of(2024, 10, 26, 10, 0);
        LocalDateTime arrivalDate1 = LocalDateTime.of(2024, 10, 27, 10, 0);
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(departureDate1, arrivalDate1));
        Flight flight = new Flight(segments);

        WithStopsFilter filter = new WithStopsFilter();

        assertFalse(filter.test(flight));
    }

    /**
     * Проверяем, что фильтр возвращает false для пустого рейса.
     */
    @Test
    @DisplayName("Test that the filter returns false for an empty flight")
    void testEmptyFlight() {
        Flight flight = new Flight(new ArrayList<>());
        WithStopsFilter filter = new WithStopsFilter();

        assertFalse(filter.test(flight));
    }

}