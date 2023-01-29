package fa.training.util;

public enum MESSAGE {

	MSG1("Sorry, your username or password is incorrect. Please try again!")
	,MSG2("Username must be not empty!")
	,MSG3("Password must be not empty!")
	,MSG4("You must input all required fields!")
	,MSG5("Wrong format.")
	,MSG6("Please input number only.")
	,MSG7("“Expected Start Date” must not be later than “Expected End Date”.")
	,MSG8("Nothing found.")
	,MSG9("You must start phone number with “0”.")
	,MSG10("Cannot input future date.")
	,MSG11("Cannot input past date.")
	,MSG12("Please select a file.")
	,MSG13("This trainee is already existed.")
	,MSG14("“Start Date” must not be later than “End Date”.")
	,MSG15("“Topic” must be unique.")
	,MSG16("“Passing Score” must not be greater than “Max Score”.")
	,MSG17("“Score” must not be greater than “Max Score”.")
	,MSG18("“Date” must be in “Milestone”.")
	,MSG19("0 ≤ “Bonus Point” ≤ 20.")
	,MSG20("0 ≤ “Penalty Point” ≤ 20.")
	,MSG21("“Start Date” must be later than “Committed Working Start Date”.")
	,MSG22("Update successfully.")
	,MSG23("Create successfully.")
	,MSG24("0 ≤ “Phone” ≤ 20")
	,MSG25("Please choose an excel file to import.")
	,MSG26("Submit successfully.")
	,MSG27("Approve successfully.")
	,MSG28("Accept successfully.")
	,MSG29("Start successfully.")
	,MSG30("Finish successfully.");

	public final String msg;

	private MESSAGE(String msg) {
		this.msg = msg;
	}
}
