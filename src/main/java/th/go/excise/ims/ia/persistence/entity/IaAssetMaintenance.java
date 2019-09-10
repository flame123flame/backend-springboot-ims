
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
@Table(name = "IA_ASSET_MAINTENANCE")
public class IaAssetMaintenance extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2458564482811616641L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_ASSET_MAINTENANCE_GEN")
	@SequenceGenerator(name = "IA_ASSET_MAINTENANCE_GEN", sequenceName = "IA_ASSET_MAINTENANCE_SEQ", allocationSize = 1)
	@Column(name = "MAINTENANCE_ID")
	private BigDecimal maintenanceId;
	@Column(name = "ASSET_BALANCE_ID")
	private BigDecimal assetBalanceId;
	@Column(name = "MAINTENANCE_AMOUNT")
	private BigDecimal maintenanceAmount;
	@Column(name = "MAINTENANCE_DATE")
	private Date maintenanceDate;
	@Column(name = "MAINTENANCE_DESCRIPTION")
	private String maintenanceDescription;
	@Column(name = "MAINTENANCE_PRICE")
	private BigDecimal maintenancePrice;
	@Column(name = "MAINTENANCE_NOTE")
	private String maintenanceNote;

	public BigDecimal getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(BigDecimal maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public BigDecimal getAssetBalanceId() {
		return assetBalanceId;
	}

	public void setAssetBalanceId(BigDecimal assetBalanceId) {
		this.assetBalanceId = assetBalanceId;
	}

	public BigDecimal getMaintenanceAmount() {
		return maintenanceAmount;
	}

	public void setMaintenanceAmount(BigDecimal maintenanceAmount) {
		this.maintenanceAmount = maintenanceAmount;
	}

	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getMaintenanceDescription() {
		return maintenanceDescription;
	}

	public void setMaintenanceDescription(String maintenanceDescription) {
		this.maintenanceDescription = maintenanceDescription;
	}

	public BigDecimal getMaintenancePrice() {
		return maintenancePrice;
	}

	public void setMaintenancePrice(BigDecimal maintenancePrice) {
		this.maintenancePrice = maintenancePrice;
	}

	public String getMaintenanceNote() {
		return maintenanceNote;
	}

	public void setMaintenanceNote(String maintenanceNote) {
		this.maintenanceNote = maintenanceNote;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
