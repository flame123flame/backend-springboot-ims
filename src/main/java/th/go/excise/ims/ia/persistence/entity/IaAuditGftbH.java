
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
@Table(name = "IA_AUDIT_GFTB_H")
public class IaAuditGftbH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4113146325132361907L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_GFTB_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_GFTB_H_GEN", sequenceName = "IA_AUDIT_GFTB_H_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_GFTB_H_SEQ")
	private BigDecimal iaAuditGftbHSeq;
	@Column(name = "AUDIT_GFTB_NO")
	private String auditGftbNo;
	@Column(name = "DEPT_DISB")
	private String deptDisb;
	@Column(name = "PERIOD_MONTH")
	private String periodMonth;
	@Column(name = "GFTB_FLAG")
	private String gftbFlag;
	@Column(name = "GFTB_CONDITION_TEXT")
	private String gftbConditionText;
	@Column(name = "GFTB_CRETERIA_TEXT")
	private String gftbCreteriaText;

	public BigDecimal getIaAuditGftbHSeq() {
		return iaAuditGftbHSeq;
	}

	public void setIaAuditGftbHSeq(BigDecimal iaAuditGftbHSeq) {
		this.iaAuditGftbHSeq = iaAuditGftbHSeq;
	}

	public String getAuditGftbNo() {
		return auditGftbNo;
	}

	public void setAuditGftbNo(String auditGftbNo) {
		this.auditGftbNo = auditGftbNo;
	}

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

	public String getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(String periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getGftbFlag() {
		return gftbFlag;
	}

	public void setGftbFlag(String gftbFlag) {
		this.gftbFlag = gftbFlag;
	}

	public String getGftbConditionText() {
		return gftbConditionText;
	}

	public void setGftbConditionText(String gftbConditionText) {
		this.gftbConditionText = gftbConditionText;
	}

	public String getGftbCreteriaText() {
		return gftbCreteriaText;
	}

	public void setGftbCreteriaText(String gftbCreteriaText) {
		this.gftbCreteriaText = gftbCreteriaText;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
