package th.co.baiwa.buckwaframework.common.bean;

import java.util.List;

public class DataTableResponse<T> {
	private Integer draw = 0;
	private Integer recordsTotal = 0;
	private List<T> data;

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
