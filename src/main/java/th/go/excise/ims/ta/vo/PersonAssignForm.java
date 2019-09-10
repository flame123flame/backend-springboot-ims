package th.go.excise.ims.ta.vo;

import java.util.List;

public class PersonAssignForm {
	private Long edPersonSeq;
	private String edLogin;
	private String edPersonName;
	private Long edPositionSeq;
	private String edPositionName;
	private String edOffcode;
	private String edPersonId;
	private String auSubdeptCode;
	private String auSubdeptLevel;
	private String auJobResp;
	private String auditStatus;
	private List<PlanWorksheetDatatableVo> listCompany;
	
	public Long getEdPersonSeq() {
		return edPersonSeq;
	}
	public void setEdPersonSeq(Long edPersonSeq) {
		this.edPersonSeq = edPersonSeq;
	}
	public String getEdLogin() {
		return edLogin;
	}
	public void setEdLogin(String edLogin) {
		this.edLogin = edLogin;
	}
	public String getEdPersonName() {
		return edPersonName;
	}
	public void setEdPersonName(String edPersonName) {
		this.edPersonName = edPersonName;
	}
	public Long getEdPositionSeq() {
		return edPositionSeq;
	}
	public void setEdPositionSeq(Long edPositionSeq) {
		this.edPositionSeq = edPositionSeq;
	}
	public String getEdPositionName() {
		return edPositionName;
	}
	public void setEdPositionName(String edPositionName) {
		this.edPositionName = edPositionName;
	}
	public String getEdOffcode() {
		return edOffcode;
	}
	public void setEdOffcode(String edOffcode) {
		this.edOffcode = edOffcode;
	}
	public String getEdPersonId() {
		return edPersonId;
	}
	public void setEdPersonId(String edPersonId) {
		this.edPersonId = edPersonId;
	}
	public String getAuSubdeptCode() {
		return auSubdeptCode;
	}
	public void setAuSubdeptCode(String auSubdeptCode) {
		this.auSubdeptCode = auSubdeptCode;
	}
	public String getAuSubdeptLevel() {
		return auSubdeptLevel;
	}
	public void setAuSubdeptLevel(String auSubdeptLevel) {
		this.auSubdeptLevel = auSubdeptLevel;
	}
	public List<PlanWorksheetDatatableVo> getListCompany() {
		return listCompany;
	}
	public void setListCompany(List<PlanWorksheetDatatableVo> listCompany) {
		this.listCompany = listCompany;
	}
	public String getAuJobResp() {
		return auJobResp;
	}
	public void setAuJobResp(String auJobResp) {
		this.auJobResp = auJobResp;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
