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
@Table(name = "TA_PAPER_PR02_D")
public class TaPaperPr02D extends BaseEntity {

	private static final long serialVersionUID = 387269441235406380L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR02_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR02_D_GEN", sequenceName = "TA_PAPER_PR02_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR02_D_SEQ")
	private Long paperPr02DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "MATERIAL_DESC")
	private String materialDesc;
	@Column(name = "OUTPUT_MATERIAL_QTY")
	private BigDecimal outputMaterialQty;
	@Column(name = "DAILY_ACCOUNT_QTY")
	private BigDecimal dailyAccountQty;
	@Column(name = "MONTH_STATEMENT_QTY")
	private BigDecimal monthStatementQty;
	@Column(name = "EXTERNAL_DATA_QTY")
	private BigDecimal externalDataQty;
	@Column(name = "MAX_DIFF_QTY")
	private BigDecimal maxDiffQty;

	public Long getPaperPr02DSeq() {
		return paperPr02DSeq;
	}

	public void setPaperPr02DSeq(Long paperPr02DSeq) {
		this.paperPr02DSeq = paperPr02DSeq;
	}

	public String getPaperPrNumber() {
		return paperPrNumber;
	}

	public void setPaperPrNumber(String paperPrNumber) {
		this.paperPrNumber = paperPrNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public BigDecimal getOutputMaterialQty() {
		return outputMaterialQty;
	}

	public void setOutputMaterialQty(BigDecimal outputMaterialQty) {
		this.outputMaterialQty = outputMaterialQty;
	}

	public BigDecimal getDailyAccountQty() {
		return dailyAccountQty;
	}

	public void setDailyAccountQty(BigDecimal dailyAccountQty) {
		this.dailyAccountQty = dailyAccountQty;
	}

	public BigDecimal getMonthStatementQty() {
		return monthStatementQty;
	}

	public void setMonthStatementQty(BigDecimal monthStatementQty) {
		this.monthStatementQty = monthStatementQty;
	}

	public BigDecimal getExternalDataQty() {
		return externalDataQty;
	}

	public void setExternalDataQty(BigDecimal externalDataQty) {
		this.externalDataQty = externalDataQty;
	}

	public BigDecimal getMaxDiffQty() {
		return maxDiffQty;
	}

	public void setMaxDiffQty(BigDecimal maxDiffQty) {
		this.maxDiffQty = maxDiffQty;
	}

}
