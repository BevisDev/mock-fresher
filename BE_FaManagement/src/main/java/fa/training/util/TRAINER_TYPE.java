package fa.training.util;

public enum TRAINER_TYPE {
	MASTER_TRAINER(1),TRAINER(0);

	public final int type;
	
	TRAINER_TYPE(int type)
	{
		this.type = type;
	}
	
	public int get() {
		return type;
	}
}
