package th.go.excise.ims.ws.persistence.entity;

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
@Table(name = "WS_INCFRI8000_M")
public class WsIncfri8000M extends BaseEntity {

	private static final long serialVersionUID = 1277474249730841472L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_INCFRI8000_M_GEN")
	@SequenceGenerator(name = "WS_INCFRI8000_M_GEN", sequenceName = "WS_INCFRI8000_M_SEQ", allocationSize = 1)
	@Column(name = "INCFRI8000_M_ID")
	private Long incfri8000MId;
	@Column(name = "DATE_TYPE")
	private String dateType;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "DUTY_GROUP_ID")
	private String dutyGroupId;
	@Column(name = "TAX_YEAR")
	private String taxYear;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "TAX_AMOUNT")
	private BigDecimal taxAmount;

	public Long getIncfri8000MId() {
		return incfri8000MId;
	}

	public void setIncfri8000MId(Long incfri8000MId) {
		this.incfri8000MId = incfri8000MId;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public String getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

}
