package th.co.baiwa.buckwaframework.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "SYS_PARAMETER_GROUP")
public class ParameterGroup extends BaseEntity {

	private static final long serialVersionUID = 6435965186481199454L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PARAM_GROUP_ID")
	private Long paramGroupId;

	@Column(name = "PARAM_GROUP_CODE")
	private String paramGroupCode;

	@Column(name = "PARAM_GROUP_DESC")
	private String paramGroupDesc;

	public Long getParamGroupId() {
		return paramGroupId;
	}

	public void setParamGroupId(Long paramGroupId) {
		this.paramGroupId = paramGroupId;
	}

	public String getParamGroupCode() {
		return paramGroupCode;
	}

	public void setParamGroupCode(String paramGroupCode) {
		this.paramGroupCode = paramGroupCode;
	}

	public String getParamGroupDesc() {
		return paramGroupDesc;
	}

	public void setParamGroupDesc(String paramGroupDesc) {
		this.paramGroupDesc = paramGroupDesc;
	}

}
