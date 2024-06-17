package com.gridnine.testing;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.gridnine.testing.FlightBuilder.filterFlights;

public class Main {

	public static void main(String[] args) {
		// Создаем лист полетов
		List<Flight> flights = new ArrayList<>();
		// Заполняем лист полетов сегментами: дата начала и дата окончания.
		flights.add(new Flight(List.of(
				new Segment(
						LocalDateTime.of(2024, 3, 26, 10, 0),
						LocalDateTime.of(2024, 3, 26, 12, 0)),
				new Segment(
						LocalDateTime.of(2024, 3, 26, 14, 0),
						LocalDateTime.of(2024, 3, 26, 16, 0))
		)));

		//Сегменты, где общее время, проведённое на земле, превышает два часа.

		flights.add(new Flight(List.of(
				new Segment(
						LocalDateTime.of(2024, 3, 27, 8, 0),
						LocalDateTime.of(2024, 3, 27, 10, 0)),

				new Segment(
						LocalDateTime.of(2024, 3, 27, 14, 0),
						LocalDateTime.of(2024, 3, 27, 16, 0))
		)));

		//Сегменты с датой прилёта раньше даты вылета.

		flights.add(new Flight(List.of(
				new Segment(
						LocalDateTime.of(2024, 5, 12, 6, 0),
						LocalDateTime.of(2024, 5, 12, 10, 0)),
				new Segment(
						LocalDateTime.of(2024, 5, 12, 8, 0),
						LocalDateTime.of(2024, 5, 12, 12, 0))
		)));

		// Создает список правил для фильтрации сегментов
		List<SegmentRule> rules = new ArrayList<>();
		// Добавляем два правила
		// Первое правило проверяет вылитает ли рейс в заданный день
		rules.add(new DepartureDayFilter(LocalDateTime.of(2024, 5, 12, 8, 0)));
		// Второе правило проверяет перелеты с промежуточными посадками.
		rules.add(new WithStopsFilter());

		List<Flight> filteredFlights = filterFlights(flights, rules);
		System.out.println("Filtered Flights:");
		// Выводим информацию о каждом заданному правилу
		for (Flight flight : filteredFlights) {
			System.out.println("Рейс в заданное время и промежуточными посадками :" + flight.getSegments());
		}


		// Максимальное время на земле 2 часа
		Duration maxGroundTime = Duration.ofHours(2);
		FlightsExceedTimeInHoursOnTheGround rule = new FlightsExceedTimeInHoursOnTheGround(maxGroundTime);

		List<Segment> segments = new ArrayList<>();
		segments.add(new Segment(LocalDateTime.of(2024, 3, 27, 8, 0), LocalDateTime.of(2024, 3, 27, 10, 0)));
		segments.add(new Segment(LocalDateTime.of(2024, 3, 27, 14, 0), LocalDateTime.of(2024, 3, 27, 16, 0)));

		Flight flight = new Flight(segments);

		// Проверяем, превышает ли время на земле максимальное значение
		boolean exceedsTime = rule.test(flight);
		// Выводим информацию к заданному правилу
		System.out.println("Время на земле превышает максимальное значение: " + exceedsTime);

	}
}
