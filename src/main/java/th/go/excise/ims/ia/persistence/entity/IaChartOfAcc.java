
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_CHART_OF_ACC")
public class IaChartOfAcc extends BaseEntity {

	private static final long serialVersionUID = -925106201581509062L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHART_OF_ACC_GEN")
	@SequenceGenerator(name = "IA_CHART_OF_ACC_GEN", sequenceName = "IA_CHART_OF_ACC_SEQ", allocationSize = 1)
	@Column(name = "COA_ID")
	private BigDecimal coaId;
	@Column(name = "COA_CODE")
	private String coaCode;
	@Column(name = "COA_NAME")
	private String coaName;
	@Column(name = "COA_DES")
	private String coaDes;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "COA_TYPE")
	private String coaType;
	@Column(name = "BALANCE_ACC_TYPE")
	private String balanceAccType;
	@Column(name = "VALUE_TRUE_TYPE")
	private String valueTrueType;

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

	public String getBalanceAccType() {
		return balanceAccType;
	}

	public void setBalanceAccType(String balanceAccType) {
		this.balanceAccType = balanceAccType;
	}

	public String getValueTrueType() {
		return valueTrueType;
	}

	public void setValueTrueType(String valueTrueType) {
		this.valueTrueType = valueTrueType;
	}

}
