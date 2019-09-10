
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
@Table(name = "IA_MEDICAL_RECEIPT")
public class IaMedicalReceipt
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1848994738886245831L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_MEDICAL_RECEIPT_GEN")
    @SequenceGenerator(name = "IA_MEDICAL_RECEIPT_GEN", sequenceName = "IA_MEDICAL_RECEIPT_SEQ", allocationSize = 1)
    @Column(name = "RECEIPT_ID")
    private Long receiptId;
    @Column(name = "RECEIPT_NO")
    private String receiptNo;
    @Column(name = "RECEIPT_AMOUNT")
    private BigDecimal receiptAmount;
    @Column(name = "RECEIPT_TYPE")
    private String receiptType;
    @Column(name = "RECEIPT_DATE")
    private Date receiptDate;
    @Column(name = "ID")
    private Long id;

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
