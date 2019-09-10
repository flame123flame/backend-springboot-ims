
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
@Table(name = "IA_QUESTIONNAIRE_SIDE_DTL")
public class IaQuestionnaireSideDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8834285629175187031L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QUESTIONNAIRE_SIDE_DTL_GEN")
	@SequenceGenerator(name = "IA_QUESTIONNAIRE_SIDE_DTL_GEN", sequenceName = "IA_QUESTIONNAIRE_SIDE_DTL_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_SIDE")
	private BigDecimal idSide;
	@Column(name = "SIDE_DTL")
	private String sideDtl;
	@Column(name = "QTN_LEVEL")
	private BigDecimal qtnLevel;
	@Column(name = "SEQ")
	private BigDecimal seq;
	@Column(name = "SEQ_DTL")
	private BigDecimal seqDtl;
	@Column(name = "ID_HEADING")
	private BigDecimal idHeading;

	public BigDecimal getIdHeading() {
		return idHeading;
	}

	public void setIdHeading(BigDecimal idHeading) {
		this.idHeading = idHeading;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdSide() {
		return idSide;
	}

	public void setIdSide(BigDecimal idSide) {
		this.idSide = idSide;
	}

	public String getSideDtl() {
		return sideDtl;
	}

	public void setSideDtl(String sideDtl) {
		this.sideDtl = sideDtl;
	}

	public BigDecimal getQtnLevel() {
		return qtnLevel;
	}

	public void setQtnLevel(BigDecimal qtnLevel) {
		this.qtnLevel = qtnLevel;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public BigDecimal getSeqDtl() {
		return seqDtl;
	}

	public void setSeqDtl(BigDecimal seqDtl) {
		this.seqDtl = seqDtl;
	}

}
