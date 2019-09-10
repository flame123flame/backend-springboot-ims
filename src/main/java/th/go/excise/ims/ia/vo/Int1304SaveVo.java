package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;

public class Int1304SaveVo {

	private List<WsPmQtHVo> pmQtData;
	private IaAuditPmqtH formHeader;

	public List<WsPmQtHVo> getPmQtData() {
		return pmQtData;
	}

	public void setPmQtData(List<WsPmQtHVo> pmQtData) {
		this.pmQtData = pmQtData;
	}

	public IaAuditPmqtH getFormHeader() {
		return formHeader;
	}

	public void setFormHeader(IaAuditPmqtH formHeader) {
		this.formHeader = formHeader;
	}

}
