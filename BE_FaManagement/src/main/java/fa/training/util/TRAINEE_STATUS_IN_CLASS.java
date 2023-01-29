package fa.training.util;

public enum TRAINEE_STATUS_IN_CLASS {
	IN_PROGRESS("In-progress"), ENROLLED("Enrolled"), ACTIVE("Active");

	public final String status;

	private TRAINEE_STATUS_IN_CLASS(String status) {
		this.status = status;
	}
}
