package th.co.baiwa.buckwaframework.common.util;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;

public class BooleanToStringConverter {
	
	public static String convertToDatabaseColumn(Boolean value) {
		return (value != null && value) ? FLAG.Y_FLAG : FLAG.N_FLAG;
	}
	
	public static Boolean convertToBeanAttribute(String value) {
		return FLAG.Y_FLAG.equals(value);
	}
	
}
