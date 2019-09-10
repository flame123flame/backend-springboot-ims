package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class TaxOperatorFormVo extends DataTableRequest {

	private static final long serialVersionUID = 2104725920450722805L;

	private String dateStart;
	private String dateEnd;
	private int dateRange;
	private String draftNumber;
	private String budgetYear;
	private String facType;
	private String dutyCode;
	private String officeCode;
	private String analysisNumber;
	private List<String> cond = new ArrayList<String>();
	private String seeDataSelect;
	private String condNumber;
	private String condSub1;
	private String condSub2;
	private String condSub3;
	private String capital;
	private String risk;
	private String newRegId;
	private String cusFullname;
	private String facFullname;
	private String mainCondFreqType;
	private Integer taxMonthStart;
	private Integer taxMonthEnd;
	private String flagPage;
	private String condSubNoAuditFlag;
	private String taxAuditLast;
	private String sector;
	private String area;
	private String worksheetStatus;
	private String newRegFlag;
	private BigDecimal sumTaxAmStart;
	private BigDecimal sumTaxAmEnd;
	private String skipCond;
	private List<String> regStatus;
	private Date regDateStart;
	private Date regDateEnd;
	private String incomeType;
	
	// new Entity
	private String cuscatId;
	private String pinnitId;
	
	private List<String> yearMonthList;
	
	

	public BigDecimal getSumTaxAmStart() {
		return sumTaxAmStart;
	}

	public void setSumTaxAmStart(BigDecimal sumTaxAmStart) {
		this.sumTaxAmStart = sumTaxAmStart;
	}

	public BigDecimal getSumTaxAmEnd() {
		return sumTaxAmEnd;
	}

	public void setSumTaxAmEnd(BigDecimal sumTaxAmEnd) {
		this.sumTaxAmEnd = sumTaxAmEnd;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getDateRange() {
		return dateRange;
	}

	public void setDateRange(int dateRange) {
		this.dateRange = dateRange;
	}

	public String getDraftNumber() {
		return draftNumber;
	}

	public void setDraftNumber(String draftNumber) {
		this.draftNumber = draftNumber;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public List<String> getCond() {
		return cond;
	}

	public void setCond(List<String> cond) {
		this.cond = cond;
	}

	public String getSeeDataSelect() {
		return seeDataSelect;
	}

	public void setSeeDataSelect(String seeDataSelect) {
		this.seeDataSelect = seeDataSelect;
	}

	public String getCondNumber() {
		return condNumber;
	}

	public void setCondNumber(String condNumber) {
		this.condNumber = condNumber;
	}

	public String getCondSub1() {
		return condSub1;
	}

	public void setCondSub1(String condSub1) {
		this.condSub1 = condSub1;
	}

	public String getCondSub2() {
		return condSub2;
	}

	public void setCondSub2(String condSub2) {
		this.condSub2 = condSub2;
	}

	public String getCondSub3() {
		return condSub3;
	}

	public void setCondSub3(String condSub3) {
		this.condSub3 = condSub3;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getMainCondFreqType() {
		return mainCondFreqType;
	}

	public void setMainCondFreqType(String mainCondFreqType) {
		this.mainCondFreqType = mainCondFreqType;
	}

	public Integer getTaxMonthStart() {
		return taxMonthStart;
	}

	public void setTaxMonthStart(Integer taxMonthStart) {
		this.taxMonthStart = taxMonthStart;
	}

	public Integer getTaxMonthEnd() {
		return taxMonthEnd;
	}

	public void setTaxMonthEnd(Integer taxMonthEnd) {
		this.taxMonthEnd = taxMonthEnd;
	}

	public String getFlagPage() {
		return flagPage;
	}

	public void setFlagPage(String flagPage) {
		this.flagPage = flagPage;
	}

	public String getCondSubNoAuditFlag() {
		return condSubNoAuditFlag;
	}

	public void setCondSubNoAuditFlag(String condSubNoAuditFlag) {
		this.condSubNoAuditFlag = condSubNoAuditFlag;
	}

	public String getTaxAuditLast() {
		return taxAuditLast;
	}

	public void setTaxAuditLast(String taxAuditLast) {
		this.taxAuditLast = taxAuditLast;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWorksheetStatus() {
		return worksheetStatus;
	}

	public void setWorksheetStatus(String worksheetStatus) {
		this.worksheetStatus = worksheetStatus;
	}

	public String getNewRegFlag() {
		return newRegFlag;
	}

	public void setNewRegFlag(String newRegFlag) {
		this.newRegFlag = newRegFlag;
	}

	public String getCuscatId() {
		return cuscatId;
	}

	public void setCuscatId(String cuscatId) {
		this.cuscatId = cuscatId;
	}

	public String getPinnitId() {
		return pinnitId;
	}

	public void setPinnitId(String pinnitId) {
		this.pinnitId = pinnitId;
	}

	public List<String> getYearMonthList() {
		return yearMonthList;
	}

	public void setYearMonthList(List<String> yearMonthList) {
		this.yearMonthList = yearMonthList;
	}

	public String getSkipCond() {
		return skipCond;
	}

	public void setSkipCond(String skipCond) {
		this.skipCond = skipCond;
	}

	public List<String> getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(List<String> regStatus) {
		this.regStatus = regStatus;
	}

	public Date getRegDateStart() {
		return regDateStart;
	}

	public void setRegDateStart(Date regDateStart) {
		this.regDateStart = regDateStart;
	}

	public Date getRegDateEnd() {
		return regDateEnd;
	}

	public void setRegDateEnd(Date regDateEnd) {
		this.regDateEnd = regDateEnd;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	

}
