package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;

public class Int1302Vo {
	private WsPmPy1H wsPmPy1H;
	private List<WsPmPy1D> wsPmPy1DList;

	public WsPmPy1H getWsPmPy1H() {
		return wsPmPy1H;
	}

	public void setWsPmPy1H(WsPmPy1H wsPmPy1H) {
		this.wsPmPy1H = wsPmPy1H;
	}

	public List<WsPmPy1D> getWsPmPy1DList() {
		return wsPmPy1DList;
	}

	public void setWsPmPy1DList(List<WsPmPy1D> wsPmPy1DList) {
		this.wsPmPy1DList = wsPmPy1DList;
	}

}
