package racinggame.domain.game;

import nextstep.utils.Randoms;
import racinggame.domain.car.Car;
import racinggame.domain.car.CarCommand;
import racinggame.domain.record.CarRacingRecord;

public class CarRacingGame {

	private CarRacingGameConfig config;

	public CarRacingGame(CarRacingGameConfig config) {
		this.config = config;
	}

	public CarRacingRecord runOneTime() {

		for (Car car : config.getCars().getAllCars()) {
			car.move(createCommand(this.random()));
		}
		return new CarRacingRecord(config.getCars());
	}

	public static CarCommand createCommand(int random) {
		if (random >= 4) {
			return CarCommand.FORWARD;
		}
		return CarCommand.STOP;
	}

	private int random() {
		return Randoms.pickNumberInRange(0, 9);
	}
}
