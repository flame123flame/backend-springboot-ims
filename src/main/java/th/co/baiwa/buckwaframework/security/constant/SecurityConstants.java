package th.co.baiwa.buckwaframework.security.constant;

public class SecurityConstants {
	
	public static final class URL {
		public static final String LOGIN_WEB = "/backend/login.htm";
		public static final String LOGIN_WEB_FAILURE = LOGIN_WEB + "?error";
		public static final String LOGIN_WEB_SUCCESS = "/backend/welcome.htm";
		public static final String LOGIN_REST = "/api/security/login";
	}
	
	public static final String USERNAME_PARAM = "username";
	public static final String PASSWORD_PARAM = "password";
	
	// Using in Security Module, for checking this User is authenticate already
	public static final class ROLE {
		public static final String USER = "ROLE_USER";
		public static final String ADMIN = "ROLE_ADMIN";
		
		// Role for Tax Audit
		public static final String PREFIX_TA = "TA";
		public static final String TA_ADM = "TA_ADM";
		public static final String TA_HEAD = "TA_HEAD";
		public static final String TA_PLAN = "TA_PLAN";
		public static final String TA_SEL = "TA_SEL";
		public static final String TA_OPR = "TA_OPR";
		
		// Role for Internal Audit
		public static final String PREFIX_IA = "IA";
		public static final String IA_ADM = "IA_ADM";
		public static final String IA_HEAD = "IA_HEAD";
		public static final String IA_OPR = "IA_OPR";
		public static final String IA_REC = "IA_REC";
		public static final String IA_SEL = "IA_SEL";
		
		// Role for Operator Audit
		public static final String PREFIX_OA = "OA";
		public static final String OA_ADM = "OA_ADM";
		public static final String OA_AU = "OA_AU";
		public static final String OA_HEAD = "OA_HEAD";
		public static final String OA_OPR = "OA_OPR";
	}
	
	public static final class SYSTEM_USER {
		public static final String SYSTEM = "SYSTEM";
		public static final String BATCH = "BATCH";
	}
	
	public static final String LOGOUT_FLAG = "logoutFlag";
	
}
