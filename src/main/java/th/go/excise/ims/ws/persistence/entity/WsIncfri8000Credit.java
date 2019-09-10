package th.go.excise.ims.ws.persistence.entity;

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
@Table(name = "WS_INCFRI8000_CREDIT")
public class WsIncfri8000Credit extends BaseEntity {

	private static final long serialVersionUID = 4897632867025691596L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_INCFRI8000_CREDIT_GEN")
	@SequenceGenerator(name = "WS_INCFRI8000_CREDIT_GEN", sequenceName = "WS_INCFRI8000_CREDIT_SEQ", allocationSize = 1)
	@Column(name = "INCFRI8000_ID")
	private Long incfri8000Id;
	@Column(name = "DATE_TYPE")
	private String dateType;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "APPROVE_NO")
	private String approveNo;
	@Column(name = "APPROVE_DATE")
	private LocalDate approveDate;
	@Column(name = "REF_DATE")
	private LocalDate refDate;

	public Long getIncfri8000Id() {
		return incfri8000Id;
	}

	public void setIncfri8000Id(Long incfri8000Id) {
		this.incfri8000Id = incfri8000Id;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getApproveNo() {
		return approveNo;
	}

	public void setApproveNo(String approveNo) {
		this.approveNo = approveNo;
	}

	public LocalDate getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(LocalDate approveDate) {
		this.approveDate = approveDate;
	}

	public LocalDate getRefDate() {
		return refDate;
	}

	public void setRefDate(LocalDate refDate) {
		this.refDate = refDate;
	}

}
