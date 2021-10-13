package racinggame.domain.car;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cars {

	private Map<String, Car> cars = new LinkedHashMap<>();

	public Cars() {
	}

	public Cars(String... carNames) {
		for (String carName : carNames) {
			this.addNewCar(carName.trim(), 0);
		}
	}

	public Cars addNewCar(String carName, int position) {

		if (carName == null) {
			throw new IllegalArgumentException("차 이름은 null일 수 없습니다.");
		}

		carName = carName.trim();
		if (cars.containsKey(carName)) {
			throw new IllegalArgumentException("차 이름은 중복될 수 없습니다.");
		}

		cars.put(carName, new Car(carName, position));
		return this;
	}

	public Car getCarByName(String carName) {

		if (!cars.containsKey(carName) || cars.get(carName) == null) {
			throw new IllegalArgumentException(
				"해당 차 이름을 찾을 수 없습니다. carName : " + carName);
		}

		return cars.get(carName);
	}

	public Collection<Car> getAllCars() {
		return cars.values();
	}
}
