package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_COND_SUB_NOAT")
public class TaWorksheetCondSubNoAudit extends BaseEntity {

	private static final long serialVersionUID = 4658217546084544809L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_SUB_NOAT_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_SUB_NOAT_GEN", sequenceName = "TA_WORKSHEET_COND_SUB_NOAT_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_SUB_NOAT_ID")
	private Long worksheetCondSubNoatId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "NO_TAX_AUDIT_YEAR_NUM")
	private Integer noTaxAuditYearNum;

	public Long getWorksheetCondSubNoatId() {
		return worksheetCondSubNoatId;
	}

	public void setWorksheetCondSubNoatId(Long worksheetCondSubNoatId) {
		this.worksheetCondSubNoatId = worksheetCondSubNoatId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public Integer getNoTaxAuditYearNum() {
		return noTaxAuditYearNum;
	}

	public void setNoTaxAuditYearNum(Integer noTaxAuditYearNum) {
		this.noTaxAuditYearNum = noTaxAuditYearNum;
	}

}
