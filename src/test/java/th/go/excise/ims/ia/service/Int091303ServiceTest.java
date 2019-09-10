package th.go.excise.ims.ia.service;

import java.util.Date;

import org.junit.Test;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;

public class Int091303ServiceTest {

	@Test
	public void printYearMM() {
//		System.out.println(ConvertDateUtils.formatDateToString(new Date(), "yy", ConvertDateUtils.LOCAL_TH));
		int currYesr = Integer.parseInt(ConvertDateUtils.formatDateToString(new Date(), "yy", ConvertDateUtils.LOCAL_TH));
		int currMonth = Integer.parseInt(ConvertDateUtils.formatDateToString(new Date(), "MM", ConvertDateUtils.LOCAL_TH));
		if(currMonth > 10 ) {
			currYesr ++;
		}
		
	}
}
