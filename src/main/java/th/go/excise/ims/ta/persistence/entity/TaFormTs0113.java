package th.go.excise.ims.ta.persistence.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_FORM_TS0113")
public class TaFormTs0113 extends BaseEntity {

	private static final long serialVersionUID = -2191891697964795608L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0113_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0113_GEN", sequenceName = "TA_FORM_TS0113_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0113_ID")
	private Long formTs0113Id;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "DOC_PLACE")
	private String docPlace;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "DOC_TIME")
	private String docTime;
	@Column(name = "HEAD_OFFICER_FULL_NAME")
	private String headOfficerFullName;
	@Column(name = "HEAD_OFFICER_POSITION")
	private String headOfficerPosition;
	@Column(name = "REF_BOOK_NUMBER1")
	private String refBookNumber1;
	@Column(name = "REF_BOOK_DATE")
	private Date refBookDate;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
	@Column(name = "FAC_SOI_NAME")
	private String facSoiName;
	@Column(name = "FAC_THN_NAME")
	private String facThnName;
	@Column(name = "FAC_TAMBOL_NAME")
	private String facTambolName;
	@Column(name = "FAC_AMPHUR_NAME")
	private String facAmphurName;
	@Column(name = "FAC_PROVINCE_NAME")
	private String facProvinceName;
	@Column(name = "FAC_ZIP_CODE")
	private String facZipCode;
	@Column(name = "AUDIT_DATE")
	private Date auditDate;
	@Column(name = "OWNER_FULL_NAME")
	private String ownerFullName;
	@Column(name = "OWNER_POSITION")
	private String ownerPosition;
	@Column(name = "FACTORY_NAME2")
	private String factoryName2;
	@Column(name = "AUDIT_FINISH_TIME")
	private String auditFinishTime;
	@Column(name = "SIGN_OWNER_FULL_NAME")
	private String signOwnerFullName;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_WITNESS_FULL_NAME1")
	private String signWitnessFullName1;
	@Column(name = "SIGN_WITNESS_FULL_NAME2")
	private String signWitnessFullName2;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0113Id() {
		return formTs0113Id;
	}

	public void setFormTs0113Id(Long formTs0113Id) {
		this.formTs0113Id = formTs0113Id;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getDocPlace() {
		return docPlace;
	}

	public void setDocPlace(String docPlace) {
		this.docPlace = docPlace;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocTime() {
		return docTime;
	}

	public void setDocTime(String docTime) {
		this.docTime = docTime;
	}

	public String getHeadOfficerFullName() {
		return headOfficerFullName;
	}

	public void setHeadOfficerFullName(String headOfficerFullName) {
		this.headOfficerFullName = headOfficerFullName;
	}

	public String getHeadOfficerPosition() {
		return headOfficerPosition;
	}

	public void setHeadOfficerPosition(String headOfficerPosition) {
		this.headOfficerPosition = headOfficerPosition;
	}

	public String getRefBookNumber1() {
		return refBookNumber1;
	}

	public void setRefBookNumber1(String refBookNumber1) {
		this.refBookNumber1 = refBookNumber1;
	}

	public Date getRefBookDate() {
		return refBookDate;
	}

	public void setRefBookDate(Date refBookDate) {
		this.refBookDate = refBookDate;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFacAddrNo() {
		return facAddrNo;
	}

	public void setFacAddrNo(String facAddrNo) {
		this.facAddrNo = facAddrNo;
	}

	public String getFacSoiName() {
		return facSoiName;
	}

	public void setFacSoiName(String facSoiName) {
		this.facSoiName = facSoiName;
	}

	public String getFacThnName() {
		return facThnName;
	}

	public void setFacThnName(String facThnName) {
		this.facThnName = facThnName;
	}

	public String getFacTambolName() {
		return facTambolName;
	}

	public void setFacTambolName(String facTambolName) {
		this.facTambolName = facTambolName;
	}

	public String getFacAmphurName() {
		return facAmphurName;
	}

	public void setFacAmphurName(String facAmphurName) {
		this.facAmphurName = facAmphurName;
	}

	public String getFacProvinceName() {
		return facProvinceName;
	}

	public void setFacProvinceName(String facProvinceName) {
		this.facProvinceName = facProvinceName;
	}

	public String getFacZipCode() {
		return facZipCode;
	}

	public void setFacZipCode(String facZipCode) {
		this.facZipCode = facZipCode;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getOwnerPosition() {
		return ownerPosition;
	}

	public void setOwnerPosition(String ownerPosition) {
		this.ownerPosition = ownerPosition;
	}

	public String getFactoryName2() {
		return factoryName2;
	}

	public void setFactoryName2(String factoryName2) {
		this.factoryName2 = factoryName2;
	}

	public String getAuditFinishTime() {
		return auditFinishTime;
	}

	public void setAuditFinishTime(String auditFinishTime) {
		this.auditFinishTime = auditFinishTime;
	}

	public String getSignOwnerFullName() {
		return signOwnerFullName;
	}

	public void setSignOwnerFullName(String signOwnerFullName) {
		this.signOwnerFullName = signOwnerFullName;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
	}

	public String getSignWitnessFullName1() {
		return signWitnessFullName1;
	}

	public void setSignWitnessFullName1(String signWitnessFullName1) {
		this.signWitnessFullName1 = signWitnessFullName1;
	}

	public String getSignWitnessFullName2() {
		return signWitnessFullName2;
	}

	public void setSignWitnessFullName2(String signWitnessFullName2) {
		this.signWitnessFullName2 = signWitnessFullName2;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
