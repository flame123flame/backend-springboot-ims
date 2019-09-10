package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingH;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrFormVo;

public interface IaEmpWorkingHRepositoryCustom {
	
	public List<IaEmpWorkingH> findByMonth(IaEmpWorkingHdrFormVo formVo);
}
