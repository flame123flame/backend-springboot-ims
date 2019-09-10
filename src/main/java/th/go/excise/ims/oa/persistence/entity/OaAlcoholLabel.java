
package th.go.excise.ims.oa.persistence.entity;

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
@Table(name = "OA_ALCOHOL_LABEL")
public class OaAlcoholLabel
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_LABEL_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_LABEL_GEN", sequenceName = "OA_ALCOHOL_LABEL_SEQ", allocationSize = 1)
    @Column(name = "OA_ALCOHOL_LABEL_ID")
    private BigDecimal oaAlcoholLabelId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "AUDIT_DATE")
    private Date auditDate;
    @Column(name = "LABEL")
    private String label;
    @Column(name = "EVIDENCE")
    private String evidence;
    @Column(name = "RECEIVE")
    private BigDecimal receive;
    @Column(name = "PAY")
    private BigDecimal pay;
    @Column(name = "BALANCE")
    private BigDecimal balance;

    public BigDecimal getOaAlcoholLabelId() {
        return oaAlcoholLabelId;
    }

    public void setOaAlcoholLabelId(BigDecimal oaAlcoholLabelId) {
        this.oaAlcoholLabelId = oaAlcoholLabelId;
    }

    public BigDecimal getOaAlcoholId() {
        return oaAlcoholId;
    }

    public void setOaAlcoholId(BigDecimal oaAlcoholId) {
        this.oaAlcoholId = oaAlcoholId;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public BigDecimal getReceive() {
        return receive;
    }

    public void setReceive(BigDecimal receive) {
        this.receive = receive;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
