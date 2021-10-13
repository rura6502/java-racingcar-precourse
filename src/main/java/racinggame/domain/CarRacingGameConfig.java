package racinggame.domain;

import java.util.Collection;
import java.util.Set;

public class CarRacingGameConfig {

	private int tryCount;

	private Collection<Car> cars;

	public CarRacingGameConfig(int tryCount, Collection<Car> cars) {
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
