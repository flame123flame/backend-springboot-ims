
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_MEDICAL_WELFARE")
public class IaMedicalWelfare
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_MEDICAL_WELFARE_GEN")
    @SequenceGenerator(name = "IA_MEDICAL_WELFARE_GEN", sequenceName = "IA_MEDICAL_WELFARE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "BIRTHDATE")
    private Date birthdate;
    @Column(name = "SIBLINGS_ORDER")
    private BigDecimal siblingsOrder;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "AFFILIATION")
    private String affiliation;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DISEASE")
    private String disease;
    @Column(name = "HOSPITAL_NAME")
    private String hospitalName;
    @Column(name = "HOSPITAL_OWNER")
    private String hospitalOwner;
    @Column(name = "TREATED_DATE_FROM")
    private Date treatedDateFrom;
    @Column(name = "TREATED_DATE_TO")
    private Date treatedDateTo;
    @Column(name = "TOTAL_MONEY")
    private BigDecimal totalMoney;
    @Column(name = "RECEIPT_QT")
    private BigDecimal receiptQt;
    @Column(name = "CLAIM_STATUS")
    private String claimStatus;
    @Column(name = "CLAIM_MONEY")
    private BigDecimal claimMoney;
    @Column(name = "MATE_NAME")
    private String mateName;
    @Column(name = "MATE_CITIZEN_ID")
    private String mateCitizenId;
    @Column(name = "FATHER_NAME")
    private String fatherName;
    @Column(name = "FATHER_CITIZEN_ID")
    private String fatherCitizenId;
    @Column(name = "MOTHER_NAME")
    private String motherName;
    @Column(name = "MOTHER_CITIZEN_ID")
    private String motherCitizenId;
    @Column(name = "CHILD_NAME")
    private String childName;
    @Column(name = "CHILD_CITIZEN_ID")
    private String childCitizenId;
    @Column(name = "FILE_ID")
    private BigDecimal fileId;
    @Column(name = "STATUS_CHECK")
    private String statusCheck;
    @Column(name = "IA_DIS_REQ_ID")
    private BigDecimal iaDisReqId;
    @Column(name = "BIRTHDATE_2")
    private Date birthdate2;
    @Column(name = "BIRTHDATE_3")
    private Date birthdate3;
    @Column(name = "CHILD_NAME_2")
    private String childName2;
    @Column(name = "CHILD_NAME_3")
    private String childName3;
    @Column(name = "CHILD_CITIZEN_ID_2")
    private String childCitizenId2;
    @Column(name = "CHILD_CITIZEN_ID_3")
    private String childCitizenId3;
    @Column(name = "STATUS_2")
    private String status2;
    @Column(name = "STATUS_3")
    private String status3;
    @Column(name = "SIBLINGS_ORDER_2")
    private BigDecimal siblingsOrder2;
    @Column(name = "SIBLINGS_ORDER_3")
    private BigDecimal siblingsOrder3;
    @Column(name = "OTHER_CLAIM1")
    private String otherClaim1;
    @Column(name = "OTHER_CLAIM2")
    private String otherClaim2;
    @Column(name = "OTHER_CLAIM3")
    private String otherClaim3;
    @Column(name = "OTHER_CLAIM4")
    private String otherClaim4;
    @Column(name = "OWNER_CLAIM1")
    private String ownerClaim1;
    @Column(name = "OWNER_CLAIM2")
    private String ownerClaim2;
    @Column(name = "OWNER_CLAIM3")
    private String ownerClaim3;
    @Column(name = "OWNER_CLAIM4")
    private String ownerClaim4;
    @Column(name = "SELF_CHECK")
    private String selfCheck;
    @Column(name = "COUPLE_CHECK")
    private String coupleCheck;
    @Column(name = "FATHER_CHECK")
    private String fatherCheck;
    @Column(name = "MOTHER_CHECK")
    private String motherCheck;
    @Column(name = "CHILD_CHECK")
    private String childCheck;
    @Column(name = "CHILD2_CHECK")
    private String child2Check;
    @Column(name = "CHILD3_CHECK")
    private String child3Check;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public BigDecimal getSiblingsOrder() {
        return siblingsOrder;
    }

    public void setSiblingsOrder(BigDecimal siblingsOrder) {
        this.siblingsOrder = siblingsOrder;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalOwner() {
        return hospitalOwner;
    }

    public void setHospitalOwner(String hospitalOwner) {
        this.hospitalOwner = hospitalOwner;
    }

    public Date getTreatedDateFrom() {
        return treatedDateFrom;
    }

    public void setTreatedDateFrom(Date treatedDateFrom) {
        this.treatedDateFrom = treatedDateFrom;
    }

    public Date getTreatedDateTo() {
        return treatedDateTo;
    }

    public void setTreatedDateTo(Date treatedDateTo) {
        this.treatedDateTo = treatedDateTo;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getReceiptQt() {
        return receiptQt;
    }

    public void setReceiptQt(BigDecimal receiptQt) {
        this.receiptQt = receiptQt;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public BigDecimal getClaimMoney() {
        return claimMoney;
    }

    public void setClaimMoney(BigDecimal claimMoney) {
        this.claimMoney = claimMoney;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getMateCitizenId() {
        return mateCitizenId;
    }

    public void setMateCitizenId(String mateCitizenId) {
        this.mateCitizenId = mateCitizenId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherCitizenId() {
        return fatherCitizenId;
    }

    public void setFatherCitizenId(String fatherCitizenId) {
        this.fatherCitizenId = fatherCitizenId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherCitizenId() {
        return motherCitizenId;
    }

    public void setMotherCitizenId(String motherCitizenId) {
        this.motherCitizenId = motherCitizenId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildCitizenId() {
        return childCitizenId;
    }

    public void setChildCitizenId(String childCitizenId) {
        this.childCitizenId = childCitizenId;
    }

    public BigDecimal getFileId() {
        return fileId;
    }

    public void setFileId(BigDecimal fileId) {
        this.fileId = fileId;
    }

    public String getStatusCheck() {
        return statusCheck;
    }

    public void setStatusCheck(String statusCheck) {
        this.statusCheck = statusCheck;
    }

    public BigDecimal getIaDisReqId() {
        return iaDisReqId;
    }

    public void setIaDisReqId(BigDecimal iaDisReqId) {
        this.iaDisReqId = iaDisReqId;
    }

    public Date getBirthdate2() {
        return birthdate2;
    }

    public void setBirthdate2(Date birthdate2) {
        this.birthdate2 = birthdate2;
    }

    public Date getBirthdate3() {
        return birthdate3;
    }

    public void setBirthdate3(Date birthdate3) {
        this.birthdate3 = birthdate3;
    }

    public String getChildName2() {
        return childName2;
    }

    public void setChildName2(String childName2) {
        this.childName2 = childName2;
    }

    public String getChildName3() {
        return childName3;
    }

    public void setChildName3(String childName3) {
        this.childName3 = childName3;
    }

    public String getChildCitizenId2() {
        return childCitizenId2;
    }

    public void setChildCitizenId2(String childCitizenId2) {
        this.childCitizenId2 = childCitizenId2;
    }

    public String getChildCitizenId3() {
        return childCitizenId3;
    }

    public void setChildCitizenId3(String childCitizenId3) {
        this.childCitizenId3 = childCitizenId3;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getStatus3() {
        return status3;
    }

    public void setStatus3(String status3) {
        this.status3 = status3;
    }

    public BigDecimal getSiblingsOrder2() {
        return siblingsOrder2;
    }

    public void setSiblingsOrder2(BigDecimal siblingsOrder2) {
        this.siblingsOrder2 = siblingsOrder2;
    }

    public BigDecimal getSiblingsOrder3() {
        return siblingsOrder3;
    }

    public void setSiblingsOrder3(BigDecimal siblingsOrder3) {
        this.siblingsOrder3 = siblingsOrder3;
    }

    public String getOtherClaim1() {
        return otherClaim1;
    }

    public void setOtherClaim1(String otherClaim1) {
        this.otherClaim1 = otherClaim1;
    }

    public String getOtherClaim2() {
        return otherClaim2;
    }

    public void setOtherClaim2(String otherClaim2) {
        this.otherClaim2 = otherClaim2;
    }

    public String getOtherClaim3() {
        return otherClaim3;
    }

    public void setOtherClaim3(String otherClaim3) {
        this.otherClaim3 = otherClaim3;
    }

    public String getOtherClaim4() {
        return otherClaim4;
    }

    public void setOtherClaim4(String otherClaim4) {
        this.otherClaim4 = otherClaim4;
    }

    public String getOwnerClaim1() {
        return ownerClaim1;
    }

    public void setOwnerClaim1(String ownerClaim1) {
        this.ownerClaim1 = ownerClaim1;
    }

    public String getOwnerClaim2() {
        return ownerClaim2;
    }

    public void setOwnerClaim2(String ownerClaim2) {
        this.ownerClaim2 = ownerClaim2;
    }

    public String getOwnerClaim3() {
        return ownerClaim3;
    }

    public void setOwnerClaim3(String ownerClaim3) {
        this.ownerClaim3 = ownerClaim3;
    }

    public String getOwnerClaim4() {
        return ownerClaim4;
    }

    public void setOwnerClaim4(String ownerClaim4) {
        this.ownerClaim4 = ownerClaim4;
    }

    public String getSelfCheck() {
        return selfCheck;
    }

    public void setSelfCheck(String selfCheck) {
        this.selfCheck = selfCheck;
    }

    public String getCoupleCheck() {
        return coupleCheck;
    }

    public void setCoupleCheck(String coupleCheck) {
        this.coupleCheck = coupleCheck;
    }

    public String getFatherCheck() {
        return fatherCheck;
    }

    public void setFatherCheck(String fatherCheck) {
        this.fatherCheck = fatherCheck;
    }

    public String getMotherCheck() {
        return motherCheck;
    }

    public void setMotherCheck(String motherCheck) {
        this.motherCheck = motherCheck;
    }

    public String getChildCheck() {
        return childCheck;
    }

    public void setChildCheck(String childCheck) {
        this.childCheck = childCheck;
    }

    public String getChild2Check() {
        return child2Check;
    }

    public void setChild2Check(String child2Check) {
        this.child2Check = child2Check;
    }

    public String getChild3Check() {
        return child3Check;
    }

    public void setChild3Check(String child3Check) {
        this.child3Check = child3Check;
    }

}
