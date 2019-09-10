package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;

public class Int12070101ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Test
	public void test_exportReport_Blank() throws Exception {
		Int12070101Service int12070101Service = new Int12070101Service();
		
		// set data
		
		byte[] reportFile = int12070101Service.exportReport(0l);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.IA_FORM_6006_NO + "_blank"))));
	}

}
