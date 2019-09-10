
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
@Table(name = "IA_AUDIT_GFTB_D")
public class IaAuditGftbD extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4815945705710176866L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_GFTB_D_GEN")
	@SequenceGenerator(name = "IA_AUDIT_GFTB_D_GEN", sequenceName = "IA_AUDIT_GFTB_D_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_GFTB_D_SEQ")
	private BigDecimal iaAuditGftbDSeq;
	@Column(name = "AUDIT_GFTB_NO")
	private String auditGftbNo;
	@Column(name = "GFTB_SEQ")
	private Integer gftbSeq;
	@Column(name = "ACC_NO")
	private String accNo;
	@Column(name = "ACC_NAME")
	private String accName;
	@Column(name = "BRING_FORWARD")
	private BigDecimal bringForward;
	@Column(name = "CREDIT")
	private BigDecimal credit;
	@Column(name = "DEBIT")
	private BigDecimal debit;
	@Column(name = "CARRY_FORWARD")
	private BigDecimal carryForward;
	@Column(name = "GFTB_TEST_RESULT")
	private String gftbTestResult;
	@Column(name = "CHECK_FLAG")
	private String checkFlag;

	public BigDecimal getIaAuditGftbDSeq() {
		return iaAuditGftbDSeq;
	}

	public void setIaAuditGftbDSeq(BigDecimal iaAuditGftbDSeq) {
		this.iaAuditGftbDSeq = iaAuditGftbDSeq;
	}

	public String getAuditGftbNo() {
		return auditGftbNo;
	}

	public void setAuditGftbNo(String auditGftbNo) {
		this.auditGftbNo = auditGftbNo;
	}

	public Integer getGftbSeq() {
		return gftbSeq;
	}

	public void setGftbSeq(Integer gftbSeq) {
		this.gftbSeq = gftbSeq;
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

	public BigDecimal getBringForward() {
		return bringForward;
	}

	public void setBringForward(BigDecimal bringForward) {
		this.bringForward = bringForward;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public String getGftbTestResult() {
		return gftbTestResult;
	}

	public void setGftbTestResult(String gftbTestResult) {
		this.gftbTestResult = gftbTestResult;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

}