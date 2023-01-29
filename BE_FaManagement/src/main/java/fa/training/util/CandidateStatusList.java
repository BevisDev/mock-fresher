package fa.training.util;

public class CandidateStatusList {
	public static final String CANDIDATE_PASS = "Pass";
	public static final String CANDIDATE_FAIL = "Fail";
	public static final String CANDIDATE_NON = "";
	public static final String[] CANDIDATE_RESULT = { CANDIDATE_NON, CANDIDATE_PASS, CANDIDATE_FAIL };
	public static final String CANDIDATE_STATUS_NEW = "New";
	public static final String CANDIDATE_STATUS_TEST_PASS = "Test - Pass";
	public static final String CANDIDATE_STATUS_TEST_FAIL = "Test - Fail";
	public static final String CANDIDATE_STATUS_INTERVIEW_PASS = "Interview - Pass";
	public static final String CANDIDATE_STATUS_INTERVIEW_FAIL = "Interview - Fail";
	public static final String CANDIDATE_STATUS_TRANSFERRED = "Transferred";
	public static final String[] CANDIDATE_STATUS = { CANDIDATE_STATUS_NEW, CANDIDATE_STATUS_TEST_PASS,
			CANDIDATE_STATUS_TEST_FAIL, CANDIDATE_STATUS_INTERVIEW_PASS, CANDIDATE_STATUS_INTERVIEW_FAIL,
			CANDIDATE_STATUS_TRANSFERRED };
}
