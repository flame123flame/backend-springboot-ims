
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
@Table(name = "IA_CHECK_STAMP")
public class IaCheckStamp
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6168941642421669394L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHECK_STAMP_GEN")
    @SequenceGenerator(name = "IA_CHECK_STAMP_GEN", sequenceName = "IA_CHECK_STAMP_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "DATE_REC_PAY_RE")
    private Date dateRecPayRe;
    @Column(name = "STATUS_REC_PAY")
    private String statusRecPay;
    @Column(name = "AGEN_ACCEPT_PAY_STAMPS")
    private String agenAcceptPayStamps;
    @Column(name = "BOOK_NUM_REQUEST_STAMP")
    private String bookNumRequestStamp;
    @Column(name = "DATE_REQUEST_STAMP")
    private Date dateRequestStamp;
    @Column(name = "BOOK_NUM_SEND_STAMPS")
    private String bookNumSendStamps;
    @Column(name = "DATE_SEND_STAMPS")
    private Date dateSendStamps;
    @Column(name = "LEAF_NUM5")
    private String leafNum5;
    @Column(name = "DOWN_DATE")
    private Date downDate;
    @Column(name = "COUNT_DATE")
    private Date countDate;
    @Column(name = "REVIEWER1")
    private String reviewer1;
    @Column(name = "REVIEWER2")
    private String reviewer2;
    @Column(name = "REVIEWER3")
    private String reviewer3;
    @Column(name = "STAMP_TYPE_PACKSIZE")
    private String stampTypePacksize;
    @Column(name = "NUM_BOOK")
    private BigDecimal numBook;
    @Column(name = "NUM_HOROSCOPE")
    private BigDecimal numHoroscope;
    @Column(name = "PRINT_BAHT_PER_HOR")
    private BigDecimal printBahtPerHor;
    @Column(name = "INCLUD_PRINT_FEE_B")
    private BigDecimal includPrintFeeB;
    @Column(name = "TAX_BAHT")
    private BigDecimal taxBaht;
    @Column(name = "STAMP_CODE_START")
    private String stampCodeStart;
    @Column(name = "STAMP_CODE_END")
    private String stampCodeEnd;
    @Column(name = "OFF_CODE")
    private String offCode;
    @Column(name = "OFF_NAME")
    private String offName;
    @Column(name = "OFF_SHORT_NAME")
    private String offShortName;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getDateRecPayRe() {
        return dateRecPayRe;
    }

    public void setDateRecPayRe(Date dateRecPayRe) {
        this.dateRecPayRe = dateRecPayRe;
    }

    public String getStatusRecPay() {
        return statusRecPay;
    }

    public void setStatusRecPay(String statusRecPay) {
        this.statusRecPay = statusRecPay;
    }

    public String getAgenAcceptPayStamps() {
        return agenAcceptPayStamps;
    }

    public void setAgenAcceptPayStamps(String agenAcceptPayStamps) {
        this.agenAcceptPayStamps = agenAcceptPayStamps;
    }

    public String getBookNumRequestStamp() {
        return bookNumRequestStamp;
    }

    public void setBookNumRequestStamp(String bookNumRequestStamp) {
        this.bookNumRequestStamp = bookNumRequestStamp;
    }

    public Date getDateRequestStamp() {
        return dateRequestStamp;
    }

    public void setDateRequestStamp(Date dateRequestStamp) {
        this.dateRequestStamp = dateRequestStamp;
    }

    public String getBookNumSendStamps() {
        return bookNumSendStamps;
    }

    public void setBookNumSendStamps(String bookNumSendStamps) {
        this.bookNumSendStamps = bookNumSendStamps;
    }

    public Date getDateSendStamps() {
        return dateSendStamps;
    }

    public void setDateSendStamps(Date dateSendStamps) {
        this.dateSendStamps = dateSendStamps;
    }

    public String getLeafNum5() {
        return leafNum5;
    }

    public void setLeafNum5(String leafNum5) {
        this.leafNum5 = leafNum5;
    }

    public Date getDownDate() {
        return downDate;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public String getReviewer1() {
        return reviewer1;
    }

    public void setReviewer1(String reviewer1) {
        this.reviewer1 = reviewer1;
    }

    public String getReviewer2() {
        return reviewer2;
    }

    public void setReviewer2(String reviewer2) {
        this.reviewer2 = reviewer2;
    }

    public String getReviewer3() {
        return reviewer3;
    }

    public void setReviewer3(String reviewer3) {
        this.reviewer3 = reviewer3;
    }

    public String getStampTypePacksize() {
        return stampTypePacksize;
    }

    public void setStampTypePacksize(String stampTypePacksize) {
        this.stampTypePacksize = stampTypePacksize;
    }

    public BigDecimal getNumBook() {
        return numBook;
    }

    public void setNumBook(BigDecimal numBook) {
        this.numBook = numBook;
    }

    public BigDecimal getNumHoroscope() {
        return numHoroscope;
    }

    public void setNumHoroscope(BigDecimal numHoroscope) {
        this.numHoroscope = numHoroscope;
    }

    public BigDecimal getPrintBahtPerHor() {
        return printBahtPerHor;
    }

    public void setPrintBahtPerHor(BigDecimal printBahtPerHor) {
        this.printBahtPerHor = printBahtPerHor;
    }

    public BigDecimal getIncludPrintFeeB() {
        return includPrintFeeB;
    }

    public void setIncludPrintFeeB(BigDecimal includPrintFeeB) {
        this.includPrintFeeB = includPrintFeeB;
    }

    public BigDecimal getTaxBaht() {
        return taxBaht;
    }

    public void setTaxBaht(BigDecimal taxBaht) {
        this.taxBaht = taxBaht;
    }

    public String getStampCodeStart() {
        return stampCodeStart;
    }

    public void setStampCodeStart(String stampCodeStart) {
        this.stampCodeStart = stampCodeStart;
    }

    public String getStampCodeEnd() {
        return stampCodeEnd;
    }

    public void setStampCodeEnd(String stampCodeEnd) {
        this.stampCodeEnd = stampCodeEnd;
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

    public String getOffShortName() {
        return offShortName;
    }

    public void setOffShortName(String offShortName) {
        this.offShortName = offShortName;
    }

}
