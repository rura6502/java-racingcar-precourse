package racinggame.domain.game;

import racinggame.domain.car.Cars;

public class CarRacingGameConfig {

	private int tryCount;

	private Cars cars;

	public CarRacingGameConfig(int tryCount, Cars cars) {
		this.tryCount = tryCount;
		this.cars = cars;
	}

	public int getTryCount() {
		return tryCount;
	}

	public Cars getCars() {
		return cars;
	}
}
