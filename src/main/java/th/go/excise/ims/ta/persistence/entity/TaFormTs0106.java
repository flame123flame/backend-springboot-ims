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
@Table(name = "TA_FORM_TS0106")
public class TaFormTs0106 extends BaseEntity {

	private static final long serialVersionUID = -1207232615646337631L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0106_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0106_GEN", sequenceName = "TA_FORM_TS0106_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0106_ID")
	private Long formTs0106Id;
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
	@Column(name = "WRITER_FULL_NAME")
	private String writerFullName;
	@Column(name = "WRITER_POSITION_FLAG")
	private String writerPositionFlag;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
	@Column(name = "FAC_MOO_NO")
	private String facMooNo;
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
	@Column(name = "FAC_TEL_NO")
	private String facTelNo;
	@Column(name = "REF_BOOK_NUMBER1")
	private String refBookNumber1;
	@Column(name = "REF_BOOK_NUMBER2")
	private String refBookNumber2;
	@Column(name = "REF_DOC_DATE")
	private Date refDocDate;
	@Column(name = "AUTH_FULL_NAME")
	private String authFullName;
	@Column(name = "AUTH_AGE")
	private String authAge;
	@Column(name = "AUTH_ADDR_NO")
	private String authAddrNo;
	@Column(name = "AUTH_SOI_NAME")
	private String authSoiName;
	@Column(name = "AUTH_THN_NAME")
	private String authThnName;
	@Column(name = "AUTH_TAMBOL_NAME")
	private String authTambolName;
	@Column(name = "AUTH_AMPHUR_NAME")
	private String authAmphurName;
	@Column(name = "AUTH_PROVINCE_NAME")
	private String authProvinceName;
	@Column(name = "AUTH_ZIP_CODE")
	private String authZipCode;
	@Column(name = "AUTH_TEL_NO")
	private String authTelNo;
	@Column(name = "AUTH_CARD_ID")
	private String authCardId;
	@Column(name = "AUTH_CARD_PLACE")
	private String authCardPlace;
	@Column(name = "DOC_TEXT")
	private String docText;
	@Column(name = "SIGN_AUTH_FULL_NAME1")
	private String signAuthFullName1;
	@Column(name = "SIGN_AUTH_FULL_NAME2")
	private String signAuthFullName2;
	@Column(name = "SIGN_AUTH_FULL_NAME3")
	private String signAuthFullName3;
	@Column(name = "SIGN_WITNESS_FULL_NAME1")
	private String signWitnessFullName1;
	@Column(name = "SIGN_WITNESS_FULL_NAME2")
	private String signWitnessFullName2;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0106Id() {
		return formTs0106Id;
	}

	public void setFormTs0106Id(Long formTs0106Id) {
		this.formTs0106Id = formTs0106Id;
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

	public String getWriterFullName() {
		return writerFullName;
	}

	public void setWriterFullName(String writerFullName) {
		this.writerFullName = writerFullName;
	}

	public String getWriterPositionFlag() {
		return writerPositionFlag;
	}

	public void setWriterPositionFlag(String writerPositionFlag) {
		this.writerPositionFlag = writerPositionFlag;
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

	public String getFacMooNo() {
		return facMooNo;
	}

	public void setFacMooNo(String facMooNo) {
		this.facMooNo = facMooNo;
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

	public String getFacTelNo() {
		return facTelNo;
	}

	public void setFacTelNo(String facTelNo) {
		this.facTelNo = facTelNo;
	}

	public String getRefBookNumber1() {
		return refBookNumber1;
	}

	public void setRefBookNumber1(String refBookNumber1) {
		this.refBookNumber1 = refBookNumber1;
	}

	public String getRefBookNumber2() {
		return refBookNumber2;
	}

	public void setRefBookNumber2(String refBookNumber2) {
		this.refBookNumber2 = refBookNumber2;
	}

	public Date getRefDocDate() {
		return refDocDate;
	}

	public void setRefDocDate(Date refDocDate) {
		this.refDocDate = refDocDate;
	}

	public String getAuthFullName() {
		return authFullName;
	}

	public void setAuthFullName(String authFullName) {
		this.authFullName = authFullName;
	}

	public String getAuthAge() {
		return authAge;
	}

	public void setAuthAge(String authAge) {
		this.authAge = authAge;
	}

	public String getAuthAddrNo() {
		return authAddrNo;
	}

	public void setAuthAddrNo(String authAddrNo) {
		this.authAddrNo = authAddrNo;
	}

	public String getAuthSoiName() {
		return authSoiName;
	}

	public void setAuthSoiName(String authSoiName) {
		this.authSoiName = authSoiName;
	}

	public String getAuthThnName() {
		return authThnName;
	}

	public void setAuthThnName(String authThnName) {
		this.authThnName = authThnName;
	}

	public String getAuthTambolName() {
		return authTambolName;
	}

	public void setAuthTambolName(String authTambolName) {
		this.authTambolName = authTambolName;
	}

	public String getAuthAmphurName() {
		return authAmphurName;
	}

	public void setAuthAmphurName(String authAmphurName) {
		this.authAmphurName = authAmphurName;
	}

	public String getAuthProvinceName() {
		return authProvinceName;
	}

	public void setAuthProvinceName(String authProvinceName) {
		this.authProvinceName = authProvinceName;
	}

	public String getAuthZipCode() {
		return authZipCode;
	}

	public void setAuthZipCode(String authZipCode) {
		this.authZipCode = authZipCode;
	}

	public String getAuthTelNo() {
		return authTelNo;
	}

	public void setAuthTelNo(String authTelNo) {
		this.authTelNo = authTelNo;
	}

	public String getAuthCardId() {
		return authCardId;
	}

	public void setAuthCardId(String authCardId) {
		this.authCardId = authCardId;
	}

	public String getAuthCardPlace() {
		return authCardPlace;
	}

	public void setAuthCardPlace(String authCardPlace) {
		this.authCardPlace = authCardPlace;
	}

	public String getDocText() {
		return docText;
	}

	public void setDocText(String docText) {
		this.docText = docText;
	}

	public String getSignAuthFullName1() {
		return signAuthFullName1;
	}

	public void setSignAuthFullName1(String signAuthFullName1) {
		this.signAuthFullName1 = signAuthFullName1;
	}

	public String getSignAuthFullName2() {
		return signAuthFullName2;
	}

	public void setSignAuthFullName2(String signAuthFullName2) {
		this.signAuthFullName2 = signAuthFullName2;
	}

	public String getSignAuthFullName3() {
		return signAuthFullName3;
	}

	public void setSignAuthFullName3(String signAuthFullName3) {
		this.signAuthFullName3 = signAuthFullName3;
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
