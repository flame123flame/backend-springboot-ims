package th.go.excise.ims.ia.constant;

public class IaConstants {

//	************************* QUESTIONNAIRE *************************
	public class IA_STATUS {
		public static final String PARAM_GROUP_CODE = "IA_STATUS";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "สร้างแบบสอบถาม";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "รอตรวจสอบแบบสอบถาม";

		public static final String STATUS_3_CODE = "3";
		public static final String STATUS_3_DESC = "รอส่งแบบสอบถาม";

		public static final String STATUS_4_CODE = "4";
		public static final String STATUS_4_DESC = "รอผลตอบแบบสอบถาม";

		public static final String STATUS_5_CODE = "5";
		public static final String STATUS_5_DESC = "ตอบแบบสอบถามเรียบร้อย";

		public static final String STATUS_6_CODE = "6";
		public static final String STATUS_6_DESC = "สรุปผลแบบสอบถามแล้ว";

		public static final String STATUS_7_CODE = "7";
		public static final String STATUS_7_DESC = "ยกเลิกส่งแบบสอบถาม";

	}

	public class IA_STATUS_REPLY_QTN {
		public static final String PARAM_GROUP_CODE = "IA_STATUS_REPLY_QTN";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "รอผลตอบแบบสอบถาม";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "กำลังดำเนินการตอบแบบสอบถาม";

		public static final String STATUS_3_CODE = "3";
		public static final String STATUS_3_DESC = "ตอบแบบสอบถามเรียบร้อยและส่งกลับ";

		public static final String STATUS_4_CODE = "4";
		public static final String STATUS_4_DESC = "ยกเลิกแบบสอบถาม";

	}
	
	public class USAGE_PATTERNS_QTN {
		public static final String QUESTIONNAIR_DESC = "Q";
		
		public static final String FACTOR_DESC = "F";

		public static final String NULL_DESC = "";
	}

//	************************* Risk Factors *************************

	public class IA_DATA_EVALUATE {
		
		public static final String PARAM_GROUP_CODE = "IA_DATA_EVALUATE";
		
		public static final String NEW = "NEW";
		public static final String QUESTIONNAIRE = "questionnaire";
		public static final String BUDGET_PROJECT = "budget_project";
		public static final String PROJECT_EFFICIENCY = "project_efficiency";
		public static final String SYSTEM_UNWORKING = "system_unworking";
		public static final String CHECK_PERIOD = "check_period";
		public static final String INCOME_PERFORM = "income_perform";
		public static final String SUPPRESSION = "suppression";
	}

	public class IA_RISK_COLOR {
		
		public static final String PARAM_GROUP_CODE = "IA_RISK_COLOR";
		
		public static final String COLOR1 = "เขียวเข้ม";
		public static final String COLOR1_CODE = "#22911ef5";

		public static final String COLOR2 = "เขียว";
		public static final String COLOR2_CODE = "#66d13cf5";

		public static final String COLOR3 = "เหลือง";
		public static final String COLOR3_CODE = "#f5f114";

		public static final String COLOR4 = "ส้ม";
		public static final String COLOR4_CODE = "#f58941";

		public static final String COLOR5 = "แดง";
		public static final String COLOR5_CODE = "#ff231fe3";

	}

	public class IA_STATUS_RISK_FACTORS {

		public static final String PARAM_GROUP_CODE = "IA_STATUS_RISK_FACTORS";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "ยังไม่กำหนดเกณฑ์";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "กำหนดเกณฑ์เรียบร้อย";

		public static final String STATUS_3_CODE = "3";
		public static final String STATUS_3_DESC = "กำหนดเงื่อนไขความเสี่ยงทั้งหมดเรียบร้อย";

	}

//	************************* PLAN HDR *************************
	
	public class PLAN_HDR_STATUS {

		public static final String PARAM_GROUP_CODE = "IA_PLAN_HDR_STATUS";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "อนุมัติ";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "ไม่อนุมัติ";

	}
	
//	************************* PLAN DAY ACTIVITY *************************
	
	public class PLAN_DAY_WORDING {
		public static final String ENGAGEMENT_FULL = "ENGAGEMENT";
		public static final String ENGAGEMENT_ABBREVIATION = "EN";
		
		public static final String AUDIT_FULL = "AUDIT";
		public static final String AUDIT_ABBREVIATION = "AU";
		
