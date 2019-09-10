package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialData {

	@SerializedName("materialSeq")
	@Expose
	private String materialSeq;

	@SerializedName("materialId")
	@Expose
	private String materialId;

	@SerializedName("materialName")
	@Expose
	private String materialName;

	@SerializedName("balanceBF_Qty")
	@Expose
	private String balanceBfQty;

	@SerializedName("materialEntry")
	@Expose
	private List<DataEntry> materialEntry;

	public String getMaterialSeq() {
		return materialSeq;
	}

	public void setMaterialSeq(String materialSeq) {
		this.materialSeq = materialSeq;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getBalanceBfQty() {
		return balanceBfQty;
	}

	public void setBalanceBfQty(String balanceBfQty) {
		this.balanceBfQty = balanceBfQty;
	}

	public List<DataEntry> getMaterialEntry() {
		return materialEntry;
	}

	public void setMaterialEntry(List<DataEntry> materialEntry) {
		this.materialEntry = materialEntry;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
