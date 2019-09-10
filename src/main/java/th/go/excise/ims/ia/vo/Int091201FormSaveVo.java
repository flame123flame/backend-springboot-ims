package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;

public class Int091201FormSaveVo extends IaAuditWorkingH {
	private String branch;
	private String sector;
	private String area;
	private List<IaAuditWorkingD1> iaAuditWorkingD1List;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<IaAuditWorkingD1> getIaAuditWorkingD1List() {
		return iaAuditWorkingD1List;
	}

	public void setIaAuditWorkingD1List(List<IaAuditWorkingD1> iaAuditWorkingD1List) {
		this.iaAuditWorkingD1List = iaAuditWorkingD1List;
	}

}
