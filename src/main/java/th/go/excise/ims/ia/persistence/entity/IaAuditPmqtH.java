
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_PMQT_H")
public class IaAuditPmqtH
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3755791115993876846L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PMQT_H_GEN")
    @SequenceGenerator(name = "IA_AUDIT_PMQT_H_GEN", sequenceName = "IA_AUDIT_PMQT_H_SEQ", allocationSize = 1)
    @Column(name = "AUDIT_PMQT_H_ID")
    private BigDecimal auditPmqtHId;
    @Column(name = "AUDIT_PMQT_NO")
    private String auditPmqtNo;
    @Column(name = "BUGGET_YEAR")
    private String buggetYear;
    @Column(name = "PM_QT_H_SEQ")
    private BigDecimal pmQtHSeq;
    @Column(name = "OFF_CODE")
    private String offCode;
    @Column(name = "OFF_NAME")
    private String offName;
    @Column(name = "FORM_YEAR")
    private String formYear;
    @Column(name = "FORM_CODE")
    private String formCode;
    @Column(name = "FORM_NAME")
    private String formName;
    @Column(name = "FORM_ROUND")
    private String formRound;
    @Column(name = "FORM_STATUS")
    private String formStatus;
    @Column(name = "FORM_STATUS_DESC")
    private String formStatusDesc;
    @Column(name = "SUMMARY")
    private String summary;
    @Column(name = "PROCESS_BY")
    private String processBy;
    @Column(name = "PROCESS_POSITION")
    private String processPosition;
    @Column(name = "PROCESS_DATE")
    private Date processDate;
    @Column(name = "QT_AUDIT_RESULT")
    private String qtAuditResult;
    @Column(name = "QT_AUDIT_EVIDENT")
    private String qtAuditEvident;
    @Column(name = "QT_AUDIT_SUGGESTION")
    private String qtAuditSuggestion;

    public BigDecimal getAuditPmqtHId() {
        return auditPmqtHId;
    }

    public void setAuditPmqtHId(BigDecimal auditPmqtHId) {
        this.auditPmqtHId = auditPmqtHId;
    }

    public String getAuditPmqtNo() {
        return auditPmqtNo;
    }

    public void setAuditPmqtNo(String auditPmqtNo) {
        this.auditPmqtNo = auditPmqtNo;
    }

    public String getBuggetYear() {
        return buggetYear;
    }

    public void setBuggetYear(String buggetYear) {
        this.buggetYear = buggetYear;
    }

    public BigDecimal getPmQtHSeq() {
        return pmQtHSeq;
    }

    public void setPmQtHSeq(BigDecimal pmQtHSeq) {
        this.pmQtHSeq = pmQtHSeq;
    }

    public String getOffCode() {
        return offCode;
    }

    public void setOffCode(String offCode) {
        this.offCode = offCode;
    }

    public String getOffName() {
        return offName;
    }

    public void setOffName(String offName) {
        this.offName = offName;
    }

    public String getFormYear() {
        return formYear;
    }

    public void setFormYear(String formYear) {
        this.formYear = formYear;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormRound() {
        return formRound;
    }

    public void setFormRound(String formRound) {
        this.formRound = formRound;
    }

    public String getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(String formStatus) {
        this.formStatus = formStatus;
    }

    public String getFormStatusDesc() {
        return formStatusDesc;
    }

    public void setFormStatusDesc(String formStatusDesc) {
        this.formStatusDesc = formStatusDesc;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProcessBy() {
        return processBy;
    }

    public void setProcessBy(String processBy) {
        this.processBy = processBy;
    }

    public String getProcessPosition() {
        return processPosition;
    }

    public void setProcessPosition(String processPosition) {
        this.processPosition = processPosition;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public String getQtAuditResult() {
        return qtAuditResult;
    }

    public void setQtAuditResult(String qtAuditResult) {
        this.qtAuditResult = qtAuditResult;
    }

    public String getQtAuditEvident() {
        return qtAuditEvident;
    }

    public void setQtAuditEvident(String qtAuditEvident) {
        this.qtAuditEvident = qtAuditEvident;
    }

    public String getQtAuditSuggestion() {
        return qtAuditSuggestion;
    }

    public void setQtAuditSuggestion(String qtAuditSuggestion) {
        this.qtAuditSuggestion = qtAuditSuggestion;
    }

}
