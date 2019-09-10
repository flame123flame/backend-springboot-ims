package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;

public interface TaWsInc8000MRepositoryCustom {
	
	public Map<String, List<TaWsInc8000M>> findByMonthRange(String startMonth, String endMonth);
	
	public Map<String, BigDecimal> findByMonthRangeDuty(String newRegId, String dutyCode, WorksheetDateRangeVo dateRangeVo, String incomeTaxType);
	
	public Map<String, BigDecimal> findByMonthRangePivot(String newRegId, String dutyCode, List<String> yyyyMMList, String incomeTaxType);
	
}
