package th.go.excise.ims.oa.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbCompare;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydCustomerLicenDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbCompareRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0106JdbcRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0201JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0106FormVo;
import th.go.excise.ims.oa.vo.Oa0106SolventVo;
import th.go.excise.ims.oa.vo.Oa0106Vo;
import th.go.excise.ims.oa.vo.Oa020103Vo;

@Service
public class Oa0106Service {

	@Autowired
	private Oa0106JdbcRepository oa0106JdbcRepo;

	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRepo;
	
	@Autowired
	private OaHydCustomerLicenDtlRepository customerLicenseRepo;
	
	@Autowired
	private OaHydrocarbCompareRepository hydCompareRepo;
	
	@Autowired
	private Oa0201JdbcRepository oa0201JdbcRep;

	public DataTableAjax<Oa0106Vo> filterByCriteria(Oa0106FormVo request, String officeCode) {
		String offCode = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa0106Vo> data = oa0106JdbcRepo.getData(request, offCode);
		int count = oa0106JdbcRepo.countData(request, offCode);
		DataTableAjax<Oa0106Vo> dataTableAjax = new DataTableAjax<Oa0106Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}

	public DataTableAjax<Oa0106Vo> filterHydByCriteria(Oa0106FormVo request, String officeCode) {
		String offCode = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa0106Vo> data = oa0106JdbcRepo.getDataHyd(request, offCode);
		int count = oa0106JdbcRepo.countDataHyd(request, offCode);
		DataTableAjax<Oa0106Vo> dataTableAjax = new DataTableAjax<Oa0106Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}

