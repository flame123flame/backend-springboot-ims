package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsCust;

public class Oa020106DtlVo {
	private BigDecimal oaLubricantsDtlId;
	private BigDecimal oaLubricantsId;
	private String officePlaceOwner;
	private BigDecimal officeRentAmount;
	private Date workingStartDate;
	private Date workingEndDate;
	private BigDecimal workdayPermonth;
	private BigDecimal numberOfTank;
	private String tankCapacity;
	private String numberUtility;
	private String orderType;
	private String orderPayMethod;
	private BigDecimal employeePermanent;
	private BigDecimal employeeTemporary;
	private String payMethodOther;
	private String dailyAcc;
	private String dailyAccDoc;
	private String dailyAuditRemark;
	private String monthlyAcc;
	private String monthlyAccDoc;
	private String monthlyAuditRemark;
	private String monthlyAcc04;
	private String monthlyAccDoc04;
	private String monthlyAuditRemark04;
	private String materail;
	private BigDecimal document;
	private String productProcess;
	private String productNextime;
	private Date useStartDate;
	private Date useEndDate;
	private String buyOverlimit;
	private String buyFromIndust;
	private String buyIndustLicense;
	private String buyFromImporter;
	private String buyImporterLicense;
	private String buyFromAgent;
	private String buyAgentLicense;
	private String usedType;
	private String usedRemark;
	private String salerType;
	private String salerCapacity;
	private BigDecimal numOfCust;
	private String goodQuality;
	private Date agentStartDate;
	private Date agentEndDate;
	private String agentOverlimit;
	private String ABuyFromIndust;
	private String ABuyIndustLicense;
	private String ABuyFromImporter;
	private String AImporterLicense;
	private String ABuyFromAgent;
	private String ABuyAgentLicense;
	private String ASaleToAgent;
	private String ASaleAgentLicense;
	private String ASaleToUser;
	private String ASaleUserLicense;
	private String sentToAgent;
	private String agentRemark;
	private String sentToUser;
	private String otherRemark;
	private List<OaLubricantsCust> customers;
	private List<OaLubricantsCust> custdeles;
	private String auditResult;

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public List<OaLubricantsCust> getCustomers() {
		return customers;
	}

	public void setCustomers(List<OaLubricantsCust> customers) {
		this.customers = customers;
	}

	public List<OaLubricantsCust> getCustdeles() {
		return custdeles;
	}

	public void setCustdeles(List<OaLubricantsCust> custdeles) {
		this.custdeles = custdeles;
	}

	public BigDecimal getOaLubricantsDtlId() {
		return oaLubricantsDtlId;
	}

	public void setOaLubricantsDtlId(BigDecimal oaLubricantsDtlId) {
		this.oaLubricantsDtlId = oaLubricantsDtlId;
	}

	public BigDecimal getOaLubricantsId() {
		return oaLubricantsId;
	}

	public void setOaLubricantsId(BigDecimal oaLubricantsId) {
		this.oaLubricantsId = oaLubricantsId;
	}

	public String getOfficePlaceOwner() {
		return officePlaceOwner;
	}

	public void setOfficePlaceOwner(String officePlaceOwner) {
		this.officePlaceOwner = officePlaceOwner;
	}

	public BigDecimal getOfficeRentAmount() {
		return officeRentAmount;
	}

	public void setOfficeRentAmount(BigDecimal officeRentAmount) {
		this.officeRentAmount = officeRentAmount;
	}

	public Date getWorkingStartDate() {
		return workingStartDate;
	}

	public void setWorkingStartDate(Date workingStartDate) {
		this.workingStartDate = workingStartDate;
	}

	public Date getWorkingEndDate() {
		return workingEndDate;
	}

	public void setWorkingEndDate(Date workingEndDate) {
		this.workingEndDate = workingEndDate;
	}

	public BigDecimal getWorkdayPermonth() {
		return workdayPermonth;
	}

	public void setWorkdayPermonth(BigDecimal workdayPermonth) {
		this.workdayPermonth = workdayPermonth;
	}

	public BigDecimal getNumberOfTank() {
		return numberOfTank;
	}

	public void setNumberOfTank(BigDecimal numberOfTank) {
		this.numberOfTank = numberOfTank;
	}

	public String getTankCapacity() {
		return tankCapacity;
	}

	public void setTankCapacity(String tankCapacity) {
		this.tankCapacity = tankCapacity;
	}

	public String getNumberUtility() {
		return numberUtility;
	}

