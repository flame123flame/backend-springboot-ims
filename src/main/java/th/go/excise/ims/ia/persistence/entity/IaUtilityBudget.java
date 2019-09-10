
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
@Table(name = "IA_UTILITY_BUDGET")
public class IaUtilityBudget extends BaseEntity {

	private static final long serialVersionUID = 6537397155999477141L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_UTILITY_BUDGET_GEN")
	@SequenceGenerator(name = "IA_UTILITY_BUDGET_GEN", sequenceName = "IA_UTILITY_BUDGET_SEQ", allocationSize = 1)
	@Column(name = "UTILITY_BUDGET_SEQ")
	private Long utilityBudgetSeq;
	@Column(name = "EXCISE_CODE")
	private String exciseCode;
	@Column(name = "UBUDGET_Q")
	private String ubudgetQ;
	@Column(name = "BUDGET_AMT")
	private BigDecimal budgetAmt;
	@Column(name = "NON_BUDGET_AMT")
	private BigDecimal nonBudgetAmt;


	public Long getUtilityBudgetSeq() {
		return utilityBudgetSeq;
	}

	public void setUtilityBudgetSeq(Long utilityBudgetSeq) {
		this.utilityBudgetSeq = utilityBudgetSeq;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public String getUbudgetQ() {
		return ubudgetQ;
	}

	public void setUbudgetQ(String ubudgetQ) {
		this.ubudgetQ = ubudgetQ;
	}

	public BigDecimal getBudgetAmt() {
		return budgetAmt;
	}

	public void setBudgetAmt(BigDecimal budgetAmt) {
		this.budgetAmt = budgetAmt;
	}

	public BigDecimal getNonBudgetAmt() {
		return nonBudgetAmt;
	}

	public void setNonBudgetAmt(BigDecimal nonBudgetAmt) {
		this.nonBudgetAmt = nonBudgetAmt;
	}

	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
