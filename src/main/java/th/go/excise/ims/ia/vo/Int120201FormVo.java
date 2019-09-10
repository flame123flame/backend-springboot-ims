package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAssetBalance;
import th.go.excise.ims.ia.persistence.entity.IaAssetMaintenance;

public class Int120201FormVo {
	private Long assetBalanceId;
	private IaAssetBalance assetBalance;
	private IaAssetMaintenance assetMaintenance;
	private List<IaAssetBalance> assetBalanceList;
	private List<IaAssetMaintenance> assetMaintenanceList;
	private Long day;
	private Long month;
	private Long year;
	private List<Long> assetBalanceIdList;

	public Long getAssetBalanceId() {
		return assetBalanceId;
	}

	public void setAssetBalanceId(Long assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
	}

	public IaAssetBalance getAssetBalance() {
		return assetBalance;
	}

	public void setAssetBalance(IaAssetBalance assetBalance) {
		this.assetBalance = assetBalance;
	}

	public IaAssetMaintenance getAssetMaintenance() {
		return assetMaintenance;
	}

	public void setAssetMaintenance(IaAssetMaintenance assetMaintenance) {
		this.assetMaintenance = assetMaintenance;
	}

	public List<IaAssetBalance> getAssetBalanceList() {
		return assetBalanceList;
	}

	public void setAssetBalanceList(List<IaAssetBalance> assetBalanceList) {
		this.assetBalanceList = assetBalanceList;
	}

	public List<IaAssetMaintenance> getAssetMaintenanceList() {
		return assetMaintenanceList;
	}

	public void setAssetMaintenanceList(List<IaAssetMaintenance> assetMaintenanceList) {
		this.assetMaintenanceList = assetMaintenanceList;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public List<Long> getAssetBalanceIdList() {
		return assetBalanceIdList;
	}

	public void setAssetBalanceIdList(List<Long> assetBalanceIdList) {
		this.assetBalanceIdList = assetBalanceIdList;
	}

}
