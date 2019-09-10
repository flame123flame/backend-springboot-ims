package th.go.excise.ims.oa.vo;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Oa0106FormVo extends DataTableRequest{
	
	private String id;
	private List<Oa020103FromVo> listID;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Oa020103FromVo> getListID() {
		return listID;
	}

	public void setListID(List<Oa020103FromVo> listID) {
		this.listID = listID;
	}
	

}
