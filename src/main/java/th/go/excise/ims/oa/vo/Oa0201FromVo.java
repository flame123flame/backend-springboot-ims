package th.go.excise.ims.oa.vo;

import java.util.Date;
import java.util.List;

public class Oa0201FromVo {
	
	private String planId;
	private Date dateFrom;
	private Date dateTo;
	private String fiscolYear;
	
	private List<Oa0201001Vo> listCompany;
	private List<Oa020103Vo>  listAuditer;
	private List<Oa020103Vo>  listApprover;
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public String getFiscolYear() {
		return fiscolYear;
	}
	public List<Oa0201001Vo> getListCompany() {
		return listCompany;
	}
	public List<Oa020103Vo> getListAuditer() {
		return listAuditer;
	}
	public List<Oa020103Vo> getListApprover() {
		return listApprover;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public void setFiscolYear(String fiscolYear) {
		this.fiscolYear = fiscolYear;
	}
	public void setListCompany(List<Oa0201001Vo> listCompany) {
		this.listCompany = listCompany;
	}
	public void setListAuditer(List<Oa020103Vo> listAuditer) {
		this.listAuditer = listAuditer;
	}
	public void setListApprover(List<Oa020103Vo> listApprover) {
		this.listApprover = listApprover;
	}
	
	
	

}
