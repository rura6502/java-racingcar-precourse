package racinggame.service;

import static racinggame.service.message.CarRacingGameMessage.*;
import static racinggame.service.message.MessagePrinter.*;

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

	private Cars makeCars(String input) {
		String[] carStrs = input.split(",");
		Cars cars = new Cars(carStrs);
		return cars;
	}

	private boolean isValidInputTryCount(String inputTryCount) {
		if (inputTryCount.matches("^\\d+$")) {
			return true;
		}
		error(ERROR_ONLY_NUMBER);
		return false;
	}

	private boolean isValidInputCars(String inputCars) {
		if (inputCars.matches("^(\\s*\\w+\\s*)(,\\s*\\w+\\s*)*$")) {
			return true;
		}
		error(ERROR_ONLY_CAR_NAMES_WITH_COMMA_SEPERATOR);
		return false;
	}

}
