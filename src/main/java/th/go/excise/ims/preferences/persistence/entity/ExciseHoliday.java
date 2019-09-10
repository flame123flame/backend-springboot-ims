package th.go.excise.ims.preferences.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_HOLIDAY")
public class ExciseHoliday extends BaseEntity {

	private static final long serialVersionUID = 2855378733880410836L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_HOLIDAY_GEN")
	@SequenceGenerator(name = "EXCISE_HOLIDAY_GEN", sequenceName = "EXCISE_HOLIDAY_SEQ", allocationSize = 1)
	@Column(name = "HOLIDAY_ID")
	private Long holidayId;
	@Column(name = "HOLIDAY_DATE")
	private LocalDate holidayDate;
	
	public Long getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

}
