package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;


import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

public class IaAuditIncD3DatatableDtlVo {
	private List<WsIncfri8020Inc> wsIncfri8020Inc;
	private BigDecimal sumAmt;
	
	
	public List<WsIncfri8020Inc> getWsIncfri8020Inc() {
		return wsIncfri8020Inc;
	}
	public void setWsIncfri8020Inc(List<WsIncfri8020Inc> wsIncfri8020Inc) {
		this.wsIncfri8020Inc = wsIncfri8020Inc;
	}
	public BigDecimal getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

	
}
