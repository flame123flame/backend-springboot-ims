package th.go.excise.ims.preferences.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_HOSPITAL")
public class ExciseHospital extends BaseEntity {

	private static final long serialVersionUID = 2201967473428709801L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_HOSPITAL_GEN")
	@SequenceGenerator(name = "EXCISE_HOSPITAL_GEN", sequenceName = "EXCISE_HOSPITAL_SEQ", allocationSize = 1)
	@Column(name = "HOSP_ID")
	private Long hospId;
	@Column(name = "HOSP_CODE")
	private String hospCode;
	@Column(name = "HOSP_TYPE")
	private String hospType;
	@Column(name = "HOSP_CATE")
	private String hospCate;
	@Column(name = "HOSP_NAME")
	private String hospName;
	@Column(name = "ADDRNO")
	private String addrno;
	@Column(name = "THNNAME")
	private String thnname;
	@Column(name = "TAMBOL_CODE")
	private String tambolCode;
	@Column(name = "ZIPCODE")
	private String zipcode;
	@Column(name = "BEGIN_DATE")
	private LocalDate beginDate;

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public String getHospType() {
		return hospType;
	}

	public void setHospType(String hospType) {
		this.hospType = hospType;
	}

	public String getHospCate() {
		return hospCate;
	}

	public void setHospCate(String hospCate) {
		this.hospCate = hospCate;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public String getAddrno() {
		return addrno;
	}

	public void setAddrno(String addrno) {
		this.addrno = addrno;
	}

	public String getThnname() {
		return thnname;
	}

	public void setThnname(String thnname) {
		this.thnname = thnname;
	}

	public String getTambolCode() {
		return tambolCode;
	}

	public void setTambolCode(String tambolCode) {
		this.tambolCode = tambolCode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

}
