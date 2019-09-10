
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
@Table(name = "IA_AUDIT_INC_SEND_H")
public class IaAuditIncSendH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975277312079308134L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_SEND_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_SEND_H_GEN", sequenceName = "IA_AUDIT_INC_SEND_H_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_INC_SEND_H_SEQ")
	private BigDecimal auditIncSendHSeq;
	@Column(name = "INCSEND_NO")
	private String incsendNo;
	@Column(name = "INCSEND_OFFICE_CODE")
	private String incsendOfficeCode;
	@Column(name = "INCSEND_PERIOD_MONTH")
	private String incsendPeriodMonth;
	@Column(name = "AUDIT_FLAG")
	private String auditFlag;
	@Column(name = "INCSEND_CONDITION_TEXT")
	private String incsendConditionText;
	@Column(name = "INCSEND_CRETERIA_TEXT")
	private String incsendCreteriaText;

	public BigDecimal getAuditIncSendHSeq() {
		return auditIncSendHSeq;
	}

	public void setAuditIncSendHSeq(BigDecimal auditIncSendHSeq) {
		this.auditIncSendHSeq = auditIncSendHSeq;
	}

	public String getIncsendNo() {
		return incsendNo;
	}

	public void setIncsendNo(String incsendNo) {
		this.incsendNo = incsendNo;
	}

	public String getIncsendOfficeCode() {
		return incsendOfficeCode;
	}

	public void setIncsendOfficeCode(String incsendOfficeCode) {
		this.incsendOfficeCode = incsendOfficeCode;
	}

	public String getIncsendPeriodMonth() {
		return incsendPeriodMonth;
	}

	public void setIncsendPeriodMonth(String incsendPeriodMonth) {
		this.incsendPeriodMonth = incsendPeriodMonth;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getIncsendConditionText() {
		return incsendConditionText;
	}

	public void setIncsendConditionText(String incsendConditionText) {
		this.incsendConditionText = incsendConditionText;
	}

	public String getIncsendCreteriaText() {
		return incsendCreteriaText;
	}

	public void setIncsendCreteriaText(String incsendCreteriaText) {
		this.incsendCreteriaText = incsendCreteriaText;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
