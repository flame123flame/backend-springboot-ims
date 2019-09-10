
package th.go.excise.ims.ia.persistence.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_GFUPLOAD_H")
public class IaGfuploadH extends BaseEntity {

	private static final long serialVersionUID = -1275066896923669008L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_GFUPLOAD_H_GEN")
	@SequenceGenerator(name = "IA_GFUPLOAD_H_GEN", sequenceName = "IA_GFUPLOAD_H_SEQ", allocationSize = 1)
	@Column(name = "GFUPLOAD_H_ID")
	private Long gfuploadHId;
	@Column(name = "UPLOAD_TYPE")
	private String uploadType;
	@Column(name = "DEPT_DISB")
	private String deptDisb;
	@Column(name = "PERIOD_MONTH")
	private String periodMonth;
	@Column(name = "PERIOD_YEAR")
	private String periodYear;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "FILE_NAME")
	private String fileName;

	public Long getGfuploadHId() {
		return gfuploadHId;
	}

	public void setGfuploadHId(Long gfuploadHId) {
		this.gfuploadHId = gfuploadHId;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

	public String getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(String periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
