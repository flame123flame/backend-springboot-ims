package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaAlcoholDistil;

public class Oa04010606FormVo {
	private List<OaAlcoholDistil> save;
	private List<BigDecimal> delete;

	public List<OaAlcoholDistil> getSave() {
		return save;
	}

	public void setSave(List<OaAlcoholDistil> save) {
		this.save = save;
	}

	public List<BigDecimal> getDelete() {
		return delete;
	}

	public void setDelete(List<BigDecimal> delete) {
		this.delete = delete;
	}
}
