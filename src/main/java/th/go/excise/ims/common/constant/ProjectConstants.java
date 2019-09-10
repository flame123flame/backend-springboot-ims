package th.go.excise.ims.common.constant;

public class ProjectConstants {
	
	public class TA_MAS_COND_MAIN_TYPE {
		public static final String TAX = "T";
		public static final String OTHER = "O";
	}
	
	public class EXCISE_OFFICE_CODE {
		public static final String CENTRAL = "000000";
		public static final String TA_CENTRAL = "001400";
		public static final String TA_CENTRAL_SELECTOR = "001401";
		public static final String TA_CENTRAL_OPERATOR1 = "001402";
		public static final String TA_CENTRAL_OPERATOR2 = "001403";
	}
	
	public class EXCISE_SUBDEPT_LEVEL {
		public static final String LV1 = "1";
		public static final String LV2 = "2";
		public static final String LV3 = "3";
	}
	
	public class TAX_COMPARE_TYPE {
		public static final String HALF = "1";
		public static final String MONTH = "2";
	}
	
	public class TA_DUTY_TYPE {
		public static final String SEPARATE = "S";
		public static final String GROUP = "G";
	}
	
	public class TA_WORKSHEET_STATUS {
		public static final String DRAFT = "D";
		public static final String CONDITION = "C";
		public static final String SELECTION = "S";
	}
	
	public class TA_PLAN_STATUS {
		public static final String CODE_0100 = "0100";
		public static final String CODE_0200 = "0200";
	}
	
	public class TA_AUDIT_STATUS {
		public static final String CODE_0100 = "0100";
		public static final String CODE_0101 = "0101";
		public static final String CODE_0200 = "0200";
		public static final String CODE_0300 = "0300";
		public static final String CODE_0301 = "0301";
		public static final String CODE_0400 = "0400";
		public static final String CODE_0401 = "0401";
		public static final String CODE_0501 = "0501";
		public static final String CODE_0502 = "0502";
		public static final String CODE_0503 = "0503";
		public static final String CODE_0900 = "0900";
	}
	
	public static class QUARTER {
		public static final String[] Q1 = {"10","11","12"};
		public static final String[] Q2 = {"01","02","03"};
		public static final String[] Q3 = {"04","05","06"};
		public static final String[] Q4 = {"07","08","09"};
	}
	
	public class WEB_SERVICE {
		public static final String PCC_RESPONSE_CODE_OK = "OK";
		
		public class INCFRI8000 {
			public static final String DATE_TYPE_RECEIPT = "Receipt";
			public static final String DATE_TYPE_INCOME = "Income";
			public static final String DATE_TYPE_RECEIPT_CODE = "R";
			public static final String DATE_TYPE_INCOME_CODE = "I";
		}
		
		public class OASFRI0100 {
			public static final String DATA_TYPE_MATERIAL = "M";
			public static final String DATA_TYPE_PRODUCT = "P";
			public static final String PS0704_ACC05 = "รับเดือนนี้";
			public static final String PS0704_ACC07 = "ผลิตสินค้าตามพิกัดฯ";
			public static final String PS0704_ACC14 = "รับจากการผลิต";
			public static final String PS0704_ACC18 = "จำหน่ายในประเทศ";
			public static final String PS0704_ACC19 = "จำหน่ายต่างประเทศ";
		}
		
		public class ANAFRI0001 {
			public static final String FORM_CODE_PS0307 = "ภส0307";
			public static final String FORM_CODE_PS0308 = "ภส0308";
		}
	}
	
	public static class DUTY_GROUP_TYPE {
		public static final String PRODUCT = "1";
		public static final String SERVICE = "2";
		public static final String OTHER = "3";
		public static final String SURA = "4";
	}
	
	public static class TA_RISK_LEVEL {
		public static final String LOWER = "2";
		public static final String LOW = "3";
		public static final String MEDIUM = "4";
		public static final String HIGH = "5";
		public static final String HIGHER = "6";
	}
	public static class TA_PLAN_WORKSHEET_STATUS {
		public static final String ONPLAN = "I";
		public static final String RESERVE = "R";
		public static final String OUTPLAN = "E";
		public static final String SUMMARY = "S";
	}
	
