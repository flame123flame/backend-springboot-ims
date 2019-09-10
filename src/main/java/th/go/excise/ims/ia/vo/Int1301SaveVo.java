package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessH;

public class Int1301SaveVo {
	private List<WsPmAssessHVo> pmAssessData;
	private IaAuditPmassessH formHeader;

	public List<WsPmAssessHVo> getPmAssessData() {
		return pmAssessData;
	}

	public void setPmAssessData(List<WsPmAssessHVo> pmAssessData) {
		this.pmAssessData = pmAssessData;
	}

	public IaAuditPmassessH getFormHeader() {
		return formHeader;
	}

	public void setFormHeader(IaAuditPmassessH formHeader) {
		this.formHeader = formHeader;
	}

}
