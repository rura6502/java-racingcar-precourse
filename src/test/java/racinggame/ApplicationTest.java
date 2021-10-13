package racinggame;

import static racinggame.Application.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import nextstep.test.NSTest;

public class ApplicationTest extends NSTest {
	private static final int MOVING_FORWARD = FORWARD_STOP_CRITERIA;
	private static final int STOP = MOVING_FORWARD - 1;

	private static final String ERROR_MESSAGE = "[ERROR]";

	@BeforeEach
	void beforeEach() {
		setUp();
	}

	@Test
	@DisplayName("게임 진행 1 회 참가자 두 명, 우승자 한 명 테스트")
	void 전진_정지_참가자_두_명_1_회_우승자_한_명() {
		assertRandomTest(() -> {
			run("pobi,woni", "1");
			verify("pobi : -", "woni : ", "최종 우승자는 pobi 입니다.");
		}, MOVING_FORWARD, STOP);
	}

	@Test
	@DisplayName("게임 진행 1 회 참가자 두 명, 우승자 두 명 테스트")
	void 전진_정지_참가자_두_명_1_회_우승자_두_명() {
		assertRandomTest(() -> {
			run("pobi,woni", "1");
			verify("pobi : -", "woni : -", "최종 우승자는 pobi,woni 입니다.");
		}, MOVING_FORWARD, MOVING_FORWARD);
	}

	@Test
	@DisplayName("게임 진행 3 회 참가자 세 명, 우승자 두 명 테스트")
	void 전진_정지_참가자_세_명_3_회_우승자_두_명() {
		assertRandomTest(() -> {
				run("car1,car2,car3", "3");
				verify("car1 : --"
					, "car2 : ---"
					, "car3 : ---"
					, "최종 우승자는 car2,car3 입니다.");
			}, STOP, MOVING_FORWARD, MOVING_FORWARD
			, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
			, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
		);
	}

	@Test
	@DisplayName("유저 입력 차 이름 6글자 초과 값 에러 메세지 출력 테스트")
	void 유저_입력_차_이름_글자수_초과_에러_메세지_출력() {
		assertSimpleTest(() -> {
			runNoLineFound("pobi,javaji");
			verify(ERROR_MESSAGE);
		});
	}

	@ParameterizedTest
	@DisplayName("유저 입력 차 이름 빈칸 값 에러 메세지 출력 테스트")
	@ValueSource(strings = {
		" ", " ,", ", ", ",,", " , , ,"
		, "car1,car2,"
		, "car1,,"
		, "car1,,car3"
		, ",car2,car3"
		, ",,car3"
	})
	void 유저_입력_차_이름_빈칸_에러_메세지_출력(String userInputCars) {
		assertSimpleTest(() -> {
			runNoLineFound(userInputCars);
			verify(ERROR_MESSAGE);
		});
	}

	@AfterEach
	void tearDown() {
		outputStandard();
	}

	@Override
	public void runMain() {
		Application.main(new String[] {});
	}
}
