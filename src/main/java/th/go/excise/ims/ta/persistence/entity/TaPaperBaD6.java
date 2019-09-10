package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PAPER_BA_D6")
public class TaPaperBaD6 extends BaseEntity {

	private static final long serialVersionUID = -7169496241564588808L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D6_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D6_GEN", sequenceName = "TA_PAPER_BA_D6_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D6_SEQ")
	private Long paperBaD6Seq;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "TAX_SUBMISSION_DATE")
	private LocalDate taxSubmissionDate;
	@Column(name = "ANA_TAX_SUBMISSION_DATE")
	private LocalDate anaTaxSubmissionDate;
	@Column(name = "RESULT_TAX_SUBMISSION")
	private String resultTaxSubmission;

	public Long getPaperBaD6Seq() {
		return paperBaD6Seq;
	}

	public void setPaperBaD6Seq(Long paperBaD6Seq) {
		this.paperBaD6Seq = paperBaD6Seq;
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

	public LocalDate getTaxSubmissionDate() {
		return taxSubmissionDate;
	}

	public void setTaxSubmissionDate(LocalDate taxSubmissionDate) {
		this.taxSubmissionDate = taxSubmissionDate;
	}

	public LocalDate getAnaTaxSubmissionDate() {
		return anaTaxSubmissionDate;
	}

	public void setAnaTaxSubmissionDate(LocalDate anaTaxSubmissionDate) {
		this.anaTaxSubmissionDate = anaTaxSubmissionDate;
	}

	public String getResultTaxSubmission() {
		return resultTaxSubmission;
	}

	public void setResultTaxSubmission(String resultTaxSubmission) {
		this.resultTaxSubmission = resultTaxSubmission;
	}

}
