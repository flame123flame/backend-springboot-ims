package th.co.baiwa.buckwaframework.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "SYS_PARAMETER_INFO")
public class ParameterInfo extends BaseEntity {

	private static final long serialVersionUID = 5841123178056803593L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PARAM_INFO_ID")
	private Long paramInfoId;

	@Column(name = "PARAM_GROUP_CODE")
	private String paramGroupCode;

	@Column(name = "PARAM_CODE")
	private String paramCode;

	@Column(name = "VALUE_1")
	private String value1;

	@Column(name = "VALUE_2")
	private String value2;

	@Column(name = "VALUE_3")
	private String value3;

	@Column(name = "VALUE_4")
	private String value4;

	@Column(name = "VALUE_5")
	private String value5;

	@Column(name = "VALUE_6")
	private String value6;

	@Column(name = "SORTING_ORDER")
	private Integer sortingOrder;

	@Column(name = "IS_DEFAULT")
	private String isDefault;

	public Long getParamInfoId() {
		return paramInfoId;
	}

	public void setParamInfoId(Long paramInfoId) {
		this.paramInfoId = paramInfoId;
	}

	public String getParamGroupCode() {
		return paramGroupCode;
	}

	public void setParamGroupCode(String paramGroupCode) {
		this.paramGroupCode = paramGroupCode;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public String getValue6() {
		return value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}

	public Integer getSortingOrder() {
		return sortingOrder;
	}

	public void setSortingOrder(Integer sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}
