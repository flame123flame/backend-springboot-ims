package th.co.baiwa.buckwaframework.common.bean;

import java.util.List;

public class EditDataTable<T> {
	private String action;
	private List<T> data;
	private String error;
	private List<FieldErrors> fieldErrors;

	public String getAction() {
		return action;
	}

	public List<FieldErrors> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrors> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
}
