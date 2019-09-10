
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;

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
@Table(name = "IA_EMP_WORKING_H")
public class IaEmpWorkingH extends BaseEntity {

	private static final long serialVersionUID = 489413356035582647L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EMP_WORKING_H_GEN")
	@SequenceGenerator(name = "IA_EMP_WORKING_H_GEN", sequenceName = "IA_EMP_WORKING_H_SEQ", allocationSize = 1)
	@Column(name = "IA_EMP_WORKING_H_SEQ")
	private Long iaEmpWorkingHSeq;
	@Column(name = "USER_LOGIN")
	private String userLogin;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_POSITION")
	private String userPosition;
	@Column(name = "USER_OFFCODE")
	private String userOffcode;
	@Column(name = "WORKING_MONTH")
	private String workingMonth;
	@Column(name = "OWNER_CASE_SPIRITS")
	private BigDecimal ownerCaseSpirits;
	@Column(name = "ASST_CASE_SPIRITS")
	private BigDecimal asstCaseSpirits;
	@Column(name = "REMARK_CASE_SPIRITS")
	private String remarkCaseSpirits;
	@Column(name = "OWNER_CASE_TOBACCO")
	private BigDecimal ownerCaseTobacco;
	@Column(name = "ASST_CASE_TOBACCO")
	private BigDecimal asstCaseTobacco;
	@Column(name = "REMARK_CASE_TOBACCO")
	private String remarkCaseTobacco;
	@Column(name = "OWNER_CASE_CARD")
	private BigDecimal ownerCaseCard;
	@Column(name = "ASST_CASE_CARD")
	private BigDecimal asstCaseCard;
	@Column(name = "REMARK_CASE_CARD")
	private String remarkCaseCard;
	@Column(name = "OWNER_CASE_EDTAX")
	private BigDecimal ownerCaseEdtax;
	@Column(name = "ASST_CASE_EDTAX")
	private BigDecimal asstCaseEdtax;
	@Column(name = "REMARK_CASE_EDTAX")
	private String remarkCaseEdtax;
	@Column(name = "OWNER_CASE_SPIRITS_FW")
	private BigDecimal ownerCaseSpiritsFw;
	@Column(name = "ASST_CASE_SPIRITS_FW")
	private BigDecimal asstCaseSpiritsFw;
	@Column(name = "OWNER_CASE_TOBACCO_FW")
	private BigDecimal ownerCaseTobaccoFw;
	@Column(name = "ASST_CASE_TOBACCO_FW")
	private BigDecimal asstCaseTobaccoFw;
	@Column(name = "OWNER_CASE_CARD_FW")
	private BigDecimal ownerCaseCardFw;
	@Column(name = "ASST_CASE_CARD_FW")
	private BigDecimal asstCaseCardFw;
	@Column(name = "OWNER_CASE_EDTAX_FW")
	private BigDecimal ownerCaseEdtaxFw;
	@Column(name = "ASST_CASE_EDTAX_FW")
	private BigDecimal asstCaseEdtaxFw;

	public Long getIaEmpWorkingHSeq() {
		return iaEmpWorkingHSeq;
	}

