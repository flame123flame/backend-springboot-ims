
package th.go.excise.ims.ws.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

//@Entity
//@Table(name = "WS_INCR0003")
public class WsIncr0003 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7956166491671112592L;
	@Column(name = "TRN_DATE")
	private Date trnDate;
	@Column(name = "INCCTL_NO")
	private String incctlNo;
	@Column(name = "BANK_NAME")
	private String bankName;
	@Column(name = "CHQ_NO")
	private String chqNo;
	@Column(name = "CHQ_DATE")
	private String chqDate;
	@Column(name = "CUS_ID")
	private String cusId;
	@Column(name = "CUS_NAME")
	private String cusName;
	@Column(name = "OFFCODE")
	private String offcode;
	@Column(name = "OFFNAME")
	private String offname;
	@Column(name = "INC_CODE")
	private String incCode;
	@Column(name = "INC_NAME")
	private String incName;
	@Column(name = "SUM1")
	private BigDecimal sum1;
	@Column(name = "SUM2")
	private BigDecimal sum2;
	@Column(name = "SUM3")
	private BigDecimal sum3;
	@Column(name = "SUM4")
	private BigDecimal sum4;
	@Column(name = "SUM5")
	private BigDecimal sum5;
	@Column(name = "SUM6")
	private BigDecimal sum6;
	@Column(name = "SUM7")
	private BigDecimal sum7;

	public Date getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(Date trnDate) {
		this.trnDate = trnDate;
	}

	public String getIncctlNo() {
		return incctlNo;
	}

	public void setIncctlNo(String incctlNo) {
		this.incctlNo = incctlNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getChqNo() {
		return chqNo;
	}

	public void setChqNo(String chqNo) {
		this.chqNo = chqNo;
	}

	public String getChqDate() {
		return chqDate;
	}

	public void setChqDate(String chqDate) {
		this.chqDate = chqDate;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getOffname() {
		return offname;
	}

	public void setOffname(String offname) {
		this.offname = offname;
	}

	public String getIncCode() {
		return incCode;
	}

	public void setIncCode(String incCode) {
		this.incCode = incCode;
	}

	public String getIncName() {
		return incName;
	}

	public void setIncName(String incName) {
		this.incName = incName;
	}

	public BigDecimal getSum1() {
		return sum1;
	}

	public void setSum1(BigDecimal sum1) {
		this.sum1 = sum1;
	}

	public BigDecimal getSum2() {
		return sum2;
	}

	public void setSum2(BigDecimal sum2) {
		this.sum2 = sum2;
	}

	public BigDecimal getSum3() {
		return sum3;
	}

	public void setSum3(BigDecimal sum3) {
		this.sum3 = sum3;
	}

	public BigDecimal getSum4() {
		return sum4;
	}

	public void setSum4(BigDecimal sum4) {
		this.sum4 = sum4;
	}

	public BigDecimal getSum5() {
		return sum5;
	}

	public void setSum5(BigDecimal sum5) {
		this.sum5 = sum5;
	}

	public BigDecimal getSum6() {
		return sum6;
	}

	public void setSum6(BigDecimal sum6) {
		this.sum6 = sum6;
	}

	public BigDecimal getSum7() {
		return sum7;
	}

	public void setSum7(BigDecimal sum7) {
		this.sum7 = sum7;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
