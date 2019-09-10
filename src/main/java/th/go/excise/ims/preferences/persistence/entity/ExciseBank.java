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
@Table(name = "EXCISE_BANK")
public class ExciseBank extends BaseEntity {

	private static final long serialVersionUID = -1002638595022147650L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_BANK_GEN")
	@SequenceGenerator(name = "EXCISE_BANK_GEN", sequenceName = "EXCISE_BANK_SEQ", allocationSize = 1)
	@Column(name = "BANK_ID")
	private Long bankId;
	@Column(name = "BANK_CODE")
	private String bankCode;
	@Column(name = "BANK_NAME")
	private String bankName;
	@Column(name = "SHORT_NAME")
	private String shortName;
	@Column(name = "BEGIN_DATE")
	private LocalDate beginDate;

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

}
