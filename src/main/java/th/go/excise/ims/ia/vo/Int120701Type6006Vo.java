package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int120701Type6006Vo {
	private Long id;
	private String name;
	private String ranking;
	private String sector;
	private String typeUse;
	private int totalMonth;
	private int totalReceipt;
	private BigDecimal totalMoney;
	private String createDateStr;
	private String period;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getTypeUse() {
		return typeUse;
	}

	public void setTypeUse(String typeUse) {
		this.typeUse = typeUse;
	}

	public int getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	public int getTotalReceipt() {
		return totalReceipt;
	}

	public void setTotalReceipt(int totalReceipt) {
		this.totalReceipt = totalReceipt;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
}
