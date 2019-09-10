
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_PLAN_DTL")
public class IaPlanDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086219331868114520L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_PLAN_DTL_GEN")
	@SequenceGenerator(name = "IA_PLAN_DTL_GEN", sequenceName = "IA_PLAN_DTL_SEQ", allocationSize = 1)
	@Column(name = "PLAN_DTL_ID")
	private BigDecimal planDtlId;
	@Column(name = "PLAN_HDR_ID")
	private BigDecimal planHdrId;
	@Column(name = "INSPECTION_WORK")
	private String inspectionWork;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "ACTIVITY")
	private String activity;
	@Column(name = "FREQUENCY")
	private BigDecimal frequency;
	@Column(name = "UNIT")
	private String unit;
	@Column(name = "ACTIVITY_STATUS")
	private String activityStatus;
	@Column(name = "RESPONSIBLE_PERSON")
	private String responsiblePerson;
	@Column(name = "INSPECTOR")
	private String inspector;
	@Column(name = "OFFICER")
	private String officer;
	@Column(name = "POSITION")
	private String position;
	

	public BigDecimal getPlanDtlId() {
		return planDtlId;
	}

	public void setPlanDtlId(BigDecimal planDtlId) {
		this.planDtlId = planDtlId;
	}

	public BigDecimal getPlanHdrId() {
		return planHdrId;
	}

	public void setPlanHdrId(BigDecimal planHdrId) {
		this.planHdrId = planHdrId;
	}

	public String getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(String inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public BigDecimal getFrequency() {
		return frequency;
	}

	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
