package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.persistence.entity.IaExpensesD1;

public class Int12040101FindVo extends IaExpenses {
	private static final long serialVersionUID = -7820544015445590498L;
	
	private List<IaExpensesD1> iaExpensesD1;

	public List<IaExpensesD1> getIaExpensesD1() {
		return iaExpensesD1;
	}

	public void setIaExpensesD1(List<IaExpensesD1> iaExpensesD1) {
		this.iaExpensesD1 = iaExpensesD1;
	}

}
