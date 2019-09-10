package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;

public class Int020201DtlVo {
	
	List<IaQuestionnaireMade> qtnMadeList = null;
	private String status;
	private BigDecimal idMadeHdr;
	private Boolean flagConfirm = false;
	
	public List<IaQuestionnaireMade> getQtnMadeList() {
		return qtnMadeList;
	}
	public void setQtnMadeList(List<IaQuestionnaireMade> qtnMadeList) {
		this.qtnMadeList = qtnMadeList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getIdMadeHdr() {
		return idMadeHdr;
	}
	public void setIdMadeHdr(BigDecimal idMadeHdr) {
		this.idMadeHdr = idMadeHdr;
	}
	public Boolean getFlagConfirm() {
		return flagConfirm;
	}
	public void setFlagConfirm(Boolean flagConfirm) {
		this.flagConfirm = flagConfirm;
	}
	
}
