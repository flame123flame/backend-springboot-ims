
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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_INC_SEND_D")
public class IaAuditIncSendD extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6112191036977073613L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_SEND_D_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_SEND_D_GEN", sequenceName = "IA_AUDIT_INC_SEND_D_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_INC_SEND_D_SEQ")
	private BigDecimal auditIncSendDSeq;
	@Column(name = "INCSEND_NO")
	private String incsendNo;
	@Column(name = "INCSEND_TRN_DATE")
	private Date incsendTrnDate;
	@Column(name = "INCSEND_GF_DATE")
	private Date incsendGfDate;
	@Column(name = "INCSEND_PERIOD")
	private BigDecimal incsendPeriod;
	@Column(name = "INCSEND_GF_OFFCODE")
	private String incsendGfOffcode;
	@Column(name = "INCSEND_ACTCOST_CENT")
	private String incsendActcostCent;
	@Column(name = "INCSEND_REF_NO")
	private String incsendRefNo;
	@Column(name = "INCSEND_CNT")
	private BigDecimal incsendCnt;
	@Column(name = "INCSEND_TOTAL_AMT")
	private BigDecimal incsendTotalAmt;
	@Column(name = "INCSEND_AMOUNT")
	private BigDecimal incsendAmount;
	@Column(name = "INCSEND_EDC")
	private BigDecimal incsendEdc;
	@Column(name = "INCSEND_EDC_LICENSE")
	private BigDecimal incsendEdcLicense;
	@Column(name = "INCSEND_ACC_CASH")
	private String incsendAccCash;
	@Column(name = "INCSEND_ACC_PAY_IN")
	private String incsendAccPayIn;
	@Column(name = "INCSEND_AMT_DELIVERY")
	private BigDecimal incsendAmtDelivery;
	@Column(name = "INCSEND_INC_KTB")
	private BigDecimal incsendIncKtb;
	@Column(name = "INCSEND_INC_STM")
	private String incsendIncStm;
	@Column(name = "INCSEND_INC_115010")
	private BigDecimal incsendInc115010;
	@Column(name = "INCSEND_INC_116010")
	private BigDecimal incsendInc116010;
	@Column(name = "INCSEND_NOTE")
	private String incsendNote;
	@Column(name = "INC_TRANSFER_115010_116010")
	private String incTransfer115010_116010;
	
	public BigDecimal getAuditIncSendDSeq() {
		return auditIncSendDSeq;
	}

	public void setAuditIncSendDSeq(BigDecimal auditIncSendDSeq) {
		this.auditIncSendDSeq = auditIncSendDSeq;
	}

	public String getIncsendNo() {
		return incsendNo;
	}

	public void setIncsendNo(String incsendNo) {
		this.incsendNo = incsendNo;
	}

	public Date getIncsendTrnDate() {
		return incsendTrnDate;
	}

	public void setIncsendTrnDate(Date incsendTrnDate) {
		this.incsendTrnDate = incsendTrnDate;
	}

	public Date getIncsendGfDate() {
		return incsendGfDate;
	}

	public void setIncsendGfDate(Date incsendGfDate) {
		this.incsendGfDate = incsendGfDate;
	}

	public BigDecimal getIncsendPeriod() {
		return incsendPeriod;
	}

	public void setIncsendPeriod(BigDecimal incsendPeriod) {
		this.incsendPeriod = incsendPeriod;
	}

	public String getIncsendGfOffcode() {
		return incsendGfOffcode;
	}

	public void setIncsendGfOffcode(String incsendGfOffcode) {
		this.incsendGfOffcode = incsendGfOffcode;
	}

	public String getIncsendActcostCent() {
		return incsendActcostCent;
	}

	public void setIncsendActcostCent(String incsendActcostCent) {
		this.incsendActcostCent = incsendActcostCent;
	}

	public String getIncsendRefNo() {
		return incsendRefNo;
	}

	public void setIncsendRefNo(String incsendRefNo) {
		this.incsendRefNo = incsendRefNo;
	}

	public BigDecimal getIncsendCnt() {
		return incsendCnt;
	}

	public void setIncsendCnt(BigDecimal incsendCnt) {
		this.incsendCnt = incsendCnt;
	}

	public BigDecimal getIncsendTotalAmt() {
		return incsendTotalAmt;
	}

	public void setIncsendTotalAmt(BigDecimal incsendTotalAmt) {
		this.incsendTotalAmt = incsendTotalAmt;
	}

	public BigDecimal getIncsendAmount() {
		return incsendAmount;
	}

	public void setIncsendAmount(BigDecimal incsendAmount) {
		this.incsendAmount = incsendAmount;
	}

	public BigDecimal getIncsendEdc() {
		return incsendEdc;
	}

	public void setIncsendEdc(BigDecimal incsendEdc) {
		this.incsendEdc = incsendEdc;
	}

	public BigDecimal getIncsendEdcLicense() {
		return incsendEdcLicense;
	}

	public void setIncsendEdcLicense(BigDecimal incsendEdcLicense) {
		this.incsendEdcLicense = incsendEdcLicense;
	}

	public String getIncsendAccCash() {
		return incsendAccCash;
	}

	public void setIncsendAccCash(String incsendAccCash) {
		this.incsendAccCash = incsendAccCash;
	}

	public String getIncsendAccPayIn() {
		return incsendAccPayIn;
	}

	public void setIncsendAccPayIn(String incsendAccPayIn) {
		this.incsendAccPayIn = incsendAccPayIn;
	}

	public BigDecimal getIncsendAmtDelivery() {
		return incsendAmtDelivery;
	}

	public void setIncsendAmtDelivery(BigDecimal incsendAmtDelivery) {
		this.incsendAmtDelivery = incsendAmtDelivery;
	}

	public BigDecimal getIncsendIncKtb() {
		return incsendIncKtb;
	}

	public void setIncsendIncKtb(BigDecimal incsendIncKtb) {
		this.incsendIncKtb = incsendIncKtb;
	}

	public String getIncsendIncStm() {
		return incsendIncStm;
	}

	public void setIncsendIncStm(String incsendIncStm) {
		this.incsendIncStm = incsendIncStm;
	}

	public BigDecimal getIncsendInc115010() {
		return incsendInc115010;
	}

	public void setIncsendInc115010(BigDecimal incsendInc115010) {
		this.incsendInc115010 = incsendInc115010;
	}

	public BigDecimal getIncsendInc116010() {
		return incsendInc116010;
	}

	public void setIncsendInc116010(BigDecimal incsendInc116010) {
		this.incsendInc116010 = incsendInc116010;
	}

	public String getIncsendNote() {
		return incsendNote;
	}

	public void setIncsendNote(String incsendNote) {
		this.incsendNote = incsendNote;
	}

	public String getIncTransfer115010_116010() {
		return incTransfer115010_116010;
	}

	public void setIncTransfer115010_116010(String incTransfer115010_116010) {
		this.incTransfer115010_116010 = incTransfer115010_116010;
	}

}