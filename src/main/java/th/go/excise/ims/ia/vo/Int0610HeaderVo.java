package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int0610HeaderVo {
	private List<Int0610Vo> dataList;
	private ExciseDepartmentVo exciseDepartmentVo;
	private String monthPeriodFrom;
	private String monthPeriodTo;
	private String auditIncGfNo;
	private String auditFlag;
	private String incgfConditionText;
	private String incgfCreteriaText;

	public List<Int0610Vo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Int0610Vo> dataList) {
		this.dataList = dataList;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

	public String getMonthPeriodFrom() {
		return monthPeriodFrom;
	}

	public void setMonthPeriodFrom(String monthPeriodFrom) {
		this.monthPeriodFrom = monthPeriodFrom;
	}

	public String getMonthPeriodTo() {
		return monthPeriodTo;
	}

	public void setMonthPeriodTo(String monthPeriodTo) {
		this.monthPeriodTo = monthPeriodTo;
	}

	public String getAuditIncGfNo() {
		return auditIncGfNo;
	}

	public void setAuditIncGfNo(String auditIncGfNo) {
		this.auditIncGfNo = auditIncGfNo;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getIncgfConditionText() {
		return incgfConditionText;
	}

	public void setIncgfConditionText(String incgfConditionText) {
		this.incgfConditionText = incgfConditionText;
	}

	public String getIncgfCreteriaText() {
		return incgfCreteriaText;
	}

	public void setIncgfCreteriaText(String incgfCreteriaText) {
		this.incgfCreteriaText = incgfCreteriaText;
	}

}