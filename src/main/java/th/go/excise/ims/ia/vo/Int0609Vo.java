package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendH;

public class Int0609Vo {
	private List<Int0609TableVo> table;
	private IaAuditIncSendH header;
	private Int0609TableVo footer;
	private ExciseDepartmentVo exciseDepartmentVo;

	public List<Int0609TableVo> getTable() {
		return table;
	}

	public void setTable(List<Int0609TableVo> table) {
		this.table = table;
	}

	public Int0609TableVo getFooter() {
		return footer;
	}

	public void setFooter(Int0609TableVo footer) {
		this.footer = footer;
	}

	public IaAuditIncSendH getHeader() {
		return header;
	}

	public void setHeader(IaAuditIncSendH header) {
		this.header = header;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}