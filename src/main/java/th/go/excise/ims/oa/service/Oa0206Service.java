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

import net.sf.jasperreports.engine.JRDataSource;
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
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCompare;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCust;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenDetailRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsCompareRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0201JdbcRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0206JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa020103Vo;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;
import th.go.excise.ims.oa.vo.Oa0206CustomersVo;
import th.go.excise.ims.oa.vo.Oa0206FormVo;
import th.go.excise.ims.oa.vo.Oa0206LubricantVo;
import th.go.excise.ims.oa.vo.Oa0206Vo;

@Service
public class Oa0206Service {

	@Autowired
	private Oa0206JdbcRepository oa0206JdbcRepo;

	@Autowired
	private OaLubricantsDtlRepository oaLubricantsDtlRepo;

	@Autowired
	private Oa02010607Service oa02010607Service;

	@Autowired
	private Oa02010608Service oa02010608Service;

	@Autowired
	private Oa02010609Service oa02010609Service;

	@Autowired
	private OaCustomerLicenDetailRepository customerLicenseRepo;

	@Autowired
	private OaLubricantsCompareRepository lubricantsComapreRepo;
	
	@Autowired
	private Oa0201JdbcRepository oa0201JdbcRep;

	public DataTableAjax<Oa0206Vo> filterByCriteria(Oa0206FormVo request, String officeCode) {
		String offCode = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa0206Vo> data = oa0206JdbcRepo.getData(request, offCode);
		int count = oa0206JdbcRepo.countData(request, offCode);
		DataTableAjax<Oa0206Vo> dataTableAjax = new DataTableAjax<Oa0206Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}

	public DataTableAjax<Oa0206Vo> filterHydByCriteria(Oa0206FormVo request, String officeCode) {
		String offCode = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa0206Vo> data = oa0206JdbcRepo.getDataHyd(request, offCode);
		int count = oa0206JdbcRepo.countDataHyd(request, offCode);
		DataTableAjax<Oa0206Vo> dataTableAjax = new DataTableAjax<Oa0206Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}

