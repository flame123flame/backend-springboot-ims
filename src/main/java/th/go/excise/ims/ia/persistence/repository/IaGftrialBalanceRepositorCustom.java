package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.vo.Int0801Vo;
import th.go.excise.ims.ia.vo.Int0802SearchVo;
import th.go.excise.ims.ia.vo.Int0802Vo;
import th.go.excise.ims.ia.vo.Int0803Search;
import th.go.excise.ims.ia.vo.Int0803TableVo;
import th.go.excise.ims.ia.vo.Int0803Vo;
import th.go.excise.ims.ia.vo.SearchVo;

public interface IaGftrialBalanceRepositorCustom {
	public void batchInsert(List<IaGftrialBalance> iaGftrialBalances);

	public List<IaGftrialBalance> findByGfDisburseUnit(String gfDisburseUnit);

	public List<Int0802Vo> findDiferrenceByConditionTab1(Int0802SearchVo request);
	
	public List<Int0802Vo> findDiferrenceByConditionTab2(Int0802SearchVo request);

	public List<Int0803TableVo> findExperimentalBudgetByRequest(Int0803Search request);

	public List<Int0803TableVo> findDepositsReportByRequest(Int0803Search request);

	public List<Int0801Vo> findDataAccByRequest(SearchVo request);
}
