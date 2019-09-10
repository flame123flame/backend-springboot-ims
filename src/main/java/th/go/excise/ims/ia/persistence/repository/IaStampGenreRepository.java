
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaStampGenre;

public interface IaStampGenreRepository
    extends CommonJpaCrudRepository<IaStampGenre, Long>
{

	 List<IaStampGenre> findByStampTypeId(String stamTypeId);
}
