package th.go.excise.ims.ta.persistence.repository;

import java.util.List;
import java.util.Map;

import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WsReg4000Vo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;

public interface TaWsReg4000RepositoryCustom {
	
	public void batchMerge(List<TaWsReg4000> taWsReg4000List);
	
	public List<WsReg4000Vo> findByCriteria(TaxOperatorFormVo formVo);
	
	public Long countByCriteria(TaxOperatorFormVo formVo);
	
	public List<OutsidePlanVo> outsidePlan(OutsidePlanFormVo formVo);
	
	public Long countOutsidePlan(OutsidePlanFormVo formVo);
	
	public WsRegfri4000FormVo findByNewRegId(String newRegId);
	
	public Map<String, List<String>> findDutyByNewRegId(List<String> newRegIdList);
	
	public List<TaxOperatorDetailVo> findByCriteriaPivotDatatable(TaxOperatorFormVo formVo, Map<String, String> auditPlanMap, Map<String, String> maxYearMap, String incomeTaxType);
	
}
