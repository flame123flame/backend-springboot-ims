package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Oa0208ApproveVo {
	private BigDecimal id;
	private String areaName;
	private Date auditStart;
	private Date auditEnd;
	private String budgetYear;
	private List<String> companies;
	private String sectorName;
	private String status;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

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

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
