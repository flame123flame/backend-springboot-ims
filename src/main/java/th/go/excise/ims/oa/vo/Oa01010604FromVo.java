package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.List;

import th.go.excise.ims.oa.persistence.entity.OaHydrocarbCompare;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbSummary;

public class Oa01010604FromVo {
	private BigDecimal oaHydrocarbDtlId;
	private BigDecimal oaHydrocarbId;
	private List<OaHydrocarbCompare> listOaHydrocarbCompare;
//	private List<Oa0201060401FromVo> arrayHydrocarbSummary;
	private List<OaHydrocarbSummary> listOaHydrocarbSummary;
	private String result;
	
	
	public BigDecimal getOaHydrocarbDtlId() {
		return oaHydrocarbDtlId;
	}
	public BigDecimal getOaHydrocarbId() {
		return oaHydrocarbId;
	}
	public List<OaHydrocarbCompare> getListOaHydrocarbCompare() {
		return listOaHydrocarbCompare;
	}
	public List<OaHydrocarbSummary> getListOaHydrocarbSummary() {
		return listOaHydrocarbSummary;
	}
	public String getResult() {
		return result;
	}
	public void setOaHydrocarbDtlId(BigDecimal oaHydrocarbDtlId) {
		this.oaHydrocarbDtlId = oaHydrocarbDtlId;
	}
	public void setOaHydrocarbId(BigDecimal oaHydrocarbId) {
		this.oaHydrocarbId = oaHydrocarbId;
	}
	public void setListOaHydrocarbCompare(List<OaHydrocarbCompare> listOaHydrocarbCompare) {
		this.listOaHydrocarbCompare = listOaHydrocarbCompare;
	}
	public void setListOaHydrocarbSummary(List<OaHydrocarbSummary> listOaHydrocarbSummary) {
		this.listOaHydrocarbSummary = listOaHydrocarbSummary;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	
}
