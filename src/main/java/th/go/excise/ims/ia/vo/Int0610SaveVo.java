package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfd;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfh;

public class Int0610SaveVo {
	private IaAuditIncGfh header;
	private List<IaAuditIncGfd> details;

	public IaAuditIncGfh getHeader() {
		return header;
	}

	public void setHeader(IaAuditIncGfh header) {
		this.header = header;
	}

	public List<IaAuditIncGfd> getDetails() {
		return details;
	}

	public void setDetails(List<IaAuditIncGfd> details) {
		this.details = details;
	}

}
