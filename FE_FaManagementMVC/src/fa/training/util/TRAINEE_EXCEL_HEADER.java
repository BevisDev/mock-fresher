package fa.training.util;

public enum TRAINEE_EXCEL_HEADER {
	EMP_ID(1,"Empl ID"), NAME(2,"Name"), DOB(3,"DOB"), GENDER(4,"Gender"), UNIVERSITY(5,"University")
	,FACULTY(6,"Faculty"), PHONE(7,"Phone"), EMAIL(8,"Email");

	public int cellIndex;
	public String header;

	TRAINEE_EXCEL_HEADER(int cellIndex,String header) {
		this.cellIndex = cellIndex;
		this.header = header;
	}

	public String getStatus() {
		return header;
	}

}
