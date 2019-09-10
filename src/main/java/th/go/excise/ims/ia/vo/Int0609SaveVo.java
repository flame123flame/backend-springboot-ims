package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendH;

public class Int0609SaveVo {
	private IaAuditIncSendH header;
	private List<IaAuditIncSendDVo> details;

	public IaAuditIncSendH getHeader() {
		return header;
	}

	public void setHeader(IaAuditIncSendH header) {
		this.header = header;
	}

	public List<IaAuditIncSendDVo> getDetails() {
		return details;
	}

	public void setDetails(List<IaAuditIncSendDVo> details) {
		this.details = details;
	}

}