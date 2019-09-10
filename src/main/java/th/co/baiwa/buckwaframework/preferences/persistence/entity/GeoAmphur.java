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
@Table(name = "SYS_GEO_AMPHUR")
public class GeoAmphur extends BaseEntity {

	private static final long serialVersionUID = -4773394569710721849L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_GEO_AMPHUR_GEN")
	@SequenceGenerator(name = "SYS_GEO_AMPHUR_GEN", sequenceName = "SYS_GEO_AMPHUR_SEQ", allocationSize = 1)
	@Column(name = "AMPHUR_ID")
	private Long amphurId;
	@Column(name = "AMPHUR_CODE")
	private String amphurCode;
	@Column(name = "AMPHUR_NAME")
	private String amphurName;

	public Long getAmphurId() {
		return amphurId;
	}

	public void setAmphurId(Long amphurId) {
		this.amphurId = amphurId;
	}

	public String getAmphurCode() {
		return amphurCode;
	}

	public void setAmphurCode(String amphurCode) {
		this.amphurCode = amphurCode;
	}

	public String getAmphurName() {
		return amphurName;
	}

	public void setAmphurName(String amphurName) {
		this.amphurName = amphurName;
	}

}
