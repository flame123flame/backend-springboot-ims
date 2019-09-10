package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_FORM_TS0111_DTL")
public class TaFormTs0111Dtl extends BaseEntity {

	private static final long serialVersionUID = 2627676884935036560L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0111_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0111_DTL_GEN", sequenceName = "TA_FORM_TS0111_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0111_DTL_ID")
	private Long formTs0111DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "DOC_NAME")
	private String docName;
	@Column(name = "DOC_QTY")
	private String docQty;
	@Column(name = "DOC_COMMENT")
	private String docComment;

	public Long getFormTs0111DtlId() {
		return formTs0111DtlId;
	}

	public void setFormTs0111DtlId(Long formTs0111DtlId) {
		this.formTs0111DtlId = formTs0111DtlId;
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

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocQty() {
		return docQty;
	}

	public void setDocQty(String docQty) {
		this.docQty = docQty;
	}

	public String getDocComment() {
		return docComment;
	}

	public void setDocComment(String docComment) {
		this.docComment = docComment;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
