package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaFileUpload;

public interface TaFileUploadRepository extends CommonJpaCrudRepository<TaFileUpload, Long>, TaFileUploadRepositoryCustom {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.uploadNo = :uploadNo")
	public TaFileUpload findByUploadNo(@Param("uploadNo") String uploadNo);
	
	@Modifying
	@Query("update #{#entityName} e set e.isDeleted = '" + FLAG.Y_FLAG + "' where e.uploadNo = :uploadNo")
	public void deleteByUploadNo(@Param("uploadNo") String uploadNo);
	
}
