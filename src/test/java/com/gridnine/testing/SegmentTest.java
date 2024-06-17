package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Проверка класса Segment
 */
class SegmentTest {

    /**
     * Проверка класса Segment на правильное создание объектов с заданными датами вылета и прилета.
     */
    @Test
    @DisplayName("Create one segment")
    void testCreateSegment() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 10, 26, 10, 0, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 10, 26, 12, 0, 0);
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(new Segment(departureDate, arrivalDate));
        assertEquals(1, segmentList.size());
    }
    /**
     * Проверка класса Segment на правильное создание объектов с заданными датами вылета и прилета.
     */
    @Test
    void testConstructor() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 10, 26, 10, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 10, 26, 12, 0);
        Segment segment = new Segment(departureDate, arrivalDate);
        assertNotNull(segment);
        assertEquals(departureDate, segment.getDepartureDate());
        assertEquals(arrivalDate, segment.getArrivalDate());
    }
    /**
     * Проверяет, что метод toString() возвращает строковое представление сегмента в правильном формате.
     */
    @Test
    void testToString() {
        LocalDateTime departureDate = LocalDateTime.of(2024, 10, 26, 10, 0);
        LocalDateTime arrivalDate = LocalDateTime.of(2024, 10, 26, 12, 0);
        Segment segments = new Segment(departureDate, arrivalDate);
        String expectedString = "[2024-10-26T10:00|2024-10-26T12:00]";
        assertEquals(expectedString, segments.toString());
    }

    /**
     * Проверяет, что конструктор выбрасывает NullPointerException, если передается null для даты вылета.
     */
    @Test
    void testConstructor_NullDepartureDate() {
        LocalDateTime departure = LocalDateTime.of(2023, 10, 26, 12, 30);
        assertThrows(NullPointerException.class, () -> new Segment(null, departure));
    }

    /**
     * Проверяет, что конструктор выбрасывает NullPointerException, если передается null для даты прилёта.
     */
    @Test
    void testConstructor_NullArrivalDate() {
        LocalDateTime arrival = LocalDateTime.of(2023, 10, 26, 12, 30);
        assertThrows(NullPointerException.class, () -> new Segment(null, arrival));
    }


}