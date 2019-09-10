package th.go.excise.ims.ws.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "WS_REGFRI4000")
public class WsRegfri4000 extends BaseEntity {

	private static final long serialVersionUID = -7048262812422174478L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_REGFRI4000_GEN")
	@SequenceGenerator(name = "WS_REGFRI4000_GEN", sequenceName = "WS_REGFRI4000_SEQ", allocationSize = 1)
	@Column(name = "REGFRI4000_ID")
	private Long regfri4000Id;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "REG_STATUS")
	private String regStatus;
	@Column(name = "REG_STATUS_DESC")
	private String regStatusDesc;
	@Column(name = "REG_STATUS_DATE")
	private LocalDate regStatusDate;
	@Column(name = "CUS_ID")
	private String cusId;
	@Column(name = "CUS_FULLNAME")
	private String cusFullname;
	@Column(name = "CUS_ADDRESS")
	private String cusAddress;
	@Column(name = "CUS_TELNO")
	private String cusTelno;
	@Column(name = "CUS_EMAIL")
	private String cusEmail;
	@Column(name = "CUS_URL")
	private String cusUrl;
	@Column(name = "FAC_ID")
	private String facId;
	@Column(name = "FAC_FULLNAME")
	private String facFullname;
	@Column(name = "FAC_ADDRESS")
	private String facAddress;
	@Column(name = "FAC_TELNO")
	private String facTelno;
	@Column(name = "FAC_EMAIL")
	private String facEmail;
	@Column(name = "FAC_URL")
	private String facUrl;
	@Column(name = "FAC_TYPE")
	private String facType;
	@Column(name = "REG_CAPITAL")
	private BigDecimal regCapital;
	@Column(name = "REG_DATE")
	private LocalDate regDate;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "ACTIVE_FLAG")
	private String activeFlag;
	@Column(name = "SYNC_DATE")
	private LocalDateTime syncDate;

	public Long getRegfri4000Id() {
		return regfri4000Id;
	}

	public void setRegfri4000Id(Long regfri4000Id) {
		this.regfri4000Id = regfri4000Id;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
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

	public String getRegStatusDesc() {
		return regStatusDesc;
	}

	public void setRegStatusDesc(String regStatusDesc) {
		this.regStatusDesc = regStatusDesc;
	}

	public LocalDate getRegStatusDate() {
		return regStatusDate;
	}

	public void setRegStatusDate(LocalDate regStatusDate) {
		this.regStatusDate = regStatusDate;
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

	public BigDecimal getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
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

}
