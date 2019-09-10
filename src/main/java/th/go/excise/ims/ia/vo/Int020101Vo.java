package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Int020101Vo extends BaseEntity {

    private BigDecimal id;
    private BigDecimal idHead;
    private String sideName;
    private BigDecimal quantity;

    public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdHead() {
        return idHead;
    }

    public void setIdHead(BigDecimal idHead) {
        this.idHead = idHead;
    }

    public String getSideName() {
        return sideName;
    }

    public void setSideName(String sideName) {
        this.sideName = sideName;
    }

}
