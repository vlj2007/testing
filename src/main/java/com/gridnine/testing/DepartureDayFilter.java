package com.gridnine.testing;

import java.time.LocalDateTime;
/**
 * Фильтр DepartureDayFilter, который проверяет, вылетает ли рейс в заданный день.
 */
public class DepartureDayFilter implements SegmentRule {
    private final LocalDateTime departureDate;


    public DepartureDayFilter(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public boolean test(Flight flight){
        return flight.getSegments().get(0).getDepartureDate().toLocalDate().isEqual(departureDate.toLocalDate());

    }
}
