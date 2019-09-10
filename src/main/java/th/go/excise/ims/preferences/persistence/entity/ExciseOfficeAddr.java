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
@Table(name = "EXCISE_OFFICE_ADDR")
public class ExciseOfficeAddr extends BaseEntity {

	private static final long serialVersionUID = -3727688362002946799L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_OFFICE_ADDR_GEN")
	@SequenceGenerator(name = "EXCISE_OFFICE_ADDR_GEN", sequenceName = "EXCISE_OFFICE_ADDR_SEQ", allocationSize = 1)
	@Column(name = "OFFICE_ADDR_ID")
	private Long officeAddrId;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "FAX")
	private String fax;

	public Long getOfficeAddrId() {
		return officeAddrId;
	}

	public void setOfficeAddrId(Long officeAddrId) {
		this.officeAddrId = officeAddrId;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
