
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_EXPENSES")
public class IaExpenses extends BaseEntity {

	private static final long serialVersionUID = -3253860408150851803L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EXPENSES_GEN")
	@SequenceGenerator(name = "IA_EXPENSES_GEN", sequenceName = "IA_EXPENSES_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	@Column(name = "SERVICE_RECEIVE")
	private BigDecimal serviceReceive;
	@Column(name = "SERVICE_WITHDRAW")
	private BigDecimal serviceWithdraw;
	@Column(name = "SERVICE_BALANCE")
	private BigDecimal serviceBalance;
	@Column(name = "SUPPRESS_RECEIVE")
	private BigDecimal suppressReceive;
	@Column(name = "SUPPRESS_WITHDRAW")
	private BigDecimal suppressWithdraw;
	@Column(name = "SUPPRESS_BALANCE")
	private BigDecimal suppressBalance;
	@Column(name = "BUDGET_RECEIVE")
	private BigDecimal budgetReceive;
	@Column(name = "BUDGET_WITHDRAW")
	private BigDecimal budgetWithdraw;
	@Column(name = "BUDGET_BALANCE")
	private BigDecimal budgetBalance;
	@Column(name = "SUM_RECEIVE")
	private BigDecimal sumReceive;
	@Column(name = "SUM_WITHDRAW")
	private BigDecimal sumWithdraw;
	@Column(name = "SUM_BALANCE")
	private BigDecimal sumBalance;
	@Column(name = "MONEY_BUDGET")
	private BigDecimal moneyBudget;
	@Column(name = "MONEY_OUT")
	private BigDecimal moneyOut;
	@Column(name = "AVERAGE_COST")
	private BigDecimal averageCost;
	@Column(name = "AVERAGE_GIVE")
	private String averageGive;
	@Column(name = "AVERAGE_FROM")
	private BigDecimal averageFrom;
	@Column(name = "AVERAGE_COME_COST")
	private String averageComeCost;
	@Column(name = "NOTE")
	private String note;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "OFFICE_DESC")
	private String officeDesc;
	@Column(name = "AVERAGE_COST_OUT")
	private BigDecimal averageCostOut;
	@Column(name = "AVERAGE_GIVE_OUT")
	private String averageGiveOut;
	@Column(name = "AVERAGE_FROM_OUT")
	private BigDecimal averageFromOut;
	@Column(name = "AVERAGE_COME_COST_OUT")
	private String averageComeCostOut;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "EXPENSE_DATE")
	private Date expenseDate;
	@Column(name = "EXPENSE_YEAR")
	private String expenseYear;
	@Column(name = "EXPENSE_MONTH")
	private String expenseMonth;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getServiceReceive() {
		return serviceReceive;
	}

	public void setServiceReceive(BigDecimal serviceReceive) {
		this.serviceReceive = serviceReceive;
	}

	public BigDecimal getServiceWithdraw() {
		return serviceWithdraw;
	}

	public void setServiceWithdraw(BigDecimal serviceWithdraw) {
		this.serviceWithdraw = serviceWithdraw;
	}

	public BigDecimal getServiceBalance() {
		return serviceBalance;
	}

	public void setServiceBalance(BigDecimal serviceBalance) {
		this.serviceBalance = serviceBalance;
	}

	public BigDecimal getSuppressReceive() {
		return suppressReceive;
	}

	public void setSuppressReceive(BigDecimal suppressReceive) {
		this.suppressReceive = suppressReceive;
	}

	public BigDecimal getSuppressWithdraw() {
		return suppressWithdraw;
	}

	public void setSuppressWithdraw(BigDecimal suppressWithdraw) {
		this.suppressWithdraw = suppressWithdraw;
	}

	public BigDecimal getSuppressBalance() {
		return suppressBalance;
	}

	public void setSuppressBalance(BigDecimal suppressBalance) {
		this.suppressBalance = suppressBalance;
	}

	public BigDecimal getBudgetReceive() {
		return budgetReceive;
	}

	public void setBudgetReceive(BigDecimal budgetReceive) {
		this.budgetReceive = budgetReceive;
	}

	public BigDecimal getBudgetWithdraw() {
		return budgetWithdraw;
	}

	public void setBudgetWithdraw(BigDecimal budgetWithdraw) {
		this.budgetWithdraw = budgetWithdraw;
	}

	public BigDecimal getBudgetBalance() {
		return budgetBalance;
	}

	public void setBudgetBalance(BigDecimal budgetBalance) {
		this.budgetBalance = budgetBalance;
	}

	public BigDecimal getSumReceive() {
		return sumReceive;
	}

	public void setSumReceive(BigDecimal sumReceive) {
		this.sumReceive = sumReceive;
	}

	public BigDecimal getSumWithdraw() {
		return sumWithdraw;
	}

	public void setSumWithdraw(BigDecimal sumWithdraw) {
		this.sumWithdraw = sumWithdraw;
	}

	public BigDecimal getSumBalance() {
		return sumBalance;
	}

	public void setSumBalance(BigDecimal sumBalance) {
		this.sumBalance = sumBalance;
	}

	public BigDecimal getMoneyBudget() {
		return moneyBudget;
	}

	public void setMoneyBudget(BigDecimal moneyBudget) {
		this.moneyBudget = moneyBudget;
	}

	public BigDecimal getMoneyOut() {
		return moneyOut;
	}

	public void setMoneyOut(BigDecimal moneyOut) {
		this.moneyOut = moneyOut;
	}

	public BigDecimal getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(BigDecimal averageCost) {
		this.averageCost = averageCost;
	}

	public String getAverageGive() {
		return averageGive;
	}

	public void setAverageGive(String averageGive) {
		this.averageGive = averageGive;
	}

	public BigDecimal getAverageFrom() {
		return averageFrom;
	}

	public void setAverageFrom(BigDecimal averageFrom) {
		this.averageFrom = averageFrom;
	}

	public String getAverageComeCost() {
		return averageComeCost;
	}

	public void setAverageComeCost(String averageComeCost) {
		this.averageComeCost = averageComeCost;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeDesc() {
		return officeDesc;
	}

	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public BigDecimal getAverageCostOut() {
		return averageCostOut;
	}

	public void setAverageCostOut(BigDecimal averageCostOut) {
		this.averageCostOut = averageCostOut;
	}

	public String getAverageGiveOut() {
		return averageGiveOut;
	}

	public void setAverageGiveOut(String averageGiveOut) {
		this.averageGiveOut = averageGiveOut;
	}

	public BigDecimal getAverageFromOut() {
		return averageFromOut;
	}

	public void setAverageFromOut(BigDecimal averageFromOut) {
		this.averageFromOut = averageFromOut;
	}

	public String getAverageComeCostOut() {
		return averageComeCostOut;
	}

	public void setAverageComeCostOut(String averageComeCostOut) {
		this.averageComeCostOut = averageComeCostOut;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getExpenseYear() {
		return expenseYear;
	}

	public void setExpenseYear(String expenseYear) {
		this.expenseYear = expenseYear;
	}

	public String getExpenseMonth() {
		return expenseMonth;
	}

	public void setExpenseMonth(String expenseMonth) {
		this.expenseMonth = expenseMonth;
	}

}
