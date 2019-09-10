package th.go.excise.ims.ia.vo;

import org.springframework.web.multipart.MultipartFile;

public class Int15UploadVo {
	
	private MultipartFile file;
	private String typeData;
	private String monthly;
	private String disburseMoney;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getTypeData() {
		return typeData;
	}
	public void setTypeData(String typeData) {
		this.typeData = typeData;
	}
	public String getMonthly() {
		return monthly;
	}
	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}
	public String getDisburseMoney() {
		return disburseMoney;
	}
	public void setDisburseMoney(String disburseMoney) {
		this.disburseMoney = disburseMoney;
	}
}
