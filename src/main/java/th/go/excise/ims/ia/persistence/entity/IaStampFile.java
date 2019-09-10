
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
@Table(name = "IA_STAMP_FILE")
public class IaStampFile extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4179819497373008916L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_FILE_GEN")
	@SequenceGenerator(name = "IA_STAMP_FILE_GEN", sequenceName = "IA_STAMP_FILE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "DETAIL_ID")
	private BigDecimal detailId;
	@Column(name = "FILE_NAME")
	private String fileName;
	@Column(name = "VALUE1")
	private String value1;
	@Column(name = "VALUE2")
	private String value2;
	@Column(name = "VALUE3")
	private String value3;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getDetailId() {
		return detailId;
	}

	public void setDetailId(BigDecimal detailId) {
		this.detailId = detailId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

}