	public static class TA_FORM_TS_CODE {
		public static final String TS0101 = "TS0101";
		public static final String TS0102 = "TS0102";
		public static final String TS0103 = "TS0103";
		public static final String TS0104 = "TS0104";
		public static final String TS0105 = "TS0105";
		public static final String TS0106 = "TS0106";
		public static final String TS0107 = "TS0107";
		public static final String TS0108 = "TS0108";
		public static final String TS0109 = "TS0109";
		public static final String TS0110 = "TS0110";
		public static final String TS0111 = "TS0111";
		public static final String TS0112 = "TS0112";
		public static final String TS0113 = "TS0113";
		public static final String TS0114 = "TS0114";
		public static final String TS01141 = "TS01141";
		public static final String TS01142 = "TS01142";
		public static final String TS0115 = "TS0115";
		public static final String TS0116 = "TS0116";
		public static final String TS0117 = "TS0117";
		public static final String TS01171 = "TS01171";
		public static final String TS0118 = "TS0118";
		public static final String TS0119 = "TS0119";
		public static final String TS0120 = "TS0120";
		public static final String TS0121 = "TS0121";
		public static final String TS0302 = "TS0302";
		public static final String TS0303 = "TS0303";
		public static final String TS0423 = "TS0423";
		public static final String TS0424 = "TS0424";
	}
	
	public static class TA_AUDIT_STEP_STATUS {
		/**
		 * For Monitoring and Field Audit
		 */
		public static class TYPE_MF {
			public static final String CODE_1000 = "1000";
			public static final String CODE_1010 = "1010";
			public static final String CODE_1020 = "1020";
			public static final String CODE_1030 = "1030";
			public static final String CODE_1040 = "1040";
			public static final String CODE_2000 = "2000";
			public static final String CODE_2010 = "2010";
			public static final String CODE_2020 = "2020";
			public static final String CODE_2021 = "2021";
			public static final String CODE_2022 = "2022";
			public static final String CODE_2023 = "2023";
			public static final String CODE_3000 = "3000";
			public static final String CODE_3010 = "3010";
			public static final String CODE_3020 = "3020";
			public static final String CODE_3021 = "3021";
			public static final String CODE_3022 = "3022";
			public static final String CODE_3030 = "3030";
			public static final String CODE_3031 = "3031";
			public static final String CODE_3032 = "3032";
			public static final String CODE_3040 = "3040";
			public static final String CODE_3041 = "3041";
			public static final String CODE_3042 = "3042";
			public static final String CODE_3050 = "3050";
		}
		/**
		 * For Desk Audit
		 */
		public static class TYPE_D {
			public static final String CODE_1000 = "1000";
			public static final String CODE_1010 = "1010";
			public static final String CODE_1020 = "1020";
			public static final String CODE_1030 = "1030";
			public static final String CODE_1040 = "1040";
			public static final String CODE_1050 = "1050";
			public static final String CODE_2000 = "2000";
			public static final String CODE_2010 = "2010";
			public static final String CODE_2021 = "2021";
			public static final String CODE_2022 = "2022";
			public static final String CODE_2031 = "2031";
			public static final String CODE_2032 = "2032";
			public static final String CODE_2041 = "2041";
			public static final String CODE_2042 = "2042";
			public static final String CODE_2051 = "2051";
			public static final String CODE_2052 = "2052";
			public static final String CODE_2053 = "2053";
			public static final String CODE_2054 = "2054";
			public static final String CODE_2055 = "2055";
			public static final String CODE_3000 = "3000";
			public static final String CODE_3010 = "3010";
			public static final String CODE_3021 = "3021";
			public static final String CODE_3022 = "3022";
			public static final String CODE_3023 = "3023";
			public static final String CODE_3030 = "3030";
			public static final String CODE_3041 = "3041";
			public static final String CODE_3042 = "3042";
			public static final String CODE_3043 = "3043";
			public static final String CODE_3051 = "3051";
			public static final String CODE_3052 = "3052";
			public static final String CODE_3053 = "3053";
			public static final String CODE_3054 = "3054";
			public static final String CODE_3061 = "3061";
			public static final String CODE_3062 = "3062";
			public static final String CODE_3063 = "3063";
			public static final String CODE_3064 = "3064";
			public static final String CODE_3065 = "3065";
			public static final String CODE_3071 = "3071";
			public static final String CODE_3072 = "3072";
		}
		/**
		 * For Searching
		 */
		public static final class TYPE_S {
			public static final String CODE_1000 = "1000";
			public static final String CODE_1010 = "1010";
			public static final String CODE_1020 = "1020";
			public static final String CODE_1030 = "1030";
			public static final String CODE_2000 = "2000";
			public static final String CODE_2010 = "2010";
			public static final String CODE_3000 = "3000";
			public static final String CODE_3010 = "3010";
		}
		/**
		 * For Introduce
		 */
		public static final class TYPE_I {
			
		}
	}
	
}
