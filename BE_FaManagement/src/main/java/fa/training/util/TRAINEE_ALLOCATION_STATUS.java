package fa.training.util;

public enum TRAINEE_ALLOCATION_STATUS {
	NOT_ALLOCATIED("Not allocated"), ALLOCATED("Allocated");

	public final String status;

	private TRAINEE_ALLOCATION_STATUS(String status) {
		this.status = status;
	}
}
