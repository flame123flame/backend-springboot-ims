package th.go.excise.ims.ws.client.pcc.incfri8040.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {

	@SerializedName("BudgetYear")
	@Expose
	private String budgetYear;

	@SerializedName("DateType")
	@Expose
	private String dateType;

	@SerializedName("PageNo")
	@Expose
	private String pageNo;

	@SerializedName("DataPerPage")
	@Expose
	private String dataPerPage;

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(String dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

}
