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
@Table(name = "TA_PAPER_PR01_D")
public class TaPaperPr01D extends BaseEntity {

	private static final long serialVersionUID = 728067010507602425L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR01_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR01_D_GEN", sequenceName = "TA_PAPER_PR01_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR01_D_SEQ")
	private Long paperPr01DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "MATERIAL_DESC")
	private String materialDesc;
	@Column(name = "INPUT_MATERIAL_QTY")
	private BigDecimal inputMaterialQty;
	@Column(name = "DAILY_ACCOUNT_QTY")
	private BigDecimal dailyAccountQty;
	@Column(name = "MONTH_STATEMENT_QTY")
	private BigDecimal monthStatementQty;
	@Column(name = "EXTERNAL_DATA_QTY")
	private BigDecimal externalDataQty;
	@Column(name = "MAX_DIFF_QTY")
	private BigDecimal maxDiffQty;

	public Long getPaperPr01DSeq() {
		return paperPr01DSeq;
	}

	public void setPaperPr01DSeq(Long paperPr01DSeq) {
		this.paperPr01DSeq = paperPr01DSeq;
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

	public BigDecimal getInputMaterialQty() {
		return inputMaterialQty;
	}

	public void setInputMaterialQty(BigDecimal inputMaterialQty) {
		this.inputMaterialQty = inputMaterialQty;
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
