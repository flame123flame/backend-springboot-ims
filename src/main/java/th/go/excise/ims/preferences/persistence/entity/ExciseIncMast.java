package th.go.excise.ims.preferences.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_INC_MAST")
public class ExciseIncMast extends BaseEntity {

	private static final long serialVersionUID = 6621331168565652149L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_INC_MAST_GEN")
	@SequenceGenerator(name = "EXCISE_INC_MAST_GEN", sequenceName = "EXCISE_INC_MAST_SEQ", allocationSize = 1)
	@Column(name = "INC_MAST_ID")
	private Long incMastId;
	@Column(name = "INC_CODE")
	private String incCode;
	@Column(name = "INC_TYPE")
	private String incType;
	@Column(name = "INC_NAME")
	private String incName;
	@Column(name = "INC_NAME_PRINT")
	private String incNamePrint;
	@Column(name = "INC_FLAG")
	private String incFlag;
	@Column(name = "ACCSEND_CODE")
	private String accsendCode;
	@Column(name = "ACC_CODE")
	private String accCode;
	@Column(name = "CD_FLAG")
	private String cdFlag;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "GROUP_ID")
	private String groupId;
	@Column(name = "INCCOD_CD")
	private String inccodCd;
	@Column(name = "INCCOD_EXP")
	private String inccodExp;
	@Column(name = "INCCOD_FLAG")
	private String inccodFlag;
	@Column(name = "INCCOD_OTH")
	private String inccodOth;
	@Column(name = "INCCOD_PRNSTAMP")
	private String inccodPrnstamp;
	@Column(name = "INCGRP_CODE")
	private String incgrpCode;
	@Column(name = "INCGRP_FLAG")
	private String incgrpFlag;
	@Column(name = "LOCTYP_FLAG")
	private String loctypFlag;
	@Column(name = "LOC_FLAG")
	private String locFlag;
	@Column(name = "MONEY_TYPE")
	private String moneyType;
	@Column(name = "PRNSTAMP_FLAG")
	private String prnstampFlag;
	@Column(name = "REC_FLAG")
	private String recFlag;
	@Column(name = "BEGIN_DATE")
	private LocalDate beginDate;

	public Long getIncMastId() {
		return incMastId;
	}

	public void setIncMastId(Long incMastId) {
		this.incMastId = incMastId;
	}

	public String getIncCode() {
		return incCode;
	}

	public void setIncCode(String incCode) {
		this.incCode = incCode;
	}

	public String getIncType() {
		return incType;
	}

	public void setIncType(String incType) {
		this.incType = incType;
	}

	public String getIncName() {
		return incName;
	}

	public void setIncName(String incName) {
		this.incName = incName;
	}

	public String getIncNamePrint() {
		return incNamePrint;
	}

	public void setIncNamePrint(String incNamePrint) {
		this.incNamePrint = incNamePrint;
	}

	public String getIncFlag() {
		return incFlag;
	}

	public void setIncFlag(String incFlag) {
		this.incFlag = incFlag;
	}

	public String getAccsendCode() {
		return accsendCode;
	}

	public void setAccsendCode(String accsendCode) {
		this.accsendCode = accsendCode;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getCdFlag() {
		return cdFlag;
	}

	public void setCdFlag(String cdFlag) {
		this.cdFlag = cdFlag;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getInccodCd() {
		return inccodCd;
	}

	public void setInccodCd(String inccodCd) {
		this.inccodCd = inccodCd;
	}

	public String getInccodExp() {
		return inccodExp;
	}

	public void setInccodExp(String inccodExp) {
		this.inccodExp = inccodExp;
	}

	public String getInccodFlag() {
		return inccodFlag;
	}

	public void setInccodFlag(String inccodFlag) {
		this.inccodFlag = inccodFlag;
	}

	public String getInccodOth() {
		return inccodOth;
	}

	public void setInccodOth(String inccodOth) {
		this.inccodOth = inccodOth;
	}

	public String getInccodPrnstamp() {
		return inccodPrnstamp;
	}

	public void setInccodPrnstamp(String inccodPrnstamp) {
		this.inccodPrnstamp = inccodPrnstamp;
	}

	public String getIncgrpCode() {
		return incgrpCode;
	}

	public void setIncgrpCode(String incgrpCode) {
		this.incgrpCode = incgrpCode;
	}

	public String getIncgrpFlag() {
		return incgrpFlag;
	}

	public void setIncgrpFlag(String incgrpFlag) {
		this.incgrpFlag = incgrpFlag;
	}

	public String getLoctypFlag() {
		return loctypFlag;
	}

	public void setLoctypFlag(String loctypFlag) {
		this.loctypFlag = loctypFlag;
	}

	public String getLocFlag() {
		return locFlag;
	}

	public void setLocFlag(String locFlag) {
		this.locFlag = locFlag;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getPrnstampFlag() {
		return prnstampFlag;
	}

	public void setPrnstampFlag(String prnstampFlag) {
		this.prnstampFlag = prnstampFlag;
	}

	public String getRecFlag() {
		return recFlag;
	}

	public void setRecFlag(String recFlag) {
		this.recFlag = recFlag;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

}
