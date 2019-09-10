
package th.go.excise.ims.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;

public interface IaGfledgerAccountRepository extends CommonJpaCrudRepository<IaGfledgerAccount, Long> , IaGfledgerAccountRepositoryCustom{

}