	public void setIaEmpWorkingHSeq(Long iaEmpWorkingHSeq) {
		this.iaEmpWorkingHSeq = iaEmpWorkingHSeq;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getUserOffcode() {
		return userOffcode;
	}

	public void setUserOffcode(String userOffcode) {
		this.userOffcode = userOffcode;
	}

	public String getWorkingMonth() {
		return workingMonth;
	}

	public void setWorkingMonth(String workingMonth) {
		this.workingMonth = workingMonth;
	}

	public BigDecimal getOwnerCaseSpirits() {
		return ownerCaseSpirits;
	}

	public void setOwnerCaseSpirits(BigDecimal ownerCaseSpirits) {
		this.ownerCaseSpirits = ownerCaseSpirits;
	}

	public BigDecimal getAsstCaseSpirits() {
		return asstCaseSpirits;
	}

	public void setAsstCaseSpirits(BigDecimal asstCaseSpirits) {
		this.asstCaseSpirits = asstCaseSpirits;
	}

	public String getRemarkCaseSpirits() {
		return remarkCaseSpirits;
	}

	public void setRemarkCaseSpirits(String remarkCaseSpirits) {
		this.remarkCaseSpirits = remarkCaseSpirits;
	}

	public BigDecimal getOwnerCaseTobacco() {
		return ownerCaseTobacco;
	}

	public void setOwnerCaseTobacco(BigDecimal ownerCaseTobacco) {
		this.ownerCaseTobacco = ownerCaseTobacco;
	}

	public BigDecimal getAsstCaseTobacco() {
		return asstCaseTobacco;
	}

	public void setAsstCaseTobacco(BigDecimal asstCaseTobacco) {
		this.asstCaseTobacco = asstCaseTobacco;
	}

	public String getRemarkCaseTobacco() {
		return remarkCaseTobacco;
	}

	public void setRemarkCaseTobacco(String remarkCaseTobacco) {
		this.remarkCaseTobacco = remarkCaseTobacco;
	}

	public BigDecimal getOwnerCaseCard() {
		return ownerCaseCard;
	}

	public void setOwnerCaseCard(BigDecimal ownerCaseCard) {
		this.ownerCaseCard = ownerCaseCard;
	}

	public BigDecimal getAsstCaseCard() {
		return asstCaseCard;
	}

	public void setAsstCaseCard(BigDecimal asstCaseCard) {
		this.asstCaseCard = asstCaseCard;
	}

	public String getRemarkCaseCard() {
		return remarkCaseCard;
	}

	public void setRemarkCaseCard(String remarkCaseCard) {
		this.remarkCaseCard = remarkCaseCard;
	}

	public BigDecimal getOwnerCaseEdtax() {
		return ownerCaseEdtax;
	}

	public void setOwnerCaseEdtax(BigDecimal ownerCaseEdtax) {
		this.ownerCaseEdtax = ownerCaseEdtax;
	}

	public BigDecimal getAsstCaseEdtax() {
		return asstCaseEdtax;
	}

	public void setAsstCaseEdtax(BigDecimal asstCaseEdtax) {
		this.asstCaseEdtax = asstCaseEdtax;
	}

	public String getRemarkCaseEdtax() {
		return remarkCaseEdtax;
	}

	public void setRemarkCaseEdtax(String remarkCaseEdtax) {
		this.remarkCaseEdtax = remarkCaseEdtax;
	}

	public BigDecimal getOwnerCaseSpiritsFw() {
		return ownerCaseSpiritsFw;
	}

	public void setOwnerCaseSpiritsFw(BigDecimal ownerCaseSpiritsFw) {
		this.ownerCaseSpiritsFw = ownerCaseSpiritsFw;
	}

	public BigDecimal getAsstCaseSpiritsFw() {
		return asstCaseSpiritsFw;
	}

	public void setAsstCaseSpiritsFw(BigDecimal asstCaseSpiritsFw) {
		this.asstCaseSpiritsFw = asstCaseSpiritsFw;
	}

	public BigDecimal getOwnerCaseTobaccoFw() {
		return ownerCaseTobaccoFw;
	}

	public void setOwnerCaseTobaccoFw(BigDecimal ownerCaseTobaccoFw) {
		this.ownerCaseTobaccoFw = ownerCaseTobaccoFw;
	}

	public BigDecimal getAsstCaseTobaccoFw() {
		return asstCaseTobaccoFw;
	}

	public void setAsstCaseTobaccoFw(BigDecimal asstCaseTobaccoFw) {
		this.asstCaseTobaccoFw = asstCaseTobaccoFw;
	}

	public BigDecimal getOwnerCaseCardFw() {
		return ownerCaseCardFw;
	}

	public void setOwnerCaseCardFw(BigDecimal ownerCaseCardFw) {
		this.ownerCaseCardFw = ownerCaseCardFw;
	}

	public BigDecimal getAsstCaseCardFw() {
		return asstCaseCardFw;
	}

	public void setAsstCaseCardFw(BigDecimal asstCaseCardFw) {
		this.asstCaseCardFw = asstCaseCardFw;
	}

	public BigDecimal getOwnerCaseEdtaxFw() {
		return ownerCaseEdtaxFw;
	}

	public void setOwnerCaseEdtaxFw(BigDecimal ownerCaseEdtaxFw) {
		this.ownerCaseEdtaxFw = ownerCaseEdtaxFw;
	}

	public BigDecimal getAsstCaseEdtaxFw() {
		return asstCaseEdtaxFw;
	}

	public void setAsstCaseEdtaxFw(BigDecimal asstCaseEdtaxFw) {
		this.asstCaseEdtaxFw = asstCaseEdtaxFw;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
