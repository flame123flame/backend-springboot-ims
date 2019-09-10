package th.go.excise.ims.ta.vo;

import java.util.Date;
import java.util.List;

public class TaFormTS0115Vo extends AbstractTaFormTsVo {

	private String officeName;
	private Date docDate;
	private String ownerFullName;
	private String factoryType;
	private String factoryName;
	private String newRegId;
	private String facAddrNo;
	private String facSoiName;
	private String facThnName;
	private String facTambolName;
	private String facAmphurName;
	private String facProvinceName;
	private String facZipCode;
	private List<TaFormTS0115DtlVo> taFormTS0115DtlVoList;
	private String signOwnerFullName;
	private String signOfficerFullName;
	private String signWitnessFullName1;
	private String signWitnessFullName2;

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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

	public String getFacZipCode() {
		return facZipCode;
	}

	public void setFacZipCode(String facZipCode) {
		this.facZipCode = facZipCode;
	}

	public List<TaFormTS0115DtlVo> getTaFormTS0115DtlVoList() {
		return taFormTS0115DtlVoList;
	}

	public void setTaFormTS0115DtlVoList(List<TaFormTS0115DtlVo> taFormTS0115DtlVoList) {
		this.taFormTS0115DtlVoList = taFormTS0115DtlVoList;
	}

	public String getSignOwnerFullName() {
		return signOwnerFullName;
	}

	public void setSignOwnerFullName(String signOwnerFullName) {
		this.signOwnerFullName = signOwnerFullName;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
	}

	public String getSignWitnessFullName1() {
		return signWitnessFullName1;
	}

	public void setSignWitnessFullName1(String signWitnessFullName1) {
		this.signWitnessFullName1 = signWitnessFullName1;
	}

	public String getSignWitnessFullName2() {
		return signWitnessFullName2;
	}

	public void setSignWitnessFullName2(String signWitnessFullName2) {
		this.signWitnessFullName2 = signWitnessFullName2;
	}

}
