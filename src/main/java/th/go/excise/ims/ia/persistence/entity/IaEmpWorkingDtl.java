package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_EMP_WORKING_DTL")
public class IaEmpWorkingDtl extends BaseEntity {

	private static final long serialVersionUID = 4563502095511661696L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EMP_WORKING_DTL_GEN")
	@SequenceGenerator(name = "IA_EMP_WORKING_DTL_GEN", sequenceName = "IA_EMP_WORKING_DTL_SEQ", allocationSize = 1)
	@Column(name = "IA_EMP_WORKING_DTL_SEQ")
	private Long iaEmpWorkingDtlSeq;
	@Column(name = "USER_LOGIN")
	private String userLogin;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_POSITION")
	private String userPosition;
	@Column(name = "USER_OFFCODE")
	private String userOffcode;
	@Column(name = "WORKING_DATE")
	private Date workingDate;
	@Column(name = "WORKING_FLAG")
	private String workingFlag;
	@Column(name = "WORKING_DESC")
	private String workingDesc;
	@Column(name = "WORKING_REMARK")
	private String workingRemark;
	@Column(name = "REIMBURSE_EXP_FLAG")
	private String reimburseExpFlag;

	public Long getIaEmpWorkingDtlSeq() {
		return iaEmpWorkingDtlSeq;
	}

	public void setIaEmpWorkingDtlSeq(Long iaEmpWorkingDtlSeq) {
		this.iaEmpWorkingDtlSeq = iaEmpWorkingDtlSeq;
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

	public Date getWorkingDate() {
		return workingDate;
	}

	public void setWorkingDate(Date workingDate) {
		this.workingDate = workingDate;
	}

	public String getWorkingFlag() {
		return workingFlag;
	}

	public void setWorkingFlag(String workingFlag) {
		this.workingFlag = workingFlag;
	}

	public String getWorkingDesc() {
		return workingDesc;
	}

	public void setWorkingDesc(String workingDesc) {
		this.workingDesc = workingDesc;
	}

	public String getWorkingRemark() {
		return workingRemark;
	}

	public void setWorkingRemark(String workingRemark) {
		this.workingRemark = workingRemark;
	}

	public String getReimburseExpFlag() {
		return reimburseExpFlag;
	}

	public void setReimburseExpFlag(String reimburseExpFlag) {
		this.reimburseExpFlag = reimburseExpFlag;
	}

}
