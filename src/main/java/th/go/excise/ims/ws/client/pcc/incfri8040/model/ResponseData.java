package th.go.excise.ims.ws.client.pcc.incfri8040.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {
	
	@SerializedName("IncomeList")
	@Expose
	private List<Income> incomeList;

	public List<Income> getIncomeList() {
		return incomeList;
	}

	public void setIncomeList(List<Income> incomeList) {
		this.incomeList = incomeList;
	}
	
}
