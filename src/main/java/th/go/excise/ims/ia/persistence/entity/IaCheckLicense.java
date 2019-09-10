
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
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_CHECK_LICENSE")
public class IaCheckLicense extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1213693220152616223L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHECK_LICENSE_GEN")
	@SequenceGenerator(name = "IA_CHECK_LICENSE_GEN", sequenceName = "IA_CHECK_LICENSE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "LICENS_DATE")
	private Date licensDate;
	@Column(name = "DATE_REMITTANCE")
	private Date dateRemittance;
	@Column(name = "LICENSE_TYPE")
	private String licenseType;
	@Column(name = "PRINT_NUMBER")
	private String printNumber;
	@Column(name = "LICENSE_NUMBER")
	private String licenseNumber;
	@Column(name = "AMOUNT_OUT_SYSTEM")
	private BigDecimal amountOutSystem;
	@Column(name = "AMOUNT_COPY_LICENSE")
	private BigDecimal amountCopyLicense;
	@Column(name = "AMOUNT_FEES")
	private BigDecimal amountFees;
	@Column(name = "INTERIOR_COST_AMOUNT")
	private BigDecimal interiorCostAmount;
	@Column(name = "TOTAL")
	private BigDecimal total;
	@Column(name = "DATE_LICENSE_EFFECTIVE")
	private Date dateLicenseEffective;
	@Column(name = "LICENSE_EXPIRAT_DATE")
	private Date licenseExpiratDate;
	@Column(name = "OFFCODE")
	private String offCode;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getLicensDate() {
		return licensDate;
	}

	public void setLicensDate(Date licensDate) {
		this.licensDate = licensDate;
	}

	public Date getDateRemittance() {
		return dateRemittance;
	}

	public void setDateRemittance(Date dateRemittance) {
		this.dateRemittance = dateRemittance;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getPrintNumber() {
		return printNumber;
	}

	public void setPrintNumber(String printNumber) {
		this.printNumber = printNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public BigDecimal getAmountOutSystem() {
		return amountOutSystem;
	}

	public void setAmountOutSystem(BigDecimal amountOutSystem) {
		this.amountOutSystem = amountOutSystem;
	}

	public BigDecimal getAmountCopyLicense() {
		return amountCopyLicense;
	}

	public void setAmountCopyLicense(BigDecimal amountCopyLicense) {
		this.amountCopyLicense = amountCopyLicense;
	}

	public BigDecimal getAmountFees() {
		return amountFees;
	}

	public void setAmountFees(BigDecimal amountFees) {
		this.amountFees = amountFees;
	}

	public BigDecimal getInteriorCostAmount() {
		return interiorCostAmount;
	}

	public void setInteriorCostAmount(BigDecimal interiorCostAmount) {
		this.interiorCostAmount = interiorCostAmount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getDateLicenseEffective() {
		return dateLicenseEffective;
	}

	public void setDateLicenseEffective(Date dateLicenseEffective) {
		this.dateLicenseEffective = dateLicenseEffective;
	}

	public Date getLicenseExpiratDate() {
		return licenseExpiratDate;
	}

	public void setLicenseExpiratDate(Date licenseExpiratDate) {
		this.licenseExpiratDate = licenseExpiratDate;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

}
