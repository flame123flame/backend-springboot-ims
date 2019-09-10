
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
@Table(name = "IA_RENT_HOUSE")
public class IaRentHouse
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4251996054248048452L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RENT_HOUSE_GEN")
    @SequenceGenerator(name = "IA_RENT_HOUSE_GEN", sequenceName = "IA_RENT_HOUSE_SEQ", allocationSize = 1)
    @Column(name = "RENT_HOUSE_ID")
    private Long rentHouseId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "AFFILIATION")
    private String affiliation;
    @Column(name = "PAYMENT_COST")
    private String paymentCost;
    @Column(name = "PAYMENT_FOR")
    private String paymentFor;
    @Column(name = "PERIOD")
    private String period;
    @Column(name = "REF_RECEIPTS")
    private BigDecimal refReceipts;
    @Column(name = "BILL_AMOUNT")
    private BigDecimal billAmount;
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Column(name = "REQUEST_NO")
    private String requestNo;
    @Column(name = "NOT_OVER")
    private BigDecimal notOver;
    @Column(name = "PERIOD_WITHDRAW")
    private String periodWithdraw;
    @Column(name = "TOTAL_MONTH")
    private BigDecimal totalMonth;
    @Column(name = "TOTAL_WITHDRAW")
    private BigDecimal totalWithdraw;
    @Column(name = "RECEIPTS")
    private String receipts;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PERIOD_WITHDRAW_TO")
    private String periodWithdrawTo;
    @Column(name = "IA_DIS_REQ_ID")
    private Long iaDisReqId;
    @Column(name ="FORM_6005_NO")
    private String form6005No;
    
    public Long getRentHouseId() {
        return rentHouseId;
    }

    public void setRentHouseId(Long rentHouseId) {
        this.rentHouseId = rentHouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getPaymentCost() {
        return paymentCost;
    }

    public void setPaymentCost(String paymentCost) {
        this.paymentCost = paymentCost;
    }

    public String getPaymentFor() {
        return paymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public BigDecimal getRefReceipts() {
        return refReceipts;
    }

    public void setRefReceipts(BigDecimal refReceipts) {
        this.refReceipts = refReceipts;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public BigDecimal getNotOver() {
        return notOver;
    }

    public void setNotOver(BigDecimal notOver) {
        this.notOver = notOver;
    }

    public String getPeriodWithdraw() {
        return periodWithdraw;
    }

    public void setPeriodWithdraw(String periodWithdraw) {
        this.periodWithdraw = periodWithdraw;
    }

    public BigDecimal getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(BigDecimal totalMonth) {
        this.totalMonth = totalMonth;
    }

    public BigDecimal getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(BigDecimal totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public String getReceipts() {
        return receipts;
    }

    public void setReceipts(String receipts) {
        this.receipts = receipts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriodWithdrawTo() {
        return periodWithdrawTo;
    }

    public void setPeriodWithdrawTo(String periodWithdrawTo) {
        this.periodWithdrawTo = periodWithdrawTo;
    }

    public Long getIaDisReqId() {
        return iaDisReqId;
    }

    public void setIaDisReqId(Long iaDisReqId) {
        this.iaDisReqId = iaDisReqId;
    }

	public String getForm6005No() {
		return form6005No;
	}

	public void setForm6005No(String form6005No) {
		this.form6005No = form6005No;
	}

}
