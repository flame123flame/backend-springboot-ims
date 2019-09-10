package th.go.excise.ims.ws.persistence.entity;

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
@Table(name = "WS_OASFRI0100_D")
public class WsOasfri0100D extends BaseEntity {

	private static final long serialVersionUID = -1195808536673631950L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_OASFRI0100_D_GEN")
	@SequenceGenerator(name = "WS_OASFRI0100_D_GEN", sequenceName = "WS_OASFRI0100_D_SEQ", allocationSize = 1)
	@Column(name = "OASFRI0100_D_SEQ")
	private Long oasfri0100DSeq;
	@Column(name = "DATA_TYPE")
	private String dataType;
	@Column(name = "FORMDOC_REC0142_NO")
	private String formdocRec0142No;
	@Column(name = "DATA_SEQ")
	private Integer dataSeq;
	@Column(name = "DATA_ID")
	private String dataId;
	@Column(name = "DATA_NAME")
	private String dataName;
	@Column(name = "BAL_BF_QTY")
	private BigDecimal balBfQty;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	@Column(name = "IN_QTY")
	private BigDecimal inQty;

	public Long getOasfri0100DSeq() {
		return oasfri0100DSeq;
	}

	public void setOasfri0100DSeq(Long oasfri0100dSeq) {
		oasfri0100DSeq = oasfri0100dSeq;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFormdocRec0142No() {
		return formdocRec0142No;
	}

	public void setFormdocRec0142No(String formdocRec0142No) {
		this.formdocRec0142No = formdocRec0142No;
	}

	public Integer getDataSeq() {
		return dataSeq;
	}

	public void setDataSeq(Integer dataSeq) {
		this.dataSeq = dataSeq;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public BigDecimal getBalBfQty() {
		return balBfQty;
	}

	public void setBalBfQty(BigDecimal balBfQty) {
		this.balBfQty = balBfQty;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getInQty() {
		return inQty;
	}

	public void setInQty(BigDecimal inQty) {
		this.inQty = inQty;
	}

}
