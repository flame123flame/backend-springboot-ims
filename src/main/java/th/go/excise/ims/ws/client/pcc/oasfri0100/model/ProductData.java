package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductData {

	@SerializedName("productSeq")
	@Expose
	private String productSeq;

	@SerializedName("productId")
	@Expose
	private String productId;

	@SerializedName("productName")
	@Expose
	private String productName;

	@SerializedName("balanceBF_Qty")
	@Expose
	private String balanceBfQty;

	@SerializedName("productEntry")
	@Expose
	private List<DataEntry> productEntry;

	public String getProductSeq() {
		return productSeq;
	}

	public void setProductSeq(String productSeq) {
		this.productSeq = productSeq;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBalanceBfQty() {
		return balanceBfQty;
	}

	public void setBalanceBfQty(String balanceBfQty) {
		this.balanceBfQty = balanceBfQty;
	}

	public List<DataEntry> getProductEntry() {
		return productEntry;
	}

	public void setProductEntry(List<DataEntry> productEntry) {
		this.productEntry = productEntry;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
