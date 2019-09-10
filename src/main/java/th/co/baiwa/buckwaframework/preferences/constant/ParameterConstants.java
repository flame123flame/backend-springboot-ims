package th.co.baiwa.buckwaframework.preferences.constant;

public class ParameterConstants {
	
	// All Parameter Group
	public static final class PARAM_GROUP {
		public static final String SYSTEM_CONFIG = "SYSTEM_CONFIG";
		// Excise Master Data
		public static final String EXCISE_TAX_TYPE = "EXCISE_TAX_TYPE";
		public static final String EXCISE_PRODUCT_TYPE = "EXCISE_PRODUCT_TYPE";
		public static final String EXCISE_SERVICE_TYPE = "EXCISE_SERVICE_TYPE";
		public static final String EXCISE_FACTORY_TYPE = "EXCISE_FACTORY_TYPE";
		public static final String ED_DUTY_GROUP = "ED_DUTY_GROUP";
		// Internal Audit
		public static final String IA_RISK_COLOR = "IA_RISK_COLOR";
		// Tax Audit
		public static final String TA_MAS_COND_MAIN_DESC = "TA_MAS_COND_MAIN_DESC";
		public static final String TA_AUDIT_STATUS = "TA_AUDIT_STATUS";
		public static final String TA_CONFIG = "TA_CONFIG";
		public static final String TA_MAIN_COND_RANGE = "TA_MAIN_COND_RANGE";
		public static final String TA_AUDIT_TYPE = "TA_AUDIT_TYPE";
		public static final String TA_MAIN_COND_FREQ_TYPE = "TA_MAIN_COND_FREQ_TYPE";
		public static final String TA_REG_STATUS = "TA_REG_STATUS";
		public static final String TA_FORM_TS = "TA_FORM_TS";
	}
	
	// Parameter Group: SYSTEM_CONFIG
	public static final class SYSTEM_CONFIG {
		public static final String LOGIN_ATTEMPTS = "LOGIN_ATTEMPTS";
	}
	
	// Parameter Group: EXCISE_FACTORY_TYPE
	public static final class EXCISE_FACTORY_TYPE {
		public static final String PRODUCT = "1";
		public static final String SERVICE = "2";
		public static final String IMPORT = "3";
	}
	
	// Parameter Group: TA_CONFIG
	public class TA_CONFIG {
		public static final String SEE_FLAG = "SEE_FLAG";
		public static final String SELECT_FLAG = "SELECT_FLAG";
		public static final String SEND_ALL_FLAG = "SEND_ALL_FLAG";
		public static final String INCOME_TYPE = "INCOME_TYPE";
		
		// INCOME_TYPE
		public static final String INCOME_TYPE_TAX = "TAX";
		public static final String INCOME_TYPE_NET = "NET";
	}
	
	// Parameter Group: TA_MAIN_COND_RANGE
	public class TA_MAIN_COND_RANGE {
		public static final String GREATER_THAN = "1";
		public static final String GREATER_THAN_OR_EQUALS = "2";
		public static final String EQUALS = "3";
		public static final String LESS_THAN_OR_EQUALS = "4";
		public static final String LESS_THAN = "5";
	}
	
	// Parameter Group: TA_SUB_COND_CAPITAL
	public class TA_SUB_COND_CAPITAL {
		public static final String HUGE_CAPITAL = "1";
		public static final String LARGE_CAPITAL = "2";
		public static final String MEDIUM_CAPITAL = "3";
		public static final String SMALL_CAPITAL = "4";
		public static final String OTHER = "0";
	}
	
}
