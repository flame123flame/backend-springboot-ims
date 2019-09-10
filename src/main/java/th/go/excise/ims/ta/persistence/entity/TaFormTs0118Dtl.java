package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "TA_FORM_TS0118_DTL")
public class TaFormTs0118Dtl extends BaseEntity {

	private static final long serialVersionUID = -3993155821628313344L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0118_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0118_DTL_GEN", sequenceName = "TA_FORM_TS0118_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0118_DTL_ID")
	private Long formTs0118DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "DUTY_TYPE_TEXT")
	private String dutyTypeText;
	@Column(name = "GOODS_QTY")
	private BigDecimal goodsQty;
	@Column(name = "GOODS_VALUE")
	private BigDecimal goodsValue;
	@Column(name = "TAX_RATE")
	private BigDecimal taxRate;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "FINE_AMT")
	private BigDecimal fineAmt;
	@Column(name = "EXTRA_AMT")
	private BigDecimal extraAmt;
	@Column(name = "SUM_TAX_AMT")
	private BigDecimal sumTaxAmt;
	@Column(name = "FUND_HEALTH_AMT")
	private BigDecimal fundHealthAmt;
	@Column(name = "FUND_TV_AMT")
	private BigDecimal fundTvAmt;
	@Column(name = "FUND_SPORT_AMT")
	private BigDecimal fundSportAmt;
	@Column(name = "OTHER_AMT")
	private BigDecimal otherAmt;
	@Column(name = "SUM_ALL_TAX_AMT")
	private BigDecimal sumAllTaxAmt;
	@Column(name = "MOI_AMT")
	private BigDecimal moiAmt;
	@Column(name = "NET_TAX_AMT")
	private BigDecimal netTaxAmt;

	public Long getFormTs0118DtlId() {
		return formTs0118DtlId;
	}

	public void setFormTs0118DtlId(Long formTs0118DtlId) {
		this.formTs0118DtlId = formTs0118DtlId;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public String getDutyTypeText() {
		return dutyTypeText;
	}

	public void setDutyTypeText(String dutyTypeText) {
		this.dutyTypeText = dutyTypeText;
	}

	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	public BigDecimal getGoodsValue() {
		return goodsValue;
	}

	public void setGoodsValue(BigDecimal goodsValue) {
		this.goodsValue = goodsValue;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
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

	public BigDecimal getSumTaxAmt() {
		return sumTaxAmt;
	}

	public void setSumTaxAmt(BigDecimal sumTaxAmt) {
		this.sumTaxAmt = sumTaxAmt;
	}

	public BigDecimal getFundHealthAmt() {
		return fundHealthAmt;
	}

	public void setFundHealthAmt(BigDecimal fundHealthAmt) {
		this.fundHealthAmt = fundHealthAmt;
	}

	public BigDecimal getFundTvAmt() {
		return fundTvAmt;
	}

	public void setFundTvAmt(BigDecimal fundTvAmt) {
		this.fundTvAmt = fundTvAmt;
	}

	public BigDecimal getFundSportAmt() {
		return fundSportAmt;
	}

	public void setFundSportAmt(BigDecimal fundSportAmt) {
		this.fundSportAmt = fundSportAmt;
	}

	public BigDecimal getOtherAmt() {
		return otherAmt;
	}

	public void setOtherAmt(BigDecimal otherAmt) {
		this.otherAmt = otherAmt;
	}

	public BigDecimal getSumAllTaxAmt() {
		return sumAllTaxAmt;
	}

	public void setSumAllTaxAmt(BigDecimal sumAllTaxAmt) {
		this.sumAllTaxAmt = sumAllTaxAmt;
	}

	public BigDecimal getMoiAmt() {
		return moiAmt;
	}

	public void setMoiAmt(BigDecimal moiAmt) {
		this.moiAmt = moiAmt;
	}

	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}

	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
