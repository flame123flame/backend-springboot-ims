package th.go.excise.ims.preferences.vo;

import java.util.List;

public class ExcelHeaderVo {
	private List<ExcelHeaderNameVo> items;
	private int start = 0;
	private int stop = 0;

	public List<ExcelHeaderNameVo> getItems() {
		return items;
	}

	public void setItems(List<ExcelHeaderNameVo> items) {
		this.items = items;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}
}
