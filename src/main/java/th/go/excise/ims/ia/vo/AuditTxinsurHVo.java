package th.go.excise.ims.ia.vo;

import java.util.Date;

public class AuditTxinsurHVo {
	/* entity */
	private Long iaAuditTxinsurHId;
	private String auditTxinsurNo;
	private String officeCode;
	private Date registDateStart;
	private Date registDateEnd;
	private Date auditDate;
	private String txinsurAuditFlag;
	private String txinsurConditionText;
	private String txinsurCriteriaText;

	/* custom */
	private String registDateStartStr;
	private String registDateEndStr;
	private String auditDateStr;
	private ExciseDepartmentVo exciseDepartmentVo;

	public Long getIaAuditTxinsurHId() {
		return iaAuditTxinsurHId;
	}

	public void setIaAuditTxinsurHId(Long iaAuditTxinsurHId) {
		this.iaAuditTxinsurHId = iaAuditTxinsurHId;
	}

	public String getAuditTxinsurNo() {
		return auditTxinsurNo;
	}

	public void setAuditTxinsurNo(String auditTxinsurNo) {
		this.auditTxinsurNo = auditTxinsurNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public Date getRegistDateStart() {
		return registDateStart;
	}

	public void setRegistDateStart(Date registDateStart) {
		this.registDateStart = registDateStart;
	}

	public Date getRegistDateEnd() {
		return registDateEnd;
	}

	public void setRegistDateEnd(Date registDateEnd) {
		this.registDateEnd = registDateEnd;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getTxinsurAuditFlag() {
		return txinsurAuditFlag;
	}

	public void setTxinsurAuditFlag(String txinsurAuditFlag) {
		this.txinsurAuditFlag = txinsurAuditFlag;
	}

	public String getTxinsurConditionText() {
		return txinsurConditionText;
	}

	public void setTxinsurConditionText(String txinsurConditionText) {
		this.txinsurConditionText = txinsurConditionText;
	}

	public String getTxinsurCriteriaText() {
		return txinsurCriteriaText;
	}

	public void setTxinsurCriteriaText(String txinsurCriteriaText) {
		this.txinsurCriteriaText = txinsurCriteriaText;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

	public String getRegistDateStartStr() {
		return registDateStartStr;
	}

	public void setRegistDateStartStr(String registDateStartStr) {
		this.registDateStartStr = registDateStartStr;
	}

	public String getRegistDateEndStr() {
		return registDateEndStr;
	}

	public void setRegistDateEndStr(String registDateEndStr) {
		this.registDateEndStr = registDateEndStr;
	}

	public String getAuditDateStr() {
		return auditDateStr;
	}

	public void setAuditDateStr(String auditDateStr) {
		this.auditDateStr = auditDateStr;
	}

}
