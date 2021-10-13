package racinggame.domain.game;

import java.util.List;

import racinggame.domain.car.Car;

public class CarRacingGameConfig {

	private int tryCount;

	private List<Car> cars;

	public CarRacingGameConfig(int tryCount, List<Car> cars) {
		this.tryCount = tryCount;
		this.cars = cars;
	}

	public int getTryCount() {
		return tryCount;
	}

	public List<Car> getCars() {
		return cars;
	}
}
