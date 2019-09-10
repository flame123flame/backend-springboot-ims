package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int0613FormVo extends DataTableRequest{
	private String dataPerPage;
	private String offcode;
	private String pageNo;
	private String searchFlag;
	private String yearMonthFrom;
	private String yearMonthTo;

	public String getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(String dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getYearMonthFrom() {
		return yearMonthFrom;
	}

	public void setYearMonthFrom(String yearMonthFrom) {
		this.yearMonthFrom = yearMonthFrom;
	}

	public String getYearMonthTo() {
		return yearMonthTo;
	}

	public void setYearMonthTo(String yearMonthTo) {
		this.yearMonthTo = yearMonthTo;
	}

}
