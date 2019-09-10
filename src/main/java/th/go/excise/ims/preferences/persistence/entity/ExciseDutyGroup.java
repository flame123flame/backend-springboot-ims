package th.go.excise.ims.preferences.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_DUTY_GROUP")
public class ExciseDutyGroup extends BaseEntity {

	private static final long serialVersionUID = -3940642654634322984L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_DUTY_GROUP_GEN")
	@SequenceGenerator(name = "EXCISE_DUTY_GROUP_GEN", sequenceName = "EXCISE_DUTY_GROUP_SEQ", allocationSize = 1)
	@Column(name = "DUTY_GROUP_ID")
	private Long dutyGroupId;
	@Column(name = "DUTY_GROUP_CODE")
	private String dutyGroupCode;
	@Column(name = "DUTY_GROUP_NAME")
	private String dutyGroupName;
	@Column(name = "DUTY_GROUP_STATUS")
	private String dutyGroupStatus;
	@Column(name = "SUP_DUTY_GROUP_CODE")
	private String supDutyGroupCode;
	@Column(name = "REG_STATUS")
	private String regStatus;
	@Column(name = "BEGIN_DATE")
	private LocalDate beginDate;
	@Column(name = "DUTY_GROUP_TYPE")
	private String dutyGroupType;

	public String getDutyGroupType() {
		return dutyGroupType;
	}

	public void setDutyGroupType(String dutyGroupType) {
		this.dutyGroupType = dutyGroupType;
	}

	public Long getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(Long dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public String getDutyGroupCode() {
		return dutyGroupCode;
	}

	public void setDutyGroupCode(String dutyGroupCode) {
		this.dutyGroupCode = dutyGroupCode;
	}

	public String getDutyGroupName() {
		return dutyGroupName;
	}

	public void setDutyGroupName(String dutyGroupName) {
		this.dutyGroupName = dutyGroupName;
	}

	public String getDutyGroupStatus() {
		return dutyGroupStatus;
	}

	public void setDutyGroupStatus(String dutyGroupStatus) {
		this.dutyGroupStatus = dutyGroupStatus;
	}

	public String getSupDutyGroupCode() {
		return supDutyGroupCode;
	}

	public void setSupDutyGroupCode(String supDutyGroupCode) {
		this.supDutyGroupCode = supDutyGroupCode;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

}
