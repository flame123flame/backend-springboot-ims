
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_ESTIMATE_EXP_D1")
public class IaEstimateExpD1 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5884755501761502583L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_ESTIMATE_EXP_D1_GEN")
	@SequenceGenerator(name = "IA_ESTIMATE_EXP_D1_GEN", sequenceName = "IA_ESTIMATE_EXP_D1_SEQ", allocationSize = 1)
	@Column(name = "ESTIMATE_EXP_D1_ID")
	private Long estimateExpD1Id;
	@Column(name = "EST_EXP_NO")
	private String estExpNo;
	@Column(name = "SEQ_NO")
	private BigDecimal seqNo;
	@Column(name = "PERSON_TEAM_CODE")
	private String personTeamCode;
	@Column(name = "POSITION")
	private String position;
	@Column(name = "ALLOWANCES_DAY")
	private BigDecimal allowancesDay;
	@Column(name = "ALLOWANCES_HALF_DAY")
	private BigDecimal allowancesHalfDay;
	@Column(name = "ACCOM_FEE_PACKAGES")
	private BigDecimal accomFeePackages;
	@Column(name = "ACCOM_FEE_PACKAGES_DAT")
	private BigDecimal accomFeePackagesDat;
	@Column(name = "TRAN_COST")
	private BigDecimal tranCost;
	@Column(name = "SUM_AMT")
	private BigDecimal sumAmt;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "OTHER_EXPENSES")
	private BigDecimal otherExpenses;
	@Column(name = "SUM_ALLOWANCES")
	private BigDecimal sumAllowances;
	@Column(name = "SUM_ACCOM")
	private BigDecimal sumAccom;
	@Column(name = "FLAG_NOT_WITHDRAWING")
	private String flagNotWithdrawing;


	public Long getEstimateExpD1Id() {
		return estimateExpD1Id;
	}

	public void setEstimateExpD1Id(Long estimateExpD1Id) {
		this.estimateExpD1Id = estimateExpD1Id;
	}

	public String getEstExpNo() {
		return estExpNo;
	}

	public void setEstExpNo(String estExpNo) {
		this.estExpNo = estExpNo;
	}

	public BigDecimal getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	public String getPersonTeamCode() {
		return personTeamCode;
	}

	public void setPersonTeamCode(String personTeamCode) {
		this.personTeamCode = personTeamCode;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public BigDecimal getAllowancesDay() {
		return allowancesDay;
	}

	public void setAllowancesDay(BigDecimal allowancesDay) {
		this.allowancesDay = allowancesDay;
	}

	public BigDecimal getAllowancesHalfDay() {
		return allowancesHalfDay;
	}

	public void setAllowancesHalfDay(BigDecimal allowancesHalfDay) {
		this.allowancesHalfDay = allowancesHalfDay;
	}

	public BigDecimal getAccomFeePackages() {
		return accomFeePackages;
	}

	public void setAccomFeePackages(BigDecimal accomFeePackages) {
		this.accomFeePackages = accomFeePackages;
	}

	public BigDecimal getAccomFeePackagesDat() {
		return accomFeePackagesDat;
	}

	public void setAccomFeePackagesDat(BigDecimal accomFeePackagesDat) {
		this.accomFeePackagesDat = accomFeePackagesDat;
	}

	public BigDecimal getTranCost() {
		return tranCost;
	}

	public void setTranCost(BigDecimal tranCost) {
		this.tranCost = tranCost;
	}

	public BigDecimal getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(BigDecimal otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public BigDecimal getSumAllowances() {
		return sumAllowances;
	}

	public void setSumAllowances(BigDecimal sumAllowances) {
		this.sumAllowances = sumAllowances;
	}

	public BigDecimal getSumAccom() {
		return sumAccom;
	}

	public void setSumAccom(BigDecimal sumAccom) {
		this.sumAccom = sumAccom;
	}

	public String getFlagNotWithdrawing() {
		return flagNotWithdrawing;
	}

	public void setFlagNotWithdrawing(String flagNotWithdrawing) {
		this.flagNotWithdrawing = flagNotWithdrawing;
	}
	
}
