package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.vo.TaPlanMasVo;

public interface TaPlanMasRepositoryCustom {
	
	public List<TaPlanMasVo> findPlanCountByOfficeCode(String officeCode,String budgetYear);
	
	public List<TaPlanMasVo> findPlanCountByCentral(String budgetYear);

}
