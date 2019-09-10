package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.persistence.entity.IaExpensesD1;

public class Int12040101SaveFormVo extends IaExpenses {
	private static final long serialVersionUID = 2721635924220256250L;
	private String expenseDateStr;
	private String area;
	private List<IaExpensesD1> iaExpensesD1;
	private List<String> deleteId;
	public String getExpenseDateStr() {
		return expenseDateStr;
	}

	public void setExpenseDateStr(String expenseDateStr) {
		this.expenseDateStr = expenseDateStr;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<IaExpensesD1> getIaExpensesD1() {
		return iaExpensesD1;
	}

	public void setIaExpensesD1(List<IaExpensesD1> iaExpensesD1) {
		this.iaExpensesD1 = iaExpensesD1;
	}

	public List<String> getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(List<String> deleteId) {
		this.deleteId = deleteId;
	}
}
