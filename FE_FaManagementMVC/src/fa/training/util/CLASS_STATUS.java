package fa.training.util;

public enum CLASS_STATUS {
	DRAFT("Draft"), IN_PROCESS("In process"), SUBMITED("Submitted"), CANCELLED("Cancelled"), REJECTED("Rejected"),
	PLANNING("Planning"), PLANNED("Planned"), DECLINED("Declined"), PENDING_FOR_REVIEW("Pending for review"),
	CLOSED("Closed"), WAITING_FOR_MORE_INFO("Waiting for more information"), ACTIVE("Active");

	private String status;

	CLASS_STATUS(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
