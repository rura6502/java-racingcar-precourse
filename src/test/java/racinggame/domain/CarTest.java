package racinggame.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

	@ParameterizedTest
	@ValueSource(strings = {"", "   ", "123456"})
	void 차_이름_글자_수_유효성_체크(String carName) {

		assertThatExceptionOfType(
			IllegalArgumentException.class).isThrownBy(() -> new Car(carName));
		assertThatExceptionOfType(
			IllegalArgumentException.class).isThrownBy(() -> new Car(carName));
		assertThatExceptionOfType(
			IllegalArgumentException.class).isThrownBy(() -> new Car(carName));
	}

}
