package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr04D;

public interface TaPaperPr04DRepository extends CommonJpaCrudRepository<TaPaperPr04D, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paperPrNumber = :paperPrNumber order by e.seqNo")
	public List<TaPaperPr04D> findByPaperPrNumber(@Param("paperPrNumber") String paperPrNumber);
	
}
