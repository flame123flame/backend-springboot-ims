package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TaFormTS0118Vo extends AbstractTaFormTsVo {

	private String bookNumber1;
	private String bookNumber2;
	private Date docDate;
	private String ownerFullName;
	private String factoryType;
	private String factoryName;
	private String newRegId;
	private String factoryAddress;
	private String companyAddress;
	private String lawSection;
	private String lawGroup;
	private Date auditDateStart;
	private Date auditDateEnd;
	private BigDecimal sumAllTaxAmt;
	private String sumAllTaxText;
	private String officeName;
	private String tableHeaderDutyType;
	private String tableHeaderUnit;
	private List<TaFormTS0118DtlVo> taFormTS0118DtlVoList;
	private String reasonText;
	private String signOfficerFullName1;
	private Date signOfficerDate1;
	private String signOfficerFullName2;
	private Date signOfficerDate2;
	private Date extraMoneyDate;

	public String getBookNumber1() {
		return bookNumber1;
	}

	public void setBookNumber1(String bookNumber1) {
		this.bookNumber1 = bookNumber1;
	}

	public String getBookNumber2() {
		return bookNumber2;
	}

	public void setBookNumber2(String bookNumber2) {
		this.bookNumber2 = bookNumber2;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLawSection() {
		return lawSection;
	}

	public void setLawSection(String lawSection) {
		this.lawSection = lawSection;
	}

	public String getLawGroup() {
		return lawGroup;
	}

	public void setLawGroup(String lawGroup) {
		this.lawGroup = lawGroup;
	}

	public Date getAuditDateStart() {
		return auditDateStart;
	}

	public void setAuditDateStart(Date auditDateStart) {
		this.auditDateStart = auditDateStart;
	}

	public Date getAuditDateEnd() {
		return auditDateEnd;
	}

	public void setAuditDateEnd(Date auditDateEnd) {
		this.auditDateEnd = auditDateEnd;
	}

	public BigDecimal getSumAllTaxAmt() {
		return sumAllTaxAmt;
	}

	public void setSumAllTaxAmt(BigDecimal sumAllTaxAmt) {
		this.sumAllTaxAmt = sumAllTaxAmt;
	}

	public String getSumAllTaxText() {
		return sumAllTaxText;
	}

	public void setSumAllTaxText(String sumAllTaxText) {
		this.sumAllTaxText = sumAllTaxText;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getTableHeaderDutyType() {
		return tableHeaderDutyType;
	}

	public void setTableHeaderDutyType(String tableHeaderDutyType) {
		this.tableHeaderDutyType = tableHeaderDutyType;
	}

	public String getTableHeaderUnit() {
		return tableHeaderUnit;
	}

	public void setTableHeaderUnit(String tableHeaderUnit) {
		this.tableHeaderUnit = tableHeaderUnit;
	}

	public List<TaFormTS0118DtlVo> getTaFormTS0118DtlVoList() {
		return taFormTS0118DtlVoList;
	}

	public void setTaFormTS0118DtlVoList(List<TaFormTS0118DtlVo> taFormTS0118DtlVoList) {
		this.taFormTS0118DtlVoList = taFormTS0118DtlVoList;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public String getSignOfficerFullName1() {
		return signOfficerFullName1;
	}

	public void setSignOfficerFullName1(String signOfficerFullName1) {
		this.signOfficerFullName1 = signOfficerFullName1;
	}

	public Date getSignOfficerDate1() {
		return signOfficerDate1;
	}

	public void setSignOfficerDate1(Date signOfficerDate1) {
		this.signOfficerDate1 = signOfficerDate1;
	}

	public String getSignOfficerFullName2() {
		return signOfficerFullName2;
	}

	public void setSignOfficerFullName2(String signOfficerFullName2) {
		this.signOfficerFullName2 = signOfficerFullName2;
	}

	public Date getSignOfficerDate2() {
		return signOfficerDate2;
	}

	public void setSignOfficerDate2(Date signOfficerDate2) {
		this.signOfficerDate2 = signOfficerDate2;
	}

	public Date getExtraMoneyDate() {
		return extraMoneyDate;
	}

	public void setExtraMoneyDate(Date extraMoneyDate) {
		this.extraMoneyDate = extraMoneyDate;
	}

}
