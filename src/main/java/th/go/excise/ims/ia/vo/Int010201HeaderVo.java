package th.go.excise.ims.ia.vo;

import java.util.Date;

public class Int010201HeaderVo {

	private String inspector;
	private String officer;
	private String inspectionWork;
	private String inspectionWorkStr;
	private Date dateStartActivity;
	private Date dateEndActivity;

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getOfficer() {
		return officer;
	}

	public void setOfficer(String officer) {
		this.officer = officer;
	}

	public String getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(String inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getInspectionWorkStr() {
		return inspectionWorkStr;
	}

	public void setInspectionWorkStr(String inspectionWorkStr) {
		this.inspectionWorkStr = inspectionWorkStr;
	}

	public Date getDateStartActivity() {
		return dateStartActivity;
	}

	public void setDateStartActivity(Date dateStartActivity) {
		this.dateStartActivity = dateStartActivity;
	}

	public Date getDateEndActivity() {
		return dateEndActivity;
	}

	public void setDateEndActivity(Date dateEndActivity) {
		this.dateEndActivity = dateEndActivity;
	}

}
