package th.go.excise.ims.ta.vo;

import java.util.List;

public class TaFormTS0302Vo extends AbstractTaFormTsVo {

	private String factoryName;
	private String factoryTypeText;
	private String ownerName;
	private String newRegId;
	private String facAddrNo;
	private String facSoiName;
	private String facThnName;
	private String facTambolName;
	private String facAmphurName;
	private String facProvinceName;
	private String assessmentText;
	private List<TaFormTS0302DtlVo> taFormTS0302DtlVoList;

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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

	public String getAssessmentText() {
		return assessmentText;
	}

	public void setAssessmentText(String assessmentText) {
		this.assessmentText = assessmentText;
	}

	public List<TaFormTS0302DtlVo> getTaFormTS0302DtlVoList() {
		return taFormTS0302DtlVoList;
	}

	public void setTaFormTS0302DtlVoList(List<TaFormTS0302DtlVo> taFormTS0302DtlVoList) {
		this.taFormTS0302DtlVoList = taFormTS0302DtlVoList;
	}

}
