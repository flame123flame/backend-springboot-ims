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
@Table(name = "SYS_GEO_DISTRICT")
public class GeoDistrict extends BaseEntity {

	private static final long serialVersionUID = -1885867103349188982L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_GEO_DISTRICT_GEN")
	@SequenceGenerator(name = "SYS_GEO_DISTRICT_GEN", sequenceName = "SYS_GEO_DISTRICT_SEQ", allocationSize = 1)
	@Column(name = "DISTRICT_ID")
	private Long districtId;
	@Column(name = "DISTRICT_CODE")
	private String districtCode;
	@Column(name = "DISTRICT_NAME")
	private String districtName;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}
