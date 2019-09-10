package th.go.excise.ims.ta.vo;

import java.util.List;

public class TaFormTS0108Vo extends AbstractTaFormTsVo {

	private List<TaFormTS0108DtlVo> taFormTS0108DtlVoList;

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public List<TaFormTS0108DtlVo> getTaFormTS0108DtlVoList() {
		return taFormTS0108DtlVoList;
	}

	public void setTaFormTS0108DtlVoList(List<TaFormTS0108DtlVo> taFormTS0108DtlVoList) {
		this.taFormTS0108DtlVoList = taFormTS0108DtlVoList;
	}

}
