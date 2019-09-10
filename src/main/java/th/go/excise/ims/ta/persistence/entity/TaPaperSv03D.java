package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PAPER_SV03_D")
public class TaPaperSv03D extends BaseEntity {

	private static final long serialVersionUID = 4136912028844966769L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV03_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_SV03_D_GEN", sequenceName = "TA_PAPER_SV03_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SV03_D_SEQ")
	private Long paperSv03DSeq;
	@Column(name = "PAPER_SV_NUMBER")
	private String paperSvNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "MEMBER_CODE")
	private String memberCode;
	@Column(name = "MEMBER_FULL_NAME")
	private String memberFullName;
	@Column(name = "MEMBER_START_DATE")
	private LocalDate memberStartDate;
	@Column(name = "MEMBER_END_DATE")
	private LocalDate memberEndDate;
	@Column(name = "MEMBER_COUPON")
	private String memberCoupon;
	@Column(name = "MEMBER_USED_DATE")
	private LocalDate memberUsedDate;
	@Column(name = "MEMBER_STATUS")
	private String memberStatus;

	public Long getPaperSv03DSeq() {
		return paperSv03DSeq;
	}

	public void setPaperSv03DSeq(Long paperSv03DSeq) {
		this.paperSv03DSeq = paperSv03DSeq;
	}

	public String getPaperSvNumber() {
		return paperSvNumber;
	}

	public void setPaperSvNumber(String paperSvNumber) {
		this.paperSvNumber = paperSvNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

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

	public LocalDate getMemberStartDate() {
		return memberStartDate;
	}

	public void setMemberStartDate(LocalDate memberStartDate) {
		this.memberStartDate = memberStartDate;
	}

	public LocalDate getMemberEndDate() {
		return memberEndDate;
	}

	public void setMemberEndDate(LocalDate memberEndDate) {
		this.memberEndDate = memberEndDate;
	}

	public String getMemberCoupon() {
		return memberCoupon;
	}

	public void setMemberCoupon(String memberCoupon) {
		this.memberCoupon = memberCoupon;
	}

	public LocalDate getMemberUsedDate() {
		return memberUsedDate;
	}

	public void setMemberUsedDate(LocalDate memberUsedDate) {
		this.memberUsedDate = memberUsedDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

}
