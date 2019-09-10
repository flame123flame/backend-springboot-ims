package th.go.excise.ims.ta.vo;

import java.util.List;

public class TaFormTS0423Vo extends AbstractTaFormTsVo {

	private String alphabet;
	private String factoryTypeText;
	private String ownerName;
	private String newRegId;
	private String facAddrNo;
	private String facSoiName;
	private String facThnName;
	private String facTambolName;
	private String facAmphurName;
	private String facProvinceName;
	private List<TaFormTS0423DtlVo> taFormTS0423DtlVoList;

	public String getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getFacAddrNo() {
		return facAddrNo;
	}

	public void setFacAddrNo(String facAddrNo) {
		this.facAddrNo = facAddrNo;
	}

	public String getFacSoiName() {
		return facSoiName;
	}

	public void setFacSoiName(String facSoiName) {
		this.facSoiName = facSoiName;
	}

	public String getFacThnName() {
		return facThnName;
	}

	public void setFacThnName(String facThnName) {
		this.facThnName = facThnName;
	}

	public String getFacTambolName() {
		return facTambolName;
	}

	public void setFacTambolName(String facTambolName) {
		this.facTambolName = facTambolName;
	}

	public String getFacAmphurName() {
		return facAmphurName;
	}

	public void setFacAmphurName(String facAmphurName) {
		this.facAmphurName = facAmphurName;
	}

	public String getFacProvinceName() {
		return facProvinceName;
	}

	public void setFacProvinceName(String facProvinceName) {
		this.facProvinceName = facProvinceName;
	}

	public List<TaFormTS0423DtlVo> getTaFormTS0423DtlVoList() {
		return taFormTS0423DtlVoList;
	}

	public void setTaFormTS0423DtlVoList(List<TaFormTS0423DtlVo> taFormTS0423DtlVoList) {
		this.taFormTS0423DtlVoList = taFormTS0423DtlVoList;
	}

}
