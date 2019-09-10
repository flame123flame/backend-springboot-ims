package th.go.excise.ims.ws.client.pm.py2.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Py2Detail {

	@SerializedName("py2TopicSeq")
	@Expose
	private Integer py2TopicSeq;

	@SerializedName("py2TopicName")
	@Expose
	private String py2TopicName;

	@SerializedName("py2Topic1Main")
	@Expose
	private String py2Topic1Main;

	@SerializedName("py2Topic2Ctl")
	@Expose
	private String py2Topic2Ctl;

	@SerializedName("py2Topic3Assess")
	@Expose
	private String py2Topic3Assess;

	@SerializedName("py2Topic4Risk")
	@Expose
	private String py2Topic4Risk;

	@SerializedName("py2Topic5Improve")
	@Expose
	private String py2Topic5Improve;

	@SerializedName("py2Topic6Owner")
	@Expose
	private String py2Topic6Owner;

	@SerializedName("py2Topic7Remark")
	@Expose
	private String py2Topic7Remark;

	public Integer getPy2TopicSeq() {
		return py2TopicSeq;
	}

	public void setPy2TopicSeq(Integer py2TopicSeq) {
		this.py2TopicSeq = py2TopicSeq;
	}

	public String getPy2TopicName() {
		return py2TopicName;
	}

	public void setPy2TopicName(String py2TopicName) {
		this.py2TopicName = py2TopicName;
	}

	public String getPy2Topic1Main() {
		return py2Topic1Main;
	}

	public void setPy2Topic1Main(String py2Topic1Main) {
		this.py2Topic1Main = py2Topic1Main;
	}

	public String getPy2Topic2Ctl() {
		return py2Topic2Ctl;
	}

	public void setPy2Topic2Ctl(String py2Topic2Ctl) {
		this.py2Topic2Ctl = py2Topic2Ctl;
	}

	public String getPy2Topic3Assess() {
		return py2Topic3Assess;
	}

	public void setPy2Topic3Assess(String py2Topic3Assess) {
		this.py2Topic3Assess = py2Topic3Assess;
	}

	public String getPy2Topic4Risk() {
		return py2Topic4Risk;
	}

	public void setPy2Topic4Risk(String py2Topic4Risk) {
		this.py2Topic4Risk = py2Topic4Risk;
	}

	public String getPy2Topic5Improve() {
		return py2Topic5Improve;
	}

	public void setPy2Topic5Improve(String py2Topic5Improve) {
		this.py2Topic5Improve = py2Topic5Improve;
	}

	public String getPy2Topic6Owner() {
		return py2Topic6Owner;
	}

	public void setPy2Topic6Owner(String py2Topic6Owner) {
		this.py2Topic6Owner = py2Topic6Owner;
	}

	public String getPy2Topic7Remark() {
		return py2Topic7Remark;
	}

	public void setPy2Topic7Remark(String py2Topic7Remark) {
		this.py2Topic7Remark = py2Topic7Remark;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
