package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Operation;
import th.co.baiwa.buckwaframework.accesscontrol.vo.OperationFormVo;

public interface OperationRepositoryCustom {
	
	
	
	public Integer countByCriteria(OperationFormVo operationFormVo);
	
	public List<Operation>  findByCriteria(OperationFormVo operationFormVo);

	
}
