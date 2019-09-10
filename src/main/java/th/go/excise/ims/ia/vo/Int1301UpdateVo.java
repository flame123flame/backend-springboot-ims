package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int1301UpdateVo {
	private IaAuditPmassessHVo header;
	private List<IaAuditPmassessDVo> detail;

	public IaAuditPmassessHVo getHeader() {
		return header;
	}

	public void setHeader(IaAuditPmassessHVo header) {
		this.header = header;
	}

	public List<IaAuditPmassessDVo> getDetail() {
		return detail;
	}

	public void setDetail(List<IaAuditPmassessDVo> detail) {
		this.detail = detail;
	}

}
