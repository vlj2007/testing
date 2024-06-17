package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Проверка класса Flight
 */
class FlightTest {

    @Test
    @DisplayName("Creating an Empty Flight.")
    void testEmptyFlight() {
        Flight flight = new Flight(List.of());
        assertEquals(0, flight.getSegments().size());
    }

    /**
     * Проверка класса Flight на правильное создание объектов с заданными датами вылета и прилета.
     */
    @Test
    @DisplayName("Checking the class for the correct creation of objects with specified departure and arrival dates.")
    void getSegments() {
        LocalDateTime departureDate1 = LocalDateTime.of(2024, 10, 26, 10, 0, 0);
        LocalDateTime arrivalDate1 = LocalDateTime.of(2024, 10, 26, 12, 0, 0);
        LocalDateTime departureDate2 = LocalDateTime.of(2024, 11, 2, 16, 0, 0);
        LocalDateTime arrivalDate2 = LocalDateTime.of(2024, 11, 2, 18, 0, 0);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(departureDate1, arrivalDate1));
        segments.add(new Segment(departureDate2, arrivalDate2));
        Flight flight = new Flight(segments);
        assertNotNull(flight);
        assertNotNull(segments);
        assertEquals(segments, flight.getSegments());
    }

    /**
     * Проверяет, что метод toString() возвращает строковое представление сегмента в правильном формате.
     */
    @Test
    @DisplayName("Сheck method toString")
    void testToString() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 10, 26, 10, 0, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 10, 26, 12, 0, 0);

        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(departureDate, arrivalDate));
        Flight flight = new Flight(segments);

        List<Segment> expectedSegments = new ArrayList<>();
        expectedSegments.add(new Segment(departureDate, arrivalDate));
        Flight expectedFlight2 = new Flight(segments);
        assertNotNull(expectedFlight2);
        assertNotNull(flight);
        assertEquals(expectedFlight2.toString(), flight.toString());
    }

}