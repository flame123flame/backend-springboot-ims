package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaAlcoholPacking;

public class Oa04010607FormVo {
	private List<OaAlcoholPacking> save;
	private List<BigDecimal> delete;

	public List<OaAlcoholPacking> getSave() {
		return save;
	}

	public void setSave(List<OaAlcoholPacking> save) {
		this.save = save;
	}

	public List<BigDecimal> getDelete() {
		return delete;
	}

	public void setDelete(List<BigDecimal> delete) {
		this.delete = delete;
	}
}
