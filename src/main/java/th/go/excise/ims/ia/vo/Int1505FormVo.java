package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Int1505FormVo {

	private BigDecimal coaId;
	private String coaCode;
	private String coaName;
	private String coaDes;
	private Date startDate;
	private Date endDate;
	private String coaType;

	public BigDecimal getCoaId() {
		return coaId;
	}

	public void setCoaId(BigDecimal coaId) {
		this.coaId = coaId;
	}

	public String getCoaCode() {
		return coaCode;
	}

	public void setCoaCode(String coaCode) {
		this.coaCode = coaCode;
	}

	public String getCoaName() {
		return coaName;
	}

	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}

	public String getCoaDes() {
		return coaDes;
	}

	public void setCoaDes(String coaDes) {
		this.coaDes = coaDes;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCoaType() {
		return coaType;
	}

	public void setCoaType(String coaType) {
		this.coaType = coaType;
	}

}
