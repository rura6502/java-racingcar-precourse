package racinggame.service;

import static racinggame.service.message.CarRacingGameMessage.*;
import static racinggame.service.message.MessagePrinter.*;
import static racinggame.service.validation.UserInputRegex.*;

import nextstep.utils.Console;
import racinggame.domain.car.Cars;
import racinggame.domain.game.CarRacingGame;
import racinggame.domain.game.CarRacingGameConfig;

public class CarRacingGameService {

	public void runRacingGame() {

		final Cars carsByUser = this.getCarsFromUserInput();

		final int tryCount = this.getTryCountFromUserInput();

		CarRacingGameConfig config =
			new CarRacingGameConfig(tryCount, carsByUser);

		print(new CarRacingGame(config).run());
	}

	public Cars getCarsFromUserInput() {
		String inputCars;
		do {
			println(USER_INPUT_CAR_NAMES);
			inputCars = Console.readLine();
			inputCars = removePrePostSeparator(inputCars);
		} while (!isValidInputCars(inputCars));
		return makeCars(inputCars);
	}

	public int getTryCountFromUserInput() {
		String inputTryCount;
		do {
			println(USER_INPUT_TRY_COUNT);
			inputTryCount = Console.readLine();
		} while (!isValidInputTryCount(inputTryCount));
		return Integer.parseInt(inputTryCount);
	}

	private String removePrePostSeparator(String inputCars) {
		String removedInputCars = inputCars;
		while (removedInputCars.startsWith(",")) {
			removedInputCars = removedInputCars.substring(1);
		}
		while (removedInputCars.endsWith(",")) {
			removedInputCars = removedInputCars.substring(0, removedInputCars.length() - 1);
		}
		return removedInputCars;
	}

	private Cars makeCars(String input) {
		String[] carStrs = input.split(",");
		Cars cars = new Cars(carStrs);
		return cars;
	}

	private boolean isValidInputTryCount(String inputTryCount) {
		try {
			Integer.parseInt(inputTryCount);
		} catch (NumberFormatException e) {
			error(ERROR_ONLY_NUMBER);
			return false;
		}
		return true;

	}

	private boolean isValidInputCars(String inputCars) {
		if (inputCars.matches(INPUT_CARS_REGEX)) {
			return true;
		}
		error(ERROR_ONLY_CAR_NAMES_WITH_COMMA_SEPARATOR);
		return false;
	}

}
