package th.go.excise.ims.oa.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0701JdbcRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa07JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0701Reg8000Vo;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;
import th.go.excise.ims.oa.vo.Oa07Vo;
import th.go.excise.ims.oa.vo.ResponseOa07Vo;

@Service
public class Oa0701Service {

	private static final Logger logger = LoggerFactory.getLogger(Oa0701Service.class);

	@Autowired
	private Oa0701JdbcRepository oa0701JdbcRepository;
	
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

			List<String> taxListDtl = new ArrayList<>();
			List<String> percenDiffList = new ArrayList<>();
			
			// ==> convert date
			String startDate = null;
			String endDate = null;
			if ("1".equals(formVo.getCheckType())) {
				Date yearPreviouse = DateUtils.addYears(ConvertDateUtils.parseStringToDate(formVo.getBudgetYear(), ConvertDateUtils.YYYY), -1);
				startDate = ConvertDateUtils.formatDateToString(yearPreviouse, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN) + "10";
				endDate = ConvertDateUtils.formatDateToString(
							ConvertDateUtils.parseStringToDate(formVo.getBudgetYear(), ConvertDateUtils.YYYY), 
							ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN) + "09";
				
			}else if("2".equals(formVo.getCheckType())){
				Date dateS = ConvertDateUtils.parseStringToDate(formVo.getMonthStart(), ConvertDateUtils.MM_YYYY);
				Date dateE = ConvertDateUtils.parseStringToDate(formVo.getMonthEnd(), ConvertDateUtils.MM_YYYY);
				startDate = ConvertDateUtils.formatDateToString(dateS, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
				endDate = ConvertDateUtils.formatDateToString(dateE, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
			}else {}
			
			// ==> query tax pay
			List<Oa0701Reg8000Vo> reg8000MList = oa0701JdbcRepository.reg8000M(reg4000.getNewRegId(), startDate, endDate);
			// ==> 8000M
			Map<String, Oa0701Reg8000Vo> reg8000MMap = new HashMap<>();
			for (Oa0701Reg8000Vo voMap : reg8000MList) {
				reg8000MMap.put(voMap.getYearMonth().toString(), voMap);
			}
			
			//==> Add list months
			Date dateS = ConvertDateUtils.parseStringToDate(startDate, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
			//Date dateE = ConvertDateUtils.parseStringToDate(formVo.getMonthStart(), ConvertDateUtils.MM_YYYY);
			List<String> yearmonthList = new ArrayList<>();
			for (int m = 0; m < Integer.valueOf(formVo.getMonthNum()); m++) {
				String ym = ConvertDateUtils.formatDateToString(DateUtils.addMonths(dateS, m), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
				yearmonthList.add(ym);
			}
			
			for (int idx = 0; idx < Integer.valueOf(formVo.getMonthNum()); idx++) {
				Oa0701Reg8000Vo reg8000 = reg8000MMap.get(yearmonthList.get(idx));
				
				if(reg8000 != null) {
					// check sum null
					if (reg8000.getTaxAmount() == null) {
						reg8000.setTaxAmount(BigDecimal.ZERO);
					}

					taxListDtl.add(reg8000.getTaxAmount().toString());
					if (idx > 0) {
						String taxAmBeforArr = taxListDtl.get(idx - 1);
						BigDecimal taxAmBefor = new BigDecimal(taxAmBeforArr);
						BigDecimal sub = reg8000.getTaxAmount().subtract(taxAmBefor); // b-a
						BigDecimal multi = sub.multiply(new BigDecimal(100)); // b-a*100
						BigDecimal avg = multi.divide(reg8000.getTaxAmount(), 2, RoundingMode.HALF_UP); // b-a*100/b

						percenDiffList.add(avg.toString() + " %");
					} else {
						percenDiffList.add("");
					}
				}else {
					percenDiffList.add("");
					taxListDtl.add(BigDecimal.ZERO.toString());
				}			
			}
			//calculate months
				List<String> monthYear = new ArrayList<>();
				if ("1".equals(formVo.getCheckType())) {
					String monthStart = "10/"+ (NumberUtils.toInt(formVo.getBudgetYear()) -1);
					for(int i=0; i< 12; i++){
						Date date = ConvertDateUtils.parseStringToDate(monthStart, ConvertDateUtils.MM_YYYY);
						Date addMonth = DateUtils.addMonths(date, i);
						String dateStr = ConvertDateUtils.formatDateToString(addMonth, ConvertDateUtils.MMM_YYYY_SPAC);
						monthYear.add(dateStr);
					}
				} else if ("2".equals(formVo.getCheckType())){
					for(int i=0; i< NumberUtils.toInt(formVo.getMonthNum()); i++){
						Date date = ConvertDateUtils.parseStringToDate(formVo.getMonthStart(), ConvertDateUtils.MM_YYYY);
						Date addMonth = DateUtils.addMonths(date, i);
						String dateStr = ConvertDateUtils.formatDateToString(addMonth, ConvertDateUtils.MMM_YYYY_SPAC);
						monthYear.add(dateStr);
					}
			}
			vo.setTaxPayList(taxListDtl);
			vo.setPerceneDiff(percenDiffList);
			vo.setGroupYearMonth(monthYear);

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
