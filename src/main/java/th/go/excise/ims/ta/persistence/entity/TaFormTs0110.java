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
@Table(name = "TA_FORM_TS0110")
public class TaFormTs0110 extends BaseEntity {

	private static final long serialVersionUID = 4491330955629302063L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0110_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0110_GEN", sequenceName = "TA_FORM_TS0110_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0110_ID")
	private Long formTs0110Id;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "TESTIMONY_PAGE_NO")
	private String testimonyPageNo;
	@Column(name = "TESTIMONY_OF")
	private String testimonyOf;
	@Column(name = "TESTIMONY_TOPIC")
	private String testimonyTopic;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;
	@Column(name = "OFFICER_POSITION")
	private String officerPosition;
	@Column(name = "TESTIMONY_FULL_NAME")
	private String testimonyFullName;
	@Column(name = "TESTIMONY_AGE")
	private String testimonyAge;
	@Column(name = "TESTIMONY_NATIONALITY")
	private String testimonyNationality;
	@Column(name = "TESTIMONY_RACE")
	private String testimonyRace;
	@Column(name = "TESTIMONY_ADDR_NO")
	private String testimonyAddrNo;
	@Column(name = "TESTIMONY_BUILD_NAME_VILLAGE")
	private String testimonyBuildNameVillage;
	@Column(name = "TESTIMONY_ROOM_NO")
	private String testimonyRoomNo;
	@Column(name = "TESTIMONY_FLOOR_NO")
	private String testimonyFloorNo;
	@Column(name = "TESTIMONY_SOI_NAME")
	private String testimonySoiName;
	@Column(name = "TESTIMONY_THN_NAME")
	private String testimonyThnName;
	@Column(name = "TESTIMONY_TAMBOL_NAME")
	private String testimonyTambolName;
	@Column(name = "TESTIMONY_AMPHUR_NAME")
	private String testimonyAmphurName;
	@Column(name = "TESTIMONY_PROVINCE_NAME")
	private String testimonyProvinceName;
	@Column(name = "TESTIMONY_ZIP_CODE")
	private String testimonyZipCode;
	@Column(name = "TESTIMONY_TEL_NO")
	private String testimonyTelNo;
	@Column(name = "TESTIMONY_CARD_TYPE")
	private String testimonyCardType;
	@Column(name = "TESTIMONY_CARD_OTHER_DESC")
	private String testimonyCardOtherDesc;
	@Column(name = "TESTIMONY_CARD_NO")
	private String testimonyCardNo;
	@Column(name = "TESTIMONY_CARD_SOURCE")
	private String testimonyCardSource;
	@Column(name = "TESTIMONY_CARD_COUNTRY")
	private String testimonyCardCountry;
	@Column(name = "TESTIMONY_POSITION")
	private String testimonyPosition;
	@Column(name = "TESTIMONY_FACTORY_FULL_NAME")
	private String testimonyFactoryFullName;
	@Column(name = "TESTIMONY_TEXT")
	private String testimonyText;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0110Id() {
		return formTs0110Id;
	}

	public void setFormTs0110Id(Long formTs0110Id) {
		this.formTs0110Id = formTs0110Id;
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

	public String getTestimonyPageNo() {
		return testimonyPageNo;
	}

	public void setTestimonyPageNo(String testimonyPageNo) {
		this.testimonyPageNo = testimonyPageNo;
	}

	public String getTestimonyOf() {
		return testimonyOf;
	}

	public void setTestimonyOf(String testimonyOf) {
		this.testimonyOf = testimonyOf;
	}

	public String getTestimonyTopic() {
		return testimonyTopic;
	}

	public void setTestimonyTopic(String testimonyTopic) {
		this.testimonyTopic = testimonyTopic;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getOfficerFullName() {
		return officerFullName;
	}

	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}

	public String getTestimonyFullName() {
		return testimonyFullName;
	}

	public void setTestimonyFullName(String testimonyFullName) {
		this.testimonyFullName = testimonyFullName;
	}

	public String getTestimonyAge() {
		return testimonyAge;
	}

	public void setTestimonyAge(String testimonyAge) {
		this.testimonyAge = testimonyAge;
	}

	public String getTestimonyNationality() {
		return testimonyNationality;
	}

	public void setTestimonyNationality(String testimonyNationality) {
		this.testimonyNationality = testimonyNationality;
	}

	public String getTestimonyRace() {
		return testimonyRace;
	}

	public void setTestimonyRace(String testimonyRace) {
		this.testimonyRace = testimonyRace;
	}

	public String getTestimonyAddrNo() {
		return testimonyAddrNo;
	}

	public void setTestimonyAddrNo(String testimonyAddrNo) {
		this.testimonyAddrNo = testimonyAddrNo;
	}

	public String getTestimonyBuildNameVillage() {
		return testimonyBuildNameVillage;
	}

	public void setTestimonyBuildNameVillage(String testimonyBuildNameVillage) {
		this.testimonyBuildNameVillage = testimonyBuildNameVillage;
	}

	public String getTestimonyRoomNo() {
		return testimonyRoomNo;
	}

	public void setTestimonyRoomNo(String testimonyRoomNo) {
		this.testimonyRoomNo = testimonyRoomNo;
	}

	public String getTestimonyFloorNo() {
		return testimonyFloorNo;
	}

	public void setTestimonyFloorNo(String testimonyFloorNo) {
		this.testimonyFloorNo = testimonyFloorNo;
	}

	public String getTestimonySoiName() {
		return testimonySoiName;
	}

	public void setTestimonySoiName(String testimonySoiName) {
		this.testimonySoiName = testimonySoiName;
	}

	public String getTestimonyThnName() {
		return testimonyThnName;
	}

	public void setTestimonyThnName(String testimonyThnName) {
		this.testimonyThnName = testimonyThnName;
	}

	public String getTestimonyTambolName() {
		return testimonyTambolName;
	}

	public void setTestimonyTambolName(String testimonyTambolName) {
		this.testimonyTambolName = testimonyTambolName;
	}

	public String getTestimonyAmphurName() {
		return testimonyAmphurName;
	}

	public void setTestimonyAmphurName(String testimonyAmphurName) {
		this.testimonyAmphurName = testimonyAmphurName;
	}

	public String getTestimonyProvinceName() {
		return testimonyProvinceName;
	}

	public void setTestimonyProvinceName(String testimonyProvinceName) {
		this.testimonyProvinceName = testimonyProvinceName;
	}

	public String getTestimonyZipCode() {
		return testimonyZipCode;
	}

	public void setTestimonyZipCode(String testimonyZipCode) {
		this.testimonyZipCode = testimonyZipCode;
	}

	public String getTestimonyTelNo() {
		return testimonyTelNo;
	}

	public void setTestimonyTelNo(String testimonyTelNo) {
		this.testimonyTelNo = testimonyTelNo;
	}

	public String getTestimonyCardType() {
		return testimonyCardType;
	}

	public void setTestimonyCardType(String testimonyCardType) {
		this.testimonyCardType = testimonyCardType;
	}

	public String getTestimonyCardOtherDesc() {
		return testimonyCardOtherDesc;
	}

	public void setTestimonyCardOtherDesc(String testimonyCardOtherDesc) {
		this.testimonyCardOtherDesc = testimonyCardOtherDesc;
	}

	public String getTestimonyCardNo() {
		return testimonyCardNo;
	}

	public void setTestimonyCardNo(String testimonyCardNo) {
		this.testimonyCardNo = testimonyCardNo;
	}

	public String getTestimonyCardSource() {
		return testimonyCardSource;
	}

	public void setTestimonyCardSource(String testimonyCardSource) {
		this.testimonyCardSource = testimonyCardSource;
	}

	public String getTestimonyCardCountry() {
		return testimonyCardCountry;
	}

	public void setTestimonyCardCountry(String testimonyCardCountry) {
		this.testimonyCardCountry = testimonyCardCountry;
	}

	public String getTestimonyPosition() {
		return testimonyPosition;
	}

	public void setTestimonyPosition(String testimonyPosition) {
		this.testimonyPosition = testimonyPosition;
	}

	public String getTestimonyFactoryFullName() {
		return testimonyFactoryFullName;
	}

	public void setTestimonyFactoryFullName(String testimonyFactoryFullName) {
		this.testimonyFactoryFullName = testimonyFactoryFullName;
	}

	public String getTestimonyText() {
		return testimonyText;
	}

	public void setTestimonyText(String testimonyText) {
		this.testimonyText = testimonyText;
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
