package th.go.excise.ims.ta.vo;

import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;

public class MasCondSubCapitalVo {
	
	private TaMasCondSubCapital capital;
	private String capitalDesc;
	
	public TaMasCondSubCapital getCapital() {
		return capital;
	}
	public void setCapital(TaMasCondSubCapital capital) {
		this.capital = capital;
	}
	public String getCapitalDesc() {
		return capitalDesc;
	}
	public void setCapitalDesc(String capitalDesc) {
		this.capitalDesc = capitalDesc;
	}
}