		public static final String REPORT_FULL = "REPORT";
		public static final String REPORT_ABBREVIATION = "RE";
		
		public static final String MONITORING_FULL = "MONITORING";
		public static final String MONITORING_ABBREVIATION = "MO";
	}
	
	public class PLAN_DAY_ACTIVITY_STATUS {
		public static final String PARAM_GROUP_CODE = "IA_PLAN_DAY_ACTIVITY_STATUS";

		public static final String PARAM_CODE_I = "1";
		public static final String VALUE_1_DESC_I = "ยังไม่ได้ดำเนินการ";
		public static final String VALUE_2_COLOR_I = "#646464";

		public static final String PARAM_CODE_II = "2";
		public static final String VALUE_1_DESC_II ="กำลังดำเนินการ";
		public static final String VALUE_2_COLOR_II = "#3880df";
				
		public static final String PARAM_CODE_III = "3";
		public static final String VALUE_1_DESC_III = "ดำเนินการเสร็จสิ้น";
		public static final String VALUE_2_COLOR_III = "#5ef35e";
	}
	
//	************************* IA_STAMP_DETAIL *************************
	
	public class STAMP_STATUS {

		public static final String PARAM_GROUP_CODE = "IA_STAMP_STATUS";

		public static final String PARAM_CODE_I = "1";
		public static final String VALUE_1_DESC_I = "รับ";

		public static final String PARAM_CODE_II = "2";
		public static final String VALUE_1_DESC_II = "จ่าย";
		
		public static final String PARAM_CODE_III = "3";
		public static final String VALUE_1_DESC_III = "ส่งคืน";

	}
	
//	************************* IA_UTILITY_BILL *************************
	
	public class UTILITY_BILL_REASON {

		public static final String PARAM_GROUP_CODE = "IA_UTILITY_BILL_REASON";

		public static final String PARAM_CODE_I = "1";
		public static final String VALUE_1_DESC_I = "ยังไม่ได้รับใบแจ้งหนี้";

		public static final String PARAM_CODE_II = "2";
		public static final String VALUE_1_DESC_II = "รอจัดสรรงบประมาณ";
		
		public static final String PARAM_CODE_III = "3";
		public static final String VALUE_1_DESC_III = "อยู่ระหว่างอนุมัติสั่งจ่าย";
		
		public static final String PARAM_CODE_IV = "4";
		public static final String VALUE_1_DESC_IV = "รอเบิกจ่ายพร้อมกัน";
		
		public static final String PARAM_CODE_V = "5";
		public static final String VALUE_1_DESC_V = "อื่นๆ";

	}
	
	public class UTILITY_BILL_TYPE {
		
		public static final String PARAM_GROUP_CODE = "IA_UTILITY_BILL_TYPE";
		
		public static final String PARAM_CODE_I = "1";
		public static final String VALUE_1_DESC_I = "ค่าไฟฟ้า";
		
		public static final String PARAM_CODE_II = "2";
		public static final String VALUE_1_DESC_II = "ค่าน้ำประปา";
		
		public static final String PARAM_CODE_III = "3";
		public static final String VALUE_1_DESC_III = "ค่าโทรศัพท์";
		
		public static final String PARAM_CODE_IV = "4";
		public static final String VALUE_1_DESC_IV = "ค่าโทรศัพท์เคลื่อนที่";
		
		public static final String PARAM_CODE_V = "5";
		public static final String VALUE_1_DESC_V = "ค่าบริการไปรษณีย์โทรเลข";
		
		public static final String PARAM_CODE_VI = "6";
		public static final String VALUE_1_DESC_VI = "ค่าบริการสื่อสารและโทรคมนาคม";
		
	}
	
	public static class STATUS {
    	public static final String PROCESS = "2048";
    	public static final String PASS = "2055";
    	public static final String NOT_PASS = "2056";
    	public static final String SUCCESS = "2057";	    				    			   
    }
	
//	************************* IA_CHART_OF_ACC *************************
	
	public class CHART_OF_ACC_SYMBOLS {
		public static final String ZERO = "0";
		public static final String POSITIVE = "1";
		public static final String NEGATIVE = "2";
	}
	
	public class CHART_OF_ACC_TYPE {
		public static final String DEBIT = "D";
		public static final String CREDIT = "C";
	}
}
