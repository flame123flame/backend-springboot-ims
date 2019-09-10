
package th.go.excise.ims.ia.persistence.repository;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;

public interface IaGftrialBalanceRepository extends CommonJpaCrudRepository<IaGftrialBalance, Long> ,  IaGftrialBalanceRepositorCustom {
	@Query(
			value = " SELECT DISTINCT ACC_NAME FROM IA_GFTRIAL_BALANCE " +
					" WHERE ACC_NO = ? ",
			nativeQuery = true
		)
		public String findAccName(String accNo);
}
