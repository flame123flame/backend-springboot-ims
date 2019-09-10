
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
@Table(name = "IA_RISK_SYSTEM_UNWORKING")
public class IaRiskSystemUnworking extends BaseEntity {

	private static final long serialVersionUID = -742764404301564140L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_SYSTEM_UNWORKING_GEN")
	@SequenceGenerator(name = "IA_RISK_SYSTEM_UNWORKING_GEN", sequenceName = "IA_RISK_SYSTEM_UNWORKING_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "SYSTEM_CODE")
	private String systemCode;
	@Column(name = "SYSTEM_NAME")
	private String systemName;
	@Column(name = "COUNT_ALL")
	private String countAll;
	@Column(name = "COUNT_NORMAL")
	private String countNormal;
	@Column(name = "COUNT_ERROR")
	private String countError;
	@Column(name = "START_DATE")
	private String startDate;
	@Column(name = "END_DATE")
	private String endDate;
	@Column(name = "YEAR")
	private String year;
	@Column(name = "MONTH")
	private String month;
	@Column(name = "STATUS")
	private String status;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getCountAll() {
		return countAll;
	}

	public void setCountAll(String countAll) {
		this.countAll = countAll;
	}

	public String getCountNormal() {
		return countNormal;
	}

	public void setCountNormal(String countNormal) {
		this.countNormal = countNormal;
	}

	public String getCountError() {
		return countError;
	}

	public void setCountError(String countError) {
		this.countError = countError;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
