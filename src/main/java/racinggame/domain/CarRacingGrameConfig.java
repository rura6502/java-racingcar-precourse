package racinggame.domain;

import java.util.Collection;

public class CarRacingGrameConfig {

	private int tryCount;

	private Collection<Car> cars;

	public CarRacingGrameConfig(int tryCount, Collection<Car> cars) {
		this.tryCount = tryCount;
		this.cars = cars;
	}

	public int getTryCount() {
		return tryCount;
	}

	public Collection<Car> getCars() {
		return cars;
	}
}
