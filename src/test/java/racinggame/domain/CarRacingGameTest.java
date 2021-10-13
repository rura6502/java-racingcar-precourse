package racinggame.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import nextstep.utils.Randoms;
import racinggame.domain.car.Car;
import racinggame.domain.car.CarCommand;
import racinggame.domain.car.Cars;
import racinggame.domain.game.CarRacingGame;
import racinggame.domain.game.CarRacingGameConfig;
import racinggame.domain.record.CarRacingRecord;
import racinggame.domain.record.CarRacingRecords;

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
	@DisplayName("단일 게임 결과 포지션 값 증가 정상 테스트")
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
	@DisplayName("단일 게임 결과 레코드 값 정상 기록 확인 테스트")
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

	@Test
	@DisplayName("전체 게임(다중 횟수) 게임 결과 레코드 값 정상 기록 확인 테스트")
	void 전체_게임_결과_CarRacingRecords_반환_테스트() {
		Cars cars = new Cars("car1", "car2", "car3");

		CarRacingGameConfig config = new CarRacingGameConfig(3, cars);
		CarRacingGame game = new CarRacingGame(config);

		try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			mockRandoms
				.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
				.thenReturn(
					1, 4, 9, /* first */
					0, 3, 7, /* second */
					5, 5, 2 /* third */);

			CarRacingRecords records = game.run();

			/* try : first */
			assertThat(records.getRecordByTryStep(0).getCarByName("car1").isThisPosition(0)).isTrue();
			assertThat(records.getRecordByTryStep(0).getCarByName("car2").isThisPosition(1)).isTrue();
			assertThat(records.getRecordByTryStep(0).getCarByName("car3").isThisPosition(1)).isTrue();

			/* try : second */
			assertThat(records.getRecordByTryStep(1).getCarByName("car1").isThisPosition(0)).isTrue();
			assertThat(records.getRecordByTryStep(1).getCarByName("car2").isThisPosition(1)).isTrue();
			assertThat(records.getRecordByTryStep(1).getCarByName("car3").isThisPosition(2)).isTrue();

			/* try : third */
			assertThat(records.getRecordByTryStep(2).getCarByName("car1").isThisPosition(1)).isTrue();
			assertThat(records.getRecordByTryStep(2).getCarByName("car2").isThisPosition(2)).isTrue();
			assertThat(records.getRecordByTryStep(2).getCarByName("car3").isThisPosition(2)).isTrue();

			List<Car> expectedWinners = new ArrayList<>();
			expectedWinners.add(new Car("car2", 2));
			expectedWinners.add(new Car("car3", 2));
			assertThat(records.getWinners()).hasSameElementsAs(expectedWinners);
		}
	}
}
