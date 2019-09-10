package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;

public class Int02010101FormVo {
	private List<IaQuestionnaireSideDtl> save;
	private List<IaQuestionnaireSideDtl> delete;
	private BigDecimal idHead;

	public List<IaQuestionnaireSideDtl> getSave() {
		return save;
	}

	public void setSave(List<IaQuestionnaireSideDtl> save) {
		this.save = save;
	}

	public List<IaQuestionnaireSideDtl> getDelete() {
		return delete;
	}

	public void setDelete(List<IaQuestionnaireSideDtl> delete) {
		this.delete = delete;
	}

	public BigDecimal getIdHead() {
		return idHead;
	}

	public void setIdHead(BigDecimal idHead) {
		this.idHead = idHead;
	}
	
}
