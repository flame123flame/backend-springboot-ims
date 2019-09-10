
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_AUDIT_PY1_D")
public class IaAuditPy1D
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8470084181373785546L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PY1_D_GEN")
    @SequenceGenerator(name = "IA_AUDIT_PY1_D_GEN", sequenceName = "IA_AUDIT_PY1_D_SEQ", allocationSize = 1)
    @Column(name = "AUDIT_PY1_D_ID")
    private Long auditPy1DId;
    @Column(name = "AUDIT_PY1_NO")
    private String auditPy1No;
    @Column(name = "TOPIC_DESC")
    private String topicDesc;
    @Column(name = "TOPIC_ANSWER")
    private String topicAnswer;
    @Column(name = "AUDIT_RESULT")
    private String auditResult;

    public Long getAuditPy1DId() {
        return auditPy1DId;
    }

    public void setAuditPy1DId(Long auditPy1DId) {
        this.auditPy1DId = auditPy1DId;
    }

    public String getAuditPy1No() {
        return auditPy1No;
    }

    public void setAuditPy1No(String auditPy1No) {
        this.auditPy1No = auditPy1No;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getTopicAnswer() {
        return topicAnswer;
    }

    public void setTopicAnswer(String topicAnswer) {
        this.topicAnswer = topicAnswer;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
