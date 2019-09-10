package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv03D;

public interface TaPaperSv03DRepository extends CommonJpaCrudRepository<TaPaperSv03D, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paperSvNumber = :paperSvNumber order by e.seqNo")
	public List<TaPaperSv03D> findByPaperSvNumber(@Param("paperSvNumber") String paperSvNumber);
}
