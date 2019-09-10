package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaPagingAndSortingRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

public interface MessageRepository extends CommonJpaPagingAndSortingRepository<Message, Long>, MessageRepositoryCustom {

}
