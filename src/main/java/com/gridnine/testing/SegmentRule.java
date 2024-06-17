package com.gridnine.testing;

import java.util.function.Predicate;
/**
 * Интерфейс SegmentRule для определения правил фильтрации.
 */
public interface SegmentRule extends Predicate<Flight> {
}
