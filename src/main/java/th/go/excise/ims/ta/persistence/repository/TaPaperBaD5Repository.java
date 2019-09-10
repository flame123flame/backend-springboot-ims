package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD5;

public interface TaPaperBaD5Repository extends CommonJpaCrudRepository<TaPaperBaD5, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paperBaNumber = :paperBaNumber order by e.seqNo")
	public List<TaPaperBaD5> findByPaperBaNumber(@Param("paperBaNumber") String paperBaNumber);

}
