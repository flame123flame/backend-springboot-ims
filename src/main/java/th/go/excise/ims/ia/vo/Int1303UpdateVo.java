package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int1303UpdateVo {
	private IaAuditPy2HVo headers;
	private List<IaAuditPy2DVo> details;
	private ExciseDepartmentVo exciseDepartmentVo;

	public IaAuditPy2HVo getHeaders() {
		return headers;
	}

	public void setHeaders(IaAuditPy2HVo headers) {
		this.headers = headers;
	}

	public List<IaAuditPy2DVo> getDetails() {
		return details;
	}

	public void setDetails(List<IaAuditPy2DVo> details) {
		this.details = details;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}
