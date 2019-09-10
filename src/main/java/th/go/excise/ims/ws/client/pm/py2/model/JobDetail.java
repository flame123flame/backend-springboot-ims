package th.go.excise.ims.ws.client.pm.py2.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobDetail {

	@SerializedName("jobName")
	@Expose
	private String jobName;

	@SerializedName("processBy")
	@Expose
	private String processBy;

	@SerializedName("processPosition")
	@Expose
	private String processPosition;

	@SerializedName("processDate")
	@Expose
	private String processDate;

	@SerializedName("py2Detail")
	@Expose
	private List<Py2Detail> py2Detail;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getProcessBy() {
		return processBy;
	}

	public void setProcessBy(String processBy) {
		this.processBy = processBy;
	}

	public String getProcessPosition() {
		return processPosition;
	}

	public void setProcessPosition(String processPosition) {
		this.processPosition = processPosition;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public List<Py2Detail> getPy2Detail() {
		return py2Detail;
	}

	public void setPy2Detail(List<Py2Detail> py2Detail) {
		this.py2Detail = py2Detail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
