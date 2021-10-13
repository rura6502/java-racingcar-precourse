package racinggame.domain.record;

import java.util.Collection;

import racinggame.domain.car.Car;
import racinggame.domain.car.Cars;

public class CarRacingRecord {

	private Cars cars;

	private int longestPosition = 0;

	public CarRacingRecord(Cars cars) {
		this.cars = new Cars();
		for (Car car : cars.getAllCars()) {
			this.cars.addNewCar(car.getName(), car.getPosition());
			longestPosition = Math.max(longestPosition, car.getPosition());
		}
	}

	public Collection<Car> getWinners() {
		return this.getCars().getCarsByPosition(this.longestPosition);
	}

	public Cars getCars() {
		return cars;
	}

	public Collection<Car> getAllCars() {
		return this.cars.getAllCars();
	}

	public int getLongestPosition() {
		return longestPosition;
	}
}
