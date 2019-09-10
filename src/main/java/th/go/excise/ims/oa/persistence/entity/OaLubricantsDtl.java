
package th.go.excise.ims.oa.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "OA_LUBRICANTS_DTL")
public class OaLubricantsDtl
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_LUBRICANTS_DTL_GEN")
    @SequenceGenerator(name = "OA_LUBRICANTS_DTL_GEN", sequenceName = "OA_LUBRICANTS_DTL_SEQ", allocationSize = 1)
    @Column(name = "OA_LUBRICANTS_DTL_ID")
    private BigDecimal oaLubricantsDtlId;
    @Column(name = "OA_LUBRICANTS_ID")
    private BigDecimal oaLubricantsId;
    @Column(name = "OFFICE_PLACE_OWNER")
    private String officePlaceOwner;
    @Column(name = "OFFICE_RENT_AMOUNT")
    private BigDecimal officeRentAmount;
    @Column(name = "WORKING_START_DATE")
    private Date workingStartDate;
    @Column(name = "WORKING_END_DATE")
    private Date workingEndDate;
    @Column(name = "WORKDAY_PERMONTH")
    private BigDecimal workdayPermonth;
    @Column(name = "NUMBER_OF_TANK")
    private BigDecimal numberOfTank;
    @Column(name = "TANK_CAPACITY")
    private String tankCapacity;
    @Column(name = "NUMBER_UTILITY")
    private String numberUtility;
    @Column(name = "ORDER_TYPE")
    private String orderType;
    @Column(name = "ORDER_PAY_METHOD")
    private String orderPayMethod;
    @Column(name = "EMPLOYEE_PERMANENT")
    private BigDecimal employeePermanent;
    @Column(name = "EMPLOYEE_TEMPORARY")
    private BigDecimal employeeTemporary;
    @Column(name = "PAY_METHOD_OTHER")
    private String payMethodOther;
    @Column(name = "DAILY_ACC")
    private String dailyAcc;
    @Column(name = "DAILY_ACC_DOC")
    private String dailyAccDoc;
    @Column(name = "DAILY_AUDIT_REMARK")
    private String dailyAuditRemark;
    @Column(name = "MONTHLY_ACC")
    private String monthlyAcc;
    @Column(name = "MONTHLY_ACC_DOC")
    private String monthlyAccDoc;
    @Column(name = "MONTHLY_AUDIT_REMARK")
    private String monthlyAuditRemark;
    @Column(name = "MONTHLY_ACC_04")
    private String monthlyAcc04;
    @Column(name = "MONTHLY_ACC_DOC_04")
    private String monthlyAccDoc04;
    @Column(name = "MONTHLY_AUDIT_REMARK_04")
    private String monthlyAuditRemark04;
    @Column(name = "MATERAIL")
    private String materail;
    @Column(name = "DOCUMENT")
    private BigDecimal document;
    @Column(name = "PRODUCT_PROCESS")
    private String productProcess;
    @Column(name = "PRODUCT_NEXTIME")
    private String productNextime;
    @Column(name = "USE_START_DATE")
    private Date useStartDate;
    @Column(name = "USE_END_DATE")
    private Date useEndDate;
    @Column(name = "BUY_OVERLIMIT")
    private String buyOverlimit;
    @Column(name = "BUY_FROM_INDUST")
    private String buyFromIndust;
    @Column(name = "BUY_INDUST_LICENSE")
    private String buyIndustLicense;
    @Column(name = "BUY_FROM_IMPORTER")
    private String buyFromImporter;
    @Column(name = "BUY_IMPORTER_LICENSE")
    private String buyImporterLicense;
    @Column(name = "BUY_FROM_AGENT")
    private String buyFromAgent;
    @Column(name = "BUY_AGENT_LICENSE")
    private String buyAgentLicense;
    @Column(name = "USED_TYPE")
    private String usedType;
    @Column(name = "USED_REMARK")
    private String usedRemark;
    @Column(name = "SALER_TYPE")
    private String salerType;
    @Column(name = "SALER_CAPACITY")
    private String salerCapacity;
    @Column(name = "NUM_OF_CUST")
    private BigDecimal numOfCust;
    @Column(name = "GOOD_QUALITY")
    private String goodQuality;
    @Column(name = "AGENT_START_DATE")
    private Date agentStartDate;
    @Column(name = "AGENT_END_DATE")
    private Date agentEndDate;
    @Column(name = "AGENT_OVERLIMIT")
    private String agentOverlimit;
    @Column(name = "A_BUY_FROM_INDUST")
    private String ABuyFromIndust;
    @Column(name = "A_BUY_INDUST_LICENSE")
    private String ABuyIndustLicense;
    @Column(name = "A_BUY_FROM_IMPORTER")
    private String ABuyFromImporter;
    @Column(name = "A_IMPORTER_LICENSE")
    private String AImporterLicense;
    @Column(name = "A_BUY_FROM_AGENT")
    private String ABuyFromAgent;
    @Column(name = "A_BUY_AGENT_LICENSE")
    private String ABuyAgentLicense;
    @Column(name = "A_SALE_TO_AGENT")
    private String ASaleToAgent;
    @Column(name = "A_SALE_AGENT_LICENSE")
    private String ASaleAgentLicense;
    @Column(name = "A_SALE_TO_USER")
    private String ASaleToUser;
    @Column(name = "A_SALE_USER_LICENSE")
    private String ASaleUserLicense;
    @Column(name = "SENT_TO_AGENT")
    private String sentToAgent;
    @Column(name = "AGENT_REMARK")
    private String agentRemark;
    @Column(name = "SENT_TO_USER")
    private String sentToUser;
    @Column(name = "OTHER_REMARK")
    private String otherRemark;
    @Column(name = "AUDIT_RESULT")
    private String auditResult;

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

    public void setABuyFromIndust(String ABuyFromIndust) {
        this.ABuyFromIndust = ABuyFromIndust;
    }

    public String getABuyIndustLicense() {
        return ABuyIndustLicense;
    }

    public void setABuyIndustLicense(String ABuyIndustLicense) {
        this.ABuyIndustLicense = ABuyIndustLicense;
    }

    public String getABuyFromImporter() {
        return ABuyFromImporter;
    }

    public void setABuyFromImporter(String ABuyFromImporter) {
        this.ABuyFromImporter = ABuyFromImporter;
    }

    public String getAImporterLicense() {
        return AImporterLicense;
    }

    public void setAImporterLicense(String AImporterLicense) {
        this.AImporterLicense = AImporterLicense;
    }

    public String getABuyFromAgent() {
        return ABuyFromAgent;
    }

    public void setABuyFromAgent(String ABuyFromAgent) {
        this.ABuyFromAgent = ABuyFromAgent;
    }

    public String getABuyAgentLicense() {
        return ABuyAgentLicense;
    }

    public void setABuyAgentLicense(String ABuyAgentLicense) {
        this.ABuyAgentLicense = ABuyAgentLicense;
    }

    public String getASaleToAgent() {
        return ASaleToAgent;
    }

    public void setASaleToAgent(String ASaleToAgent) {
        this.ASaleToAgent = ASaleToAgent;
    }

    public String getASaleAgentLicense() {
        return ASaleAgentLicense;
    }

    public void setASaleAgentLicense(String ASaleAgentLicense) {
        this.ASaleAgentLicense = ASaleAgentLicense;
    }

    public String getASaleToUser() {
        return ASaleToUser;
    }

    public void setASaleToUser(String ASaleToUser) {
        this.ASaleToUser = ASaleToUser;
    }

    public String getASaleUserLicense() {
        return ASaleUserLicense;
    }

    public void setASaleUserLicense(String ASaleUserLicense) {
        this.ASaleUserLicense = ASaleUserLicense;
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

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
    

}
