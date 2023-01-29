package fa.training.util;

public enum ROLE {

	ROLE_MANAGER("ROLE_MANAGER"), ROLE_REC("ROLE_REC"), ROLE_DELIVERY_MANAGER("ROLE_DELIVERYMANAGER"),
	ROLE_CLASS_ADMIN("ROLE_CLASSADMIN"), ROLE_TRAINER("ROLE_TRAINER"),ROLE_SYSTEM("ROLE_SYSTEM");

	public final String name;

	private ROLE(String name) {
		this.name = name;
	}

}
