package fa.training.util;

public enum TRAINEE_STATUS {
	ENROLLED("Enrolled"), WAITING_FOR_CLASS("Waiting for Class"), WAITING_FOR_ALLOCATION("Waiting for Allocation"),
	DEFERRED("Deferred"), DROP_OUT("Drop-out"), ALLOCATED("Allocated");

	public final String status;

	private TRAINEE_STATUS(String status) {
		this.status = status;
	}
}
