
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
@Table(name = "IA_EXP_ESTIMATE_H")
public class IaExpEstimateH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2807199424411777277L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EXP_ESTIMATE_H_GEN")
	@SequenceGenerator(name = "IA_EXP_ESTIMATE_H_GEN", sequenceName = "IA_EXP_ESTIMATE_H_SEQ", allocationSize = 1)
	@Column(name = "EXP_ESTIMATE_NO")
	private Long expEstimateNo;
	@Column(name = "EXPENSE_TYPE")
	private String expenseType;
	@Column(name = "REQ_DEPT_CODE")
	private String reqDeptCode;
	@Column(name = "DEPT_NAME")
	private BigDecimal deptName;
	@Column(name = "DES_OFFICE_CODE1")
	private String desOfficeCode1;
	@Column(name = "DES_OFFICE_CODE2")
	private String desOfficeCode2;
	@Column(name = "DES_OFFICE_CODE3")
	private String desOfficeCode3;
	@Column(name = "TRAVEL_DATE_START")
	private String travelDateStart;
	@Column(name = "TRAVEL_DATE_END")
	private BigDecimal travelDateEnd;

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

	public BigDecimal getDeptName() {
		return deptName;
	}

	public void setDeptName(BigDecimal deptName) {
		this.deptName = deptName;
	}

	public String getDesOfficeCode1() {
		return desOfficeCode1;
	}

	public void setDesOfficeCode1(String desOfficeCode1) {
		this.desOfficeCode1 = desOfficeCode1;
	}

	public String getDesOfficeCode2() {
		return desOfficeCode2;
	}

	public void setDesOfficeCode2(String desOfficeCode2) {
		this.desOfficeCode2 = desOfficeCode2;
	}

	public String getDesOfficeCode3() {
		return desOfficeCode3;
	}

	public void setDesOfficeCode3(String desOfficeCode3) {
		this.desOfficeCode3 = desOfficeCode3;
	}

	public String getTravelDateStart() {
		return travelDateStart;
	}

	public void setTravelDateStart(String travelDateStart) {
		this.travelDateStart = travelDateStart;
	}

	public BigDecimal getTravelDateEnd() {
		return travelDateEnd;
	}

	public void setTravelDateEnd(BigDecimal travelDateEnd) {
		this.travelDateEnd = travelDateEnd;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
