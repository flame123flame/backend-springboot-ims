package th.go.excise.ims.ia.vo;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int120101FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383751020714806129L;
	private String sector;
	private String area;
	private String branch;
	private String dateForm;
	private String dateTo;
	private List<Int120101Vo> dataList;
	private Int120101Vo data;
	private String searchFlag;
	private String paramCode;
	private String status;
	private String officeCode;

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDateForm() {
		return dateForm;
	}

	public void setDateForm(String dateForm) {
		this.dateForm = dateForm;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public List<Int120101Vo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Int120101Vo> dataList) {
		this.dataList = dataList;
	}

	public Int120101Vo getData() {
		return data;
	}

	public void setData(Int120101Vo data) {
		this.data = data;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}
