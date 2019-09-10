package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditPy1D;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1H;

public class Int1302Py1NoVo {
	private IaAuditPy1H iaAuditPy1H;
	private List<IaAuditPy1D> iaAuditPy1DList;
	private ExciseDepartmentVo exciseDepartmentVo;

	public IaAuditPy1H getIaAuditPy1H() {
		return iaAuditPy1H;
	}

	public void setIaAuditPy1H(IaAuditPy1H iaAuditPy1H) {
		this.iaAuditPy1H = iaAuditPy1H;
	}

	public List<IaAuditPy1D> getIaAuditPy1DList() {
		return iaAuditPy1DList;
	}

	public void setIaAuditPy1DList(List<IaAuditPy1D> iaAuditPy1DList) {
		this.iaAuditPy1DList = iaAuditPy1DList;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}
