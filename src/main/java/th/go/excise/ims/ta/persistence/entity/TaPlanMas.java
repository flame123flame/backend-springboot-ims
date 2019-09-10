package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_PLAN_MAS")
public class TaPlanMas extends BaseEntity {

	private static final long serialVersionUID = -6707041443741655133L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_MAS_GEN")
	@SequenceGenerator(name = "TA_PLAN_MAS_GEN", sequenceName = "TA_PLAN_MAS_SEQ", allocationSize = 1)
	@Column(name = "PLAN_MAS_ID")
	private Long planMasId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "MONTH")
	private String month;
	@Column(name = "FAC_NUM")
	private Integer facNum;

	public Long getPlanMasId() {
		return planMasId;
	}

	public void setPlanMasId(Long planMasId) {
		this.planMasId = planMasId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getFacNum() {
		return facNum;
	}

	public void setFacNum(Integer facNum) {
		this.facNum = facNum;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
