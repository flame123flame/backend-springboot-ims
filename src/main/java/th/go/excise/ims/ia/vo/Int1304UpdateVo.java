package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int1304UpdateVo {

	private IaAuditPmQtHVo header;
	private List<IaAuditPmQtDVo> detail;

	public IaAuditPmQtHVo getHeader() {
		return header;
	}

	public void setHeader(IaAuditPmQtHVo header) {
		this.header = header;
	}

	public List<IaAuditPmQtDVo> getDetail() {
		return detail;
	}

	public void setDetail(List<IaAuditPmQtDVo> detail) {
		this.detail = detail;
	}

}
