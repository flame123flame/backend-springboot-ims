package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaExpenses;

public class Int090101Vo extends IaExpenses {

	private static final long serialVersionUID = -7071109742842862569L;
	
	
	private double experimentalBudget;
	private double differenceExperimentalBudget;
	private double ledger;
	private double differenceLedger;

	public double getExperimentalBudget() {
		return experimentalBudget;
	}

	public void setExperimentalBudget(double experimentalBudget) {
		this.experimentalBudget = experimentalBudget;
	}

	public double getDifferenceExperimentalBudget() {
		return differenceExperimentalBudget;
	}

	public void setDifferenceExperimentalBudget(double differenceExperimentalBudget) {
		this.differenceExperimentalBudget = differenceExperimentalBudget;
	}

	public double getLedger() {
		return ledger;
	}

	public void setLedger(double ledger) {
		this.ledger = ledger;
	}

	public double getDifferenceLedger() {
		return differenceLedger;
	}

	public void setDifferenceLedger(double differenceLedger) {
		this.differenceLedger = differenceLedger;
	}

}
