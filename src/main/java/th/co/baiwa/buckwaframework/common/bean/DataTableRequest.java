package th.co.baiwa.buckwaframework.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataTableRequest implements Serializable {

	private static final long serialVersionUID = -2101666256658867880L;
	private Integer start;
	private Integer length;
	List<DataTableSort> sort = new ArrayList<DataTableSort>();
	private Integer draw;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public List<DataTableSort> getSort() {
		return sort;
	}

	public void setSort(List<DataTableSort> sort) {
		this.sort = sort;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

}
