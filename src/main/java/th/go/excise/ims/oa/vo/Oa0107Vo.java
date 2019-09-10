package th.go.excise.ims.oa.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Oa0107Vo extends DataTableRequest {
	private String sector;
	private String area;

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
