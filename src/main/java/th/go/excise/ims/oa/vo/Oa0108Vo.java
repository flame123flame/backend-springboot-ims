package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Oa0108Vo {
	private BigDecimal id;
	private Date auditStart;
	private Date auditEnd;
	private String budgetYear;
	private List<String> companies;

	public Date getAuditStart() {
		return auditStart;
	}

	public void setAuditStart(Date auditStart) {
		this.auditStart = auditStart;
	}

	public Date getAuditEnd() {
		return auditEnd;
	}

	public void setAuditEnd(Date auditEnd) {
		this.auditEnd = auditEnd;
	}

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

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}
}
