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
@Table(name = "TA_FORM_TS0111_HDR")
public class TaFormTs0111Hdr extends BaseEntity {

	private static final long serialVersionUID = 871209121287726609L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0111_HDR_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0111_HDR_GEN", sequenceName = "TA_FORM_TS0111_HDR_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0111_HDR_ID")
	private Long formTs0111HdrId;
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
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;
	@Column(name = "OFFICER_POSITION")
	private String officerPosition;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
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
	@Column(name = "DELIVER_FULL_NAME")
	private String deliverFullName;
	@Column(name = "DELIVER_POSITION")
	private String deliverPosition;
	@Column(name = "DELIVER_OTHER")
	private String deliverOther;
	@Column(name = "REF_BOOK_NUMBER1")
	private String refBookNumber1;
	@Column(name = "REF_BOOK_NUMBER2")
	private String refBookNumber2;
	@Column(name = "REF_DOC_DATE")
	private Date refDocDate;
	@Column(name = "SIGN_AUTH_FULL_NAME1")
	private String signAuthFullName1;
	@Column(name = "SIGN_WITNESS_FULL_NAME1")
	private String signWitnessFullName1;
	@Column(name = "SIGN_WITNESS_FULL_NAME2")
	private String signWitnessFullName2;
	@Column(name = "AUTH_FULL_NAME1")
	private String authFullName1;
	@Column(name = "SIGN_AUTH_FULL_NAME2")
	private String signAuthFullName2;
	@Column(name = "SIGN_WITNESS_FULL_NAME3")
	private String signWitnessFullName3;
	@Column(name = "SIGN_WITNESS_FULL_NAME4")
	private String signWitnessFullName4;
	@Column(name = "AUTH_FULL_NAME2")
	private String authFullName2;
	@Column(name = "AUTH_POSITION")
	private String authPosition;
	@Column(name = "AUTH_POSITION_OTHER")
	private String authPositionOther;
	@Column(name = "AUTH_FROM")
	private String authFrom;
	@Column(name = "AUTH_DATE")
	private Date authDate;
	@Column(name = "SIGN_AUTH_FULL_NAME3")
	private String signAuthFullName3;
	@Column(name = "SIGN_AUTH_FULL_NAME4")
	private String signAuthFullName4;
	@Column(name = "SIGN_WITNESS_FULL_NAME5")
	private String signWitnessFullName5;
	@Column(name = "SIGN_WITNESS_FULL_NAME6")
	private String signWitnessFullName6;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0111HdrId() {
		return formTs0111HdrId;
	}

	public void setFormTs0111HdrId(Long formTs0111HdrId) {
		this.formTs0111HdrId = formTs0111HdrId;
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

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
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

	public String getDeliverFullName() {
		return deliverFullName;
	}

	public void setDeliverFullName(String deliverFullName) {
		this.deliverFullName = deliverFullName;
	}

	public String getDeliverPosition() {
		return deliverPosition;
	}

	public void setDeliverPosition(String deliverPosition) {
		this.deliverPosition = deliverPosition;
	}

	public String getDeliverOther() {
		return deliverOther;
	}

	public void setDeliverOther(String deliverOther) {
		this.deliverOther = deliverOther;
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

	public String getSignAuthFullName1() {
		return signAuthFullName1;
	}

	public void setSignAuthFullName1(String signAuthFullName1) {
		this.signAuthFullName1 = signAuthFullName1;
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

	public String getAuthFullName1() {
		return authFullName1;
	}

	public void setAuthFullName1(String authFullName1) {
		this.authFullName1 = authFullName1;
	}

	public String getSignAuthFullName2() {
		return signAuthFullName2;
	}

	public void setSignAuthFullName2(String signAuthFullName2) {
		this.signAuthFullName2 = signAuthFullName2;
	}

	public String getSignWitnessFullName3() {
		return signWitnessFullName3;
	}

	public void setSignWitnessFullName3(String signWitnessFullName3) {
		this.signWitnessFullName3 = signWitnessFullName3;
	}

	public String getSignWitnessFullName4() {
		return signWitnessFullName4;
	}

	public void setSignWitnessFullName4(String signWitnessFullName4) {
		this.signWitnessFullName4 = signWitnessFullName4;
	}

	public String getAuthFullName2() {
		return authFullName2;
	}

	public void setAuthFullName2(String authFullName2) {
		this.authFullName2 = authFullName2;
	}

	public String getAuthPosition() {
		return authPosition;
	}

	public void setAuthPosition(String authPosition) {
		this.authPosition = authPosition;
	}

	public String getAuthPositionOther() {
		return authPositionOther;
	}

	public void setAuthPositionOther(String authPositionOther) {
		this.authPositionOther = authPositionOther;
	}

	public String getAuthFrom() {
		return authFrom;
	}

	public void setAuthFrom(String authFrom) {
		this.authFrom = authFrom;
	}

	public Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public String getSignAuthFullName3() {
		return signAuthFullName3;
	}

	public void setSignAuthFullName3(String signAuthFullName3) {
		this.signAuthFullName3 = signAuthFullName3;
	}

	public String getSignAuthFullName4() {
		return signAuthFullName4;
	}

	public void setSignAuthFullName4(String signAuthFullName4) {
		this.signAuthFullName4 = signAuthFullName4;
	}

	public String getSignWitnessFullName5() {
		return signWitnessFullName5;
	}

	public void setSignWitnessFullName5(String signWitnessFullName5) {
		this.signWitnessFullName5 = signWitnessFullName5;
	}

	public String getSignWitnessFullName6() {
		return signWitnessFullName6;
	}

	public void setSignWitnessFullName6(String signWitnessFullName6) {
		this.signWitnessFullName6 = signWitnessFullName6;
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
