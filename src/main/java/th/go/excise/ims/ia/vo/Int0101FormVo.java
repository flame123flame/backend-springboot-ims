package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int0101FormVo {
	private BigDecimal planDtlId;
	private String responsiblePerson;
	private String position;
	private List<Int0101PlanDayVo> planVo;
	private Boolean flagUpdate;

	public BigDecimal getPlanDtlId() {
		return planDtlId;
	}

	public void setPlanDtlId(BigDecimal planDtlId) {
		this.planDtlId = planDtlId;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Int0101PlanDayVo> getPlanVo() {
		return planVo;
	}

	public void setPlanVo(List<Int0101PlanDayVo> planVo) {
		this.planVo = planVo;
	}

	public Boolean getFlagUpdate() {
		return flagUpdate;
	}

	public void setFlagUpdate(Boolean flagUpdate) {
		this.flagUpdate = flagUpdate;
	}
	

}