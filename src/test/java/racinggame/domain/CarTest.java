package racinggame.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import racinggame.domain.car.Car;
import racinggame.domain.car.CarCommand;

public class CarTest {

	@ParameterizedTest
	@MethodSource("carNameTestValueSource")
	@DisplayName("차 이름 글자수 유효성 테스트 : 1~5글자")
	void 차_이름_글자_수_유효성_체크(String carName) {

		assertThatExceptionOfType(
			IllegalArgumentException.class).isThrownBy(() -> new Car(carName));
	}

	@Test
	@DisplayName("차 전진 시 포지션 값 증가 테스트")
	void 차_전진_카운팅_테스트() {
		Car car = new Car("test1");
		car.move(CarCommand.FORWARD);        // +1
		car.move(CarCommand.FORWARD);        // +1
		car.move(CarCommand.STOP);           // +0

		assertThat(car.getPosition()).isEqualTo(2);
	}

	private static List<String> carNameTestValueSource() {
		List<String> list = new ArrayList<>();
		list.add(null);
		list.add("");
		list.add("  ");
		list.add("123456");
		return list;
	}

}
