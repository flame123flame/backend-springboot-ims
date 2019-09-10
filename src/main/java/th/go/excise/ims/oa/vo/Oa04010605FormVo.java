package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaAlcoholFerment;

public class Oa04010605FormVo {
	private List<OaAlcoholFerment> save;
	private List<BigDecimal> delete;

	public List<OaAlcoholFerment> getSave() {
		return save;
	}

	public void setSave(List<OaAlcoholFerment> save) {
		this.save = save;
	}

	public List<BigDecimal> getDelete() {
		return delete;
	}

	public void setDelete(List<BigDecimal> delete) {
		this.delete = delete;
	}
}
