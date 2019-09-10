
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_GFMOVEMENT_ACCOUNT")
public class IaGfmovementAccount extends BaseEntity {

	private static final long serialVersionUID = -233674781808721659L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_GFMOVEMENT_ACCOUNT_GEN")
	@SequenceGenerator(name = "IA_GFMOVEMENT_ACCOUNT_GEN", sequenceName = "IA_GFMOVEMENT_ACCOUNT_SEQ", allocationSize = 1)
	@Column(name = "GFMOVEMENT_ACCOUNT_ID")
	private Long gfmovementAccountId;
	@Column(name = "GFUPLOAD_H_ID")
	private Long gfuploadHId;
	@Column(name = "ACC_TYPE_NO")
	private String accTypeNo;
	@Column(name = "ACC_TYPE_NAME")
	private String accTypeName;
	@Column(name = "ACC_NO")
	private String accNo;
	@Column(name = "GF_ACC_NO")
	private String gfAccNo;
	@Column(name = "GF_DOC_DATE")
	private Date gfDocDate;
	@Column(name = "GF_DOC_NO")
	private String gfDocNo;
	@Column(name = "GF_DOC_TYEP")
	private String gfDocTyep;
	@Column(name = "GF_REF_DOC")
	private String gfRefDoc;
	@Column(name = "CARE_INSTEAD")
	private String careInstead;
	@Column(name = "DETERMINATON")
	private String determinaton;
	@Column(name = "DEPT_DISB")
	private String deptDisb;
	@Column(name = "DEBIT")
	private BigDecimal debit;
	@Column(name = "CREDIT")
	private BigDecimal credit;
	@Column(name = "CARRY_FORWARD")
	private BigDecimal carryForward;

	public Long getGfmovementAccountId() {
		return gfmovementAccountId;
	}

	public void setGfmovementAccountId(Long gfmovementAccountId) {
		this.gfmovementAccountId = gfmovementAccountId;
	}

	public Long getGfuploadHId() {
		return gfuploadHId;
	}

	public void setGfuploadHId(Long gfuploadHId) {
		this.gfuploadHId = gfuploadHId;
	}

	public String getAccTypeNo() {
		return accTypeNo;
	}

	public void setAccTypeNo(String accTypeNo) {
		this.accTypeNo = accTypeNo;
	}

	public String getAccTypeName() {
		return accTypeName;
	}

	public void setAccTypeName(String accTypeName) {
		this.accTypeName = accTypeName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getGfAccNo() {
		return gfAccNo;
	}

	public void setGfAccNo(String gfAccNo) {
		this.gfAccNo = gfAccNo;
	}

	public Date getGfDocDate() {
		return gfDocDate;
	}

	public void setGfDocDate(Date gfDocDate) {
		this.gfDocDate = gfDocDate;
	}

	public String getGfDocNo() {
		return gfDocNo;
	}

	public void setGfDocNo(String gfDocNo) {
		this.gfDocNo = gfDocNo;
	}

	public String getGfDocTyep() {
		return gfDocTyep;
	}

	public void setGfDocTyep(String gfDocTyep) {
		this.gfDocTyep = gfDocTyep;
	}

	public String getGfRefDoc() {
		return gfRefDoc;
	}

	public void setGfRefDoc(String gfRefDoc) {
		this.gfRefDoc = gfRefDoc;
	}

	public String getCareInstead() {
		return careInstead;
	}

	public void setCareInstead(String careInstead) {
		this.careInstead = careInstead;
	}

	public String getDeterminaton() {
		return determinaton;
	}

	public void setDeterminaton(String determinaton) {
		this.determinaton = determinaton;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

}
