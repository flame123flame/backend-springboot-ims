
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "IA_PLAN_DAY_ACTIVITY")
public class IaPlanDayActivity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3407506101087418313L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_PLAN_DAY_ACTIVITY_GEN")
	@SequenceGenerator(name = "IA_PLAN_DAY_ACTIVITY_GEN", sequenceName = "IA_PLAN_DAY_ACTIVITY_SEQ", allocationSize = 1)
	@Column(name = "PLAN_DAY_ACTIVITY_ID")
	private BigDecimal planDayActivityId;
	@Column(name = "PLAN_HDR_ID")
	private BigDecimal planHdrId;
	@Column(name = "PLAN_DTL_ID")
	private BigDecimal planDtlId;
	@Column(name = "ACTIVITY")
	private String activity;
	@Column(name = "DATE_START_ACTIVITY")
	private Date dateStartActivity;
	@Column(name = "DATE_END_ACTIVITY")
	private Date dateEndActivity;
	@Column(name = "ACTIVITY_STATUS")
	private String activityStatus;
	@Column(name = "ACTIVITY_SHORT")
	private String activityShort;
	
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

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
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

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getActivityShort() {
		return activityShort;
	}

	public void setActivityShort(String activityShort) {
		this.activityShort = activityShort;
	}

}
