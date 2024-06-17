package com.gridnine.testing;

/**
 * Фильтр WithStopsFilter для рейсов с промежуточными остановками.
 */
public class WithStopsFilter implements SegmentRule {

    @Override
    public boolean test(Flight flight) {
        return flight.getSegments().size() > 1;
    }


}
