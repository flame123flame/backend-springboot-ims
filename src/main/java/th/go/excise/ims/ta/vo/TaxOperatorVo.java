package th.go.excise.ims.ta.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaxOperatorVo {

	private List<String> condGroups;
	private List<TaxOperatorDatatableVo> datas;
	private Long count;

	public List<String> getCondGroups() {
		return condGroups;
	}

	public void setCondGroups(List<String> condGroups) {
		this.condGroups = condGroups;
	}

	public List<TaxOperatorDatatableVo> getDatas() {
		return datas;
	}

	public void setDatas(List<TaxOperatorDatatableVo> datas) {
		this.datas = datas;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
