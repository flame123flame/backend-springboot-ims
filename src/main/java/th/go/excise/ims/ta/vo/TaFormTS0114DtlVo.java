package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TaFormTS0114DtlVo {

	private String formTs0114DtlId;
	private String recNo;
	private Date taxDate;
	private String dutyTypeText;
	private BigDecimal taxAmt;
	private BigDecimal fineAmt;
	private BigDecimal extraAmt;
	private BigDecimal moiAmt;
	private BigDecimal sumAmt;

	public String getFormTs0114DtlId() {
		return formTs0114DtlId;
	}

	public void setFormTs0114DtlId(String formTs0114DtlId) {
		this.formTs0114DtlId = formTs0114DtlId;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public Date getTaxDate() {
		return taxDate;
	}

	public void setTaxDate(Date taxDate) {
		this.taxDate = taxDate;
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

	public BigDecimal getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

}
