
package th.go.excise.ims.ia.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_ESTIMATE_EXP_H")
public class IaEstimateExpH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6440354105604710055L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_ESTIMATE_EXP_H_GEN")
	@SequenceGenerator(name = "IA_ESTIMATE_EXP_H_GEN", sequenceName = "IA_ESTIMATE_EXP_H_SEQ", allocationSize = 1)
	@Column(name = "ESTIMATE_EXP_H_ID")
	private Long estimateExpHId;
	@Column(name = "EST_EXP_NO")
	private String estExpNo;
	@Column(name = "PERSON_RESP")
	private String personResp;
	@Column(name = "RESP_DEPT_CODE")
	private String respDeptCode;
	@Column(name = "EXP_REQ_DATE")
	private Date expReqDate;
	@Column(name = "DESTINATION_PLACE")
	private String destinationPlace;
	@Column(name = "WORK_ST_DATE")
	private Date workStDate;
	@Column(name = "WORK_FH_DATE")
	private Date workFhDate;

	public Long getEstimateExpHId() {
		return estimateExpHId;
	}

	public void setEstimateExpHId(Long estimateExpHId) {
		this.estimateExpHId = estimateExpHId;
	}

	public String getEstExpNo() {
		return estExpNo;
	}

	public void setEstExpNo(String estExpNo) {
		this.estExpNo = estExpNo;
	}

	public String getPersonResp() {
		return personResp;
	}

	public void setPersonResp(String personResp) {
		this.personResp = personResp;
	}

	public String getRespDeptCode() {
		return respDeptCode;
	}

	public void setRespDeptCode(String respDeptCode) {
		this.respDeptCode = respDeptCode;
	}

	public Date getExpReqDate() {
		return expReqDate;
	}

	public void setExpReqDate(Date expReqDate) {
		this.expReqDate = expReqDate;
	}

	public String getDestinationPlace() {
		return destinationPlace;
	}

	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}

	public Date getWorkStDate() {
		return workStDate;
	}

	public void setWorkStDate(Date workStDate) {
		this.workStDate = workStDate;
	}

	public Date getWorkFhDate() {
		return workFhDate;
	}

	public void setWorkFhDate(Date workFhDate) {
		this.workFhDate = workFhDate;
	}

}
