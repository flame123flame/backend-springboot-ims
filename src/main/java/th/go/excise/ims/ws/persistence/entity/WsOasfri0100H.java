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
@Table(name = "WS_OASFRI0100")
public class WsOasfri0100H extends BaseEntity {

	private static final long serialVersionUID = -8708199146675484408L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_OASFRI0100_GEN")
	@SequenceGenerator(name = "WS_OASFRI0100_GEN", sequenceName = "WS_OASFRI0100_SEQ", allocationSize = 1)
	@Column(name = "OASFRI0100_H_SEQ")
	private Long oasfri0100HSeq;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "TAX_YEAR")
	private String taxYear;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "FORMDOC_REC0142_NO")
	private String formdocRec0142No;
	@Column(name = "FORMDOC_REC0142_DATE")
	private LocalDate formdocRec0142Date;
	@Column(name = "FORMDOC_REC0142_BY")
	private String formdocRec0142By;
	@Column(name = "RCVDOC_SIGN_BY")
	private String rcvdocSignBy;
	@Column(name = "RCVDOC_SIGN_DATE")
	private LocalDate rcvdocSignDate;

	public Long getOasfri0100HSeq() {
		return oasfri0100HSeq;
	}

	public void setOasfri0100HSeq(Long oasfri0100hSeq) {
		oasfri0100HSeq = oasfri0100hSeq;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public String getFormdocRec0142No() {
		return formdocRec0142No;
	}

	public void setFormdocRec0142No(String formdocRec0142No) {
		this.formdocRec0142No = formdocRec0142No;
	}

	public LocalDate getFormdocRec0142Date() {
		return formdocRec0142Date;
	}

	public void setFormdocRec0142Date(LocalDate formdocRec0142Date) {
		this.formdocRec0142Date = formdocRec0142Date;
	}

	public String getFormdocRec0142By() {
		return formdocRec0142By;
	}

	public void setFormdocRec0142By(String formdocRec0142By) {
		this.formdocRec0142By = formdocRec0142By;
	}

	public String getRcvdocSignBy() {
		return rcvdocSignBy;
	}

	public void setRcvdocSignBy(String rcvdocSignBy) {
		this.rcvdocSignBy = rcvdocSignBy;
	}

	public LocalDate getRcvdocSignDate() {
		return rcvdocSignDate;
	}

	public void setRcvdocSignDate(LocalDate rcvdocSignDate) {
		this.rcvdocSignDate = rcvdocSignDate;
	}

}
