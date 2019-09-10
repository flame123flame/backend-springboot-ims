
package th.go.excise.ims.preferences.persistence.entity;

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
@Table(name = "EXCISE_CTRL_DUTY")
public class ExciseCtrlDuty extends BaseEntity {

	private static final long serialVersionUID = 785144111426093247L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_CTRL_DUTY_GEN")
	@SequenceGenerator(name = "EXCISE_CTRL_DUTY_GEN", sequenceName = "EXCISE_CTRL_DUTY_SEQ", allocationSize = 1)
	@Column(name = "EXCISE_CTRL_DUTY_ID")
	private Long exciseCtrlDutyId;
	@Column(name = "DUTY_GROUP_CODE")
	private String dutyGroupCode;
	@Column(name = "DUTY_GROUP_NAME")
	private String dutyGroupName;
	@Column(name = "RES_OFFCODE")
	private String resOffcode;

	public Long getExciseCtrlDutyId() {
		return exciseCtrlDutyId;
	}

	public void setExciseCtrlDutyId(Long exciseCtrlDutyId) {
		this.exciseCtrlDutyId = exciseCtrlDutyId;
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

	public String getResOffcode() {
		return resOffcode;
	}

	public void setResOffcode(String resOffcode) {
		this.resOffcode = resOffcode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
