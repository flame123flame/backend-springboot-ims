package th.go.excise.ims.ws.client.pm.py2.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DivDetail {

	@SerializedName("divSeq")
	@Expose
	private Integer divSeq;

	@SerializedName("divName")
	@Expose
	private String divName;

	@SerializedName("jobDetail")
	@Expose
	private List<JobDetail> jobDetail;

	

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public List<JobDetail> getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(List<JobDetail> jobDetail) {
		this.jobDetail = jobDetail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Integer getDivSeq() {
		return divSeq;
	}

	public void setDivSeq(Integer divSeq) {
		this.divSeq = divSeq;
	}


}
