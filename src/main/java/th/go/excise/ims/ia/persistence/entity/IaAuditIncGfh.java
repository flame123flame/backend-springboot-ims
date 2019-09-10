
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
@Table(name = "IA_AUDIT_INC_GFH")
public class IaAuditIncGfh extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1374169317432645892L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_GFH_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_GFH_GEN", sequenceName = "IA_AUDIT_INC_GFH_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_INC_GFH_SEQ")
	private BigDecimal auditIncGfhSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "INC_MONTH_FROM")
	private String incMonthFrom;
	@Column(name = "INC_YEAR_FROM")
	private String incYearFrom;
	@Column(name = "INC_MONTH_TO")
	private String incMonthTo;
	@Column(name = "INC_YEAR_TO")
	private String incYearTo;
	@Column(name = "AUDIT_INC_GF_NO")
	private String auditIncGfNo;
	@Column(name = "AUDIT_FLAG")
	private String auditFlag;
	@Column(name = "INCGF_CONDITION_TEXT")
	private String incgfConditionText;
	@Column(name = "INCGF_CRETERIA_TEXT")
	private String incgfCreteriaText;

	public BigDecimal getAuditIncGfhSeq() {
		return auditIncGfhSeq;
	}

	public void setAuditIncGfhSeq(BigDecimal auditIncGfhSeq) {
		this.auditIncGfhSeq = auditIncGfhSeq;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getIncMonthFrom() {
		return incMonthFrom;
	}

	public void setIncMonthFrom(String incMonthFrom) {
		this.incMonthFrom = incMonthFrom;
	}

	public String getIncYearFrom() {
		return incYearFrom;
	}

	public void setIncYearFrom(String incYearFrom) {
		this.incYearFrom = incYearFrom;
	}

	public String getIncMonthTo() {
		return incMonthTo;
	}

	public void setIncMonthTo(String incMonthTo) {
		this.incMonthTo = incMonthTo;
	}

	public String getIncYearTo() {
		return incYearTo;
	}

	public void setIncYearTo(String incYearTo) {
		this.incYearTo = incYearTo;
	}

	public String getAuditIncGfNo() {
		return auditIncGfNo;
	}

	public void setAuditIncGfNo(String auditIncGfNo) {
		this.auditIncGfNo = auditIncGfNo;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getIncgfConditionText() {
		return incgfConditionText;
	}

	public void setIncgfConditionText(String incgfConditionText) {
		this.incgfConditionText = incgfConditionText;
	}

	public String getIncgfCreteriaText() {
		return incgfCreteriaText;
	}

	public void setIncgfCreteriaText(String incgfCreteriaText) {
		this.incgfCreteriaText = incgfCreteriaText;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
