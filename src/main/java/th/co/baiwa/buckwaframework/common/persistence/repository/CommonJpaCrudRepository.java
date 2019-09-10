package th.co.baiwa.buckwaframework.common.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/*
 * @Author: Taechapon Himarat (Su)
 * @Create: Jul 20, 2018
 */
@NoRepositoryBean
public interface CommonJpaCrudRepository<T extends BaseEntity, ID extends Serializable> extends CrudRepository<T, ID> {
	
	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'")
	List<T> findAll();

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	@Query("select count(1) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'")
	long count();
	
}
