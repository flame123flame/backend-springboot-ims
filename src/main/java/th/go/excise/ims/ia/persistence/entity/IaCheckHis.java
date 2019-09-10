package th.go.excise.ims.ia.persistence.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_CHECK_HIS")
public class IaCheckHis extends BaseEntity {

	private static final long serialVersionUID = 222447688102814335L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHECK_HIS_GEN")
	@SequenceGenerator(name = "IA_CHECK_HIS_GEN", sequenceName = "IA_CHECK_HIS_SEQ", allocationSize = 1)
	@Column(name = "CHECK_HIS_ID")
	private Long checkHisId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "AU_DATE_START")
	private LocalDate auDateStart;
	@Column(name = "AU_DATE_END")
	private LocalDate auDateEnd;
	@Column(name = "AU_NUM_DAY")
	private String auNumDay;
	@Column(name = "REMARK_DESC")
	private String remarkDesc;

	public Long getCheckHisId() {
		return checkHisId;
	}

	public void setCheckHisId(Long checkHisId) {
		this.checkHisId = checkHisId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public LocalDate getAuDateStart() {
		return auDateStart;
	}

	public void setAuDateStart(LocalDate auDateStart) {
		this.auDateStart = auDateStart;
	}

	public LocalDate getAuDateEnd() {
		return auDateEnd;
	}

	public void setAuDateEnd(LocalDate auDateEnd) {
		this.auDateEnd = auDateEnd;
	}

	public String getAuNumDay() {
		return auNumDay;
	}

	public void setAuNumDay(String auNumDay) {
		this.auNumDay = auNumDay;
	}

	public String getRemarkDesc() {
		return remarkDesc;
	}

	public void setRemarkDesc(String remarkDesc) {
		this.remarkDesc = remarkDesc;
	}

}
