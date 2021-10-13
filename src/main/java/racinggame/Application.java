package racinggame;

import racinggame.service.CarRacingGameService;

public class Application {
	public static void main(String[] args) {

		CarRacingGameService service = new CarRacingGameService();
		service.runRacingGame();
	}
}
