package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData2 {

	@SerializedName("formdoc")
	@Expose
	private FormDoc formDoc;

	@SerializedName("receiveDoc")
	@Expose
	private ReceiveDoc receiveDoc;

	@SerializedName("materialData")
	@Expose
	private List<MaterialData> materialData;

	@SerializedName("productData")
	@Expose
	private List<ProductData> productData;

	public FormDoc getFormDoc() {
		return formDoc;
	}

	public void setFormDoc(FormDoc formDoc) {
		this.formDoc = formDoc;
	}

	public ReceiveDoc getReceiveDoc() {
		return receiveDoc;
	}

	public void setReceiveDoc(ReceiveDoc receiveDoc) {
		this.receiveDoc = receiveDoc;
	}

	public List<MaterialData> getMaterialData() {
		return materialData;
	}

	public void setMaterialData(List<MaterialData> materialData) {
		this.materialData = materialData;
	}

	public List<ProductData> getProductData() {
		return productData;
	}

	public void setProductData(List<ProductData> productData) {
		this.productData = productData;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
