package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_FORM_TS0107_DTL")
public class TaFormTs0107Dtl extends BaseEntity {

	private static final long serialVersionUID = -6642334621003129949L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0107_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0107_DTL_GEN", sequenceName = "TA_FORM_TS0107_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0107_DTL_ID")
	private Long formTs0107DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;
	@Column(name = "OFFICER_POSITION")
	private String officerPosition;

	public Long getFormTs0107DtlId() {
		return formTs0107DtlId;
	}

	public void setFormTs0107DtlId(Long formTs0107DtlId) {
		this.formTs0107DtlId = formTs0107DtlId;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOfficerFullName() {
		return officerFullName;
	}

	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}

}
