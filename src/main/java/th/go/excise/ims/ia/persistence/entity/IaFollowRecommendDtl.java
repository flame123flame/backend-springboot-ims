
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "IA_FOLLOW_RECOMMEND_DTL")
public class IaFollowRecommendDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4208367712698917091L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_FOLLOW_RECOMMEND_DTL_GEN")
	@SequenceGenerator(name = "IA_FOLLOW_RECOMMEND_DTL_GEN", sequenceName = "IA_FOLLOW_RECOMMEND_DTL_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_FOLLOW_RECOMMEND_HDR")
	private BigDecimal idFollowRecommendHdr;
	@Column(name = "FOLLOW_NOTIFY_BOOK_NUMBER")
	private String followNotifyBookNumber;
	@Column(name = "FOLLOW_NOTIFY_DATE")
	private Date followNotifyDate;
	@Column(name = "DAEDLINES_START")
	private Date daedlinesStart;
	@Column(name = "DAEDLINES_END")
	private Date daedlinesEnd;
	@Column(name = "RESULT_NOTIFY_BOOK_NUMBER")
	private String resultNotifyBookNumber;
	@Column(name = "RESULT_NOTIFY_DATE")
	private Date resultNotifyDate;
	@Column(name = "FOLLOW_REPORT_BOOK_NUMBER")
	private String followReportBookNumber;
	@Column(name = "FOLLOW_REPORT_DATE")
	private Date followReportDate;
	@Column(name = "TIME_NOTIFY")
	private BigDecimal timeNotify;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdFollowRecommendHdr() {
		return idFollowRecommendHdr;
	}

	public void setIdFollowRecommendHdr(BigDecimal idFollowRecommendHdr) {
		this.idFollowRecommendHdr = idFollowRecommendHdr;
	}

	public String getFollowNotifyBookNumber() {
		return followNotifyBookNumber;
	}

	public void setFollowNotifyBookNumber(String followNotifyBookNumber) {
		this.followNotifyBookNumber = followNotifyBookNumber;
	}

	public Date getFollowNotifyDate() {
		return followNotifyDate;
	}

	public void setFollowNotifyDate(Date followNotifyDate) {
		this.followNotifyDate = followNotifyDate;
	}

	public Date getDaedlinesStart() {
		return daedlinesStart;
	}

	public void setDaedlinesStart(Date daedlinesStart) {
		this.daedlinesStart = daedlinesStart;
	}

	public Date getDaedlinesEnd() {
		return daedlinesEnd;
	}

	public void setDaedlinesEnd(Date daedlinesEnd) {
		this.daedlinesEnd = daedlinesEnd;
	}

	public String getResultNotifyBookNumber() {
		return resultNotifyBookNumber;
	}

	public void setResultNotifyBookNumber(String resultNotifyBookNumber) {
		this.resultNotifyBookNumber = resultNotifyBookNumber;
	}

	public Date getResultNotifyDate() {
		return resultNotifyDate;
	}

	public void setResultNotifyDate(Date resultNotifyDate) {
		this.resultNotifyDate = resultNotifyDate;
	}

	public String getFollowReportBookNumber() {
		return followReportBookNumber;
	}

	public void setFollowReportBookNumber(String followReportBookNumber) {
		this.followReportBookNumber = followReportBookNumber;
	}

	public Date getFollowReportDate() {
		return followReportDate;
	}

	public void setFollowReportDate(Date followReportDate) {
		this.followReportDate = followReportDate;
	}

	public BigDecimal getTimeNotify() {
		return timeNotify;
	}

	public void setTimeNotify(BigDecimal timeNotify) {
		this.timeNotify = timeNotify;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
