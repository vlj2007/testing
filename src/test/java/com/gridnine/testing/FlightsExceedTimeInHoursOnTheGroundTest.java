package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест для фильтра FlightsExceedTimeInHoursOnTheGround
 */
class FlightsExceedTimeInHoursOnTheGroundTest {

    /**
     * Тестовый полет с одним сегментом возвращает - False.
     */
    @Test
    void test_flightWithOneSegment_returnsFalse() {
        FlightsExceedTimeInHoursOnTheGround rule = new FlightsExceedTimeInHoursOnTheGround(Duration.ofHours(2));
        Flight flight = new Flight(List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1))));

        boolean result = rule.test(flight);

        assertFalse(result);
    }

    /**
     * Тестовый полет с двумя сегментами в пределах максимального времени нахождения на земле возвращает - False.
     */
    @Test
    void test_flightWithTwoSegments_withinMaxGroundTime_returnsFalse() {
        FlightsExceedTimeInHoursOnTheGround rule = new FlightsExceedTimeInHoursOnTheGround(Duration.ofHours(2));
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        segments.add(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3)));
        Flight flight = new Flight(segments);

        boolean result = rule.test(flight);

        assertFalse(result);
    }

    /**
     * Тестовый полет с двумя сегментами в пределах максимального времени нахождения на земле возвращает - True.
     */
    @Test
    void test_flightWithTwoSegments_exceedingMaxGroundTime_returnsTrue() {
        FlightsExceedTimeInHoursOnTheGround rule = new FlightsExceedTimeInHoursOnTheGround(Duration.ofHours(1));
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        segments.add(new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4)));
        Flight flight = new Flight(segments);

        boolean result = rule.test(flight);

        assertTrue(result);
    }

    /**
     * Тестовый полет со многими сегментами превышение максимального времени нахождения на земле возвращает - True
     */
    @Test
    void test_flightWithMultipleSegments_exceedingMaxGroundTime_returnsTrue() {
        FlightsExceedTimeInHoursOnTheGround rule = new FlightsExceedTimeInHoursOnTheGround(Duration.ofHours(1));
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        segments.add(new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4)));
        segments.add(new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6)));
        Flight flight = new Flight(segments);

        boolean result = rule.test(flight);

        assertTrue(result);
    }

    /**
     * Тестовый полет со многими сегментами в течение максимального времени нахождения на земле возвращает - False
     */
    @Test
    void test_flightWithMultipleSegments_withinMaxGroundTime_returnsFalse() {
        FlightsExceedTimeInHoursOnTheGround rule = new FlightsExceedTimeInHoursOnTheGround(Duration.ofHours(2));
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        segments.add(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3)));
        segments.add(new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5)));
        Flight flight = new Flight(segments);

        boolean result = rule.test(flight);

        assertFalse(result);
    }
}
