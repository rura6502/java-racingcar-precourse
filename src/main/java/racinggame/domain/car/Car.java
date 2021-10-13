package racinggame.domain.car;

import java.util.Objects;

public class Car {

	private final String name;

	private int position = 0;

	private static final int CAR_NAME_MAX_LENGTH = 5;

	public Car(String name) {
		this(name, 0);
	}

	public Car(String name, int position) {
		if (name == null || !isValidName(name)) {
			throw new IllegalArgumentException("자동차 이름은 null 또는 빈칸이 아닌 5자 이하 문자만 가능합니다.");
		}
		this.name = name.trim();
		this.position = position;
	}

	public Car move(CarCommand carCommand) {
		if (carCommand.equals(CarCommand.FORWARD)) {
			this.forwardOneStep();
		}
		return this;
	}

	/**
	 *
	 * @return the position after forward
	 */
	private int forwardOneStep() {
		return this.forward(1);
	}

	private int forward(int step) {
		position += step;
		return position;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public boolean isThisPosition(int position) {
		return this.position == position;
	}

	private boolean isValidName(String name) {
		name = name.trim();
		if (name.length() == 0) {
			return false;
		}

		return name.length() <= CAR_NAME_MAX_LENGTH;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Car car = (Car)object;
		return position == car.position && name.equals(car.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, position);
	}
}
