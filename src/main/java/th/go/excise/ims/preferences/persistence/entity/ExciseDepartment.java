package th.go.excise.ims.preferences.persistence.entity;

import java.time.LocalDate;

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
@Table(name = "EXCISE_DEPARTMENT")
public class ExciseDepartment extends BaseEntity {

	private static final long serialVersionUID = 3667814912140304434L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_DEPARTMENT_GEN")
	@SequenceGenerator(name = "EXCISE_DEPARTMENT_GEN", sequenceName = "EXCISE_DEPARTMENT_SEQ", allocationSize = 1)
	@Column(name = "OFF_ID")
	private Long offId;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "OFF_NAME")
	private String offName;
	@Column(name = "OFF_SHORT_NAME")
	private String offShortName;
	@Column(name = "OFF_INDC")
	private String offIndc;
	@Column(name = "OFF_LOCATION_CODE")
	private String offLocationCode;
	@Column(name = "OFF_SUP_CODE")
	private String offSupCode;
	@Column(name = "TELNO")
	private String telno;
	@Column(name = "START_DATE")
	private LocalDate startDate;
	@Column(name = "END_DATE")
	private LocalDate endDate;

	public Long getOffId() {
		return offId;
	}

	public void setOffId(Long offId) {
		this.offId = offId;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public String getOffShortName() {
		return offShortName;
	}

	public void setOffShortName(String offShortName) {
		this.offShortName = offShortName;
	}

	public String getOffIndc() {
		return offIndc;
	}

	public void setOffIndc(String offIndc) {
		this.offIndc = offIndc;
	}

	public String getOffLocationCode() {
		return offLocationCode;
	}

	public void setOffLocationCode(String offLocationCode) {
		this.offLocationCode = offLocationCode;
	}

	public String getOffSupCode() {
		return offSupCode;
	}

	public void setOffSupCode(String offSupCode) {
		this.offSupCode = offSupCode;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
