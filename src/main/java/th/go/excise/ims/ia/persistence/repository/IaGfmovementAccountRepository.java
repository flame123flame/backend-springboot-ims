
package th.go.excise.ims.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;

public interface IaGfmovementAccountRepository extends CommonJpaCrudRepository<IaGfmovementAccount, Long> ,IaGfmovementAccountRepositoryCustom {

}
