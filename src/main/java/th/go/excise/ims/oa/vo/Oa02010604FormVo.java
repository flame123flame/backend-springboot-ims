package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsCompare;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsSummary;

public class Oa02010604FormVo {
	
	private BigDecimal oaLubricantsDtlId;
	private BigDecimal oaLubricantsId;
	private List<OaLubricantsCompare> listLubricantsCompare;
	private List<Oa0201060401FromVo> arrayListLubricantsSummary;
	private List<OaLubricantsSummary> listOaLubricantsSummary;
	private String result;
	
	public BigDecimal getOaLubricantsDtlId() {
		return oaLubricantsDtlId;
	}
	public BigDecimal getOaLubricantsId() {
		return oaLubricantsId;
	}
	public List<OaLubricantsCompare> getListLubricantsCompare() {
		return listLubricantsCompare;
	}
	public List<Oa0201060401FromVo> getArrayListLubricantsSummary() {
		return arrayListLubricantsSummary;
	}
	public void setOaLubricantsDtlId(BigDecimal oaLubricantsDtlId) {
		this.oaLubricantsDtlId = oaLubricantsDtlId;
	}
	public void setOaLubricantsId(BigDecimal oaLubricantsId) {
		this.oaLubricantsId = oaLubricantsId;
	}
	public void setListLubricantsCompare(List<OaLubricantsCompare> listLubricantsCompare) {
		this.listLubricantsCompare = listLubricantsCompare;
	}
	public void setArrayListLubricantsSummary(List<Oa0201060401FromVo> arrayListLubricantsSummary) {
		this.arrayListLubricantsSummary = arrayListLubricantsSummary;
	}
	public List<OaLubricantsSummary> getListOaLubricantsSummary() {
		return listOaLubricantsSummary;
	}
	public void setListOaLubricantsSummary(List<OaLubricantsSummary> listOaLubricantsSummary) {
		this.listOaLubricantsSummary = listOaLubricantsSummary;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	

}
