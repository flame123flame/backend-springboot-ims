package th.go.excise.ims.oa.vo;

import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsSummary;

public class Oa0201060401FromVo {
	
	private String name;
	private List<OaLubricantsSummary> listOaLubricantsSummary;
	
	public String getName() {
		return name;
	}
	public List<OaLubricantsSummary> getListOaLubricantsSummary() {
		return listOaLubricantsSummary;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setListOaLubricantsSummary(List<OaLubricantsSummary> listOaLubricantsSummary) {
		this.listOaLubricantsSummary = listOaLubricantsSummary;
	}
	
	

}
