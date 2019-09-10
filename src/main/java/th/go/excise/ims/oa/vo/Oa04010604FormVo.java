package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaAlcoholMaterial;

public class Oa04010604FormVo {
	private List<OaAlcoholMaterial> save;
	private List<BigDecimal> delete;

	public List<OaAlcoholMaterial> getSave() {
		return save;
	}

	public void setSave(List<OaAlcoholMaterial> save) {
		this.save = save;
	}

	public List<BigDecimal> getDelete() {
		return delete;
	}

	public void setDelete(List<BigDecimal> delete) {
		this.delete = delete;
	}
}
