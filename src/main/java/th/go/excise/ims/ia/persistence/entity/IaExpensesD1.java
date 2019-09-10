
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_EXPENSES_D1")
public class IaExpensesD1 extends BaseEntity {
	private static final long serialVersionUID = -462977421314650123L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_EXPENSES_D1_GEN")
	@SequenceGenerator(name = "IA_EXPENSES_D1_GEN", sequenceName = "IA_EXPENSES_D1_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "EXPENSE_YEAR")
	private String expenseYear;
	@Column(name = "EXPENSE_MONTH")
	private String expenseMonth;
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	@Column(name = "DETAIL_SEQ_NO")
	private Long detailSeqNo;
	@Column(name = "AVERAGE_COST")
	private Double averageCost;
	@Column(name = "AVERAGE_GIVE")
	private String averageGive;
	@Column(name = "AVERAGE_FROM")
	private Double averageFrom;
	@Column(name = "AVERAGE_COME_COST")
	private String averageComeCost;
	@Column(name = "MONEY_BUDGET_TYPE")
	private String moneyBudgetType;
	@Column(name = "NOTE")
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Long getDetailSeqNo() {
		return detailSeqNo;
	}

	public void setDetailSeqNo(Long detailSeqNo) {
		this.detailSeqNo = detailSeqNo;
	}

	

	public Double getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(Double averageCost) {
		this.averageCost = averageCost;
	}

	public String getAverageGive() {
		return averageGive;
	}

	public void setAverageGive(String averageGive) {
		this.averageGive = averageGive;
	}

	public Double getAverageFrom() {
		return averageFrom;
	}

	public void setAverageFrom(Double averageFrom) {
		this.averageFrom = averageFrom;
	}

	public String getAverageComeCost() {
		return averageComeCost;
	}

	public void setAverageComeCost(String averageComeCost) {
		this.averageComeCost = averageComeCost;
	}

	public String getMoneyBudgetType() {
		return moneyBudgetType;
	}

	public void setMoneyBudgetType(String moneyBudgetType) {
		this.moneyBudgetType = moneyBudgetType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
