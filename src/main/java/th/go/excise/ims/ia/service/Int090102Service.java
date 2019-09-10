package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingH;
import th.go.excise.ims.ia.persistence.repository.IaEmpWorkingDtlRepository;
import th.go.excise.ims.ia.persistence.repository.IaEmpWorkingHRepository;
import th.go.excise.ims.ia.vo.IaEmpWorkingDtlReportFieldVo;
import th.go.excise.ims.ia.vo.IaEmpWorkingDtlSaveVo;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrFormVo;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseHoliday;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Int090102Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Int090102Service.class);

	@Autowired
	private IaEmpWorkingDtlRepository empWorkingDtlRepository;
	
	@Autowired
	private IaEmpWorkingHRepository empWorkingHRepository;
	
	public void save(IaEmpWorkingDtlSaveVo formVo) {
		IaEmpWorkingDtl emp = new IaEmpWorkingDtl();
		if (null != formVo.getIaEmpWorkingDtlSeq()) {
			emp = empWorkingDtlRepository.findByIaEmpWorkingDtlSeq(formVo.getIaEmpWorkingDtlSeq());
		}
		emp.setUserLogin(UserLoginUtils.getCurrentUsername());
		String userName = UserLoginUtils.getCurrentUserBean().getUserThaiName() + " " + UserLoginUtils.getCurrentUserBean().getUserThaiSurname();
		emp.setUserName(userName);
		emp.setUserPosition(UserLoginUtils.getCurrentUserBean().getTitle());
		emp.setUserOffcode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		emp.setWorkingDate(ConvertDateUtils.parseStringToDate(formVo.getWorkingDate(), ConvertDateUtils.DD_MM_YYYY));
		emp.setWorkingFlag(formVo.getWorkingFlag());
		emp.setWorkingDesc(formVo.getWorkingDesc());
		emp.setWorkingRemark(formVo.getWorkingRemark());
		emp.setReimburseExpFlag(formVo.getReimburseExpFlag());
		
		empWorkingDtlRepository.save(emp);
	}
	
	public List<IaEmpWorkingDtl> getByMonth(IaEmpWorkingDtlSaveVo formVo) {
		List<IaEmpWorkingDtl> emp = empWorkingDtlRepository.findByMonth(formVo.getWorkingDate());
		return emp;
	}
	
	public void delete(IaEmpWorkingDtlSaveVo formVo) {
		IaEmpWorkingDtl emp = new IaEmpWorkingDtl();
		emp = empWorkingDtlRepository.findByIaEmpWorkingDtlSeq(formVo.getIaEmpWorkingDtlSeq());
		emp.setIsDeleted(FLAG.Y_FLAG);
		
		empWorkingDtlRepository.save(emp);
	}
	
	public void saveHdr(IaEmpWorkingHdrVo formVo) {
		IaEmpWorkingH emp = new IaEmpWorkingH();
		if (null != formVo.getIaEmpWorkingHSeq()) {
			emp = empWorkingHRepository.findByIaEmpWorkingHSeq(formVo.getIaEmpWorkingHSeq());
		}
		emp.setUserLogin(UserLoginUtils.getCurrentUsername());
		String userName = UserLoginUtils.getCurrentUserBean().getUserThaiName() + " " + UserLoginUtils.getCurrentUserBean().getUserThaiSurname();
		emp.setUserName(userName);
		emp.setUserPosition(UserLoginUtils.getCurrentUserBean().getTitle());
		emp.setUserOffcode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		emp.setWorkingMonth(formVo.getWorkingMonth());
		emp.setOwnerCaseSpirits(formVo.getOwnerCaseSpirits());
		emp.setAsstCaseSpirits(formVo.getAsstCaseSpirits());
		emp.setRemarkCaseSpirits(formVo.getRemarkCaseSpirits());
		emp.setOwnerCaseTobacco(formVo.getOwnerCaseTobacco());
		emp.setAsstCaseTobacco(formVo.getAsstCaseTobacco());
		emp.setRemarkCaseTobacco(formVo.getRemarkCaseTobacco());
		emp.setOwnerCaseCard(formVo.getOwnerCaseCard());
		emp.setAsstCaseCard(formVo.getAsstCaseCard());
		emp.setRemarkCaseCard(formVo.getRemarkCaseCard());
		emp.setOwnerCaseEdtax(formVo.getOwnerCaseEdtax());
		emp.setAsstCaseEdtax(formVo.getAsstCaseEdtax());
		emp.setRemarkCaseEdtax(formVo.getRemarkCaseEdtax());
		emp.setOwnerCaseSpiritsFw(formVo.getOwnerCaseSpiritsFw());
		emp.setAsstCaseSpiritsFw(formVo.getAsstCaseSpiritsFw());
		emp.setOwnerCaseTobaccoFw(formVo.getOwnerCaseTobaccoFw());
		emp.setAsstCaseTobaccoFw(formVo.getAsstCaseTobaccoFw());
		emp.setOwnerCaseCardFw(formVo.getOwnerCaseCardFw());
		emp.setAsstCaseCardFw(formVo.getAsstCaseCardFw());
		emp.setOwnerCaseEdtaxFw(formVo.getOwnerCaseEdtaxFw());
		emp.setAsstCaseEdtaxFw(formVo.getAsstCaseEdtaxFw());
		
		empWorkingHRepository.save(emp);
	}
	
	public IaEmpWorkingHdrVo getByMonthHdr(IaEmpWorkingHdrFormVo formVo) {
		List<IaEmpWorkingH> emp = empWorkingHRepository.findByMonth(formVo);
		IaEmpWorkingHdrVo hrd = new IaEmpWorkingHdrVo();
		BigDecimal ownerCaseSpiritsFw = new BigDecimal(0);
		BigDecimal asstCaseSpiritsFw = new BigDecimal(0);
		BigDecimal ownerCaseTobaccoFw = new BigDecimal(0);
		BigDecimal asstCaseTobaccoFw = new BigDecimal(0);
		BigDecimal ownerCaseCardFw = new BigDecimal(0);
		BigDecimal asstCaseCardFw = new BigDecimal(0);
		BigDecimal ownerCaseEdtaxFw = new BigDecimal(0);
		BigDecimal asstCaseEdtaxFw = new BigDecimal(0);
		
		BigDecimal ownerTotal = new BigDecimal(0);
		BigDecimal asstTotal = new BigDecimal(0);
		BigDecimal ownerEdtaxTotal = new BigDecimal(0);
		BigDecimal asstEdtaxTotal = new BigDecimal(0);
		for (IaEmpWorkingH em : emp) {
			if (formVo.getWorkingMonth2().equals(em.getWorkingMonth())) {
				hrd.setIaEmpWorkingHSeq(em.getIaEmpWorkingHSeq());
				hrd.setUserLogin(em.getUserLogin());
				hrd.setUserName(em.getUserName());
				hrd.setUserPosition(em.getUserPosition());
				hrd.setUserOffcode(em.getUserOffcode());
				hrd.setWorkingMonth(em.getWorkingMonth());
				hrd.setOwnerCaseSpirits(em.getOwnerCaseSpirits());
				hrd.setAsstCaseSpirits(em.getAsstCaseSpirits());
				hrd.setRemarkCaseSpirits(em.getRemarkCaseSpirits());
				hrd.setOwnerCaseTobacco(em.getOwnerCaseTobacco());
				hrd.setAsstCaseTobacco(em.getAsstCaseTobacco());
				hrd.setRemarkCaseTobacco(em.getRemarkCaseTobacco());
				hrd.setOwnerCaseCard(em.getOwnerCaseCard());
				hrd.setAsstCaseCard(em.getAsstCaseCard());
				hrd.setRemarkCaseCard(em.getRemarkCaseCard());
				hrd.setOwnerCaseEdtax(em.getOwnerCaseEdtax());
				hrd.setAsstCaseEdtax(em.getAsstCaseEdtax());
				hrd.setRemarkCaseEdtax(em.getRemarkCaseEdtax());
				ownerTotal = em.getOwnerCaseSpirits().add(em.getOwnerCaseTobacco()).add(em.getOwnerCaseCard()).add(em.getOwnerCaseEdtax());
				asstTotal = em.getAsstCaseSpirits().add(em.getAsstCaseTobacco()).add(em.getAsstCaseCard()).add(em.getAsstCaseEdtax());
			}
			ownerCaseSpiritsFw = ownerCaseSpiritsFw.add(em.getOwnerCaseSpirits());
			asstCaseSpiritsFw = asstCaseSpiritsFw.add(em.getAsstCaseSpirits());
			ownerCaseTobaccoFw = ownerCaseTobaccoFw.add(em.getOwnerCaseTobacco());
			asstCaseTobaccoFw = asstCaseTobaccoFw.add(em.getAsstCaseTobacco());
			ownerCaseCardFw = ownerCaseCardFw.add(em.getOwnerCaseCard());
			asstCaseCardFw = asstCaseCardFw.add(em.getAsstCaseCard());
			ownerCaseEdtaxFw = ownerCaseEdtaxFw.add(em.getOwnerCaseEdtax());
			asstCaseEdtaxFw = asstCaseEdtaxFw.add(em.getAsstCaseEdtax());
			
			ownerEdtaxTotal = ownerEdtaxTotal.add(em.getOwnerCaseSpirits()).add(em.getOwnerCaseTobacco()).add(em.getOwnerCaseCard()).add(em.getOwnerCaseEdtax());
			asstEdtaxTotal = asstEdtaxTotal.add(em.getAsstCaseSpirits()).add(em.getAsstCaseTobacco()).add(em.getAsstCaseCard()).add(em.getAsstCaseEdtax());
		}
		
		if (0 == emp.size()) {
			hrd.setOwnerCaseSpirits(new BigDecimal(0));
			hrd.setAsstCaseSpirits(new BigDecimal(0));
			hrd.setOwnerCaseTobacco(new BigDecimal(0));
			hrd.setAsstCaseTobacco(new BigDecimal(0));
			hrd.setOwnerCaseCard(new BigDecimal(0));
			hrd.setAsstCaseCard(new BigDecimal(0));
			hrd.setOwnerCaseEdtax(new BigDecimal(0));
			hrd.setAsstCaseEdtax(new BigDecimal(0));
		}
		
		hrd.setOwnerCaseSpiritsFw(ownerCaseSpiritsFw);
		hrd.setAsstCaseSpiritsFw(asstCaseSpiritsFw);
		hrd.setOwnerCaseTobaccoFw(ownerCaseTobaccoFw);
		hrd.setAsstCaseTobaccoFw(asstCaseTobaccoFw);
		hrd.setOwnerCaseCardFw(ownerCaseCardFw);
		hrd.setAsstCaseCardFw(asstCaseCardFw);
		hrd.setOwnerCaseEdtaxFw(ownerCaseEdtaxFw);
		hrd.setAsstCaseEdtaxFw(asstCaseEdtaxFw);
		hrd.setOwnerTotal(ownerTotal);
		hrd.setAsstTotal(asstTotal);
		hrd.setOwnerEdtaxTotal(ownerEdtaxTotal);
		hrd.setAsstEdtaxTotal(asstEdtaxTotal);
		
		return hrd;
	}
	
	public byte[] generateReport(IaEmpWorkingHdrVo formVo) throws IOException, JRException {
		logger.info("generateReport");
		
		List<IaEmpWorkingDtl> emp = empWorkingDtlRepository.findByMonth(formVo.getWorkingMonth());
		ArrayList<IaEmpWorkingDtlReportFieldVo> workingDtlList = new ArrayList<IaEmpWorkingDtlReportFieldVo>();
		List<ExciseHoliday> holiday = empWorkingDtlRepository.getHoliday("01/05/2018");
//		List<ExciseHoliday> holiday = empWorkingDtlRepository.getHoliday(formVo.getWorkingMonth());

		Map<String, Object> params = new HashMap<>();
		String userName = UserLoginUtils.getCurrentUserBean().getUserThaiName() + " " + UserLoginUtils.getCurrentUserBean().getUserThaiSurname();
		ExciseDepartment userOffcode = ApplicationCache.getExciseDepartment(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		int workingFlag1Total = 0;
		int workingFlag3Total = 0;
		int workingFlag5Total = 0;
		int workingFlag6Total = 0;
		int numWeekend = 0;
		params.put("userName", userName);
		params.put("userPosition", UserLoginUtils.getCurrentUserBean().getTitle());
		params.put("userOffcode", userOffcode.getDeptName());
		params.put("workingDate", ConvertDateUtils.parseStringToDate(formVo.getWorkingMonth(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		
		int year = ConvertDateUtils.parseStringToLocalDate(formVo.getWorkingMonth(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN).getYear();
		int month = ConvertDateUtils.parseStringToLocalDate(formVo.getWorkingMonth(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN).getMonthValue() - 1;
		Calendar cal = new GregorianCalendar(year, month, 1);
		int index = 0;
		boolean haveEvent = false;
		do {
			haveEvent = false;
			IaEmpWorkingDtlReportFieldVo workingDtl = new IaEmpWorkingDtlReportFieldVo();
		    // get the day of the week for the current day
		    int dayWk = cal.get(Calendar.DAY_OF_WEEK);
		    
		    for (int i = 0; i < emp.size(); i++) {
				IaEmpWorkingDtl em = emp.get(i);
				String toDayStr = ConvertDateUtils.formatDateToString(em.getWorkingDate(), ConvertDateUtils.DD_MM_YYYY);
				int toDay = ConvertDateUtils.parseStringToLocalDate(toDayStr, ConvertDateUtils.DD_MM_YYYY).getDayOfMonth();
				if (cal.get(Calendar.DAY_OF_MONTH) == toDay) {
					haveEvent = true;
					workingDtl.setWorkingDesc(em.getWorkingDesc());
					workingDtl.setWorkingRemark(em.getWorkingRemark());
					if ("1".equals(em.getWorkingFlag())) {
						workingFlag1Total = workingFlag1Total+1;
					} else if ("3".equals(em.getWorkingFlag())) {
						workingFlag3Total = workingFlag3Total+1;
					} else if ("5".equals(em.getWorkingFlag())) {
						workingFlag5Total = workingFlag5Total+1;
					} else if ("6".equals(em.getWorkingFlag())) {
						workingFlag6Total = workingFlag6Total+1;
					}
				}
			}
		    if (!haveEvent) {
		    	for (ExciseHoliday hol : holiday) {
		    		int hday = hol.getHolidayDate().getDayOfMonth();
		    		if (cal.get(Calendar.DAY_OF_MONTH) == hday) {
		    			workingDtl.setWorkingDesc("วันหยุดนักขัตฤกษ์");
		    			if (dayWk != Calendar.SATURDAY && dayWk != Calendar.SUNDAY) {
					    	// check if it is a Saturday or Sunday
							numWeekend += 1;
				    	}
		    			break;
		    		}
				}
		    	if (dayWk == Calendar.SATURDAY) {
			    	// check if it is a Saturday
					workingDtl.setWorkingDesc("หยุดราชการวันเสาร์");
					numWeekend += 1;
		    	} else if (dayWk == Calendar.SUNDAY) {
			    	// check if it is a Sunday
			    	workingDtl.setWorkingDesc("หยุดราชการวันอาทิตย์");
			    	numWeekend += 1;
			    }
			}
		    
		    workingDtl.setNumber(String.valueOf(index+1));
		    workingDtlList.add(workingDtl);
		    
		    // advance to the next day
		    cal.add(Calendar.DAY_OF_YEAR, 1);
		    index++;
		}  while (cal.get(Calendar.MONTH) == month);
		// stop when we reach the start of the next month

		logger.info("export IA_EMP_WORKING_2");
		params.put("userName", userName);
		params.put("userPosition", UserLoginUtils.getCurrentUserBean().getTitle());
		params.put("ownerCaseSpirits", formVo.getOwnerCaseSpirits());
		params.put("asstCaseSpirits", formVo.getAsstCaseSpirits());
		params.put("remarkCaseSpirits", formVo.getRemarkCaseSpirits());
		params.put("ownerCaseTobacco", formVo.getOwnerCaseTobacco());
		params.put("asstCaseTobacco", formVo.getAsstCaseTobacco());
		params.put("remarkCaseTobacco", formVo.getRemarkCaseTobacco());
		params.put("ownerCaseCard", formVo.getOwnerCaseCard());
		params.put("asstCaseCard", formVo.getAsstCaseCard());
		params.put("remarkCaseCard", formVo.getRemarkCaseCard());
		params.put("ownerCaseEdtax", formVo.getOwnerCaseEdtax());
		params.put("asstCaseEdtax", formVo.getAsstCaseEdtax());
		params.put("remarkCaseEdtax", formVo.getRemarkCaseEdtax());
		params.put("ownerCaseSpiritsFw", formVo.getOwnerCaseSpiritsFw());
		params.put("asstCaseSpiritsFw", formVo.getAsstCaseSpiritsFw());
		params.put("ownerCaseTobaccoFw", formVo.getOwnerCaseTobaccoFw());
		params.put("asstCaseTobaccoFw", formVo.getAsstCaseTobaccoFw());
		params.put("ownerCaseCardFw", formVo.getOwnerCaseCardFw());
		params.put("asstCaseCardFw", formVo.getAsstCaseCardFw());
		params.put("ownerCaseEdtaxFw", formVo.getOwnerCaseEdtaxFw());
		params.put("asstCaseEdtaxFw", formVo.getAsstCaseEdtaxFw());
		params.put("ownerTotal", formVo.getOwnerTotal());
		params.put("asstTotal", formVo.getAsstTotal());
		params.put("ownerEdtaxTotal", formVo.getOwnerEdtaxTotal());
		params.put("asstEdtaxTotal", formVo.getAsstEdtaxTotal());
		params.put("workingFlag1Total", new BigDecimal(workingFlag1Total));
		params.put("workingFlag3Total", new BigDecimal(workingFlag3Total));
		params.put("workingFlag5Total", new BigDecimal(workingFlag5Total));
		params.put("workingFlag6Total", new BigDecimal(workingFlag6Total));
		params.put("noWorkingTotal", new BigDecimal(numWeekend+workingFlag3Total));
		params.put("sector", userOffcode.getDeptName());
		params.put("area", userOffcode.getDeptName());

		JRDataSource dataSource = new JRBeanCollectionDataSource(workingDtlList);

		// set output
		JasperPrint jasperPrint1 = ReportUtils.getJasperPrint(REPORT_NAME.IA_EMP_WORKING + "." + FILE_EXTENSION.JASPER, params, dataSource);
		JasperPrint jasperPrint2 = ReportUtils.getJasperPrint(REPORT_NAME.IA_EMP_WORKING_2 + "." + FILE_EXTENSION.JASPER, params);

		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		items.add(new SimpleExporterInputItem(jasperPrint2));

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
	
	public List<ExciseHoliday> getHoliday(String workingDate) {
		List<ExciseHoliday> holiday = empWorkingDtlRepository.getHoliday(workingDate);
		return holiday;
	}
}
