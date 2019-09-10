
package th.go.excise.ims.ia.persistence.entity;

import java.util.Date;

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
@Table(name = "IA_AUDIT_TXINSUR_H")
public class IaAuditTxinsurH
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -9069958473959875230L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_TXINSUR_H_GEN")
    @SequenceGenerator(name = "IA_AUDIT_TXINSUR_H_GEN", sequenceName = "IA_AUDIT_TXINSUR_H_SEQ", allocationSize = 1)
    @Column(name = "IA_AUDIT_TXINSUR_H_ID")
    private Long iaAuditTxinsurHId;
    @Column(name = "AUDIT_TXINSUR_NO")
    private String auditTxinsurNo;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "REGIST_DATE_START")
    private Date registDateStart;
    @Column(name = "REGIST_DATE_END")
    private Date registDateEnd;
    @Column(name = "AUDIT_DATE")
    private Date auditDate;
    @Column(name = "TXINSUR_AUDIT_FLAG")
    private String txinsurAuditFlag;
    @Column(name = "TXINSUR_CONDITION_TEXT")
    private String txinsurConditionText;
    @Column(name = "TXINSUR_CRITERIA_TEXT")
    private String txinsurCriteriaText;

    public Long getIaAuditTxinsurHId() {
        return iaAuditTxinsurHId;
    }

    public void setIaAuditTxinsurHId(Long iaAuditTxinsurHId) {
        this.iaAuditTxinsurHId = iaAuditTxinsurHId;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Date getRegistDateStart() {
        return registDateStart;
    }

    public void setRegistDateStart(Date registDateStart) {
        this.registDateStart = registDateStart;
    }

    public Date getRegistDateEnd() {
        return registDateEnd;
    }

    public void setRegistDateEnd(Date registDateEnd) {
        this.registDateEnd = registDateEnd;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getTxinsurAuditFlag() {
        return txinsurAuditFlag;
    }

    public void setTxinsurAuditFlag(String txinsurAuditFlag) {
        this.txinsurAuditFlag = txinsurAuditFlag;
    }

    public String getTxinsurConditionText() {
        return txinsurConditionText;
    }

    public void setTxinsurConditionText(String txinsurConditionText) {
        this.txinsurConditionText = txinsurConditionText;
    }

    public String getTxinsurCriteriaText() {
        return txinsurCriteriaText;
    }

    public void setTxinsurCriteriaText(String txinsurCriteriaText) {
        this.txinsurCriteriaText = txinsurCriteriaText;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

	public String getAuditTxinsurNo() {
		return auditTxinsurNo;
	}

	public void setAuditTxinsurNo(String auditTxinsurNo) {
		this.auditTxinsurNo = auditTxinsurNo;
	}

}
