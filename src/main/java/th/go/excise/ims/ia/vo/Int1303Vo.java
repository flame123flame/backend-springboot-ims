package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmPy2DVo;

public class Int1303Vo {
	private WsPmPy2HVo headers;
	private List<WsPmPy2DVo> details;
	private ExciseDepartmentVo exciseDepartmentVo;

	public WsPmPy2HVo getHeaders() {
		return headers;
	}

	public void setHeaders(WsPmPy2HVo headers) {
		this.headers = headers;
	}

	public List<WsPmPy2DVo> getDetails() {
		return details;
	}

	public void setDetails(List<WsPmPy2DVo> details) {
		this.details = details;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}
