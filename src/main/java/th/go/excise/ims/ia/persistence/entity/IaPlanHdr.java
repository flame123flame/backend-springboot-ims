
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
@Table(name = "IA_PLAN_HDR")
public class IaPlanHdr extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624538979216629616L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_PLAN_HDR_GEN")
	@SequenceGenerator(name = "IA_PLAN_HDR_GEN", sequenceName = "IA_PLAN_HDR_SEQ", allocationSize = 1)
	@Column(name = "PLAN_HDR_ID")
	private BigDecimal planHdrId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "POSITION")
	private String position;
	@Column(name = "APPROVERS")
	private String approvers;

	public BigDecimal getPlanHdrId() {
		return planHdrId;
	}

	public void setPlanHdrId(BigDecimal planHdrId) {
		this.planHdrId = planHdrId;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getApprovers() {
		return approvers;
	}

	public void setApprovers(String approvers) {
		this.approvers = approvers;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
