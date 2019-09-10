package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;

public interface ExcisePersonRepository extends CommonJpaCrudRepository<ExcisePerson, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'and e.seq = 0 and edLogin = :edLogin")
	public ExcisePerson findByEdLogin(@Param("edLogin") String edLogin);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.edPersonName like :edPersonName ")
	public List<ExcisePerson> findByEdPersonName(@Param("edPersonName") String edPersonName);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'  and edLogin = :edLogin order by seq")
	public List<ExcisePerson> findExcisePersonOrderbySeq(@Param("edLogin")String edLogin);
	
	@Modifying
	@Query(value = "update excise_person e set e.seq = e.seq+1 where e.is_deleted = 'N' and e.ed_login = :edLogin " , nativeQuery = true)
	public void updateExcisePersonFromLdapData(@Param("edLogin") String edLogin);
	
	
}
