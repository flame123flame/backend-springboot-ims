
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
@Table(name = "IA_GFTRIAL_BALANCE")
public class IaGftrialBalance extends BaseEntity {

	private static final long serialVersionUID = 1536622124722705683L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_GFTRIAL_BALANCE_GEN")
	@SequenceGenerator(name = "IA_GFTRIAL_BALANCE_GEN", sequenceName = "IA_GFTRIAL_BALANCE_SEQ", allocationSize = 1)
	@Column(name = "IA_GFTRIAL_BALANCE_ID")
	private Long iaGftrialBalanceId;
	@Column(name = "GFUPLOAD_H_ID")
	private Long gfuploadHId;
	@Column(name = "DEPT_DISB")
	private String deptDisb;
	@Column(name = "PERIOD_FROM")
	private String periodFrom;
	@Column(name = "PERIOD_TO")
	private String periodTo;
	@Column(name = "PERIOD_YEAR")
	private String periodYear;
	@Column(name = "ACC_NO")
	private String accNo;
	@Column(name = "ACC_NAME")
	private String accName;
	@Column(name = "CARRY_FORWARD")
	private BigDecimal carryForward;
	@Column(name = "BRING_FORWARD")
	private BigDecimal bringForward;
	@Column(name = "DEBIT")
	private BigDecimal debit;
	@Column(name = "CREDIT")
	private BigDecimal credit;

	public Long getIaGftrialBalanceId() {
		return iaGftrialBalanceId;
	}

	public void setIaGftrialBalanceId(Long iaGftrialBalanceId) {
		this.iaGftrialBalanceId = iaGftrialBalanceId;
	}

	public Long getGfuploadHId() {
		return gfuploadHId;
	}

	public void setGfuploadHId(Long gfuploadHId) {
		this.gfuploadHId = gfuploadHId;
	}

	public String getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	public String getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	public String getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public BigDecimal getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public BigDecimal getBringForward() {
		return bringForward;
	}

	public void setBringForward(BigDecimal bringForward) {
		this.bringForward = bringForward;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

}
