package th.go.excise.ims.bi.persistence.entity;

public class TempCustomer {

	private String cusId;
	private String customerName;
	private String tin;
	private String pinnitId;

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getPinnitId() {
		return pinnitId;
	}

	public void setPinnitId(String pinnitId) {
		this.pinnitId = pinnitId;
	}

}
