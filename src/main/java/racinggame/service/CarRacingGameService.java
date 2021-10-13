package racinggame.service;

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
			println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
			inputCars = Console.readLine();
		} while (!isValidInputCars(inputCars));
		return makeCars(inputCars);
	}

	public int getTryCountFromUserInput() {
		String inputTryCount;
		do {
			println("시도할 횟수는 몇회인가요?");
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
		error("숫자만 입력해주세요.");
		return false;
	}

	private boolean isValidInputCars(String inputCars) {
		if (inputCars.matches("^(\\s*\\w+\\s*)(,\\s*\\w+\\s*)*$")) {
			return true;
		}
		error("쉼표로 구분된 차 이름 목록을 입력해주세요. (예. car1,car2,car3)");
		return false;
	}

}
