
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_RISK_FACTORS_MASTER_2")
public class IaRiskFactorsMaster2 extends BaseEntity {
	private static final long serialVersionUID = 8653535067328094607L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_FACTORS_MASTER_2_GEN")
	@SequenceGenerator(name = "IA_RISK_FACTORS_MASTER_2_GEN", sequenceName = "IA_RISK_FACTORS_MASTER_2_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "RISK_FACTORS_MASTER")
	private String riskFactorsMaster;
	@Column(name = "INSPECTION_WORK")
	private BigDecimal inspectionWork;
	@Column(name = "NOT_DELETE")
	private String notDelete;
	@Column(name = "DATA_EVALUATE")
	private String dataEvaluate;
	@Column(name = "SIDE")
	private String side;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getRiskFactorsMaster() {
		return riskFactorsMaster;
	}

	public void setRiskFactorsMaster(String riskFactorsMaster) {
		this.riskFactorsMaster = riskFactorsMaster;
	}

	public BigDecimal getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(BigDecimal inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getNotDelete() {
		return notDelete;
	}

	public void setNotDelete(String notDelete) {
		this.notDelete = notDelete;
	}

	public String getDataEvaluate() {
		return dataEvaluate;
	}

	public void setDataEvaluate(String dataEvaluate) {
		this.dataEvaluate = dataEvaluate;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

}
