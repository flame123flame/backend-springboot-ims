package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int01TableVo {

	private String inspectionWork;
	private String inspectionWorkStr;
	private BigDecimal frequency;
	private String unit;
	private List<Int01DtlVo> detail = null;

	public String getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(String inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public List<Int01DtlVo> getDetail() {
		return detail;
	}

	public void setDetail(List<Int01DtlVo> detail) {
		this.detail = detail;
	}

	public BigDecimal getFrequency() {
		return frequency;
	}

	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInspectionWorkStr() {
		return inspectionWorkStr;
	}

	public void setInspectionWorkStr(String inspectionWorkStr) {
		this.inspectionWorkStr = inspectionWorkStr;
	}

}
