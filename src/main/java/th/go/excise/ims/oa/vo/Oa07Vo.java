package th.go.excise.ims.oa.vo;

import java.util.ArrayList;
import java.util.List;

public class Oa07Vo {
	private String newRegId;
	private String cusFullname;
	private String facFullname;
	private String facAddress;
	private String dutyCode;
	private String dutyDesc;
	private String officeCodeR4000;
	private String secCode;
	private String secDesc;
	private String areaCode;
	private String areaDesc;
	private String regStatus;
	private String regDate;
	private String regCapital;
	private List<String> taxPayList = new ArrayList<>();
	private List<String> perceneDiff = new ArrayList<>();
	private List<String> groupTaxPay = new ArrayList<>();
	private List<String> groupYearMonth = new ArrayList<>();
	private List<String> groupYearMonthGraph = new ArrayList<>();
	private List<List<String>> dataTableGraph = new ArrayList<>();

	public List<List<String>> getDataTableGraph() {
		return dataTableGraph;
	}

	public void setDataTableGraph(List<List<String>> dataTableGraph) {
		this.dataTableGraph = dataTableGraph;
	}

	public List<String> getGroupYearMonthGraph() {
		return groupYearMonthGraph;
	}

	public void setGroupYearMonthGraph(List<String> groupYearMonthGraph) {
		this.groupYearMonthGraph = groupYearMonthGraph;
	}

	public List<String> getGroupYearMonth() {
		return groupYearMonth;
	}

	public void setGroupYearMonth(List<String> groupYearMonth) {
		this.groupYearMonth = groupYearMonth;
	}

	public List<String> getGroupTaxPay() {
		return groupTaxPay;
	}

	public void setGroupTaxPay(List<String> groupTaxPay) {
		this.groupTaxPay = groupTaxPay;
	}

	public String getDutyDesc() {
		return dutyDesc;
	}

	public void setDutyDesc(String dutyDesc) {
		this.dutyDesc = dutyDesc;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public List<String> getPerceneDiff() {
		return perceneDiff;
	}

	public void setPerceneDiff(List<String> perceneDiff) {
		this.perceneDiff = perceneDiff;
	}

	public List<String> getTaxPayList() {
		return taxPayList;
	}

	public void setTaxPayList(List<String> taxPayList) {
		this.taxPayList = taxPayList;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getFacAddress() {
		return facAddress;
	}

	public void setFacAddress(String facAddress) {
		this.facAddress = facAddress;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getOfficeCodeR4000() {
		return officeCodeR4000;
	}

	public void setOfficeCodeR4000(String officeCodeR4000) {
		this.officeCodeR4000 = officeCodeR4000;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

}
