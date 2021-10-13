package racinggame.service.message;

import static racinggame.Application.*;
import static racinggame.service.message.CarRacingGameMessage.*;

import java.util.Collection;

import racinggame.domain.car.Car;
import racinggame.domain.record.CarRacingRecord;
import racinggame.domain.record.CarRacingRecords;

public class MessagePrinter {

	public static void print(String message) {
		System.out.print(message);
	}

	public static void println() {
		System.out.println();
	}

	public static void println(String message) {
		System.out.println(message);
	}

	public static void error(String message) {
		System.out.println(ERROR_TAG + message);
	}

	public static void print(CarRacingRecords records) {
		println();
		println(PRINT_RESULT);
		for (CarRacingRecord record : records.getRecords()) {
			print(record);
			println();
		}
		println(getWinnerPrintMessage(records.getWinners()));
	}

	public static void print(CarRacingRecord record) {
		for (Car car : record.getAllCars()) {
			print(car);
		}
	}

	public static void print(Car car) {
		println(String.format(PRINT_CAR_AND_SCORE_FORMAT,
			car.getName(), positionToDash(car.getPosition())));
	}

	private static String getWinnerPrintMessage(Collection<Car> winners) {
		return String.format(PRINT_WINNER_FORMAT, joinWinnersCarName(winners));

	}

	private static String positionToDash(int position) {
		StringBuilder dashBuilder = new StringBuilder();
		for (int i = 0; i < position; i++) {
			dashBuilder.append("-");
		}
		return dashBuilder.toString();
	}

	private static String joinWinnersCarName(Collection<Car> winners) {
		StringBuilder joinedWinnersCarNameBuilder = new StringBuilder();
		for (Car car : winners) {
			joinedWinnersCarNameBuilder.append(car.getName()).append(CAR_LIST_SEPARATOR);
		}
		return joinedWinnersCarNameBuilder.substring(0, joinedWinnersCarNameBuilder.length() - 1);
	}

}
