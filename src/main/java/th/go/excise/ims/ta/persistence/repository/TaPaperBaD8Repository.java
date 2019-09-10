package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD8;

public interface TaPaperBaD8Repository extends CommonJpaCrudRepository<TaPaperBaD8, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paperBaNumber = :paperBaNumber order by e.seqNo")
	public List<TaPaperBaD8> findByPaperBaNumber(@Param("paperBaNumber") String paperBaNumber);

}
