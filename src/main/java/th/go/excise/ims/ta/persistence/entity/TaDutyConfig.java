package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_DUTY_CONFIG")
public class TaDutyConfig extends BaseEntity {

	private static final long serialVersionUID = 4953209446070061977L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_DUTY_CONFIG_GEN")
	@SequenceGenerator(name = "TA_DUTY_CONFIG_GEN", sequenceName = "TA_DUTY_CONFIG_SEQ", allocationSize = 1)
	@Column(name = "TA_DUTY_CONFIG_SEQ")
	private Long taDutyConfigSeq;
	@Column(name = "DUTY_CODE")
	private String dutyCode;
	@Column(name = "DUTY_TYPE")
	private String dutyType;

	public Long getTaDutyConfigSeq() {
		return taDutyConfigSeq;
	}

	public void setTaDutyConfigSeq(Long taDutyConfigSeq) {
		this.taDutyConfigSeq = taDutyConfigSeq;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getDutyType() {
		return dutyType;
	}

	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}

}
