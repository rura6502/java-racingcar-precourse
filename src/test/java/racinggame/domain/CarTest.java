package racinggame.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
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

	@Test
	void 차_전진_카운팅_테스트() {
		Car car = new Car("test1");
		car.forwardOneStep();
		car.forwardOneStep();

		assertThat(car.getPosition()).isEqualTo(2);
	}

}
