package th.co.baiwa.buckwaframework.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants;

/**
 * @Author: Taechapon Himarat (Su)
 * @Create: September 11, 2013
 */
public class ReportUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportUtils.class);
	
	public static InputStream getResourceFile(String path, String fileName) {
		String inputPath = path + "/" + fileName;
		logger.debug("getResourceFile inputPath={}", inputPath);
		// App Path
		InputStream resourceFile = ReportUtils.class.getResourceAsStream(inputPath);
		if (resourceFile == null) {
			// Web Path
			resourceFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);
		}
		logger.debug("getResourceFile resourceFile={}", resourceFile);
		return resourceFile;
	}
	
	public static JasperPrint getJasperPrint(String fileName, Map<String, Object> params) throws JRException, IOException {
		return getJasperPrint(fileName, params, new JREmptyDataSource());
	}
	
	public static JasperPrint getJasperPrint(String fileName, Map<String, Object> params, JRDataSource dataSource) throws JRException, IOException {
		logger.info("getJasperPrint fileName={}", fileName);
		
		JasperPrint jasperPrint = null;
		if (FilenameUtils.isExtension(fileName, ReportConstants.FILE_EXTENSION.JASPER)) {
			InputStream jasperFile = getResourceFile(ReportConstants.PATH.JRXML_PATH, fileName);
			jasperPrint = JasperFillManager.fillReport(jasperFile, params, dataSource);
			jasperFile.close();
		} else if (FilenameUtils.isExtension(fileName, ReportConstants.FILE_EXTENSION.JRXML)) {
			InputStream jrxmlFile = getResourceFile(ReportConstants.PATH.JRXML_PATH, fileName);
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			jrxmlFile.close();
		}
		
		return jasperPrint;
	}
	
	public static JasperPrint getJasperPrint(JasperDesign jasperDesign, Map<String, Object> params) throws JRException, IOException {
		return getJasperPrint(jasperDesign, params, new JREmptyDataSource());
	}
	
	public static JasperPrint getJasperPrint(JasperDesign jasperDesign, Map<String, Object> paramMap, JRDataSource dataSource) throws JRException, IOException {
		logger.info("getJasperPrint jasperDesign={}", jasperDesign);
		
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);
		
		return jasperPrint;
	}
	
	public static void closeResourceFileInputStream(Map<String, Object> params) {
		for (Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() instanceof InputStream) {
				try {
					((InputStream) entry.getValue()).close();
					logger.debug("Close ResourceFileInputStream of key:{} Success", entry.getKey());
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
}
