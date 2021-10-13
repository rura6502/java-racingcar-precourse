package racinggame.service.message;

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
		System.out.println(String.format("[ERROR]" + message));
	}

	public static void print(CarRacingRecords records) {
		println();
		println("실행 결과");
		for (CarRacingRecord record : records.getRecords()) {
			print(record);
			println();
		}
		println(
			String.format("최종 우승자는 %s 입니다."
				, joinWinnersCarName(records.getWinners())));
	}

	public static void print(CarRacingRecord record) {
		for (Car car : record.getAllCars()) {
			print(car);
		}
	}

	public static void print(Car car) {
		println(String.format("%s : %s", car.getName(), positionToDash(car.getPosition())));
	}

	private static String positionToDash(int position) {
		StringBuilder dashBuilder = new StringBuilder();
		for (int i = 0; i < position; i++) {
			dashBuilder.append("-");
		}
		return dashBuilder.toString();
	}

	private static String joinWinnersCarName(Collection<Car> winners) {
		String joinedWinnersCarName = "";
		for (Car car : winners) {
			joinedWinnersCarName += car.getName() + ",";
		}
		return joinedWinnersCarName.substring(0, joinedWinnersCarName.length() - 1);
	}
}
