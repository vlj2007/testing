package com.gridnine.testing;

import java.time.Duration;

/**
 * Фильтр FlightsExceedTimeInHoursOnTheGround для общего времени, проведённого на земле, интервал между прилётом одного сегмента и вылетом следующего за ним.
 */
public class FlightsExceedTimeInHoursOnTheGround implements SegmentRule {

    private final Duration maxGroundTime;

    public FlightsExceedTimeInHoursOnTheGround(Duration maxGroundTime) {
        this.maxGroundTime = maxGroundTime;
    }

    @Override
    public boolean test(Flight flight) {
        if (flight.getSegments().size() <= 1) {
            return false;
        }

        for (int i = 0; i < flight.getSegments().size() - 1; i++) {
            Segment currentSegment = flight.getSegments().get(i);
            Segment nextSegment = flight.getSegments().get(i + 1);

            Duration groundTime = Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate());
            if (groundTime.compareTo(maxGroundTime) > 0) {
                return true;
            }
        }
        return false;
    }
}
