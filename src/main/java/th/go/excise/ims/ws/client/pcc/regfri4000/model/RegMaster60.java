package th.go.excise.ims.ws.client.pcc.regfri4000.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegMaster60 {

	@SerializedName("NewregId")
	@Expose
	private String newregId;

	@SerializedName("RegId")
	@Expose
	private String regId;

	@SerializedName("RegStatus")
	@Expose
	private String regStatus;

	@SerializedName("RegStatusDesc")
	@Expose
	private String regStatusDesc;

	@SerializedName("StatusDate")
	@Expose
	private String statusDate;

	@SerializedName("CusId")
	@Expose
	private String cusId;

	@SerializedName("CusSeq")
	@Expose
	private String cusSeq;

	@SerializedName("CusAddrseq")
	@Expose
	private String cusAddrseq;

	@SerializedName("CusFullname")
	@Expose
	private String cusFullname;

	@SerializedName("CusHouseno")
	@Expose
	private String cusHouseno;

	@SerializedName("CusAddrno")
	@Expose
	private String cusAddrno;

	@SerializedName("CusBuildname")
	@Expose
	private String cusBuildname;

	@SerializedName("CusFloorno")
	@Expose
	private String cusFloorno;

	@SerializedName("CusRoomno")
	@Expose
	private String cusRoomno;

	@SerializedName("CusMoono")
	@Expose
	private String cusMoono;

	@SerializedName("CusVillage")
	@Expose
	private String cusVillage;

	@SerializedName("CusSoiname")
	@Expose
	private String cusSoiname;

	@SerializedName("CusThnname")
	@Expose
	private String cusThnname;

	@SerializedName("CusTambolcode")
	@Expose
	private String cusTambolcode;

	@SerializedName("CusTambolname")
	@Expose
	private String cusTambolname;

	@SerializedName("CusAmphurcode")
	@Expose
	private String cusAmphurcode;

	@SerializedName("CusAmphurname")
	@Expose
	private String cusAmphurname;

	@SerializedName("CusProvincecode")
	@Expose
	private String cusProvincecode;

	@SerializedName("CusProvincename")
	@Expose
	private String cusProvincename;

	@SerializedName("cusZipcode")
	@Expose
	private String cusZipcode;

	@SerializedName("CusTelno")
	@Expose
	private String cusTelno;

	@SerializedName("CusEmail")
	@Expose
	private String cusEmail;

	@SerializedName("CusUrl")
	@Expose
	private String cusUrl;

	@SerializedName("FacId")
	@Expose
	private String facId;

	@SerializedName("FacSeq")
	@Expose
	private String facSeq;

	@SerializedName("FacAddrseq")
	@Expose
	private String facAddrseq;

	@SerializedName("FacFullname")
	@Expose
	private String facFullname;

	@SerializedName("FacHouseno")
	@Expose
	private String facHouseno;

	@SerializedName("FacAddrno")
	@Expose
	private String facAddrno;

	@SerializedName("FacBuildname")
	@Expose
	private String facBuildname;

	@SerializedName("FacFloorno")
	@Expose
	private String facFloorno;

	@SerializedName("FacRoomno")
	@Expose
	private String facRoomno;

	@SerializedName("FacMoono")
	@Expose
	private String facMoono;

	@SerializedName("FacVillage")
	@Expose
	private String facVillage;

	@SerializedName("FacSoiname")
	@Expose
	private String facSoiname;

	@SerializedName("FacThnname")
	@Expose
	private String facThnname;

	@SerializedName("FacTambolcode")
	@Expose
	private String facTambolcode;

	@SerializedName("FacTambolname")
	@Expose
	private String facTambolname;

	@SerializedName("FacAmphurcode")
	@Expose
	private String facAmphurcode;

	@SerializedName("FacAmphurname")
	@Expose
	private String facAmphurname;

	@SerializedName("FacProvincecode")
	@Expose
	private String facProvincecode;

	@SerializedName("FacProvincename")
	@Expose
	private String facProvincename;

	@SerializedName("FacZipcode")
	@Expose
	private String facZipcode;

	@SerializedName("FacTelno")
	@Expose
	private String facTelno;

	@SerializedName("FacEmail")
	@Expose
	private String facEmail;

	@SerializedName("FacUrl")
	@Expose
	private String facUrl;

	@SerializedName("Offcode")
	@Expose
	private String offcode;

	@SerializedName("CAPITAL")
	@Expose
	private String capital;

	@SerializedName("ActiveFlag")
	@Expose
	private String activeFlag;

	@SerializedName("RegDutyList")
	@Expose
	private List<RegDuty> regDutyList = null;

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getRegStatusDesc() {
		return regStatusDesc;
	}

	public void setRegStatusDesc(String regStatusDesc) {
		this.regStatusDesc = regStatusDesc;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusSeq() {
		return cusSeq;
	}

	public void setCusSeq(String cusSeq) {
		this.cusSeq = cusSeq;
	}

	public String getCusAddrseq() {
		return cusAddrseq;
	}

	public void setCusAddrseq(String cusAddrseq) {
		this.cusAddrseq = cusAddrseq;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getCusHouseno() {
		return cusHouseno;
	}

	public void setCusHouseno(String cusHouseno) {
		this.cusHouseno = cusHouseno;
	}

	public String getCusAddrno() {
		return cusAddrno;
	}

	public void setCusAddrno(String cusAddrno) {
		this.cusAddrno = cusAddrno;
	}

	public String getCusBuildname() {
		return cusBuildname;
	}

	public void setCusBuildname(String cusBuildname) {
		this.cusBuildname = cusBuildname;
	}

	public String getCusFloorno() {
		return cusFloorno;
	}

	public void setCusFloorno(String cusFloorno) {
		this.cusFloorno = cusFloorno;
	}

	public String getCusRoomno() {
		return cusRoomno;
	}

	public void setCusRoomno(String cusRoomno) {
		this.cusRoomno = cusRoomno;
	}

	public String getCusMoono() {
		return cusMoono;
	}

	public void setCusMoono(String cusMoono) {
		this.cusMoono = cusMoono;
	}

	public String getCusVillage() {
		return cusVillage;
	}

	public void setCusVillage(String cusVillage) {
		this.cusVillage = cusVillage;
	}

	public String getCusSoiname() {
		return cusSoiname;
	}

	public void setCusSoiname(String cusSoiname) {
		this.cusSoiname = cusSoiname;
	}

	public String getCusThnname() {
		return cusThnname;
	}

	public void setCusThnname(String cusThnname) {
		this.cusThnname = cusThnname;
	}

	public String getCusTambolcode() {
		return cusTambolcode;
	}

	public void setCusTambolcode(String cusTambolcode) {
		this.cusTambolcode = cusTambolcode;
	}

	public String getCusTambolname() {
		return cusTambolname;
	}

	public void setCusTambolname(String cusTambolname) {
		this.cusTambolname = cusTambolname;
	}

	public String getCusAmphurcode() {
		return cusAmphurcode;
	}

	public void setCusAmphurcode(String cusAmphurcode) {
		this.cusAmphurcode = cusAmphurcode;
	}

	public String getCusAmphurname() {
		return cusAmphurname;
	}

	public void setCusAmphurname(String cusAmphurname) {
		this.cusAmphurname = cusAmphurname;
	}

	public String getCusProvincecode() {
		return cusProvincecode;
	}

	public void setCusProvincecode(String cusProvincecode) {
		this.cusProvincecode = cusProvincecode;
	}

	public String getCusProvincename() {
		return cusProvincename;
	}

	public void setCusProvincename(String cusProvincename) {
		this.cusProvincename = cusProvincename;
	}

	public String getCusZipcode() {
		return cusZipcode;
	}

	public void setCusZipcode(String cusZipcode) {
		this.cusZipcode = cusZipcode;
	}

	public String getCusTelno() {
		return cusTelno;
	}

	public void setCusTelno(String cusTelno) {
		this.cusTelno = cusTelno;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusUrl() {
		return cusUrl;
	}

	public void setCusUrl(String cusUrl) {
		this.cusUrl = cusUrl;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getFacSeq() {
		return facSeq;
	}

	public void setFacSeq(String facSeq) {
		this.facSeq = facSeq;
	}

	public String getFacAddrseq() {
		return facAddrseq;
	}

	public void setFacAddrseq(String facAddrseq) {
		this.facAddrseq = facAddrseq;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getFacHouseno() {
		return facHouseno;
	}

	public void setFacHouseno(String facHouseno) {
		this.facHouseno = facHouseno;
	}

	public String getFacAddrno() {
		return facAddrno;
	}

	public void setFacAddrno(String facAddrno) {
		this.facAddrno = facAddrno;
	}

	public String getFacBuildname() {
		return facBuildname;
	}

	public void setFacBuildname(String facBuildname) {
		this.facBuildname = facBuildname;
	}

	public String getFacFloorno() {
		return facFloorno;
	}

	public void setFacFloorno(String facFloorno) {
		this.facFloorno = facFloorno;
	}

	public String getFacRoomno() {
		return facRoomno;
	}

	public void setFacRoomno(String facRoomno) {
		this.facRoomno = facRoomno;
	}

	public String getFacMoono() {
		return facMoono;
	}

	public void setFacMoono(String facMoono) {
		this.facMoono = facMoono;
	}

	public String getFacVillage() {
		return facVillage;
	}

	public void setFacVillage(String facVillage) {
		this.facVillage = facVillage;
	}

	public String getFacSoiname() {
		return facSoiname;
	}

	public void setFacSoiname(String facSoiname) {
		this.facSoiname = facSoiname;
	}

	public String getFacThnname() {
		return facThnname;
	}

	public void setFacThnname(String facThnname) {
		this.facThnname = facThnname;
	}

	public String getFacTambolcode() {
		return facTambolcode;
	}

	public void setFacTambolcode(String facTambolcode) {
		this.facTambolcode = facTambolcode;
	}

	public String getFacTambolname() {
		return facTambolname;
	}

	public void setFacTambolname(String facTambolname) {
		this.facTambolname = facTambolname;
	}

	public String getFacAmphurcode() {
		return facAmphurcode;
	}

	public void setFacAmphurcode(String facAmphurcode) {
		this.facAmphurcode = facAmphurcode;
	}

	public String getFacAmphurname() {
		return facAmphurname;
	}

	public void setFacAmphurname(String facAmphurname) {
		this.facAmphurname = facAmphurname;
	}

	public String getFacProvincecode() {
		return facProvincecode;
	}

	public void setFacProvincecode(String facProvincecode) {
		this.facProvincecode = facProvincecode;
	}

	public String getFacProvincename() {
		return facProvincename;
	}

	public void setFacProvincename(String facProvincename) {
		this.facProvincename = facProvincename;
	}

	public String getFacZipcode() {
		return facZipcode;
	}

	public void setFacZipcode(String facZipcode) {
		this.facZipcode = facZipcode;
	}

	public String getFacTelno() {
		return facTelno;
	}

	public void setFacTelno(String facTelno) {
		this.facTelno = facTelno;
	}

	public String getFacEmail() {
		return facEmail;
	}

	public void setFacEmail(String facEmail) {
		this.facEmail = facEmail;
	}

	public String getFacUrl() {
		return facUrl;
	}

	public void setFacUrl(String facUrl) {
		this.facUrl = facUrl;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public List<RegDuty> getRegDutyList() {
		return regDutyList;
	}

	public void setRegDutyList(List<RegDuty> regDutyList) {
		this.regDutyList = regDutyList;
	}

}
