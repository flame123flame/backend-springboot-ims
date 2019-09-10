package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;

@Service
public class TaxOperatorService {

	private static final Logger logger = LoggerFactory.getLogger(TaxOperatorService.class);

	@Autowired
	private DraftWorksheetService draftWorksheetService;

	// TODO Tax Operator 2
	// TODO DRAFT
	public TaxOperatorVo getPreviewData(TaxOperatorFormVo formVo) {
		logger.info("Tax  Operator 2 getPreviewData");
	
		if(StringUtils.isNotBlank(formVo.getBudgetYear())) {
			if(StringUtils.isEmpty(formVo.getDateStart()) && StringUtils.isEmpty(formVo.getDateEnd())) {
				formVo.setDateStart("01/"+formVo.getBudgetYear());
				formVo.setDateEnd("12/"+formVo.getBudgetYear());
			}
		}
	
		
		TaxOperatorVo draft = draftWorksheetService.getPreviewData(formVo);

		List<TaxOperatorDatatableVo> list = draft.getDatas();
		TaxOperatorVo res = new TaxOperatorVo();
		List<TaxOperatorDatatableVo> taxList = new ArrayList<>();

		res.setCount(draft.getCount());
		res.setCondGroups(draft.getCondGroups());
		for (int i = 0; i < formVo.getLength(); i++) {
			
			int count = formVo.getStart() + i;	
			if(list.size() <= count) break;
			taxList.add(list.get(count));
		}
		res.setDatas(taxList);
		return res;
	}

}
