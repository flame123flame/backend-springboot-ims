
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
@Table(name = "IA_CHECK_CONTROL_REGIS")
public class IaCheckControlRegis
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -99861621463939785L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHECK_CONTROL_REGIS_GEN")
    @SequenceGenerator(name = "IA_CHECK_CONTROL_REGIS_GEN", sequenceName = "IA_CHECK_CONTROL_REGIS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;
    @Column(name = "REF_PAYMENT")
    private String refPayment;
    @Column(name = "BANK_NAME")
    private String bankName;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "BUDGET_TYPE")
    private String budgetType;
    @Column(name = "ITEM_DESC")
    private String itemDesc;
    @Column(name = "PAYEE")
    private String payee;
    @Column(name = "OFFCODE")
    private String offcode;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getRefPayment() {
        return refPayment;
    }

    public void setRefPayment(String refPayment) {
        this.refPayment = refPayment;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getOffcode() {
        return offcode;
    }

    public void setOffcode(String offcode) {
        this.offcode = offcode;
    }

}
