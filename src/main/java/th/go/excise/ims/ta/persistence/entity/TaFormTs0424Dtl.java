package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "TA_FORM_TS0424_DTL")
public class TaFormTs0424Dtl extends BaseEntity {

	private static final long serialVersionUID = 7612864237995500468L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0424_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0424_DTL_GEN", sequenceName = "TA_FORM_TS0424_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0424_DTL_ID")
	private Long formTs0424DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "OPERATOR_OFFICE_NAME")
	private String operatorOfficeName;
	@Column(name = "OPERATOR_FULL_NAME")
	private String operatorFullName;
	@Column(name = "OWNER_FULL_NAME")
	private String ownerFullName;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "FACTORY_TYPE_TEXT")
	private String factoryTypeText;
	@Column(name = "CALL_DOC_NO")
	private String callDocNo;
	@Column(name = "CALL_DOC_DATE")
	private Date callDocDate;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "FINE_AMT")
	private BigDecimal fineAmt;
	@Column(name = "EXTRA_AMT")
	private BigDecimal extraAmt;
	@Column(name = "MOI_AMT")
	private BigDecimal moiAmt;
	@Column(name = "NETTAX_AMT")
	private BigDecimal nettaxAmt;
	@Column(name = "RESIDUE_NUM")
	private String residueNum;
	@Column(name = "OFFICER_COMMENT")
	private String officerComment;

	public Long getFormTs0424DtlId() {
		return formTs0424DtlId;
	}

	public void setFormTs0424DtlId(Long formTs0424DtlId) {
		this.formTs0424DtlId = formTs0424DtlId;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public String getOperatorOfficeName() {
		return operatorOfficeName;
	}

	public void setOperatorOfficeName(String operatorOfficeName) {
		this.operatorOfficeName = operatorOfficeName;
	}

	public String getOperatorFullName() {
		return operatorFullName;
	}

	public void setOperatorFullName(String operatorFullName) {
		this.operatorFullName = operatorFullName;
	}

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

	public String getCallDocNo() {
		return callDocNo;
	}

	public void setCallDocNo(String callDocNo) {
		this.callDocNo = callDocNo;
	}

	public Date getCallDocDate() {
		return callDocDate;
	}

	public void setCallDocDate(Date callDocDate) {
		this.callDocDate = callDocDate;
	}

	public Date getAuditDateStart() {
		return auditDateStart;
	}

	public void setAuditDateStart(Date auditDateStart) {
		this.auditDateStart = auditDateStart;
	}

	public Date getAuditDateEnd() {
		return auditDateEnd;
	}

	public void setAuditDateEnd(Date auditDateEnd) {
		this.auditDateEnd = auditDateEnd;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getFineAmt() {
		return fineAmt;
	}

	public void setFineAmt(BigDecimal fineAmt) {
		this.fineAmt = fineAmt;
	}

	public BigDecimal getExtraAmt() {
		return extraAmt;
	}

	public void setExtraAmt(BigDecimal extraAmt) {
		this.extraAmt = extraAmt;
	}

	public BigDecimal getMoiAmt() {
		return moiAmt;
	}

	public void setMoiAmt(BigDecimal moiAmt) {
		this.moiAmt = moiAmt;
	}

	public BigDecimal getNettaxAmt() {
		return nettaxAmt;
	}

	public void setNettaxAmt(BigDecimal nettaxAmt) {
		this.nettaxAmt = nettaxAmt;
	}

	public String getResidueNum() {
		return residueNum;
	}

	public void setResidueNum(String residueNum) {
		this.residueNum = residueNum;
	}

	public String getOfficerComment() {
		return officerComment;
	}

	public void setOfficerComment(String officerComment) {
		this.officerComment = officerComment;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
