package racinggame.service.validation;

public class UserInputRegex {

	// 빈칸을 제외한 '문자/숫자/_'가 1~5글자로 시작하고 그 다음 ,로 시작하는 똑같은 조건 반복
	public static final String INPUT_CARS_REGEX = "^(\\s*\\w{1,5}\\s*)(,\\s*\\w{1,5}\\s*)*$";
}
