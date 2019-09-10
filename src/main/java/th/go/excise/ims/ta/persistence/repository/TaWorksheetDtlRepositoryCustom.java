package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.vo.TaxDraftVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

public interface TaWorksheetDtlRepositoryCustom {

	public void batchInsert(List<TaWorksheetDtl> taWorksheetHdrList);

	public void batchUpdate(List<TaWorksheetDtl> taWorksheetDtlList);

	public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo);

	public Long countByCriteria(TaxOperatorFormVo formVo);

	public List<TaxDraftVo> findByAnalysisNumber(String analysisNumber);

	public List<LabelValueBean> groupCondSubCapital(String analysisNumber);

	public List<LabelValueBean> groupCondSubRisk(String analysisNumber);

}
