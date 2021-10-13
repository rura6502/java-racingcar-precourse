package racinggame.domain.record;

import racinggame.domain.car.Car;
import racinggame.domain.car.Cars;

public class CarRacingRecord {

	private Cars cars;

	public CarRacingRecord(Cars cars) {
		this.cars = new Cars();
		for (Car car : cars.getAllCars()) {
			this.cars.addNewCar(car.getName(), car.getPosition());
		}
	}

	public Cars getCars() {
		return cars;
	}
}
