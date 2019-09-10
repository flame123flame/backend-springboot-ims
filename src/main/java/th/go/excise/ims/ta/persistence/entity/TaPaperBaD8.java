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
@Table(name = "TA_PAPER_BA_D8")
public class TaPaperBaD8 extends BaseEntity {

	private static final long serialVersionUID = -6777608496697759226L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D8_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D8_GEN", sequenceName = "TA_PAPER_BA_D8_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D8_SEQ")
	private Long paperBaD8Seq;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "INCOME_CURRENT_YEAR_AMT")
	private BigDecimal incomeCurrentYearAmt;
	@Column(name = "INCOME_LAST_YEAR1_AMT")
	private BigDecimal incomeLastYear1Amt;
	@Column(name = "DIFF_INCOME_LAST_YEAR1_AMT")
	private BigDecimal diffIncomeLastYear1Amt;
	@Column(name = "DIFF_INCOME_LAST_YEAR1_PNT")
	private BigDecimal diffIncomeLastYear1Pnt;
	@Column(name = "INCOME_LAST_YEAR2_AMT")
	private BigDecimal incomeLastYear2Amt;
	@Column(name = "DIFF_INCOME_LAST_YEAR2_AMT")
	private BigDecimal diffIncomeLastYear2Amt;
	@Column(name = "DIFF_INCOME_LAST_YEAR2_PNT")
	private BigDecimal diffIncomeLastYear2Pnt;
	@Column(name = "INCOME_LAST_YEAR3_AMT")
	private BigDecimal incomeLastYear3Amt;
	@Column(name = "DIFF_INCOME_LAST_YEAR3_AMT")
	private BigDecimal diffIncomeLastYear3Amt;
	@Column(name = "DIFF_INCOME_LAST_YEAR3_PNT")
	private BigDecimal diffIncomeLastYear3Pnt;

	public Long getPaperBaD8Seq() {
		return paperBaD8Seq;
	}

	public void setPaperBaD8Seq(Long paperBaD8Seq) {
		this.paperBaD8Seq = paperBaD8Seq;
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

	public BigDecimal getIncomeCurrentYearAmt() {
		return incomeCurrentYearAmt;
	}

	public void setIncomeCurrentYearAmt(BigDecimal incomeCurrentYearAmt) {
		this.incomeCurrentYearAmt = incomeCurrentYearAmt;
	}

	public BigDecimal getIncomeLastYear1Amt() {
		return incomeLastYear1Amt;
	}

	public void setIncomeLastYear1Amt(BigDecimal incomeLastYear1Amt) {
		this.incomeLastYear1Amt = incomeLastYear1Amt;
	}

	public BigDecimal getDiffIncomeLastYear1Amt() {
		return diffIncomeLastYear1Amt;
	}

	public void setDiffIncomeLastYear1Amt(BigDecimal diffIncomeLastYear1Amt) {
		this.diffIncomeLastYear1Amt = diffIncomeLastYear1Amt;
	}

	public BigDecimal getDiffIncomeLastYear1Pnt() {
		return diffIncomeLastYear1Pnt;
	}

	public void setDiffIncomeLastYear1Pnt(BigDecimal diffIncomeLastYear1Pnt) {
		this.diffIncomeLastYear1Pnt = diffIncomeLastYear1Pnt;
	}

	public BigDecimal getIncomeLastYear2Amt() {
		return incomeLastYear2Amt;
	}

	public void setIncomeLastYear2Amt(BigDecimal incomeLastYear2Amt) {
		this.incomeLastYear2Amt = incomeLastYear2Amt;
	}

	public BigDecimal getDiffIncomeLastYear2Amt() {
		return diffIncomeLastYear2Amt;
	}

	public void setDiffIncomeLastYear2Amt(BigDecimal diffIncomeLastYear2Amt) {
		this.diffIncomeLastYear2Amt = diffIncomeLastYear2Amt;
	}

	public BigDecimal getDiffIncomeLastYear2Pnt() {
		return diffIncomeLastYear2Pnt;
	}

	public void setDiffIncomeLastYear2Pnt(BigDecimal diffIncomeLastYear2Pnt) {
		this.diffIncomeLastYear2Pnt = diffIncomeLastYear2Pnt;
	}

	public BigDecimal getIncomeLastYear3Amt() {
		return incomeLastYear3Amt;
	}

	public void setIncomeLastYear3Amt(BigDecimal incomeLastYear3Amt) {
		this.incomeLastYear3Amt = incomeLastYear3Amt;
	}

	public BigDecimal getDiffIncomeLastYear3Amt() {
		return diffIncomeLastYear3Amt;
	}

	public void setDiffIncomeLastYear3Amt(BigDecimal diffIncomeLastYear3Amt) {
		this.diffIncomeLastYear3Amt = diffIncomeLastYear3Amt;
	}

	public BigDecimal getDiffIncomeLastYear3Pnt() {
		return diffIncomeLastYear3Pnt;
	}

	public void setDiffIncomeLastYear3Pnt(BigDecimal diffIncomeLastYear3Pnt) {
		this.diffIncomeLastYear3Pnt = diffIncomeLastYear3Pnt;
	}

}
