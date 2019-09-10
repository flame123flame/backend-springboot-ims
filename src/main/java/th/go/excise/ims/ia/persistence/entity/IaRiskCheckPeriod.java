
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
@Table(name = "IA_RISK_CHECK_PERIOD")
public class IaRiskCheckPeriod extends BaseEntity {

	private static final long serialVersionUID = 1697636668232686047L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_CHECK_PERIOD_GEN")
	@SequenceGenerator(name = "IA_RISK_CHECK_PERIOD_GEN", sequenceName = "IA_RISK_CHECK_PERIOD_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "EXCISE_CODE")
	private String exciseCode;
	@Column(name = "SECTOR_NAME")
	private String sectorName;
	@Column(name = "AREA_NAME")
	private String areaName;
	@Column(name = "DATE_START")
	private Date dateStart;
	@Column(name = "DATE_END")
	private Date dateEnd;
	@Column(name = "LONG_TIME")
	private BigDecimal longTime;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public BigDecimal getLongTime() {
		return longTime;
	}

	public void setLongTime(BigDecimal longTime) {
		this.longTime = longTime;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

}
