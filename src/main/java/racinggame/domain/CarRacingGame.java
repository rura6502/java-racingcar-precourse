package racinggame.domain;

import java.util.Collection;

import nextstep.utils.Randoms;

public class CarRacingGame {

	private CarRacingGrameConfig config;

	public CarRacingGame(CarRacingGrameConfig config) {
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
