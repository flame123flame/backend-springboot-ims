
package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_ORG_GFMIS")
public class ExciseOrgGfmis extends BaseEntity {

	private static final long serialVersionUID = 8083932537881508545L;
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_ORG_GFMIS_GEN")
//	@SequenceGenerator(name = "EXCISE_ORG_GFMIS_GEN", sequenceName = "EXCISE_ORG_GFMIS_SEQ", allocationSize = 1)
	@Column(name = "GF_EXCISE_CODE")
	private String gfExciseCode;
	@Column(name = "GF_EXCISE_NAME")
	private String gfExciseName;
	@Column(name = "GF_EXCISE_NAME_ABBR")
	private String gfExciseNameAbbr;
	@Column(name = "GF_COST_CENTER")
	private String gfCostCenter;
	@Column(name = "GF_DISBURSE_UNIT")
	private String gfDisburseUnit;
	@Column(name = "GF_OWNER_DEPOSIT")
	private String gfOwnerDeposit;
	@Column(name = "GF_REC_BUDGET")
	private String gfRecBudget;
	@Column(name = "GF_DEPT_CODE")
	private String gfDeptCode;
	@Column(name = "GF_AREA_CODE")
	private String gfAreaCode;

	public String getGfExciseCode() {
		return gfExciseCode;
	}

	public void setGfExciseCode(String gfExciseCode) {
		this.gfExciseCode = gfExciseCode;
	}

	public String getGfExciseName() {
		return gfExciseName;
	}

	public void setGfExciseName(String gfExciseName) {
		this.gfExciseName = gfExciseName;
	}

	public String getGfExciseNameAbbr() {
		return gfExciseNameAbbr;
	}

	public void setGfExciseNameAbbr(String gfExciseNameAbbr) {
		this.gfExciseNameAbbr = gfExciseNameAbbr;
	}

	public String getGfCostCenter() {
		return gfCostCenter;
	}

	public void setGfCostCenter(String gfCostCenter) {
		this.gfCostCenter = gfCostCenter;
	}

	public String getGfDisburseUnit() {
		return gfDisburseUnit;
	}

	public void setGfDisburseUnit(String gfDisburseUnit) {
		this.gfDisburseUnit = gfDisburseUnit;
	}

	public String getGfOwnerDeposit() {
		return gfOwnerDeposit;
	}

	public void setGfOwnerDeposit(String gfOwnerDeposit) {
		this.gfOwnerDeposit = gfOwnerDeposit;
	}

	public String getGfRecBudget() {
		return gfRecBudget;
	}

	public void setGfRecBudget(String gfRecBudget) {
		this.gfRecBudget = gfRecBudget;
	}

	public String getGfDeptCode() {
		return gfDeptCode;
	}

	public void setGfDeptCode(String gfDeptCode) {
		this.gfDeptCode = gfDeptCode;
	}

	public String getGfAreaCode() {
		return gfAreaCode;
	}

	public void setGfAreaCode(String gfAreaCode) {
		this.gfAreaCode = gfAreaCode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
