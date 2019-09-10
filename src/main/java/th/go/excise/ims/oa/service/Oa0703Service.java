package th.go.excise.ims.oa.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0703JdbcRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa07JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0703TaxpayVo;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;
import th.go.excise.ims.oa.vo.Oa07Vo;
import th.go.excise.ims.oa.vo.ResponseOa07Vo;

@Service
public class Oa0703Service {

	private static final Logger logger = LoggerFactory.getLogger(Oa0703Service.class);

	@Autowired
	private Oa0703JdbcRepository oa07Jdb03cRepository;
	
	@Autowired
	private Oa07JdbcRepository oa07JdbcRepository;

	public ResponseOa07Vo reg4000(Oa07FormVo formVo) {

		List<Oa07Reg4000Vo> reg4000List = oa07JdbcRepository.reg4000(formVo);

		// ==> Add object
		List<Oa07Vo> voList = new ArrayList<>();
		Oa07Vo vo = null;
		for (Oa07Reg4000Vo reg4000 : reg4000List) {
			vo = new Oa07Vo();
			copyPropertiesReg4000(vo, reg4000);	
			String dutyDesc = ExciseUtils.getDutyDesc(vo.getDutyCode());
			vo.setDutyDesc(dutyDesc);

			//int i = 0;
			List<String> taxListDtl = new ArrayList<>();
			List<String> percenDiffList = new ArrayList<>();
			List<Integer> budgetYears = new ArrayList<>();

			//=> add list bydget year
			Date yyyyDate = ConvertDateUtils.parseStringToDate(formVo.getBudgetYear(), ConvertDateUtils.YYYY);
			String yyyy = ConvertDateUtils.formatDateToString(yyyyDate, ConvertDateUtils.YYYY,ConvertDateUtils.LOCAL_EN);

			for (int p = 0; p < Integer.valueOf(formVo.getPreviousYear()); p++) {
				int budgerYear = Integer.valueOf(yyyy);
				budgetYears.add(budgerYear - p);
			}
			Collections.sort(budgetYears); 
			// ==> query tax pay
			List<Oa0703TaxpayVo> reg8000MList = oa07Jdb03cRepository.reg8000M(reg4000.getNewRegId(), budgetYears);
			Map<String, Oa0703TaxpayVo> reg8000MMap = new HashMap<>();
			for (Oa0703TaxpayVo voMap : reg8000MList) {				
				reg8000MMap.put(voMap.getTaxYear(), voMap);
			}
						
			for (int idx = 0; idx < budgetYears.size(); idx++) {
				Oa0703TaxpayVo reg8000 = reg8000MMap.get(budgetYears.get(idx).toString());
				
				if(reg8000 != null) {
					// check sum null
					if (reg8000.getSumTaxAmount() == null) {
						reg8000.setSumTaxAmount(BigDecimal.ZERO);
					}

					taxListDtl.add(reg8000.getSumTaxAmount().toString());
					if (idx > 0) {
						String taxAmBeforArr = taxListDtl.get(idx - 1);
						BigDecimal sumTaxAmBefor = new BigDecimal(taxAmBeforArr);

						BigDecimal sub = reg8000.getSumTaxAmount().subtract(sumTaxAmBefor); // b-a
						BigDecimal multi = sub.multiply(new BigDecimal(100)); // b-a*100
						BigDecimal avg = multi.divide(reg8000.getSumTaxAmount(), 2, RoundingMode.HALF_UP); // b-a*100/b

						percenDiffList.add(avg.toString() + " %");
					} else {
						percenDiffList.add("");
					}
				}else {
					percenDiffList.add("");
					taxListDtl.add(BigDecimal.ZERO.toString());
				}
				//i++;
			}
			vo.setTaxPayList(taxListDtl);
			vo.setPerceneDiff(percenDiffList);
			voList.add(vo);
		}
		
		ResponseOa07Vo response07 = new ResponseOa07Vo();
		response07.setDataList(voList);
		response07.setCount(oa07JdbcRepository.reg4000Count(formVo));
		return response07;
	}

	private void copyPropertiesReg4000(Oa07Vo vo1, Oa07Reg4000Vo vo2) {
		try {
			BeanUtils.copyProperties(vo1, vo2);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

}
