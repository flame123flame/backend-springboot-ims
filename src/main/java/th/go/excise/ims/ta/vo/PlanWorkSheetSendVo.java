package th.go.excise.ims.ta.vo;

import th.go.excise.ims.preferences.vo.ExciseDepartment;

public class PlanWorkSheetSendVo {

	private ExciseDepartment sector;
	private ExciseDepartment area;
	private PlanWorkSheetSendDetailVo planWorksheetSend;
	private Integer totalFacNum;

	public ExciseDepartment getSector() {
		return sector;
	}

	public void setSector(ExciseDepartment sector) {
		this.sector = sector;
	}

	public ExciseDepartment getArea() {
		return area;
	}

	public void setArea(ExciseDepartment area) {
		this.area = area;
	}

	public PlanWorkSheetSendDetailVo getPlanWorksheetSend() {
		return planWorksheetSend;
	}

	public void setPlanWorksheetSend(PlanWorkSheetSendDetailVo planWorksheetSend) {
		this.planWorksheetSend = planWorksheetSend;
	}

	public Integer getTotalFacNum() {
		return totalFacNum;
	}

	public void setTotalFacNum(Integer totalFacNum) {
		this.totalFacNum = totalFacNum;
	}

}
