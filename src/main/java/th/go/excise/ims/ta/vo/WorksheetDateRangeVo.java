package th.go.excise.ims.ta.vo;

import java.time.LocalDate;
import java.util.List;

public class WorksheetDateRangeVo {

	private String ymStartReg4000;
	private String ymEndReg4000;
	private String ymG1StartInc8000M;
	private String ymG1EndInc8000M;
	private String ymG2StartInc8000M;
	private String ymG2EndInc8000M;
	private List<LocalDate> subLocalDateG1List;
	private List<LocalDate> subLocalDateG2List;

	public String getYmStartReg4000() {
		return ymStartReg4000;
	}

	public void setYmStartReg4000(String ymStartReg4000) {
		this.ymStartReg4000 = ymStartReg4000;
	}

	public String getYmEndReg4000() {
		return ymEndReg4000;
	}

	public void setYmEndReg4000(String ymEndReg4000) {
		this.ymEndReg4000 = ymEndReg4000;
	}

	public String getYmG1StartInc8000M() {
		return ymG1StartInc8000M;
	}

	public void setYmG1StartInc8000M(String ymG1StartInc8000M) {
		this.ymG1StartInc8000M = ymG1StartInc8000M;
	}

	public String getYmG1EndInc8000M() {
		return ymG1EndInc8000M;
	}

	public void setYmG1EndInc8000M(String ymG1EndInc8000M) {
		this.ymG1EndInc8000M = ymG1EndInc8000M;
	}

	public String getYmG2StartInc8000M() {
		return ymG2StartInc8000M;
	}

	public void setYmG2StartInc8000M(String ymG2StartInc8000M) {
		this.ymG2StartInc8000M = ymG2StartInc8000M;
	}

	public String getYmG2EndInc8000M() {
		return ymG2EndInc8000M;
	}

	public void setYmG2EndInc8000M(String ymG2EndInc8000M) {
		this.ymG2EndInc8000M = ymG2EndInc8000M;
	}

	public List<LocalDate> getSubLocalDateG1List() {
		return subLocalDateG1List;
	}

	public void setSubLocalDateG1List(List<LocalDate> subLocalDateG1List) {
		this.subLocalDateG1List = subLocalDateG1List;
	}

	public List<LocalDate> getSubLocalDateG2List() {
		return subLocalDateG2List;
	}

	public void setSubLocalDateG2List(List<LocalDate> subLocalDateG2List) {
		this.subLocalDateG2List = subLocalDateG2List;
	}

}
