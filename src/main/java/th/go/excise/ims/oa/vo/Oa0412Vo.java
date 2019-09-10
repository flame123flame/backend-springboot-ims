package th.go.excise.ims.oa.vo;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Oa0412Vo extends DataTableRequest {
	private String sector;
	private String area;
	private List<Oa020103FromVo> listID;
	

	public List<Oa020103FromVo> getListID() {
		return listID;
	}

	public void setListID(List<Oa020103FromVo> listID) {
		this.listID = listID;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
