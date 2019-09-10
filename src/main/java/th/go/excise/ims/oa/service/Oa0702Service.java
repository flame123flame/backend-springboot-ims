package th.go.excise.ims.oa.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0702JdbcRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa07JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0702Reg8000Vo;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;
import th.go.excise.ims.oa.vo.Oa07Vo;
import th.go.excise.ims.oa.vo.ResponseOa07Vo;

@Service
public class Oa0702Service {

	private static final Logger logger = LoggerFactory.getLogger(Oa0702Service.class);

	@Autowired
	private Oa0702JdbcRepository oa0702JdbcRepository;

	@Autowired
	private Oa07JdbcRepository oa07JdbcRepository;

	public ResponseOa07Vo reg4000(Oa07FormVo formVo) {

		List<Oa07Reg4000Vo> reg4000List = oa07JdbcRepository.reg4000(formVo);

		// ==> Add list year months
		List<String> yearsMonths = new ArrayList<>();
		// ==> for previous year
		for (int i = 0; i < NumberUtils.toInt(formVo.getPreviousYear()); i++) {

			// ==> convert date
			Date dateS = ConvertDateUtils.parseStringToDate(formVo.getMonthStart(), ConvertDateUtils.MM_YYYY);
			dateS = DateUtils.addYears(dateS, -i);

			// ==> add month
			for (int j = 0; j < NumberUtils.toInt(formVo.getMonthNum()); j++) {
				Date monthAdd = DateUtils.addMonths(dateS, j);
				String yearMonth = ConvertDateUtils.formatDateToString(monthAdd, ConvertDateUtils.YYYYMM,
						ConvertDateUtils.LOCAL_EN);
				yearsMonths.add(yearMonth);
			}
		}

		// ==> Add object
		List<Oa07Vo> voList = new ArrayList<>();
		Oa07Vo vo = null;
		for (Oa07Reg4000Vo reg4000 : reg4000List) {
			vo = new Oa07Vo();
			copyPropertiesReg4000(vo, reg4000);
			String dutyDesc = ExciseUtils.getDutyDesc(vo.getDutyCode());
			vo.setDutyDesc(dutyDesc);

			// ==> query tax pay
			List<Oa0702Reg8000Vo> reg8000MList = oa0702JdbcRepository.reg8000M(reg4000.getNewRegId(), yearsMonths);

			// ==> 8000M
			Map<String, Oa0702Reg8000Vo> reg8000MMap = new HashMap<>();
			for (Oa0702Reg8000Vo voMap : reg8000MList) {
				reg8000MMap.put(voMap.getYearMonth(), voMap);
			}

			List<String> taxListDtl = new ArrayList<>();
			for (String yearsMonth : yearsMonths) {
				Oa0702Reg8000Vo reg8000 = reg8000MMap.get(yearsMonth);
				if (reg8000 != null) {

					if (reg8000.getTaxAmount() == null) {
						reg8000.setTaxAmount(BigDecimal.ZERO);
					}
					taxListDtl.add(reg8000.getTaxAmount().toString());

				} else {
					taxListDtl.add(BigDecimal.ZERO.toString());
				}
			}

			vo.setTaxPayList(taxListDtl);

			// Group year and taxAmount
			List<String> groupTaxPay = new ArrayList<>();
			List<String> groupYearMonth = new ArrayList<>();
			List<String> groupYearMonthGraph = new ArrayList<>();

			// month
			for (String yearsMonth : yearsMonths) {
				Date date = ConvertDateUtils.parseStringToDate(yearsMonth, ConvertDateUtils.YYYYMM,
						ConvertDateUtils.LOCAL_EN);
				groupYearMonth.add(ConvertDateUtils.formatDateToString(date, ConvertDateUtils.MMM_YYYY_SPAC));

			}

			// month graph
			for (int i = 0; i < NumberUtils.toInt(formVo.getMonthNum()); i++) {

				String yearmonth = yearsMonths.get(i);

				for (int j = 0; j < NumberUtils.toInt(formVo.getPreviousYear()); j++) {
					Date date = ConvertDateUtils.parseStringToDate(yearmonth, ConvertDateUtils.YYYYMM,
							ConvertDateUtils.LOCAL_EN);
					Date subYear = DateUtils.addYears(date, -j);
					groupYearMonthGraph
							.add(ConvertDateUtils.formatDateToString(subYear, ConvertDateUtils.MMM_YYYY_SPAC));

					String subYearStr = ConvertDateUtils.formatDateToString(subYear, ConvertDateUtils.YYYYMM,
							ConvertDateUtils.LOCAL_EN);
					Oa0702Reg8000Vo reg8000 = reg8000MMap.get(subYearStr);
					if (reg8000 != null) {
						if (reg8000.getTaxAmount() == null) {
							reg8000.setTaxAmount(BigDecimal.ZERO);
							groupTaxPay.add(reg8000.getTaxAmount().toString());
						} else {
							groupTaxPay.add(reg8000.getTaxAmount().toString());
						}
					} else {
						groupTaxPay.add(BigDecimal.ZERO.toString());
					}
				}
			}

			vo.setDataTableGraph(this.addTableGraph(yearsMonths, taxListDtl, formVo.getMonthNum()));
			vo.setGroupTaxPay(groupTaxPay);
			vo.setGroupYearMonth(groupYearMonth);
			vo.setGroupYearMonthGraph(groupYearMonthGraph);
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

	private List<List<String>> addTableGraph(List<String> yearsMonths, List<String> taxListDtl, String monthNum) {

		// ==> month
		List<String> months = new ArrayList<>();
		// ==> year
		List<String> ymList = new ArrayList<>();
		for (int i = 0; i < yearsMonths.size(); i++) {
			ymList.add(yearsMonths.get(i).substring(0, 4));

			if (i < NumberUtils.toInt(monthNum)) {
				months.add(yearsMonths.get(i).substring(4, 6));
			}
		}

		List<String> yesrs = ymList.stream().distinct().sorted().collect(Collectors.toList());
		List<List<String>> result = new ArrayList<>();
		for (String year : yesrs) {

			List<String> row = new ArrayList<String>();
			row.add(year);
			for (String month : months) {

				int idx = yearsMonths.indexOf(year + month);

				if (idx == -1) {
					row.add("");
				} else {
					row.add(taxListDtl.get(idx));
				}
			} /* end for month */
			result.add(row);
		}
		return result;
	}

}
