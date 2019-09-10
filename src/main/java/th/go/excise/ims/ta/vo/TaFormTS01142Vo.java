package th.go.excise.ims.ta.vo;

import java.util.Date;
import java.util.List;

public class TaFormTS01142Vo extends AbstractTaFormTsVo {

	private String ownerFullName;
	private String factoryType;
	private String factoryName;
	private Date auditDateStart;
	private Date auditDateEnd;
	private String dutyTypeText;
	private String newRegId;
	private List<TaFormTS01142DtlVo> taFormTS01142DtlVoList;
	private Date extraAmtDate;
	private String signOwnerFullName;
	private String signOfficerFullName;

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

	public String getDutyTypeText() {
		return dutyTypeText;
	}

	public void setDutyTypeText(String dutyTypeText) {
		this.dutyTypeText = dutyTypeText;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public List<TaFormTS01142DtlVo> getTaFormTS01142DtlVoList() {
		return taFormTS01142DtlVoList;
	}

	public void setTaFormTS01142DtlVoList(List<TaFormTS01142DtlVo> taFormTS01142DtlVoList) {
		this.taFormTS01142DtlVoList = taFormTS01142DtlVoList;
	}

	public Date getExtraAmtDate() {
		return extraAmtDate;
	}

	public void setExtraAmtDate(Date extraAmtDate) {
		this.extraAmtDate = extraAmtDate;
	}

	public String getSignOwnerFullName() {
		return signOwnerFullName;
	}

	public void setSignOwnerFullName(String signOwnerFullName) {
		this.signOwnerFullName = signOwnerFullName;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
	}

}
