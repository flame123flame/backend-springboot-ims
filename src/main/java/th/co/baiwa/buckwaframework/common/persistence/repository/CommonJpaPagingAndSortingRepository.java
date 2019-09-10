package th.co.baiwa.buckwaframework.common.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/*
 * @Author: Taechapon Himarat (Su)
 * @Create: Jul 20, 2018
 */
@NoRepositoryBean
public interface CommonJpaPagingAndSortingRepository<T extends BaseEntity, ID extends Serializable> extends CommonJpaCrudRepository<T, ID> {
	
	/**
	 * Returns all entities sorted by the given options.
	 * 
	 * @param sort
	 * @return all entities sorted by the given options
	 */
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'")
	List<T> findAll(Sort sort);
	
	/**
	 * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of entities
	 */
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'")
	Page<T> findAll(Pageable pageable);
	
}
