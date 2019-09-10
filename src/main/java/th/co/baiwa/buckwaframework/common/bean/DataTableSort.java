package th.co.baiwa.buckwaframework.common.bean;

import java.io.Serializable;

public class DataTableSort implements Serializable {

	
	private static final long serialVersionUID = -6514405829011582536L;
	private String column;
	private String order;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