	public void setNumberUtility(String numberUtility) {
		this.numberUtility = numberUtility;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderPayMethod() {
		return orderPayMethod;
	}

	public void setOrderPayMethod(String orderPayMethod) {
		this.orderPayMethod = orderPayMethod;
	}

	public BigDecimal getEmployeePermanent() {
		return employeePermanent;
	}

	public void setEmployeePermanent(BigDecimal employeePermanent) {
		this.employeePermanent = employeePermanent;
	}

	public BigDecimal getEmployeeTemporary() {
		return employeeTemporary;
	}

	public void setEmployeeTemporary(BigDecimal employeeTemporary) {
		this.employeeTemporary = employeeTemporary;
	}

	public String getPayMethodOther() {
		return payMethodOther;
	}

	public void setPayMethodOther(String payMethodOther) {
		this.payMethodOther = payMethodOther;
	}

	public String getDailyAcc() {
		return dailyAcc;
	}

	public void setDailyAcc(String dailyAcc) {
		this.dailyAcc = dailyAcc;
	}

	public String getDailyAccDoc() {
		return dailyAccDoc;
	}

	public void setDailyAccDoc(String dailyAccDoc) {
		this.dailyAccDoc = dailyAccDoc;
	}

	public String getDailyAuditRemark() {
		return dailyAuditRemark;
	}

	public void setDailyAuditRemark(String dailyAuditRemark) {
		this.dailyAuditRemark = dailyAuditRemark;
	}

	public String getMonthlyAcc() {
		return monthlyAcc;
	}

	public void setMonthlyAcc(String monthlyAcc) {
		this.monthlyAcc = monthlyAcc;
	}

	public String getMonthlyAccDoc() {
		return monthlyAccDoc;
	}

	public void setMonthlyAccDoc(String monthlyAccDoc) {
		this.monthlyAccDoc = monthlyAccDoc;
	}

	public String getMonthlyAuditRemark() {
		return monthlyAuditRemark;
	}

	public void setMonthlyAuditRemark(String monthlyAuditRemark) {
		this.monthlyAuditRemark = monthlyAuditRemark;
	}

	public String getMonthlyAcc04() {
		return monthlyAcc04;
	}

	public void setMonthlyAcc04(String monthlyAcc04) {
		this.monthlyAcc04 = monthlyAcc04;
	}

	public String getMonthlyAccDoc04() {
		return monthlyAccDoc04;
	}

	public void setMonthlyAccDoc04(String monthlyAccDoc04) {
		this.monthlyAccDoc04 = monthlyAccDoc04;
	}

	public String getMonthlyAuditRemark04() {
		return monthlyAuditRemark04;
	}

	public void setMonthlyAuditRemark04(String monthlyAuditRemark04) {
		this.monthlyAuditRemark04 = monthlyAuditRemark04;
	}

	public String getMaterail() {
		return materail;
	}

	public void setMaterail(String materail) {
		this.materail = materail;
	}

	public BigDecimal getDocument() {
		return document;
	}

	public void setDocument(BigDecimal document) {
		this.document = document;
	}

	public String getProductProcess() {
		return productProcess;
	}

	public void setProductProcess(String productProcess) {
		this.productProcess = productProcess;
	}

	public String getProductNextime() {
		return productNextime;
	}

	public void setProductNextime(String productNextime) {
		this.productNextime = productNextime;
	}

	public Date getUseStartDate() {
		return useStartDate;
	}

	public void setUseStartDate(Date useStartDate) {
		this.useStartDate = useStartDate;
	}

	public Date getUseEndDate() {
		return useEndDate;
	}

	public void setUseEndDate(Date useEndDate) {
		this.useEndDate = useEndDate;
	}

	public String getBuyOverlimit() {
		return buyOverlimit;
	}

	public void setBuyOverlimit(String buyOverlimit) {
		this.buyOverlimit = buyOverlimit;
	}

	public String getBuyFromIndust() {
		return buyFromIndust;
	}

	public void setBuyFromIndust(String buyFromIndust) {
		this.buyFromIndust = buyFromIndust;
	}

	public String getBuyIndustLicense() {
		return buyIndustLicense;
	}

	public void setBuyIndustLicense(String buyIndustLicense) {
		this.buyIndustLicense = buyIndustLicense;
	}

	public String getBuyFromImporter() {
		return buyFromImporter;
	}

	public void setBuyFromImporter(String buyFromImporter) {
		this.buyFromImporter = buyFromImporter;
	}

	public String getBuyImporterLicense() {
		return buyImporterLicense;
	}

	public void setBuyImporterLicense(String buyImporterLicense) {
		this.buyImporterLicense = buyImporterLicense;
	}

	public String getBuyFromAgent() {
		return buyFromAgent;
	}

	public void setBuyFromAgent(String buyFromAgent) {
		this.buyFromAgent = buyFromAgent;
	}

	public String getBuyAgentLicense() {
		return buyAgentLicense;
	}

	public void setBuyAgentLicense(String buyAgentLicense) {
		this.buyAgentLicense = buyAgentLicense;
	}

	public String getUsedType() {
		return usedType;
	}

	public void setUsedType(String usedType) {
		this.usedType = usedType;
	}

	public String getUsedRemark() {
		return usedRemark;
	}

	public void setUsedRemark(String usedRemark) {
		this.usedRemark = usedRemark;
	}

	public String getSalerType() {
		return salerType;
	}

	public void setSalerType(String salerType) {
		this.salerType = salerType;
	}

	public String getSalerCapacity() {
		return salerCapacity;
	}

	public void setSalerCapacity(String salerCapacity) {
		this.salerCapacity = salerCapacity;
	}

	public BigDecimal getNumOfCust() {
		return numOfCust;
	}

	public void setNumOfCust(BigDecimal numOfCust) {
		this.numOfCust = numOfCust;
	}

	public String getGoodQuality() {
		return goodQuality;
	}

	public void setGoodQuality(String goodQuality) {
		this.goodQuality = goodQuality;
	}

	public Date getAgentStartDate() {
		return agentStartDate;
	}

	public void setAgentStartDate(Date agentStartDate) {
		this.agentStartDate = agentStartDate;
	}

	public Date getAgentEndDate() {
		return agentEndDate;
	}

	public void setAgentEndDate(Date agentEndDate) {
		this.agentEndDate = agentEndDate;
	}

	public String getAgentOverlimit() {
		return agentOverlimit;
	}

	public void setAgentOverlimit(String agentOverlimit) {
		this.agentOverlimit = agentOverlimit;
	}

	public String getABuyFromIndust() {
		return ABuyFromIndust;
	}

	public void setABuyFromIndust(String aBuyFromIndust) {
		ABuyFromIndust = aBuyFromIndust;
	}

	public String getABuyIndustLicense() {
		return ABuyIndustLicense;
	}

	public void setABuyIndustLicense(String aBuyIndustLicense) {
		ABuyIndustLicense = aBuyIndustLicense;
	}

	public String getABuyFromImporter() {
		return ABuyFromImporter;
	}

	public void setABuyFromImporter(String aBuyFromImporter) {
		ABuyFromImporter = aBuyFromImporter;
	}

	public String getAImporterLicense() {
		return AImporterLicense;
	}

	public void setAImporterLicense(String aImporterLicense) {
		AImporterLicense = aImporterLicense;
	}

	public String getABuyFromAgent() {
		return ABuyFromAgent;
	}

	public void setABuyFromAgent(String aBuyFromAgent) {
		ABuyFromAgent = aBuyFromAgent;
	}

	public String getABuyAgentLicense() {
		return ABuyAgentLicense;
	}

	public void setABuyAgentLicense(String aBuyAgentLicense) {
		ABuyAgentLicense = aBuyAgentLicense;
	}

	public String getASaleToAgent() {
		return ASaleToAgent;
	}

	public void setASaleToAgent(String aSaleToAgent) {
		ASaleToAgent = aSaleToAgent;
	}

	public String getASaleAgentLicense() {
		return ASaleAgentLicense;
	}

	public void setASaleAgentLicense(String aSaleAgentLicense) {
		ASaleAgentLicense = aSaleAgentLicense;
	}

	public String getASaleToUser() {
		return ASaleToUser;
	}

	public void setASaleToUser(String aSaleToUser) {
		ASaleToUser = aSaleToUser;
	}

	public String getASaleUserLicense() {
		return ASaleUserLicense;
	}

	public void setASaleUserLicense(String aSaleUserLicense) {
		ASaleUserLicense = aSaleUserLicense;
	}

	public String getSentToAgent() {
		return sentToAgent;
	}

	public void setSentToAgent(String sentToAgent) {
		this.sentToAgent = sentToAgent;
	}

	public String getAgentRemark() {
		return agentRemark;
	}

	public void setAgentRemark(String agentRemark) {
		this.agentRemark = agentRemark;
	}

	public String getSentToUser() {
		return sentToUser;
	}

	public void setSentToUser(String sentToUser) {
		this.sentToUser = sentToUser;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}
}
