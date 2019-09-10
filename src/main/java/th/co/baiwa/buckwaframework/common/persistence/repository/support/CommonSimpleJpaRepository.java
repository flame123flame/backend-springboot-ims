package th.co.baiwa.buckwaframework.common.persistence.repository.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/*
 * @Author: Taechapon Himarat (Su)
 * @Create: Jul 27, 2018
 */
public class CommonSimpleJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

	private final EntityManager entityManager;
	private Class<T> entityClass;

	public CommonSimpleJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityClass = entityInformation.getJavaType();
	}

	@Override
	public Optional<T> findById(ID id) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			Optional<T> entity = super.findById(id);
			if (entity.isPresent() && FLAG.N_FLAG.equals(((BaseEntity) entity.get()).getIsDeleted())) {
				return entity;
			} else {
				return Optional.empty();
			}
		} else {
			return super.findById(id);
		}
	}
	
	@Override
	public List<T> findAllById(Iterable<ID> ids) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			List<T> results = new ArrayList<T>();
			for (ID id : ids) {
				findById(id).ifPresent(e -> results.add(e));
			}
			return results;
		} else {
			return super.findAllById(ids);
		}
	}

	@Override
	public boolean existsById(ID id) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			return findById(id) != null;
		} else {
			return super.existsById(id);
		}
	}

	@Override
	@Transactional
	public void delete(T entity) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			BaseEntity baseEntity = (BaseEntity) entity;
			baseEntity.setIsDeleted(FLAG.Y_FLAG);
			entityManager.merge(baseEntity);
		} else {
			super.delete(entity);
		}
	}

}
