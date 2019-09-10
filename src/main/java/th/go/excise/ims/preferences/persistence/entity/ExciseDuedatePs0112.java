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
@Table(name = "EXCISE_DUEDATE_PS0112")
public class ExciseDuedatePs0112 extends BaseEntity {

	private static final long serialVersionUID = -5587780501528028545L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_DUEDATE_PS0112_GEN")
	@SequenceGenerator(name = "EXCISE_DUEDATE_PS0112_GEN", sequenceName = "EXCISE_DUEDATE_PS0112_SEQ", allocationSize = 1)
	@Column(name = "DUEDATE_PS0112_ID")
	private Long duedatePs0112Id;
	@Column(name = "YEAR")
	private String year;
	@Column(name = "MONTH")
	private String month;
	@Column(name = "DUEDATE")
	private LocalDate duedate;

	public Long getDuedatePs0112Id() {
		return duedatePs0112Id;
	}

	public void setDuedatePs0112Id(Long duedatePs0112Id) {
		this.duedatePs0112Id = duedatePs0112Id;
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

	public LocalDate getDuedate() {
		return duedate;
	}

	public void setDuedate(LocalDate duedate) {
		this.duedate = duedate;
	}

}
