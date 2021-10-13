package racinggame;

import racinggame.service.CarRacingGameService;

public class Application {

	public static final int FORWARD_STOP_CRITERIA = 4;

	public static void main(String[] args) {
		CarRacingGameService service = new CarRacingGameService();
		service.runRacingGame();
	}
}
