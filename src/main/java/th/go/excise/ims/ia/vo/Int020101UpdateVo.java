package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Int020101UpdateVo {
	private BigDecimal id;
	private String budgetYear;
	private String qtnHeaderName;
	private String note;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private String qtnYear;
	private String toDepartment;
	private String usagePatterns;
	private String factorLevel;

	private List<Int020101SideVo> side;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getQtnHeaderName() {
		return qtnHeaderName;
	}

	public void setQtnHeaderName(String qtnHeaderName) {
		this.qtnHeaderName = qtnHeaderName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQtnYear() {
		return qtnYear;
	}

	public void setQtnYear(String qtnYear) {
		this.qtnYear = qtnYear;
	}

	public List<Int020101SideVo> getSide() {
		return side;
	}

	public void setSide(List<Int020101SideVo> side) {
		this.side = side;
	}

	public String getToDepartment() {
		return toDepartment;
	}

	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}

	public String getUsagePatterns() {
		return usagePatterns;
	}

	public void setUsagePatterns(String usagePatterns) {
		this.usagePatterns = usagePatterns;
	}

	public String getFactorLevel() {
		return factorLevel;
	}

	public void setFactorLevel(String factorLevel) {
		this.factorLevel = factorLevel;
	}

}
