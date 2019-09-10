package th.co.baiwa.buckwaframework.common.bean;

import java.io.Serializable;

public class LabelValueBean  implements Serializable {

	private static final long serialVersionUID = -4903724438420463149L;
	private String label;
	private String value;

	public String getLabel() {
		return label;
	}

	public LabelValueBean(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LabelValueBean [label=" + label + ", value=" + value + "]";
	}
	
}
