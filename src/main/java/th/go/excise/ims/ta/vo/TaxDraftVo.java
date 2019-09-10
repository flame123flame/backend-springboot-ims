package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TaxDraftVo {

	private Long worksheetDtlId;
	private String newRegId;
	private String dutyCode;
	private String facType;
	private LocalDate regDate;
	private String regCapital;
	private String officeCode;
	private BigDecimal taxAmtChnPnt;
	private Integer taxMonthNo;
	private String lastAuditYear;
	
	public Long getWorksheetDtlId() {
		return worksheetDtlId;
	}
	public void setWorksheetDtlId(Long worksheetDtlId) {
		this.worksheetDtlId = worksheetDtlId;
	}
	public String getNewRegId() {
		return newRegId;
	}
	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public String getFacType() {
		return facType;
	}
	public void setFacType(String facType) {
		this.facType = facType;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public String getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public BigDecimal getTaxAmtChnPnt() {
		return taxAmtChnPnt;
	}
	public void setTaxAmtChnPnt(BigDecimal taxAmtChnPnt) {
		this.taxAmtChnPnt = taxAmtChnPnt;
	}
	public Integer getTaxMonthNo() {
		return taxMonthNo;
	}
	public void setTaxMonthNo(Integer taxMonthNo) {
		this.taxMonthNo = taxMonthNo;
	}
	public String getLastAuditYear() {
		return lastAuditYear;
	}
	public void setLastAuditYear(String lastAuditYear) {
		this.lastAuditYear = lastAuditYear;
	}

}
