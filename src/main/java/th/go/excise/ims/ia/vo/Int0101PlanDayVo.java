package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Int0101PlanDayVo {
	private BigDecimal planDayActivityId;
	private BigDecimal planHdrId;
	private BigDecimal planDtlId;
	private Date dateStartActivity;
	private String dateStartActivityStr;
	private Date dateEndActivity;
	private String dateEndActivityStr;
	private String activity;
	private String activityStatus;
	private String activityShort;
	private String colorCode;
	
	private String position;
	private String responsiblePerson;
	
	public BigDecimal getPlanDayActivityId() {
		return planDayActivityId;
	}
	public void setPlanDayActivityId(BigDecimal planDayActivityId) {
		this.planDayActivityId = planDayActivityId;
	}
	public BigDecimal getPlanHdrId() {
		return planHdrId;
	}
	public void setPlanHdrId(BigDecimal planHdrId) {
		this.planHdrId = planHdrId;
	}
	public BigDecimal getPlanDtlId() {
		return planDtlId;
	}
	public void setPlanDtlId(BigDecimal planDtlId) {
		this.planDtlId = planDtlId;
	}
	public Date getDateStartActivity() {
		return dateStartActivity;
	}
	public void setDateStartActivity(Date dateStartActivity) {
		this.dateStartActivity = dateStartActivity;
	}
	public String getDateStartActivityStr() {
		return dateStartActivityStr;
	}
	public void setDateStartActivityStr(String dateStartActivityStr) {
		this.dateStartActivityStr = dateStartActivityStr;
	}
	public Date getDateEndActivity() {
		return dateEndActivity;
	}
	public void setDateEndActivity(Date dateEndActivity) {
		this.dateEndActivity = dateEndActivity;
	}
	public String getDateEndActivityStr() {
		return dateEndActivityStr;
	}
	public void setDateEndActivityStr(String dateEndActivityStr) {
		this.dateEndActivityStr = dateEndActivityStr;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getActivityShort() {
		return activityShort;
	}
	public void setActivityShort(String activityShort) {
		this.activityShort = activityShort;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

}