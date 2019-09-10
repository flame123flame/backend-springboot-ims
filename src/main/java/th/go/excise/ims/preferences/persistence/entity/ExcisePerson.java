package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_PERSON")
public class ExcisePerson extends BaseEntity {

	private static final long serialVersionUID = -2432734262647195857L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_PERSON_GEN")
	@SequenceGenerator(name = "EXCISE_PERSON_GEN", sequenceName = "EXCISE_PERSON_SEQ", allocationSize = 1)
	@Column(name = "ED_PERSON_SEQ")
	private Long edPersonSeq;
	@Column(name = "ED_LOGIN")
	private String edLogin;
	@Column(name = "ED_PERSON_NAME")
	private String edPersonName;
	@Column(name = "ED_POSITION_SEQ")
	private Long edPositionSeq;
	@Column(name = "ED_POSITION_NAME")
	private String edPositionName;
	@Column(name = "ED_OFFCODE")
	private String edOffcode;
	@Column(name = "ED_PERSON_ID")
	private String edPersonId;
	@Column(name = "AU_SUBDEPT_CODE")
	private String auSubdeptCode;
	@Column(name = "AU_SUBDEPT_LEVEL")
	private String auSubdeptLevel;
	@Column(name = "SEQ")
	private Integer seq;

	public Long getEdPersonSeq() {
		return edPersonSeq;
	}

	public void setEdPersonSeq(Long edPersonSeq) {
		this.edPersonSeq = edPersonSeq;
	}

	public String getEdLogin() {
		return edLogin;
	}

	public void setEdLogin(String edLogin) {
		this.edLogin = edLogin;
	}

	public String getEdPersonName() {
		return edPersonName;
	}

	public void setEdPersonName(String edPersonName) {
		this.edPersonName = edPersonName;
	}

	public Long getEdPositionSeq() {
		return edPositionSeq;
	}

	public void setEdPositionSeq(Long edPositionSeq) {
		this.edPositionSeq = edPositionSeq;
	}

	public String getEdPositionName() {
		return edPositionName;
	}

	public void setEdPositionName(String edPositionName) {
		this.edPositionName = edPositionName;
	}

	public String getEdOffcode() {
		return edOffcode;
	}

	public void setEdOffcode(String edOffcode) {
		this.edOffcode = edOffcode;
	}

	public String getEdPersonId() {
		return edPersonId;
	}

	public void setEdPersonId(String edPersonId) {
		this.edPersonId = edPersonId;
	}

	public String getAuSubdeptCode() {
		return auSubdeptCode;
	}

	public void setAuSubdeptCode(String auSubdeptCode) {
		this.auSubdeptCode = auSubdeptCode;
	}

	public String getAuSubdeptLevel() {
		return auSubdeptLevel;
	}

	public void setAuSubdeptLevel(String auSubdeptLevel) {
		this.auSubdeptLevel = auSubdeptLevel;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}
