package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public interface TaWorksheetHdrRepositoryCustom {
	
	public List<TaWorksheetHdr> findAllAnalysisNumberHead(String officeCode,String budgetYear);
}
