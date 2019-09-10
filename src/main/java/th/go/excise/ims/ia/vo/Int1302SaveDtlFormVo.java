package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditPy1D;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1H;

public class Int1302SaveDtlFormVo extends IaAuditPy1H {
	private static final long serialVersionUID = -5940677892510872208L;
	private List<IaAuditPy1D> iaAuditPy1DList;

	public List<IaAuditPy1D> getIaAuditPy1DList() {
		return iaAuditPy1DList;
	}

	public void setIaAuditPy1DList(List<IaAuditPy1D> iaAuditPy1DList) {
		this.iaAuditPy1DList = iaAuditPy1DList;
	}

}


