package th.go.excise.ims.ia.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "IA_WS_LIC6010")
public class IaWsLic6010 implements Serializable {
	
	private static final long serialVersionUID = 4068483685132688744L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_WS_LIC6010_GEN")
	@SequenceGenerator(name = "IA_WS_LIC6010_GEN", sequenceName = "IA_WS_LIC6010_SEQ", allocationSize = 1)
	@Column(name = "IA_WS_LIC6010_ID")
	private Long iaWsLic6010Id;
	@Column(name = "CURRENT_LIC_ID")
	private Long currentLicId;
	@Column(name = "NEW_LIC_NO")
	private String newLicNo;
	@Column(name = "NEW_LIC_DATE")
	private LocalDate newLicDate;
	@Column(name = "NEW_LIC_ID")
	private Long newLicId;

	public Long getIaWsLic6010Id() {
		return iaWsLic6010Id;
	}

	public void setIaWsLic6010Id(Long iaWsLic6010Id) {
		this.iaWsLic6010Id = iaWsLic6010Id;
	}

	public Long getCurrentLicId() {
		return currentLicId;
	}

	public void setCurrentLicId(Long currentLicId) {
		this.currentLicId = currentLicId;
	}

	public String getNewLicNo() {
		return newLicNo;
	}

	public void setNewLicNo(String newLicNo) {
		this.newLicNo = newLicNo;
	}

	public LocalDate getNewLicDate() {
		return newLicDate;
	}

	public void setNewLicDate(LocalDate newLicDate) {
		this.newLicDate = newLicDate;
	}

	public Long getNewLicId() {
		return newLicId;
	}

	public void setNewLicId(Long newLicId) {
		this.newLicId = newLicId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
