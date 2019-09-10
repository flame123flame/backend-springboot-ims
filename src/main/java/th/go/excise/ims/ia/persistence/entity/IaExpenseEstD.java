
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
@Table(name = "IA_EXPENSE_EST_D")
public class IaExpenseEstD extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5241594247813719603L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EXPENSE_EST_D_GEN")
	@SequenceGenerator(name = "IA_EXPENSE_EST_D_GEN", sequenceName = "IA_EXPENSE_EST_D_SEQ", allocationSize = 1)
	@Column(name = "EXP_ESTIMATE_NO")
	private Long expEstimateNo;
	@Column(name = "EXPENSE_TYPE")
	private String expenseType;
	@Column(name = "REQ_DEPT_CODE")
	private String reqDeptCode;
	@Column(name = "EMP_REQ_SEQ")
	private BigDecimal empReqSeq;
	@Column(name = "PERSONAL_ID")
	private String personalId;
	@Column(name = "EMP_NAME")
	private String empName;
	@Column(name = "EMP_POSITION_CODE")
	private String empPositionCode;
	@Column(name = "EMP_POSITION_NAME")
	private String empPositionName;
	@Column(name = "ALLOWANCES_DAY")
	private BigDecimal allowancesDay;
	@Column(name = "ALLOWANCES_RATE")
	private BigDecimal allowancesRate;
	@Column(name = "ALLOWANCES_AMT")
	private BigDecimal allowancesAmt;
	@Column(name = "ACCOM_FEE_DAY")
	private BigDecimal accomFeeDay;
	@Column(name = "ACCOM_FEE_RATE")
	private BigDecimal accomFeeRate;
	@Column(name = "ACCOM_FEE_AMT")
	private BigDecimal accomFeeAmt;
	@Column(name = "TRANSPORT_AMT")
	private BigDecimal transportAmt;
	@Column(name = "EXP_OTHER_AMT")
	private BigDecimal expOtherAmt;
	@Column(name = "EXP_REMARKS")
	private String expRemarks;

	public Long getExpEstimateNo() {
		return expEstimateNo;
	}

	public void setExpEstimateNo(Long expEstimateNo) {
		this.expEstimateNo = expEstimateNo;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getReqDeptCode() {
		return reqDeptCode;
	}

	public void setReqDeptCode(String reqDeptCode) {
		this.reqDeptCode = reqDeptCode;
	}

	public BigDecimal getEmpReqSeq() {
		return empReqSeq;
	}

	public void setEmpReqSeq(BigDecimal empReqSeq) {
		this.empReqSeq = empReqSeq;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPositionCode() {
		return empPositionCode;
	}

	public void setEmpPositionCode(String empPositionCode) {
		this.empPositionCode = empPositionCode;
	}

	public String getEmpPositionName() {
		return empPositionName;
	}

	public void setEmpPositionName(String empPositionName) {
		this.empPositionName = empPositionName;
	}

	public BigDecimal getAllowancesDay() {
		return allowancesDay;
	}

	public void setAllowancesDay(BigDecimal allowancesDay) {
		this.allowancesDay = allowancesDay;
	}

	public BigDecimal getAllowancesRate() {
		return allowancesRate;
	}

	public void setAllowancesRate(BigDecimal allowancesRate) {
		this.allowancesRate = allowancesRate;
	}

	public BigDecimal getAllowancesAmt() {
		return allowancesAmt;
	}

	public void setAllowancesAmt(BigDecimal allowancesAmt) {
		this.allowancesAmt = allowancesAmt;
	}

	public BigDecimal getAccomFeeDay() {
		return accomFeeDay;
	}

	public void setAccomFeeDay(BigDecimal accomFeeDay) {
		this.accomFeeDay = accomFeeDay;
	}

	public BigDecimal getAccomFeeRate() {
		return accomFeeRate;
	}

	public void setAccomFeeRate(BigDecimal accomFeeRate) {
		this.accomFeeRate = accomFeeRate;
	}

	public BigDecimal getAccomFeeAmt() {
		return accomFeeAmt;
	}

	public void setAccomFeeAmt(BigDecimal accomFeeAmt) {
		this.accomFeeAmt = accomFeeAmt;
	}

	public BigDecimal getTransportAmt() {
		return transportAmt;
	}

	public void setTransportAmt(BigDecimal transportAmt) {
		this.transportAmt = transportAmt;
	}

	public BigDecimal getExpOtherAmt() {
		return expOtherAmt;
	}

	public void setExpOtherAmt(BigDecimal expOtherAmt) {
		this.expOtherAmt = expOtherAmt;
	}

	public String getExpRemarks() {
		return expRemarks;
	}

	public void setExpRemarks(String expRemarks) {
		this.expRemarks = expRemarks;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
