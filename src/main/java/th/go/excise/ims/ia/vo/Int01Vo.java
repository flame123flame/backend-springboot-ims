package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaPlanHdr;

public class Int01Vo {

	private Int01HdrVo header = null;
	private List<Int01TableVo> tableVo = null;
	
	public Int01HdrVo getHeader() {
		return header;
	}
	public void setHeader(Int01HdrVo header) {
		this.header = header;
	}
	public List<Int01TableVo> getTableVo() {
		return tableVo;
	}
	public void setTableVo(List<Int01TableVo> tableVo) {
		this.tableVo = tableVo;
	}

}
