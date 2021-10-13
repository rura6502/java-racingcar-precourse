package racinggame.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import nextstep.utils.Randoms;
import racinggame.domain.car.CarCommand;
import racinggame.domain.car.Cars;
import racinggame.domain.game.CarRacingGame;
import racinggame.domain.game.CarRacingGameConfig;
import racinggame.domain.record.CarRacingRecord;

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
	void 단일_게임_진행_차_포지션_결과_테스트() {

		Cars cars = new Cars("car1", "car2", "car3");

		CarRacingGameConfig config = new CarRacingGameConfig(3, cars);
		CarRacingGame game = new CarRacingGame(config);

		try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			mockRandoms
				.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
				.thenReturn(1, 4, 9);

			game.runOneTime();

			assertThat(cars.getCarByName("car1").getPosition()).isZero();
			assertThat(cars.getCarByName("car2").getPosition()).isEqualTo(1);
			assertThat(cars.getCarByName("car3").getPosition()).isEqualTo(1);
		}
	}

	@Test
	void 단일_게임_결과_CarRacingRecord_반환_테스트() {

		Cars cars = new Cars("car1", "car2", "car3");

		CarRacingGameConfig config = new CarRacingGameConfig(3, cars);
		CarRacingGame game = new CarRacingGame(config);

		try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			mockRandoms
				.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
				.thenReturn(1, 4, 9, 0, 3, 7);

			CarRacingRecord record1 = game.runOneTime();
			CarRacingRecord record2 = game.runOneTime();

			assertThat(record1.getCars().getCarByName("car1").getPosition()).isZero();
			assertThat(record1.getCars().getCarByName("car2").getPosition()).isEqualTo(1);
			assertThat(record1.getCars().getCarByName("car3").getPosition()).isEqualTo(1);

			assertThat(record2.getCars().getCarByName("car1").getPosition()).isZero();
			assertThat(record2.getCars().getCarByName("car2").getPosition()).isEqualTo(1);
			assertThat(record2.getCars().getCarByName("car3").getPosition()).isEqualTo(2);

		}
	}


		}
	}
}
