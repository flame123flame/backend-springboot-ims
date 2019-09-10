package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaAlcoholLabel;

public class Oa04010608FormVo {
	private List<OaAlcoholLabel> save;
	private List<BigDecimal> delete;

	public List<OaAlcoholLabel> getSave() {
		return save;
	}

	public void setSave(List<OaAlcoholLabel> save) {
		this.save = save;
	}

	public List<BigDecimal> getDelete() {
		return delete;
	}

	public void setDelete(List<BigDecimal> delete) {
		this.delete = delete;
	}
}