	@SuppressWarnings("finally")
	public byte[] objectToPDF(String licenseId,String dtlId) {
		byte[] content = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params = setPrepareJasperOaOpe01(licenseId,dtlId);
			
			List<OaHydrocarbCompare> listCompare = new ArrayList<OaHydrocarbCompare>();
			listCompare = hydCompareRepo.findByoaHydrocarbIdAndIsDeleted(new BigDecimal(dtlId), "N");
			
			if (listCompare.size() >0) {
				params.put("summaryDate", ConvertDateUtils.formatDateToString(listCompare.get(0).getSumaryDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
				params.put("summaryTime", ConvertDateUtils.formatDateToString(listCompare.get(0).getSumaryDate(), "HH:mm"));
				
				params.put("auditDate", ConvertDateUtils.formatDateToString(listCompare.get(0).getAuditDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
				params.put("auditTime", ConvertDateUtils.formatDateToString(listCompare.get(0).getAuditDate(), "HH:mm"));
			}
			
			JasperPrint jasperPrint1 = ReportUtils.getJasperPrint("OA_OPE_01" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint2 = ReportUtils.getJasperPrint("OA_OPE_05" + "." + FILE_EXTENSION.JASPER, params,
					listCompare.size()> 0 ?  new JRBeanCollectionDataSource(listCompare, false) :new JREmptyDataSource());
			JasperPrint jasperPrint3 = ReportUtils.getJasperPrint("OA_OPE_07" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint4 = ReportUtils.getJasperPrint("OA_OPE_02" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint5 = ReportUtils.getJasperPrint("OA_OPE_03" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint6 = ReportUtils.getJasperPrint("OA_OPE_04" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
//			JasperPrint jasperPrint7 = ReportUtils.getJasperPrint("Solvent-01", params, new JREmptyDataSource());
//			JasperPrint jasperPrint8 = ReportUtils.getJasperPrint("Solvent-02", params, new JREmptyDataSource());

			List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();

			items.add(new SimpleExporterInputItem(jasperPrint1));
			items.add(new SimpleExporterInputItem(jasperPrint2));
			items.add(new SimpleExporterInputItem(jasperPrint3));
			items.add(new SimpleExporterInputItem(jasperPrint4));
			items.add(new SimpleExporterInputItem(jasperPrint5));
			items.add(new SimpleExporterInputItem(jasperPrint6));
//			items.add(new SimpleExporterInputItem(jasperPrint7));
//			items.add(new SimpleExporterInputItem(jasperPrint8));

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(items));

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.exportReport();
			content = outputStream.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			return content;
		}
	}

	public Map<String, Object> setPrepareJasperOaOpe01(String licenseId,String dtlId) {
		Map<String, Object> params = new HashMap<String, Object>();
		BigDecimal id = new BigDecimal(licenseId);
		Oa0106Vo license = oa0106JdbcRepo.getCustomerLicenseById(licenseId);
		Optional<OaHydrocarbDtl> oaHydrocabon = oaHydrocarbDtlRepo.findById(new BigDecimal(dtlId));
		OaHydrocarbDtl data = oaHydrocabon.get();
		
		java.sql.Date sqlDate = new java.sql.Date(license.getStartDate().getTime());
		LocalDate localDate = LocalDateConverter.convertToEntityAttribute(sqlDate);
		int year = localDate.getYear()+543;
		int day = localDate.getDayOfMonth();
	
		// OA_OPE_01
		params.put("licenseType", license.getLicenseType());
		params.put("licenseNo1", license.getLicenseNo());
		params.put("licenseNo2", "");
		params.put("licenseDate",Integer.toString(day) );
		params.put("licenseMonth", ConvertDateUtils.getMonthThai(localDate.getMonthValue(), ConvertDateUtils.FULL_MONTH));
		params.put("licenseYear",Integer.toString(year)  );
		params.put("companyName", license.getCompanyName());
		params.put("identityCompany", license.getIdentifyNo());
		params.put("totalEmployee", data.getEmployeeTemporary()!=null?data.getEmployeePermanent().add(data.getEmployeePermanent()):new BigDecimal(0));
		params.put("permanentEmployee", data.getEmployeePermanent());
		params.put("temporaryEmployee", data.getEmployeeTemporary());
		params.put("warehouse", license.getWarehouseAddress());
		params.put("officeOwnType", data.getOfficePlaceOwner());
		params.put("workStartTime", ConvertDateUtils.formatDateToString(data.getWorkingStartDate(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
		params.put("workEndTime",ConvertDateUtils.formatDateToString(data.getWorkingEndDate(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
		params.put("workingDate", data.getWorkdayPermonth());
		params.put("numberOfTank", data.getNumberOfTank());
		params.put("tankCapacity", data.getTankCapacity());
		params.put("numberUtility", data.getNumberUtility());
		params.put("orderType", data.getOrderType());
		params.put("orderPayMethod", data.getOrderPayMethod());
		params.put("rentOfficePrice",data.getOfficeRentAmount() != null ? data.getOfficeRentAmount():null);
		params.put("orderPayMethodOther", data.getPayMethodOther());

		// OA_OPE_05
		
		// OA_OPE_07
		
		// OA_OPE_02
		params.put("asaleToAgent", data.getASaleToAgent());
		params.put("asaleToUser", data.getASaleToUser());
		params.put("asaleAgentLicense", data.getASaleAgentLicense());
		params.put("asaleUserLicense", data.getASaleUserLicense());
		params.put("sentToAgent", data.getSentToAgent());
		params.put("sentToUser", data.getSentToUser());
		params.put("materail", data.getMaterail());
		params.put("document", data.getDocument());
		params.put("productProcess", data.getProductProcess());
		params.put("productNextime", data.getProductNextime());
		if (data.getUseStartDate() != null && data.getUseEndDate() != null) {
			params.put("useStartDate", ConvertDateUtils.formatDateToString(data.getUseStartDate(), ConvertDateUtils.DD_MM_YYYY));
			params.put("useEndDate", ConvertDateUtils.formatDateToString(data.getUseEndDate(), ConvertDateUtils.DD_MM_YYYY));
		}
		params.put("buyOverlimit", data.getBuyOverlimit());
		
		// OA_LUB_05
		// GET SUB REPORT
		InputStream jasperStream = ReportUtils.getResourceFile(ReportConstants.PATH.JRXML_PATH, "SUB_01_OA_LUB_05"+"."+ FILE_EXTENSION.JASPER);
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			params.put("SUB_01_OA_LUB_05",  jasperReport);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal summary = new BigDecimal(0);
		List<OaHydCustomerLicenDtl> listLicenseDetail = new ArrayList<OaHydCustomerLicenDtl>();
		listLicenseDetail = customerLicenseRepo.findByoaCuslicenseIdAndIsDeleted(id, "N");
		for (int i = 0; i < listLicenseDetail.size(); i++) {
			summary = summary.add(listLicenseDetail.get(i).getAmount());
		}
		params.put("licenseList",  new JRBeanCollectionDataSource(listLicenseDetail, false));
		params.put("licenseSumary", summary);

		return params;
	}
	
	@SuppressWarnings("finally")
	public byte[] objectToSolvent(String licenseId,String dtlId, String planId) {
		byte[] content = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params = setObjectSolvent(licenseId,dtlId, planId);
			List<Oa020103Vo> persons = oa0201JdbcRep.findUserAuditerByPlanId("", new BigDecimal(planId));
			List<Oa0106SolventVo> list = new ArrayList<Oa0106SolventVo>();
			if (persons.size() > 1) {
				for(int i=1; i<persons.size(); i++) {
					Oa0106SolventVo person = new Oa0106SolventVo();
					person.setName(persons.get(i).getUserThaiName());
					person.setPosition(persons.get(i).getTitle());
					person.setSeq(arabic2thai(i));
					if (i == 1) {
						person.setFirst("Y");
					}
					list.add(person);
				}	
			} else {
				for(int i=0; i<4; i++) {
					Oa0106SolventVo person = new Oa0106SolventVo();
					person.setSeq(arabic2thai(i+1));
					if (i == 0) {
						person.setFirst("Y");
					}
					list.add(person);
				}
			}
			
			JasperPrint jasperPrint1 = ReportUtils.getJasperPrint("OA_SOLVENT_01" + "." + FILE_EXTENSION.JASPER, params,
					list.size()> 0 ?  new JRBeanCollectionDataSource(list, false) :new JREmptyDataSource());

			List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();

			items.add(new SimpleExporterInputItem(jasperPrint1));

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(items));

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.exportReport();
			content = outputStream.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			return content;
		}
	}
	
	public Map<String, Object> setObjectSolvent(String licenseId,String dtlId, String planId) {
		Map<String, Object> params = new HashMap<String, Object>();
		Oa0106Vo license = oa0106JdbcRepo.getCustomerLicenseById(licenseId);
		String province = license.getAddress().split(" ")[3].replace("จ.", "").trim();
		String amphoe = license.getAddress().split(" ")[2].replace("อ.", "").trim();
		String district = license.getAddress().split(" ")[1].replace("ต.", "").trim();
		String addressNo = license.getAddress().split(" ")[0].replace("เลขที่", "").trim();
		//	String postcode = license.getAddress().split(" ")[4].trim();
		List<Oa020103Vo> persons = oa0201JdbcRep.findUserAuditerByPlanId("", new BigDecimal(planId));
		if (persons.size() > 0) {
			Oa020103Vo data = persons.get(0);
			params.put("myname", data.getUserThaiName());
			params.put("myposition", data.getTitle());
			params.put("mylicense", data.getUserThaiId());
			params.put("underby", ApplicationCache.getExciseDepartment(data.getOfficeCode()).getDeptName());
		}
		params.put("soi", null);
		params.put("road", null);
		params.put("groupNo", null);
		params.put("province", province);
		params.put("amphoe", amphoe);
		params.put("addressNo", addressNo);
		params.put("district", district);
		params.put("factoryname", license.getCompanyName());
		params.put("username", license.getCompanyName());
		params.put("userposition", "POSITION");
		params.put("telephone", license.getTelephone());
		if ("A".equalsIgnoreCase(license.getLicenseType())) {
			params.put("agent", "Y");
		}
		if ("B".equalsIgnoreCase(license.getLicenseType())) {
			params.put("user", "Y");
		}
		params.put("starttime", null);
		params.put("endtime", null);
		params.put("day", null);
		params.put("month", null);
		params.put("year", null);
		params.put("writeat", null);
		return params;
	}
	
	private String arabic2thai(int index) {
		StringBuilder number = new StringBuilder ("" + index);
		char[] th = {'๐','๑','๒','๓','๔','๕','๖','๗','๘','๙'};
		char[] en = {'0','1','2','3','4','5','6','7','8','9'};
		for(int j=0; j<number.length(); j++) {
			for(int i=0; i<en.length; i++) {
				if (en[i] == number.charAt(j)) {
					number.setCharAt(j, th[i]);
				}
			}
		}
		return number.toString();
	}

}
