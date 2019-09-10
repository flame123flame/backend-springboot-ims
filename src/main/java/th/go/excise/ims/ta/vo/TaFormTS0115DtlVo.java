package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TaFormTS0115DtlVo {

	private String formTs0115DtlId;
	private String recNo;
	private Date recDate;
	private String dutyTypeText;
	private BigDecimal taxAmt;
	private BigDecimal fineAmt;
	private BigDecimal extraAmt;
	private BigDecimal moiAmt;
	private BigDecimal sumTaxAmt;

	public String getFormTs0115DtlId() {
		return formTs0115DtlId;
	}

	public void setFormTs0115DtlId(String formTs0115DtlId) {
		this.formTs0115DtlId = formTs0115DtlId;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getDutyTypeText() {
		return dutyTypeText;
	}

	public void setDutyTypeText(String dutyTypeText) {
		this.dutyTypeText = dutyTypeText;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getFineAmt() {
		return fineAmt;
	}

	public void setFineAmt(BigDecimal fineAmt) {
		this.fineAmt = fineAmt;
	}

	public BigDecimal getExtraAmt() {
		return extraAmt;
	}

	public void setExtraAmt(BigDecimal extraAmt) {
		this.extraAmt = extraAmt;
	}

	public BigDecimal getMoiAmt() {
		return moiAmt;
	}

	public void setMoiAmt(BigDecimal moiAmt) {
		this.moiAmt = moiAmt;
	}

	public BigDecimal getSumTaxAmt() {
		return sumTaxAmt;
	}

	public void setSumTaxAmt(BigDecimal sumTaxAmt) {
		this.sumTaxAmt = sumTaxAmt;
	}

}
