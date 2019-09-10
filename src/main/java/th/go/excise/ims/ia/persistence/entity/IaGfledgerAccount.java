
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
@Table(name = "IA_GFLEDGER_ACCOUNT")
public class IaGfledgerAccount extends BaseEntity {

	private static final long serialVersionUID = 4716224288678318071L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_GFLEDGER_ACCOUNT_GEN")
	@SequenceGenerator(name = "IA_GFLEDGER_ACCOUNT_GEN", sequenceName = "IA_GFLEDGER_ACCOUNT_SEQ", allocationSize = 1)
	@Column(name = "IA_GFLEDGER_ACCOUNT_ID")
	private Long iaGfledgerAccountId;
	@Column(name = "GFUPLOAD_H_ID")
	private Long gfuploadHId;
	@Column(name = "GL_ACC_NO")
	private String glAccNo;
	@Column(name = "DEP_CODE")
	private String depCode;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "PERIOD")
	private BigDecimal period;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "POSTING_DATE")
	private Date postingDate;
	@Column(name = "DOC_NO")
	private String docNo;
	@Column(name = "REF_CODE")
	private String refCode;
	@Column(name = "CURR_AMT")
	private BigDecimal currAmt;
	@Column(name = "PK_CODE")
	private String pkCode;
	@Column(name = "ROR_KOR")
	private String rorKor;
	@Column(name = "DETERMINATON")
	private String determinaton;
	@Column(name = "MSG")
	private String msg;
	@Column(name = "KEY_REF_3")
	private String keyRef3;
	@Column(name = "KEY_REF_1")
	private String keyRef1;
	@Column(name = "KEY_REF_2")
	private String keyRef2;
	@Column(name = "HLODING_TAXES")
	private BigDecimal hlodingTaxes;
	@Column(name = "DEPOSIT_ACC")
	private String depositAcc;
	@Column(name = "ACC_TYPE")
	private String accType;
	@Column(name = "COST_CENTER")
	private String costCenter;
	@Column(name = "DEPT_DISB")
	private String deptDisb;
	@Column(name = "CLRNG_DOC")
	private String clrngDoc;
	@Column(name = "PERIOD_YEAR")
	private String periodYear;

	public Long getIaGfledgerAccountId() {
		return iaGfledgerAccountId;
	}

	public void setIaGfledgerAccountId(Long iaGfledgerAccountId) {
		this.iaGfledgerAccountId = iaGfledgerAccountId;
	}

	public Long getGfuploadHId() {
		return gfuploadHId;
	}

	public void setGfuploadHId(Long gfuploadHId) {
		this.gfuploadHId = gfuploadHId;
	}

	public String getGlAccNo() {
		return glAccNo;
	}

	public void setGlAccNo(String glAccNo) {
		this.glAccNo = glAccNo;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPeriod() {
		return period;
	}

	public void setPeriod(BigDecimal period) {
		this.period = period;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public BigDecimal getCurrAmt() {
		return currAmt;
	}

	public void setCurrAmt(BigDecimal currAmt) {
		this.currAmt = currAmt;
	}

	public String getPkCode() {
		return pkCode;
	}

	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}

	public String getRorKor() {
		return rorKor;
	}

	public void setRorKor(String rorKor) {
		this.rorKor = rorKor;
	}

	public String getDeterminaton() {
		return determinaton;
	}

	public void setDeterminaton(String determinaton) {
		this.determinaton = determinaton;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getKeyRef3() {
		return keyRef3;
	}

	public void setKeyRef3(String keyRef3) {
		this.keyRef3 = keyRef3;
	}

	public String getKeyRef1() {
		return keyRef1;
	}

	public void setKeyRef1(String keyRef1) {
		this.keyRef1 = keyRef1;
	}

	public String getKeyRef2() {
		return keyRef2;
	}

	public void setKeyRef2(String keyRef2) {
		this.keyRef2 = keyRef2;
	}

	public BigDecimal getHlodingTaxes() {
		return hlodingTaxes;
	}

	public void setHlodingTaxes(BigDecimal hlodingTaxes) {
		this.hlodingTaxes = hlodingTaxes;
	}

	public String getDepositAcc() {
		return depositAcc;
	}

	public void setDepositAcc(String depositAcc) {
		this.depositAcc = depositAcc;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

	public String getClrngDoc() {
		return clrngDoc;
	}

	public void setClrngDoc(String clrngDoc) {
		this.clrngDoc = clrngDoc;
	}

	public String getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}

}