package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_PAPER_BA_D7")
public class TaPaperBaD7 extends BaseEntity {

	private static final long serialVersionUID = -1888614482135072576L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D7_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D7_GEN", sequenceName = "TA_PAPER_BA_D7_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D7_SEQ")
	private Long paperBaD7Seq;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "INCOME_AMT")
	private BigDecimal incomeAmt;
	@Column(name = "DIFF_INCOME_AMT")
	private BigDecimal diffIncomeAmt;
	@Column(name = "DIFF_INCOME_PNT")
	private BigDecimal diffIncomePnt;

	public Long getPaperBaD7Seq() {
		return paperBaD7Seq;
	}

	public void setPaperBaD7Seq(Long paperBaD7Seq) {
		this.paperBaD7Seq = paperBaD7Seq;
	}

	public String getPaperBaNumber() {
		return paperBaNumber;
	}

	public void setPaperBaNumber(String paperBaNumber) {
		this.paperBaNumber = paperBaNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public BigDecimal getIncomeAmt() {
		return incomeAmt;
	}

	public void setIncomeAmt(BigDecimal incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

	public BigDecimal getDiffIncomeAmt() {
		return diffIncomeAmt;
	}

	public void setDiffIncomeAmt(BigDecimal diffIncomeAmt) {
		this.diffIncomeAmt = diffIncomeAmt;
	}

	public BigDecimal getDiffIncomePnt() {
		return diffIncomePnt;
	}

	public void setDiffIncomePnt(BigDecimal diffIncomePnt) {
		this.diffIncomePnt = diffIncomePnt;
	}

}
