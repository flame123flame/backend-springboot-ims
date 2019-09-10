
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
@Table(name = "IA_RENT_HOUSE1")
public class IaRentHouse1
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RENT_HOUSE1_GEN")
    @SequenceGenerator(name = "IA_RENT_HOUSE1_GEN", sequenceName = "IA_RENT_HOUSE1_SEQ", allocationSize = 1)
    @Column(name = "RENT_HOUSE1_ID")
    private Long rentHouse1Id;
    @Column(name = "RENT_HOUSE_ID")
    private Long rentHouseId;
    @Column(name = "DOCUMENT_SEQ")
    private BigDecimal documentSeq;
    @Column(name = "DOC_RECEIPT_NO")
    private String docReceiptNo;
    @Column(name = "DOC_RECEIPT_DATE")
    private Date docReceiptDate;
    @Column(name = "DOC_RECEIPT_AMOUNT")
    private BigDecimal docReceiptAmount;

    public Long getRentHouse1Id() {
        return rentHouse1Id;
    }

    public void setRentHouse1Id(Long rentHouse1Id) {
        this.rentHouse1Id = rentHouse1Id;
    }

    public Long getRentHouseId() {
        return rentHouseId;
    }

    public void setRentHouseId(Long rentHouseId) {
        this.rentHouseId = rentHouseId;
    }

    public BigDecimal getDocumentSeq() {
        return documentSeq;
    }

    public void setDocumentSeq(BigDecimal documentSeq) {
        this.documentSeq = documentSeq;
    }

    public String getDocReceiptNo() {
        return docReceiptNo;
    }

    public void setDocReceiptNo(String docReceiptNo) {
        this.docReceiptNo = docReceiptNo;
    }

    public Date getDocReceiptDate() {
        return docReceiptDate;
    }

    public void setDocReceiptDate(Date docReceiptDate) {
        this.docReceiptDate = docReceiptDate;
    }

    public BigDecimal getDocReceiptAmount() {
        return docReceiptAmount;
    }

    public void setDocReceiptAmount(BigDecimal docReceiptAmount) {
        this.docReceiptAmount = docReceiptAmount;
    }

}
