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
@Table(name = "SYS_GEO_SECTOR")
public class GeoSector extends BaseEntity {

	private static final long serialVersionUID = 2428382483275212135L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_GEO_SECTOR_GEN")
	@SequenceGenerator(name = "SYS_GEO_SECTOR_GEN", sequenceName = "SYS_GEO_SECTOR_SEQ", allocationSize = 1)
	@Column(name = "SECTOR_ID")
	private Long sectorId;
	@Column(name = "SECTOR_CODE")
	private String sectorCode;
	@Column(name = "SECTOR_NAME")
	private String sectorName;

	public Long getSectorId() {
		return sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

}
