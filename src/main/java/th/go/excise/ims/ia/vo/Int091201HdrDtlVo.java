package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;

public class Int091201HdrDtlVo {
	private IaAuditWorkingH iaAuditWorkingH;
	private List<IaAuditWorkingD1> IaAuditWorkingD1List;
	private ExciseDepartmentVo exciseDepartmentVo;

	public IaAuditWorkingH getIaAuditWorkingH() {
		return iaAuditWorkingH;
	}

	public void setIaAuditWorkingH(IaAuditWorkingH iaAuditWorkingH) {
		this.iaAuditWorkingH = iaAuditWorkingH;
	}

	public List<IaAuditWorkingD1> getIaAuditWorkingD1List() {
		return IaAuditWorkingD1List;
	}

	public void setIaAuditWorkingD1List(List<IaAuditWorkingD1> iaAuditWorkingD1List) {
		IaAuditWorkingD1List = iaAuditWorkingD1List;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}
