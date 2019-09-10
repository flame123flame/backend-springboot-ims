package th.go.excise.ims.ta.vo;

import java.util.Date;
import java.util.List;

public class TaFormTS01141Vo extends AbstractTaFormTsVo {

	private String pageNo;
	private Date docDate;
	private String docDear;
	private String factoryName;
	private String factoryTypeText;
	private String newRegId;
	private Date auditDateStart;
	private Date auditDateEnd;
	private String auditDesc;
	private List<TaFormTS01141Vo> taFormTS01141VoList;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocDear() {
		return docDear;
	}

	public void setDocDear(String docDear) {
		this.docDear = docDear;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
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

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public List<TaFormTS01141Vo> getTaFormTS01141VoList() {
		return taFormTS01141VoList;
	}

	public void setTaFormTS01141VoList(List<TaFormTS01141Vo> taFormTS01141VoList) {
		this.taFormTS01141VoList = taFormTS01141VoList;
	}

}
