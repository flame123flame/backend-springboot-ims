package th.go.excise.ims.ws.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class WsRegfri4000Vo extends DataTableRequest {

	private static final long serialVersionUID = 3466765526713547321L;

	private String newRegId;
	private String cusId;
	private String cusFullname;
	private String cusAddress;
	private String cusTelno;
	private String cusEmail;
	private String cusUrl;
	private String facId;
	private String facFullname;
	private String facAddress;
	private String facTelno;
	private String facEmail;
	private String facUrl;
	private String facType;
	private String regId;
	private String regStatus;
	private LocalDate regDate;
	private BigDecimal regCapital;
	private String officeCode;
	private String activeFlag;
	private LocalDateTime syncDate;
	private String dutyGroupId;
	private String condMainGrp;

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusTelno() {
		return cusTelno;
	}

	public void setCusTelno(String cusTelno) {
		this.cusTelno = cusTelno;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusUrl() {
		return cusUrl;
	}

	public void setCusUrl(String cusUrl) {
		this.cusUrl = cusUrl;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getFacAddress() {
		return facAddress;
	}

	public void setFacAddress(String facAddress) {
		this.facAddress = facAddress;
	}

	public String getFacTelno() {
		return facTelno;
	}

	public void setFacTelno(String facTelno) {
		this.facTelno = facTelno;
	}

	public String getFacEmail() {
		return facEmail;
	}

	public void setFacEmail(String facEmail) {
		this.facEmail = facEmail;
	}

	public String getFacUrl() {
		return facUrl;
	}

	public void setFacUrl(String facUrl) {
		this.facUrl = facUrl;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public BigDecimal getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public LocalDateTime getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(LocalDateTime syncDate) {
		this.syncDate = syncDate;
	}

	public String getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public String getCondMainGrp() {
		return condMainGrp;
	}

	public void setCondMainGrp(String condMainGrp) {
		this.condMainGrp = condMainGrp;
	}

}
