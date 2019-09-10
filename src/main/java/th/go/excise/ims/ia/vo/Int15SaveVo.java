package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;

public class Int15SaveVo {
	private String period;
	private String year;
	private String startDate;
	private String endDate;
	private String typeData;
	private String disburseMoney;
	private String fileName;
	private List<IaGfdrawAccountExcelVo> formData1;
	private List<IaGftrialBalance> formData2;
	private List<IaGfledgerAccountVo> formData3;
	private List<IaGfmovementAccountVo> formData4;
	
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTypeData() {
		return typeData;
	}
	public void setTypeData(String typeData) {
		this.typeData = typeData;
	}
	public String getDisburseMoney() {
		return disburseMoney;
	}
	public void setDisburseMoney(String disburseMoney) {
		this.disburseMoney = disburseMoney;
	}
	public List<IaGfdrawAccountExcelVo> getFormData1() {
		return formData1;
	}
	public void setFormData1(List<IaGfdrawAccountExcelVo> formData1) {
		this.formData1 = formData1;
	}
	public List<IaGftrialBalance> getFormData2() {
		return formData2;
	}
	public void setFormData2(List<IaGftrialBalance> formData2) {
		this.formData2 = formData2;
	}
	public List<IaGfledgerAccountVo> getFormData3() {
		return formData3;
	}
	public void setFormData3(List<IaGfledgerAccountVo> formData3) {
		this.formData3 = formData3;
	}
	public List<IaGfmovementAccountVo> getFormData4() {
		return formData4;
	}
	public void setFormData4(List<IaGfmovementAccountVo> formData4) {
		this.formData4 = formData4;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
