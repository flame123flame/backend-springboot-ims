
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
//@Table(name = "WS_GFR0105_1")
public class WsGfr01051 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2771604793411240199L;
	@Column(name = "OFFNAME")
	private String offname;
	@Column(name = "TRN_DATE")
	private Date trnDate;
	@Column(name = "GF_DATE")
	private Date gfDate;
	@Column(name = "OFFCODE")
	private String offcode;
	@Column(name = "ACTCOST_CENT")
	private String actcostCent;
	@Column(name = "GF_REF_NO")
	private String gfRefNo;
	@Column(name = "CNT")
	private BigDecimal cnt;
	@Column(name = "TOTAL_AMT")
	private BigDecimal totalAmt;
	@Column(name = "TOTAL_SEND_AMT")
	private BigDecimal totalSendAmt;

	public String getOffname() {
		return offname;
	}

	public void setOffname(String offname) {
		this.offname = offname;
	}

	public Date getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(Date trnDate) {
		this.trnDate = trnDate;
	}

	public Date getGfDate() {
		return gfDate;
	}

	public void setGfDate(Date gfDate) {
		this.gfDate = gfDate;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getActcostCent() {
		return actcostCent;
	}

	public void setActcostCent(String actcostCent) {
		this.actcostCent = actcostCent;
	}

	public String getGfRefNo() {
		return gfRefNo;
	}

	public void setGfRefNo(String gfRefNo) {
		this.gfRefNo = gfRefNo;
	}

	public BigDecimal getCnt() {
		return cnt;
	}

	public void setCnt(BigDecimal cnt) {
		this.cnt = cnt;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getTotalSendAmt() {
		return totalSendAmt;
	}

	public void setTotalSendAmt(BigDecimal totalSendAmt) {
		this.totalSendAmt = totalSendAmt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
