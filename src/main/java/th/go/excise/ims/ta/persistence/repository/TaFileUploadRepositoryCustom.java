package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.vo.FileUploadFormVo;
import th.go.excise.ims.ta.vo.FileUploadVo;

public interface TaFileUploadRepositoryCustom {
	
	public List<FileUploadVo> findVoListByCriteria(FileUploadFormVo formVo);
	
}