	@SuppressWarnings("finally")
	public byte[] objectToPDF(String licenseId, String dtlId) {
		byte[] content = null;
		try {
//			Oa020106ButtonVo idAll = buttonRepo.findButtonIdById(new BigDecimal(dtlId));

			// OA_LUB_04
			// Ope020106 [09]
			Oa020106DtlVo ope02010609 = oa02010609Service.findDetailById(dtlId);
			List<OaLubricantsCust> custs = ope02010609.getCustdeles();
			List<Oa0206CustomersVo> customers = new ArrayList<>();
			Oa0206CustomersVo customer;
			int count = 0;
			if (custs != null) {
				for (OaLubricantsCust cust : custs) {
					BigDecimal seq = new BigDecimal(count++);
					customer = new Oa0206CustomersVo();
					customer.setAddress(cust.getAddress());
					customer.setCustName(cust.getCustName());
					customer.setMobile(cust.getMobile());
					customer.setSeq(seq.toString());
					customers.add(customer);
				}
			}

			JRDataSource oaOpe04DataSource = new JRBeanCollectionDataSource(customers);
			Map<String, Object> params = new HashMap<String, Object>();
			params = setPrepareJasperOaOpe01(licenseId, dtlId);

			List<OaLubricantsCompare> listCompare = new ArrayList<OaLubricantsCompare>();
			listCompare = lubricantsComapreRepo.findByOaLubricantsIdAndIsDeleted(new BigDecimal(dtlId), "N");

			if (listCompare.size() > 0) {
				params.put("summaryDate", ConvertDateUtils.formatDateToString(listCompare.get(0).getSumaryDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				params.put("summaryTime",
						ConvertDateUtils.formatDateToString(listCompare.get(0).getSumaryDate(), "HH:mm"));

				params.put("auditDate", ConvertDateUtils.formatDateToString(listCompare.get(0).getAuditDate(),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				params.put("auditTime",
						ConvertDateUtils.formatDateToString(listCompare.get(0).getAuditDate(), "HH:mm"));
			}

			JasperPrint jasperPrint1 = ReportUtils.getJasperPrint("OA_LUB_01" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint2 = ReportUtils.getJasperPrint("OA_LUB_05" + "." + FILE_EXTENSION.JASPER, params,
					listCompare.size() > 0 ? new JRBeanCollectionDataSource(listCompare, false)
							: new JREmptyDataSource());
			JasperPrint jasperPrint3 = ReportUtils.getJasperPrint("OA_LUB_07" + "." + FILE_EXTENSION.JASPER, params);
			JasperPrint jasperPrint4 = ReportUtils.getJasperPrint("OA_LUB_02" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint5 = ReportUtils.getJasperPrint("OA_LUB_03" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint6 = ReportUtils.getJasperPrint("OA_LUB_04" + "." + FILE_EXTENSION.JASPER, params,
					customers.size() > 0 ? oaOpe04DataSource : new JREmptyDataSource());
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

	public Map<String, Object> setPrepareJasperOaOpe01(String licenseId, String dtlId) {
		Map<String, Object> params = new HashMap<String, Object>();
		BigDecimal id = new BigDecimal(licenseId);
		Oa0206Vo license = oa0206JdbcRepo.getCustomerLicenseById(id);
//		Optional<OaHydrocarbDtl> oaHydrocabon = oaHydrocarbDtlRepo.findById(id);
//		OaHydrocarbDtl data = oaHydrocabon.get();
//		Oa020106ButtonVo idAll = buttonRepo.findButtonIdById(license.getOaLicensePlan());
		Optional<OaLubricantsDtl> oaHydrocabon = oaLubricantsDtlRepo.findById(new BigDecimal(dtlId));

//		Optional<OaCustomerLicen> oaCuslicense = oaCustomerLicense.findById(idAll.getOaCuslicenseId());

		OaLubricantsDtl data = oaHydrocabon.get();
//		OaCustomerLicen cus = oaCuslicense.get();
		java.sql.Date sqlDate = new java.sql.Date(license.getStartDate().getTime());
		LocalDate localDate = LocalDateConverter.convertToEntityAttribute(sqlDate);
		int year = localDate.getYear() + 543;
		int day = localDate.getDayOfMonth();

		// OA_LUB_01
		params.put("licenseType", license.getLicenseType());
		params.put("licenseNo1", license.getLicenseNo());
		params.put("licenseNo2", "");
		params.put("licenseDate", Integer.toString(day));
		params.put("licenseMonth",
				ConvertDateUtils.getMonthThai(localDate.getMonthValue(), ConvertDateUtils.FULL_MONTH));
		params.put("licenseYear", Integer.toString(year));
		params.put("companyName", license.getCompanyName());
		params.put("identityCompany", license.getIdentifyNo());
		params.put("totalEmployee",
				data.getEmployeeTemporary() != null ? data.getEmployeePermanent().add(data.getEmployeePermanent())
						: new BigDecimal(0));
		params.put("permanentEmployee", data.getEmployeePermanent());
		params.put("temporaryEmployee", data.getEmployeeTemporary());
		params.put("warehouse", license.getWarehouseAddress());
		params.put("officeOwnType", data.getOfficePlaceOwner());
		params.put("workStartTime", ConvertDateUtils.formatDateToString(data.getWorkingStartDate(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("workEndTime", ConvertDateUtils.formatDateToString(data.getWorkingEndDate(),
				ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("workingDate", data.getWorkdayPermonth());
		params.put("numberOfTank", data.getNumberOfTank());
		params.put("tankCapacity", data.getTankCapacity());
		params.put("numberUtility", data.getNumberUtility());
		params.put("orderType", data.getOrderType());
		params.put("orderPayMethod", data.getOrderPayMethod());
		params.put("rentOfficePrice", data.getOfficeRentAmount());
		params.put("orderPayMethodOther", data.getPayMethodOther());

		// OA_LUB_02
		// Ope020106 [07]
		OaLubricantsDtl ope02010607 = oa02010607Service.findDetailById(dtlId);
		// parameters
//		params.put("dailyAcc", ope02010607.getDailyAcc());
//		params.put("dailyAccDoc", ope02010607.getDailyAccDoc());
//		params.put("dailyAuditRemark", ope02010607.getDailyAuditRemark());
//		params.put("monthlyAcc", ope02010607.getMonthlyAcc());
//		params.put("monthlyAccDoc", ope02010607.getMonthlyAccDoc());
//		params.put("monthlyAuditRemark", ope02010607.getMonthlyAuditRemark());
//		params.put("monthlyAcc04", ope02010607.getMonthlyAcc04());
//		params.put("monthlyAccDoc04", ope02010607.getMonthlyAccDoc04());
//		params.put("monthlyAuditRemark04", ope02010607.getMonthlyAuditRemark04());
//		params.put("monthStart", ope02010607.getAgentStartDate());
//		params.put("monthEnd", ope02010607.getAgentEndDate());
//		params.put("abuyFromIndust", ope02010607.getABuyFromIndust());
//		params.put("abuyFromAgent", ope02010607.getABuyFromAgent());
//		params.put("abuyFromImporter", ope02010607.getABuyFromImporter());
//		params.put("abuyIndustLicense", ope02010607.getABuyIndustLicense());
//		params.put("abuyAgentLicense", ope02010607.getABuyAgentLicense());
//		params.put("abuyImporterLicense", null);
//		params.put("agentOverlimit", ope02010607.getAgentOverlimit());

		// OA_LUB_03
		// Ope020106 [08]
		OaLubricantsDtl ope02010608 = oa02010608Service.findDetailById(dtlId);
		// parameters
		params.put("asaleToAgent", ope02010608.getASaleToAgent());
		params.put("asaleToUser", ope02010608.getASaleToUser());
		params.put("asaleAgentLicense", ope02010608.getASaleAgentLicense());
		params.put("asaleUserLicense", ope02010608.getASaleUserLicense());
		params.put("sentToAgent", ope02010608.getSentToAgent());
		params.put("sentToUser", ope02010608.getSentToUser());
		params.put("materail", ope02010608.getMaterail());
		params.put("document", ope02010608.getDocument());
		params.put("productProcess", ope02010608.getProductProcess());
		params.put("productNextime", ope02010608.getProductNextime());
		params.put("useStartDate", ope02010608.getUseStartDate());
		params.put("useEndDate", ope02010608.getUseEndDate());
		params.put("buyOverlimit", ope02010608.getBuyOverlimit());

		// OA_LUB_04
		// Ope020106 [09]
		Oa020106DtlVo ope02010609 = oa02010609Service.findDetailById(dtlId);
		// parameters
		params.put("buyFromAgent", ope02010609.getBuyFromAgent());
		params.put("buyFromIndust", ope02010609.getBuyFromIndust());
		params.put("buyFromImporter", ope02010609.getBuyFromImporter());
		params.put("buyAgentLicense", ope02010609.getBuyAgentLicense());
		params.put("buyIndustLicense", ope02010609.getBuyIndustLicense());
		params.put("buyImporterLicense", ope02010609.getBuyImporterLicense());
		params.put("usedType", ope02010609.getUsedType());
		params.put("usedRemark", ope02010609.getUsedRemark());
		params.put("salerType", ope02010609.getSalerType());
		params.put("salerCapacity", ope02010609.getSalerCapacity());
		params.put("numOfCust", ope02010609.getNumOfCust());
		params.put("goodQuality", ope02010609.getGoodQuality());
		params.put("otherRemark", ope02010609.getOtherRemark());

		// OA_LUB_05
		// GET SUB REPORT
		InputStream jasperStream = ReportUtils.getResourceFile(ReportConstants.PATH.JRXML_PATH,
				"SUB_01_OA_LUB_05" + "." + FILE_EXTENSION.JASPER);
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			params.put("SUB_01_OA_LUB_05", jasperReport);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal summary = new BigDecimal(0);
		List<OaCustomerLicenDetail> listLicenseDetail = new ArrayList<OaCustomerLicenDetail>();
		listLicenseDetail = customerLicenseRepo.findByoaCuslicenseIdAndIsDeleted(id, "N");
		for (int i = 0; i < listLicenseDetail.size(); i++) {
			summary = summary.add(listLicenseDetail.get(i).getAmount());
		}
		params.put("licenseList", new JRBeanCollectionDataSource(listLicenseDetail, false));
		params.put("licenseSumary", summary);

		return params;
	}

	@SuppressWarnings("finally")
	public byte[] objectToLubricant(String licenseId, String dtlId, String planId) {
		byte[] content = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params = setObjectSolvent(licenseId, dtlId, planId);
			
			List<Oa020103Vo> persons = oa0201JdbcRep.findUserAuditerByPlanId("", new BigDecimal(planId));
			List<Oa0206LubricantVo> list = new ArrayList<Oa0206LubricantVo>();
			if (persons.size() > 1) {
				for(int i=1; i<persons.size(); i++) {
					Oa0206LubricantVo person = new Oa0206LubricantVo();
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
					Oa0206LubricantVo person = new Oa0206LubricantVo();
					person.setSeq(arabic2thai(i+1));
					if (i == 0) {
						person.setFirst("Y");
					}
					list.add(person);
				}
			}

			JasperPrint jasperPrint1 = ReportUtils.getJasperPrint("OA_LUBRICANT_01" + "." + FILE_EXTENSION.JASPER, params,
					list.size() > 0 ? new JRBeanCollectionDataSource(list, false) : new JREmptyDataSource());

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

	public Map<String, Object> setObjectSolvent(String licenseId, String dtlId, String planId) {
		Map<String, Object> params = new HashMap<String, Object>();
		Oa0206Vo license = oa0206JdbcRepo.getCustomerLicenseById(new BigDecimal(licenseId));
		String province = license.getAddress().split(" ")[3].replace("จ.", "").trim();
		String amphoe = license.getAddress().split(" ")[2].replace("อ.", "").trim();
		String district = license.getAddress().split(" ")[1].replace("ต.", "").trim();
		String addressNo = license.getAddress().split(" ")[0].replace("เลขที่", "").trim();
		// String postcode = license.getAddress().split(" ")[4].trim();
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
		params.put("day",null);
		params.put("month", null);
		params.put("year", null);
		params.put("writeat", null);
		return params;
	}

	private String arabic2thai(int index) {
		StringBuilder number = new StringBuilder("" + index);
		char[] th = { '๐', '๑', '๒', '๓', '๔', '๕', '๖', '๗', '๘', '๙' };
		char[] en = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int j = 0; j < number.length(); j++) {
			for (int i = 0; i < en.length; i++) {
				if (en[i] == number.charAt(j)) {
					number.setCharAt(j, th[i]);
				}
			}
		}
		return number.toString();
	}

}
