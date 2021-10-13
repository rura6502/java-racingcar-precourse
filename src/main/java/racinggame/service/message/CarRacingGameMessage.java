package racinggame.service.message;

public class CarRacingGameMessage {

	public static final String USER_INPUT_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	public static final String USER_INPUT_TRY_COUNT = "시도할 횟수는 몇회인가요?";

	public static final String ERROR_ONLY_NUMBER = "숫자만 입력해주세요.";
	public static final String ERROR_ONLY_CAR_NAMES_WITH_COMMA_SEPERATOR =
		"쉼표로 구분된 차 이름 목록을 입력해주세요. (예. car1,car2,car3)";
	public static final String ERROR_TAG = "[ERROR] ";

	public static final String PRINT_RESULT = "실행 결과";
	public static final String PRINT_WINNER_FORMAT = "최종 우승자는 %s 입니다.";
	public static final String PRINT_CAR_AND_SCORE_FORMAT = "%s : %s";

}
