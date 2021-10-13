package racinggame.domain;

public class Car {

	private final String name;

	private int position = 0;

	private static final int CAR_NAME_MAX_LENGTH = 5;

	public Car(String name) {
		if (!isValidName(name)) {
			throw new IllegalArgumentException("자동차 이름은 빈칸이 아닌 5자 이하 문자만 가능합니다.");
		}

		this.name = name.trim();
	}

	private boolean isValidName(String name) {
		if (name.trim().length() == 0)
			return false;

		return name.length() <= CAR_NAME_MAX_LENGTH;
	}
}
