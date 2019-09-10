package th.co.baiwa.buckwaframework.preferences.vo;

import th.co.baiwa.buckwaframework.support.domain.ParamGroup;

public class ParameterGroupVo implements ParamGroup {

	private Long paramGroupId;
	private String paramGroupCode;
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
