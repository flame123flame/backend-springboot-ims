
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
@Table(name = "IA_AUDIT_PY2_H")
public class IaAuditPy2H extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8103168055254487972L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PY2_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_PY2_H_GEN", sequenceName = "IA_AUDIT_PY2_H_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_PY2_H_ID")
	private BigDecimal iaAuditPy2HId;
	@Column(name = "AUDIT_PY2_NO")
	private String auditPy2No;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "PY2_ACTIVITY_RESULT")
	private String py2ActivityResult;
	@Column(name = "PY2_AUDIT_RESULT")
	private String py2AuditResult;
	@Column(name = "PY2_AUDIT_EVIDENT")
	private String py2AuditEvident;
	@Column(name = "PY2_AUDIT_SUGGESTION")
	private String py2AuditSuggestion;
	@Column(name = "PM_PY2_H_SEQ")
	private BigDecimal pmPy2HSeq;

	public BigDecimal getIaAuditPy2HId() {
		return iaAuditPy2HId;
	}

	public void setIaAuditPy2HId(BigDecimal iaAuditPy2HId) {
		this.iaAuditPy2HId = iaAuditPy2HId;
	}

	public String getAuditPy2No() {
		return auditPy2No;
	}

	public void setAuditPy2No(String auditPy2No) {
		this.auditPy2No = auditPy2No;
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

	public String getPy2ActivityResult() {
		return py2ActivityResult;
	}

	public void setPy2ActivityResult(String py2ActivityResult) {
		this.py2ActivityResult = py2ActivityResult;
	}

	public String getPy2AuditResult() {
		return py2AuditResult;
	}

	public void setPy2AuditResult(String py2AuditResult) {
		this.py2AuditResult = py2AuditResult;
	}

	public String getPy2AuditEvident() {
		return py2AuditEvident;
	}

	public void setPy2AuditEvident(String py2AuditEvident) {
		this.py2AuditEvident = py2AuditEvident;
	}

	public String getPy2AuditSuggestion() {
		return py2AuditSuggestion;
	}

	public void setPy2AuditSuggestion(String py2AuditSuggestion) {
		this.py2AuditSuggestion = py2AuditSuggestion;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public BigDecimal getPmPy2HSeq() {
		return pmPy2HSeq;
	}

	public void setPmPy2HSeq(BigDecimal pmPy2HSeq) {
		this.pmPy2HSeq = pmPy2HSeq;
	}
	

}
