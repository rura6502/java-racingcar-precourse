package racinggame.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import nextstep.utils.Randoms;

public class CarRacingGameTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3})
	@DisplayName("랜덤 숫자 0~3 일 경우 STOP 명령어 생성 테스트")
	void 멈춤_명령어_생성_테스트(int value) {

		assertThat(CarRacingGame.createCommand(value)).isEqualTo(CarCommand.STOP);
	}

	@ParameterizedTest
	@ValueSource(ints = {4, 5, 6, 7, 8, 9})
	@DisplayName("랜덤 숫자 4~9 일 경우 FORWARD 명령어 생성 테스트")
	void 전진_명령어_생성_테스트(int value) {

		assertThat(CarRacingGame.createCommand(value)).isEqualTo(CarCommand.FORWARD);
	}

	@Test
	void 단일_게임_진행_테스트() {

		Car car1 = new Car("car1");
		Car car2 = new Car("car2");
		Car car3 = new Car("car3");

		Collection<Car> cars = craeteCars(car1, car2, car3);

		CarRacingGameConfig config = new CarRacingGameConfig(3, cars);
		CarRacingGame game = new CarRacingGame(config);

		try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			mockRandoms
				.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
				.thenReturn(1, 4, 9);

			game.runOneTime();
			game.runOneTime();

			assertThat(car1.getPosition()).isZero();
			assertThat(car2.getPosition()).isEqualTo(1);
			assertThat(car3.getPosition()).isEqualTo(2);
		}

	}

	private Collection<Car> craeteCars(Car... cars) {
		List<Car> list = new ArrayList<>();
		for (Car car : cars) {
			list.add(car);
		}
		return list;
	}
}
