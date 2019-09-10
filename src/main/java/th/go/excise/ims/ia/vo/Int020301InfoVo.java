package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Int020301InfoVo {
	BigDecimal idMadeHdr;
	String officeCode;
	String sectorName;
	String areaName;
	String status;
	String statusText;
	Date sentDate;
	String sentBy;
	BigDecimal riskQuantity;
	BigDecimal passValue;
	BigDecimal failValue;
	BigDecimal avgRisk;
	String riskText;
	String riskColor;
	List<Int020301DataVo> sideDtls;
	BigDecimal riskNum;

	IntCalculateCriteriaVo intCalculateCriteriaVo;
	
	ExciseDepartmentVo exciseDepartmentVo;

	public BigDecimal getIdMadeHdr() {
		return idMadeHdr;
	}

	public void setIdMadeHdr(BigDecimal idMadeHdr) {
		this.idMadeHdr = idMadeHdr;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public BigDecimal getRiskQuantity() {
		return riskQuantity;
	}

	public void setRiskQuantity(BigDecimal riskQuantity) {
		this.riskQuantity = riskQuantity;
	}

	public BigDecimal getPassValue() {
		return passValue;
	}

	public void setPassValue(BigDecimal passValue) {
		this.passValue = passValue;
	}

	public BigDecimal getFailValue() {
		return failValue;
	}

	public void setFailValue(BigDecimal failValue) {
		this.failValue = failValue;
	}

	public BigDecimal getAvgRisk() {
		return avgRisk;
	}

	public void setAvgRisk(BigDecimal avgRisk) {
		this.avgRisk = avgRisk;
	}

	public String getRiskText() {
		return riskText;
	}

	public void setRiskText(String riskText) {
		this.riskText = riskText;
	}

	public String getRiskColor() {
		return riskColor;
	}

	public void setRiskColor(String riskColor) {
		this.riskColor = riskColor;
	}

	public List<Int020301DataVo> getSideDtls() {
		return sideDtls;
	}

	public void setSideDtls(List<Int020301DataVo> sideDtls) {
		this.sideDtls = sideDtls;
	}

	public BigDecimal getRiskNum() {
		return riskNum;
	}

	public void setRiskNum(BigDecimal riskNum) {
		this.riskNum = riskNum;
	}

	public IntCalculateCriteriaVo getIntCalculateCriteriaVo() {
		return intCalculateCriteriaVo;
	}

	public void setIntCalculateCriteriaVo(IntCalculateCriteriaVo intCalculateCriteriaVo) {
		this.intCalculateCriteriaVo = intCalculateCriteriaVo;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}
	
	

}
