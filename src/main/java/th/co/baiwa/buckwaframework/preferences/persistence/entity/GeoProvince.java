package th.co.baiwa.buckwaframework.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "SYS_GEO_PROVINCE")
public class GeoProvince extends BaseEntity {

	private static final long serialVersionUID = 6121843062027958623L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_GEO_PROVINCE_GEN")
	@SequenceGenerator(name = "SYS_GEO_PROVINCE_GEN", sequenceName = "SYS_GEO_PROVINCE_SEQ", allocationSize = 1)
	@Column(name = "PROVINCE_ID")
	private Long provinceId;
	@Column(name = "SECTOR_CODE")
	private String sectorCode;
	@Column(name = "PROVINCE_CODE")
	private String provinceCode;
	@Column(name = "PROVINCE_NAME")
	private String provinceName;

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
