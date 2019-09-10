
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRentHouse1;

public interface IaRentHouse1Repository
    extends CommonJpaCrudRepository<IaRentHouse1, Long>
{
	
	@Query(value = "Select e.* from IA_RENT_HOUSE1 e  WHERE e.RENT_HOUSE_ID = ?1 ",nativeQuery = true)
	public List<IaRentHouse1> findByRentHouseId(Long RentHouseId );

}
