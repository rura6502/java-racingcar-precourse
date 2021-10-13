package racinggame.domain.game;

import java.util.Collection;

import nextstep.utils.Randoms;
import racinggame.domain.car.Car;
import racinggame.domain.car.CarCommand;

public class CarRacingGame {

	private CarRacingGameConfig config;

	public CarRacingGame(CarRacingGameConfig config) {
		this.config = config;
	}

	public Collection<Car> runOneTime() {

		for (Car car : config.getCars()) {
			car.move(createCommand(this.random()));
		}
		return config.getCars();
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
