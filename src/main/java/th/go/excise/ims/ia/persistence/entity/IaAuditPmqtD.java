
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
@Table(name = "IA_AUDIT_PMQT_D")
public class IaAuditPmqtD
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3983298209829478409L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PMQT_D_GEN")
    @SequenceGenerator(name = "IA_AUDIT_PMQT_D_GEN", sequenceName = "IA_AUDIT_PMQT_D_SEQ", allocationSize = 1)
    @Column(name = "AUDIT_PMQT_D_ID")
    private BigDecimal auditPmqtDId;
    @Column(name = "AUDIT_PMQT_NO")
    private String auditPmqtNo;
    @Column(name = "AUDIT_RESULT")
    private String auditResult;
    @Column(name = "PM_QT_D_SEQ")
    private BigDecimal pmQtDSeq;
    @Column(name = "OFF_CODE")
    private String offCode;
    @Column(name = "FORM_CODE")
    private String formCode;
    @Column(name = "TOPIC_LEVEL")
    private String topicLevel;
    @Column(name = "TOPIC_CODE")
    private String topicCode;
    @Column(name = "TOPIC_NAME")
    private String topicName;
    @Column(name = "TOPIC_ANSWER")
    private String topicAnswer;
    @Column(name = "TOPIC_RESULT")
    private String topicResult;

    public BigDecimal getAuditPmqtDId() {
        return auditPmqtDId;
    }

    public void setAuditPmqtDId(BigDecimal auditPmqtDId) {
        this.auditPmqtDId = auditPmqtDId;
    }

    public String getAuditPmqtNo() {
        return auditPmqtNo;
    }

    public void setAuditPmqtNo(String auditPmqtNo) {
        this.auditPmqtNo = auditPmqtNo;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public BigDecimal getPmQtDSeq() {
        return pmQtDSeq;
    }

    public void setPmQtDSeq(BigDecimal pmQtDSeq) {
        this.pmQtDSeq = pmQtDSeq;
    }

    public String getOffCode() {
        return offCode;
    }

    public void setOffCode(String offCode) {
        this.offCode = offCode;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getTopicLevel() {
        return topicLevel;
    }

    public void setTopicLevel(String topicLevel) {
        this.topicLevel = topicLevel;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicAnswer() {
        return topicAnswer;
    }

    public void setTopicAnswer(String topicAnswer) {
        this.topicAnswer = topicAnswer;
    }

    public String getTopicResult() {
        return topicResult;
    }

    public void setTopicResult(String topicResult) {
        this.topicResult = topicResult;
    }

}
