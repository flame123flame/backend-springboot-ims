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
@Table(name = "TA_WS_INC8000_M")
public class TaWsInc8000M extends BaseEntity {

	private static final long serialVersionUID = 2162840180558825946L;

	public static class Field {
		public static final String WS_INC8000_M_ID = "WS_INC8000_M_ID";
		public static final String NEW_REG_ID = "NEW_REG_ID";
		public static final String TAX_YEAR = "TAX_YEAR";
		public static final String TAX_MONTH = "TAX_MONTH";
		public static final String TAX_AMOUNT = "TAX_AMOUNT";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WS_INC8000_M_GEN")
	@SequenceGenerator(name = "TA_WS_INC8000_M_GEN", sequenceName = "TA_WS_INC8000_M_SEQ", allocationSize = 1)
	@Column(name = "WS_INC8000_M_ID")
	private Long wsInc8000MId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "TAX_YEAR")
	private String taxYear;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "TAX_AMOUNT")
	private BigDecimal taxAmount;

	public Long getWsInc8000MId() {
		return wsInc8000MId;
	}

	public void setWsInc8000MId(Long wsInc8000MId) {
		this.wsInc8000MId = wsInc8000MId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
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
