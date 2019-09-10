package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.vo.TaxDraftVo;

public interface TaPlanWorksheetSelectRepositoryCustom {
	
	public void batchInsert(String budgetYear, List<TaxDraftVo> taxDraftVoList);
	
	public Integer findCentalAllSend();
	
}
