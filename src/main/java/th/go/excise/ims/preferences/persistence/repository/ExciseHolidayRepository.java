package th.go.excise.ims.preferences.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseHoliday;

public interface ExciseHolidayRepository extends CommonJpaCrudRepository<ExciseHoliday, Long>, ExciseHolidayRepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE EXCISE_HOLIDAY SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY();
	
	@Query(value = "select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.holidayDate >= :dateStart and e.holidayDate <= :dateEnd order by e.holidayDate")
	public List<ExciseHoliday> findByDateRange(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);
	
}
