package th.go.excise.ims.ta.vo;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;

public class MasterConditionMainHdrDtlVo {
	
	private TaMasCondMainHdrForm header;
	private List<TaMasCondMainDtl> detail;
	
	public TaMasCondMainHdrForm getHeader() {
		return header;
	}
	public void setHeader(TaMasCondMainHdrForm header) {
		this.header = header;
	}
	public List<TaMasCondMainDtl> getDetail() {
		return detail;
	}
	public void setDetail(List<TaMasCondMainDtl> detail) {
		this.detail = detail;
	}
	
}
