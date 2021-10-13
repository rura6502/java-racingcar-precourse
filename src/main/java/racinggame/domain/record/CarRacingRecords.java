package racinggame.domain.record;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import racinggame.domain.car.Car;

public class CarRacingRecords {

	private List<CarRacingRecord> records;

	private int longestPosition = 0;

	public CarRacingRecords() {
		this.records = new ArrayList<>();
	}

	/**
	 * tryStep 번 째 {@link CarRacingRecord}를 반환
	 * @param tryStep 시도 번 째 수(두 번째의 경우 2). 0부터 시작
	 * @return
	 */
	public CarRacingRecord getRecordByTryStep(int tryStep) {
		return records.get(tryStep);
	}

	public CarRacingRecords addNewRecord(CarRacingRecord record) {
		this.records.add(record);
		longestPosition = Math.max(longestPosition, record.getLongestPosition());
		return this;
	}

	public Collection<Car> getWinners() {
		CarRacingRecord lastRecord = records.get(records.size() - 1);
		return lastRecord.getWinners();
	}

	public List<CarRacingRecord> getRecords() {
		return records;
	}
}
