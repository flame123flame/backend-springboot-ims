package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_PAPER_PR04_D")
public class TaPaperPr04D extends BaseEntity {

	private static final long serialVersionUID = -5818114546883990137L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR04_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR04_D_GEN", sequenceName = "TA_PAPER_PR04_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR04_D_SEQ")
	private Long paperPr04DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "DOC_NO")
	private String docNo;
	@Column(name = "MATERIAL_DESC")
	private String materialDesc;
	@Column(name = "INPUT_MATERIAL_QTY")
	private BigDecimal inputMaterialQty;
	@Column(name = "FORMULA_MATERIAL_QTY")
	private BigDecimal formulaMaterialQty;
	@Column(name = "USED_MATERIAL_QTY")
	private BigDecimal usedMaterialQty;
	@Column(name = "REAL_USED_MATERIAL_QTY")
	private BigDecimal realUsedMaterialQty;
	@Column(name = "DIFF_MATERIAL_QTY")
	private BigDecimal diffMaterialQty;
	@Column(name = "MATERIAL_QTY")
	private BigDecimal materialQty;
	@Column(name = "GOODS_QTY")
	private BigDecimal goodsQty;
	@Column(name = "DIFF_GOODS_QTY")
	private BigDecimal diffGoodsQty;
	@Column(name = "WASTE_GOODS_PNT")
	private BigDecimal wasteGoodsPnt;
	@Column(name = "WASTE_GOODS_QTY")
	private BigDecimal wasteGoodsQty;
	@Column(name = "BALANCE_GOODS_QTY")
	private BigDecimal balanceGoodsQty;

	public Long getPaperPr04DSeq() {
		return paperPr04DSeq;
	}

	public void setPaperPr04DSeq(Long paperPr04DSeq) {
		this.paperPr04DSeq = paperPr04DSeq;
	}

	public String getPaperPrNumber() {
		return paperPrNumber;
	}

	public void setPaperPrNumber(String paperPrNumber) {
		this.paperPrNumber = paperPrNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public BigDecimal getInputMaterialQty() {
		return inputMaterialQty;
	}

	public void setInputMaterialQty(BigDecimal inputMaterialQty) {
		this.inputMaterialQty = inputMaterialQty;
	}

	public BigDecimal getFormulaMaterialQty() {
		return formulaMaterialQty;
	}

	public void setFormulaMaterialQty(BigDecimal formulaMaterialQty) {
		this.formulaMaterialQty = formulaMaterialQty;
	}

	public BigDecimal getUsedMaterialQty() {
		return usedMaterialQty;
	}

	public void setUsedMaterialQty(BigDecimal usedMaterialQty) {
		this.usedMaterialQty = usedMaterialQty;
	}

	public BigDecimal getRealUsedMaterialQty() {
		return realUsedMaterialQty;
	}

	public void setRealUsedMaterialQty(BigDecimal realUsedMaterialQty) {
		this.realUsedMaterialQty = realUsedMaterialQty;
	}

	public BigDecimal getDiffMaterialQty() {
		return diffMaterialQty;
	}

	public void setDiffMaterialQty(BigDecimal diffMaterialQty) {
		this.diffMaterialQty = diffMaterialQty;
	}

	public BigDecimal getMaterialQty() {
		return materialQty;
	}

	public void setMaterialQty(BigDecimal materialQty) {
		this.materialQty = materialQty;
	}

	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	public BigDecimal getDiffGoodsQty() {
		return diffGoodsQty;
	}

	public void setDiffGoodsQty(BigDecimal diffGoodsQty) {
		this.diffGoodsQty = diffGoodsQty;
	}

	public BigDecimal getWasteGoodsPnt() {
		return wasteGoodsPnt;
	}

	public void setWasteGoodsPnt(BigDecimal wasteGoodsPnt) {
		this.wasteGoodsPnt = wasteGoodsPnt;
	}

	public BigDecimal getWasteGoodsQty() {
		return wasteGoodsQty;
	}

	public void setWasteGoodsQty(BigDecimal wasteGoodsQty) {
		this.wasteGoodsQty = wasteGoodsQty;
	}

	public BigDecimal getBalanceGoodsQty() {
		return balanceGoodsQty;
	}

	public void setBalanceGoodsQty(BigDecimal balanceGoodsQty) {
		this.balanceGoodsQty = balanceGoodsQty;
	}

}
