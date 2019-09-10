package th.go.excise.ims.ta.vo;

public class ServicePaperMemberVo {

	private String memberCode; // รหัสสมาชิก
	private String memberFullName; // ชื่อ-นามสกุล
	private String memberStartDate; // วันที่เริ่มต้น
	private String memberEndDate; // วันหมดอายุ
	private String memberCoupon; // คูปอง
	private String memberUsedDate; // วันที่ใช้บริการ
	private String memberStatus; // สถานะการเป็นสมาชิก

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberFullName() {
		return memberFullName;
	}

	public void setMemberFullName(String memberFullName) {
		this.memberFullName = memberFullName;
	}

	public String getMemberStartDate() {
		return memberStartDate;
	}

	public void setMemberStartDate(String memberStartDate) {
		this.memberStartDate = memberStartDate;
	}

	public String getMemberEndDate() {
		return memberEndDate;
	}

	public void setMemberEndDate(String memberEndDate) {
		this.memberEndDate = memberEndDate;
	}

	public String getMemberCoupon() {
		return memberCoupon;
	}

	public void setMemberCoupon(String memberCoupon) {
		this.memberCoupon = memberCoupon;
	}

	public String getMemberUsedDate() {
		return memberUsedDate;
	}

	public void setMemberUsedDate(String memberUsedDate) {
		this.memberUsedDate = memberUsedDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

}
