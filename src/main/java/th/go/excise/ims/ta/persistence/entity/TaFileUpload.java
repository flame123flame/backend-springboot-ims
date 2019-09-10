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
@Table(name = "TA_FILE_UPLOAD")
public class TaFileUpload extends BaseEntity {

	private static final long serialVersionUID = 4324377552840524036L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FILE_UPLOAD_GEN")
	@SequenceGenerator(name = "TA_FILE_UPLOAD_GEN", sequenceName = "TA_FILE_UPLOAD_SEQ", allocationSize = 1)
	@Column(name = "TA_FILE_UPLOAD_SEQ")
	private Long taFileUploadSeq;
	@Column(name = "UPLOAD_NO")
	private String uploadNo;
	@Column(name = "MODULE_CODE")
	private String moduleCode;
	@Column(name = "REF_NO")
	private String refNo;
	@Column(name = "FILE_PATH")
	private String filePath;
	@Column(name = "FILE_NAME")
	private String fileName;

	public Long getTaFileUploadSeq() {
		return taFileUploadSeq;
	}

	public void setTaFileUploadSeq(Long taFileUploadSeq) {
		this.taFileUploadSeq = taFileUploadSeq;
	}

	public String getUploadNo() {
		return uploadNo;
	}

	public void setUploadNo(String uploadNo) {
		this.uploadNo = uploadNo;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
