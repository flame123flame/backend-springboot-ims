package th.go.excise.ims.ws.client.pcc.inquiryincmast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import th.go.excise.ims.ws.client.pcc.common.model.BaseModel;

public class IncomeMaster extends BaseModel {

	@SerializedName("ACCSEND_CODE")
	@Expose
	private String accsendCode;

	@SerializedName("ACC_CODE")
	@Expose
	private String accCode;

	@SerializedName("CD_FLAG")
	@Expose
	private String cdFlag;

	@SerializedName("FORM_CODE")
	@Expose
	private String formCode;

	@SerializedName("GROUP_ID")
	@Expose
	private String groupId;

	@SerializedName("INCCOD_CD")
	@Expose
	private String inccodCd;

	@SerializedName("INCCOD_EXP")
	@Expose
	private String inccodExp;

	@SerializedName("INCCOD_FLAG")
	@Expose
	private String inccodFlag;

	@SerializedName("INCCOD_OTH")
	@Expose
	private String inccodOth;

	@SerializedName("INCCOD_PRNSTAMP")
	@Expose
	private String inccodPrnstamp;

	@SerializedName("INCGRP_CODE")
	@Expose
	private String incgrpCode;

	@SerializedName("INCGRP_FLAG")
	@Expose
	private String incgrpFlag;

	@SerializedName("INC_CODE")
	@Expose
	private String incCode;

	@SerializedName("INC_FLAG")
	@Expose
	private String incFlag;

	@SerializedName("INC_NAME")
	@Expose
	private String incName;

	@SerializedName("INC_NAME_PRINT")
	@Expose
	private String incNamePrint;

	@SerializedName("INC_TYPE")
	@Expose
	private String incType;

	@SerializedName("LOCTYP_FLAG")
	@Expose
	private String loctypFlag;

	@SerializedName("LOC_FLAG")
	@Expose
	private String locFlag;

	@SerializedName("MONEY_TYPE")
	@Expose
	private String moneyType;

	@SerializedName("PRNSTAMP_FLAG")
	@Expose
	private String prnstampFlag;

	@SerializedName("REC_FLAG")
	@Expose
	private String recFlag;

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

	public String getIncCode() {
		return incCode;
	}

	public void setIncCode(String incCode) {
		this.incCode = incCode;
	}

	public String getIncFlag() {
		return incFlag;
	}

	public void setIncFlag(String incFlag) {
		this.incFlag = incFlag;
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

	public String getIncType() {
		return incType;
	}

	public void setIncType(String incType) {
		this.incType = incType;
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

}
