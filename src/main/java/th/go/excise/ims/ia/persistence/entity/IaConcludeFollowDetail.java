
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_CONCLUDE_FOLLOW_DETAIL")
public class IaConcludeFollowDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1006995597025244866L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CONCLUDE_FOLLOW_DETAIL_GEN")
	@SequenceGenerator(name = "IA_CONCLUDE_FOLLOW_DETAIL_GEN", sequenceName = "IA_CONCLUDE_FOLLOW_DETAIL_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ID_HDR")
	private BigDecimal idHdr;
	@Column(name = "ISSUES")
	private String issues;
	@Column(name = "DETECTED_OBSERVED")
	private String detectedObserved;
	@Column(name = "WHAT_SHOULD_BE")
	private String whatShouldBe;
	@Column(name = "RISK_EFFECT")
	private String riskEffect;
	@Column(name = "CAUSE")
	private String cause;
	@Column(name = "RECOMMEND")
	private String recommend;
	@Column(name = "LEVEL_DATA")
	private BigDecimal levelData;
	@Column(name = "SEQ")
	private BigDecimal seq;
	@Column(name = "ISSUES_CODE")
	private String issuesCode;
	@Column(name = "GUIDELINES_DEVELOPING")
	private String guidelinesDeveloping;
	@Column(name = "REFERENCE")
	private String reference;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getIdHdr() {
		return idHdr;
	}

	public void setIdHdr(BigDecimal idHdr) {
		this.idHdr = idHdr;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public String getDetectedObserved() {
		return detectedObserved;
	}

	public void setDetectedObserved(String detectedObserved) {
		this.detectedObserved = detectedObserved;
	}

	public String getWhatShouldBe() {
		return whatShouldBe;
	}

	public void setWhatShouldBe(String whatShouldBe) {
		this.whatShouldBe = whatShouldBe;
	}

	public String getRiskEffect() {
		return riskEffect;
	}

	public void setRiskEffect(String riskEffect) {
		this.riskEffect = riskEffect;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public BigDecimal getLevelData() {
		return levelData;
	}

	public void setLevelData(BigDecimal levelData) {
		this.levelData = levelData;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public String getIssuesCode() {
		return issuesCode;
	}

	public void setIssuesCode(String issuesCode) {
		this.issuesCode = issuesCode;
	}

	public String getGuidelinesDeveloping() {
		return guidelinesDeveloping;
	}

	public void setGuidelinesDeveloping(String guidelinesDeveloping) {
		this.guidelinesDeveloping = guidelinesDeveloping;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
	

}
