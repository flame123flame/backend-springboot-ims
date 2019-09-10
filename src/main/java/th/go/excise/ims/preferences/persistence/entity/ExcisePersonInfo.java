
package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_PERSON_INFO")
public class ExcisePersonInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1309396340554914958L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_PERSON_INFO_GEN")
	@SequenceGenerator(name = "EXCISE_PERSON_INFO_GEN", sequenceName = "EXCISE_PERSON_INFO_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "PERSON_LOGIN")
	private String personLogin;
	@Column(name = "PERSON_ID")
	private String personId;
	@Column(name = "PERSON_TYPE")
	private String personType;
	@Column(name = "PERSON_TH_TITLE")
	private String personThTitle;
	@Column(name = "PERSON_TH_NAME")
	private String personThName;
	@Column(name = "PERSON_TH_SURNAME")
	private String personThSurname;
	@Column(name = "PERSON_EN_TITLE")
	private String personEnTitle;
	@Column(name = "PERSON_EN_NAME")
	private String personEnName;
	@Column(name = "PERSON_EN_SURNAME")
	private String personEnSurname;
	@Column(name = "UNDER_OFFCODE")
	private String underOffcode;
	@Column(name = "UNDER_OFFNAME")
	private String underOffname;
	@Column(name = "UNDER_DEPTCODE")
	private String underDeptcode;
	@Column(name = "UNDER_DEPTNAME")
	private String underDeptname;
	@Column(name = "WORK_OFFCODE")
	private String workOffcode;
	@Column(name = "WORK_OFFNAME")
	private String workOffname;
	@Column(name = "WORK_DEPTCODE")
	private String workDeptcode;
	@Column(name = "LINE_POSITION_CODE")
	private String linePositionCode;
	@Column(name = "LINE_POSITION_LEVEL")
	private String linePositionLevel;
	@Column(name = "LINE_POSITION")
	private String linePosition;
	@Column(name = "EXC_POSITION_CODE")
	private String excPositionCode;
	@Column(name = "EXC_POSITION")
	private String excPosition;
	@Column(name = "ACTING_EXCPOSITION_CODE")
	private String actingExcpositionCode;
	@Column(name = "ACTING_EXCPOSITION")
	private String actingExcposition;
	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;
	@Column(name = "DEPT_PHONE_NO")
	private String deptPhoneNo;
	@Column(name = "PERSON_STATUS")
	private String personStatus;
	@Column(name = "COUPLE_PID")
	private String couplePid;
	@Column(name = "FATHER_PID")
	private String fatherPid;
	@Column(name = "MOTHER_PID")
	private String motherPid;
	@Column(name = "PERSON_ADDRNO")
	private String personAddrno;
	@Column(name = "PERSON_MOONO")
	private String personMoono;
	@Column(name = "PERSON_VILLAGENAME")
	private String personVillagename;
	@Column(name = "PERSON_SOINAME")
	private String personSoiname;
	@Column(name = "PERSON_ROADNAME")
	private String personRoadname;
	@Column(name = "PERSON_TABBOL_CODE")
	private String personTabbolCode;
	@Column(name = "PERSON_AMPHUR_CODE")
	private String personAmphurCode;
	@Column(name = "PERSON_PROVINCE_CODE")
	private String personProvinceCode;
	@Column(name = "COUPLE_TH_TITLE")
	private String coupleThTitle;
	@Column(name = "COUPLE_NAME")
	private String coupleName;
	@Column(name = "COUPLE_SURNAME_NAME")
	private String coupleSurnameName;
	@Column(name = "FATHER_TH_TITLE")
	private String fatherThTitle;
	@Column(name = "FATHER_NAME")
	private String fatherName;
	@Column(name = "FATHER_SURNAME_NAME")
	private String fatherSurnameName;
	@Column(name = "MOTHER_TH_TITLE")
	private String motherThTitle;
	@Column(name = "MOTHER_NAME")
	private String motherName;
	@Column(name = "MOTHER_SURNAME_NAME")
	private String motherSurnameName;
	@Column(name = "ZIP_CODE")
	private String zipCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonLogin() {
		return personLogin;
	}

	public void setPersonLogin(String personLogin) {
		this.personLogin = personLogin;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPersonThTitle() {
		return personThTitle;
	}

	public void setPersonThTitle(String personThTitle) {
		this.personThTitle = personThTitle;
	}

	public String getPersonThName() {
		return personThName;
	}

	public void setPersonThName(String personThName) {
		this.personThName = personThName;
	}

	public String getPersonThSurname() {
		return personThSurname;
	}

	public void setPersonThSurname(String personThSurname) {
		this.personThSurname = personThSurname;
	}

	public String getPersonEnTitle() {
		return personEnTitle;
	}

	public void setPersonEnTitle(String personEnTitle) {
		this.personEnTitle = personEnTitle;
	}

	public String getPersonEnName() {
		return personEnName;
	}

	public void setPersonEnName(String personEnName) {
		this.personEnName = personEnName;
	}

	public String getPersonEnSurname() {
		return personEnSurname;
	}

	public void setPersonEnSurname(String personEnSurname) {
		this.personEnSurname = personEnSurname;
	}

	public String getUnderOffcode() {
		return underOffcode;
	}

	public void setUnderOffcode(String underOffcode) {
		this.underOffcode = underOffcode;
	}

	public String getUnderOffname() {
		return underOffname;
	}

	public void setUnderOffname(String underOffname) {
		this.underOffname = underOffname;
	}

	public String getUnderDeptcode() {
		return underDeptcode;
	}

	public void setUnderDeptcode(String underDeptcode) {
		this.underDeptcode = underDeptcode;
	}

	public String getUnderDeptname() {
		return underDeptname;
	}

	public void setUnderDeptname(String underDeptname) {
		this.underDeptname = underDeptname;
	}

	public String getWorkOffcode() {
		return workOffcode;
	}

	public void setWorkOffcode(String workOffcode) {
		this.workOffcode = workOffcode;
	}

	public String getWorkOffname() {
		return workOffname;
	}

	public void setWorkOffname(String workOffname) {
		this.workOffname = workOffname;
	}

	public String getWorkDeptcode() {
		return workDeptcode;
	}

	public void setWorkDeptcode(String workDeptcode) {
		this.workDeptcode = workDeptcode;
	}

	public String getLinePositionCode() {
		return linePositionCode;
	}

	public void setLinePositionCode(String linePositionCode) {
		this.linePositionCode = linePositionCode;
	}

	public String getLinePositionLevel() {
		return linePositionLevel;
	}

	public void setLinePositionLevel(String linePositionLevel) {
		this.linePositionLevel = linePositionLevel;
	}

	public String getLinePosition() {
		return linePosition;
	}

	public void setLinePosition(String linePosition) {
		this.linePosition = linePosition;
	}

	public String getExcPositionCode() {
		return excPositionCode;
	}

	public void setExcPositionCode(String excPositionCode) {
		this.excPositionCode = excPositionCode;
	}

	public String getExcPosition() {
		return excPosition;
	}

	public void setExcPosition(String excPosition) {
		this.excPosition = excPosition;
	}

	public String getActingExcpositionCode() {
		return actingExcpositionCode;
	}

	public void setActingExcpositionCode(String actingExcpositionCode) {
		this.actingExcpositionCode = actingExcpositionCode;
	}

	public String getActingExcposition() {
		return actingExcposition;
	}

	public void setActingExcposition(String actingExcposition) {
		this.actingExcposition = actingExcposition;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDeptPhoneNo() {
		return deptPhoneNo;
	}

	public void setDeptPhoneNo(String deptPhoneNo) {
		this.deptPhoneNo = deptPhoneNo;
	}

	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	public String getCouplePid() {
		return couplePid;
	}

	public void setCouplePid(String couplePid) {
		this.couplePid = couplePid;
	}


	public String getFatherPid() {
		return fatherPid;
	}

	public void setFatherPid(String fatherPid) {
		this.fatherPid = fatherPid;
	}


	public String getMotherPid() {
		return motherPid;
	}

	public void setMotherPid(String motherPid) {
		this.motherPid = motherPid;
	}

	public String getPersonAddrno() {
		return personAddrno;
	}

	public void setPersonAddrno(String personAddrno) {
		this.personAddrno = personAddrno;
	}

	public String getPersonMoono() {
		return personMoono;
	}

	public void setPersonMoono(String personMoono) {
		this.personMoono = personMoono;
	}

	public String getPersonVillagename() {
		return personVillagename;
	}

	public void setPersonVillagename(String personVillagename) {
		this.personVillagename = personVillagename;
	}

	public String getPersonSoiname() {
		return personSoiname;
	}

	public void setPersonSoiname(String personSoiname) {
		this.personSoiname = personSoiname;
	}

	public String getPersonRoadname() {
		return personRoadname;
	}

	public void setPersonRoadname(String personRoadname) {
		this.personRoadname = personRoadname;
	}

	public String getPersonTabbolCode() {
		return personTabbolCode;
	}

	public void setPersonTabbolCode(String personTabbolCode) {
		this.personTabbolCode = personTabbolCode;
	}

	public String getPersonAmphurCode() {
		return personAmphurCode;
	}

	public void setPersonAmphurCode(String personAmphurCode) {
		this.personAmphurCode = personAmphurCode;
	}

	public String getPersonProvinceCode() {
		return personProvinceCode;
	}

	public void setPersonProvinceCode(String personProvinceCode) {
		this.personProvinceCode = personProvinceCode;
	}

	public String getCoupleThTitle() {
		return coupleThTitle;
	}

	public void setCoupleThTitle(String coupleThTitle) {
		this.coupleThTitle = coupleThTitle;
	}

	public String getCoupleName() {
		return coupleName;
	}

	public void setCoupleName(String coupleName) {
		this.coupleName = coupleName;
	}

	public String getCoupleSurnameName() {
		return coupleSurnameName;
	}

	public void setCoupleSurnameName(String coupleSurnameName) {
		this.coupleSurnameName = coupleSurnameName;
	}

	public String getFatherThTitle() {
		return fatherThTitle;
	}

	public void setFatherThTitle(String fatherThTitle) {
		this.fatherThTitle = fatherThTitle;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherSurnameName() {
		return fatherSurnameName;
	}

	public void setFatherSurnameName(String fatherSurnameName) {
		this.fatherSurnameName = fatherSurnameName;
	}

	public String getMotherThTitle() {
		return motherThTitle;
	}

	public void setMotherThTitle(String motherThTitle) {
		this.motherThTitle = motherThTitle;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherSurnameName() {
		return motherSurnameName;
	}

	public void setMotherSurnameName(String motherSurnameName) {
		this.motherSurnameName = motherSurnameName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
