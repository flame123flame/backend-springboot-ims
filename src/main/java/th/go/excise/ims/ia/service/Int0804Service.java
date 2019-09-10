package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.repository.IaGfmovementAccountRepository;
import th.go.excise.ims.ia.vo.Int0804DateVo;
import th.go.excise.ims.ia.vo.Int0804HeaderTable;
import th.go.excise.ims.ia.vo.Int0804SearchVo;
import th.go.excise.ims.ia.vo.Int0804SummaryVo;
import th.go.excise.ims.ia.vo.Int0804Vo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepaccMasRepository;

@Service
public class Int0804Service {
	@Autowired
	private ExciseDepaccMasRepository exciseDepaccMasRepository;

	@Autowired
	private IaGfmovementAccountRepository iaGfmovementAccountRepository;

	public List<ExciseDepaccMas> getDepaccMasDropdown() {
		return exciseDepaccMasRepository.getDepaccMasDropdown();
	}

	public Int0804Vo findByCondition(Int0804SearchVo request) {
		Int0804Vo response = new Int0804Vo();
		List<Int0804DateVo> daysList = new ArrayList<Int0804DateVo>();
		List<Int0804HeaderTable> headerTable = new ArrayList<Int0804HeaderTable>();

		/* _________ set request _________ */
		request.setDateFrom(ConvertDateUtils.formatDateToString(
				ConvertDateUtils.parseStringToDate(request.getDateFrom(), ConvertDateUtils.MM_YYYY),
				ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));
		request.setDateTo(ConvertDateUtils.formatDateToString(
				ConvertDateUtils.parseStringToDate(request.getDateTo(), ConvertDateUtils.MM_YYYY),
				ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));

		List<Int0804SummaryVo> summaryList = iaGfmovementAccountRepository.getResultByconditon(request);

		if (summaryList.size() > 0) {
			/* _________ initial unique header table _________ */
			Set<String> keyDate = new TreeSet<String>();
			Set<String> keyHeadTable = new TreeSet<String>();
			
			String previousMonth = "INITIAL";
			int loop = 0;
			/* _________ loop for find key _________ */
			for (Int0804SummaryVo header : summaryList) {
				/* month change */
				if (!previousMonth.equals((header.getDateDefault()).substring(4, 6)) && loop > 0) {
					/* _________ last day of months _________ */
					keyDate.add(summaryList.get(loop - 1).getDateDefault().concat("-EX")); // total of month
					keyDate.add(header.getDateDefault()); // next month
					previousMonth = header.getDateDefault().substring(4, 6); // month change
				} else if (loop == 0) {
					/* _________ first loop _________ */
					keyDate.add(header.getDateDefault());
					previousMonth = header.getDateDefault().substring(4, 6);
				} else {
					/* _________ current month _________ */
					keyDate.add(header.getDateDefault());
				}

				/* _________ key head table _________ */
				if (header.getGfExciseCode() != null) {
					keyHeadTable.add(header.getGfExciseCode());
				}
				loop++;
			}

			Int0804HeaderTable th = null;
			/* _________ loop set header table _________ */
			for (String officeCode : keyHeadTable) {
				th = new Int0804HeaderTable();
				th.setArea(ApplicationCache.getExciseDepartment(officeCode).getDeptName());
				th.setGfDepositCode(officeCode);
				headerTable.add(th);
			}

			/* _________ loop set data _________ */
			Int0804DateVo days = null;
			List<Int0804SummaryVo> daysFilter = null;
			List<Int0804SummaryVo> filterOfficeCode = null;
			List<Int0804SummaryVo> tdList = null;
			Int0804SummaryVo td = null;
			int i = 0;
			for (String key : keyDate) {
				days = new Int0804DateVo();
				// daysFilter = new ArrayList<Int0804SummaryVo>();
				tdList = new ArrayList<Int0804SummaryVo>();

				/* _________ group day _________ */
				daysFilter = summaryList.stream().filter(obj -> obj.getDateDefault().equals(key))
						.collect(Collectors.toList());

				int j = 0;
				/* _________ loop data of column (dynamic) _________ */
				for (String officeCode : keyHeadTable) {
					td = new Int0804SummaryVo();

					/* _________ group column _________ */
					filterOfficeCode = daysFilter.stream().filter(
							obj -> (obj.getGfExciseCode() == null ? "0" : obj.getGfExciseCode()).equals(officeCode))
							.collect(Collectors.toList());

					if (filterOfficeCode.size() > 0) {
						/* _________ length array = 1 _________ */
						for (Int0804SummaryVo f : filterOfficeCode) {
							td.setGfExciseCode(f.getGfExciseCode());
							td.setSumCarryForward(f.getSumCarryForward());
							td.setDateDefault(ConvertDateUtils.formatDateToString(
									ConvertDateUtils.parseStringToDate(f.getDateDefault(), ConvertDateUtils.YYYYMMDD,
											ConvertDateUtils.LOCAL_EN),
									ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
							if (i == 0) {
								td.setSumOfMonth(NumberUtils.nullToZero(f.getSumOfMonth()));
								td.setTotalMonths(NumberUtils.nullToZero(f.getTotalMonths()));
							} else {
								td.setSumOfMonth(
										NumberUtils.nullToZero(daysList.get(i - 1).getDay().get(j).getSumOfMonth())
												.add(NumberUtils.nullToZero(new BigDecimal(f.getSumCarryForward()))));
								td.setTotalMonths(
										NumberUtils.nullToZero(daysList.get(i - 1).getDay().get(j).getTotalMonths())
												.add(NumberUtils.nullToZero(new BigDecimal(f.getSumCarryForward()))));
							}
							tdList.add(td);
						}
					} else {
						/* _________ find key total of months ==> "-EX", length > 8 _________ */
						if (key.length() <= 8) {
							td.setGfExciseCode(officeCode);
							td.setDateDefault(ConvertDateUtils.formatDateToString(
									ConvertDateUtils.parseStringToDate(key, ConvertDateUtils.YYYYMMDD,
											ConvertDateUtils.LOCAL_EN),
									ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
							if(i == 0) {
								td.setSumOfMonth(BigDecimal.ZERO);
								td.setTotalMonths(BigDecimal.ZERO);
							} else {
								td.setSumOfMonth(daysList.get(i - 1).getDay().get(j).getSumOfMonth());
								td.setTotalMonths(daysList.get(i - 1).getDay().get(j).getTotalMonths());
							}
						} else {
							td.setSumCarryForward(daysList.get(i - 1).getDay().get(j).getSumOfMonth().toString());
							td.setTotalMonths(daysList.get(i - 1).getDay().get(j).getTotalMonths());
							td.setSumOfMonth(BigDecimal.ZERO);
						}
						tdList.add(td);
					}
					j++;
				}
				days.setDay(tdList);
				daysList.add(days);
				i++;
			}

			/* _________ add list total last month _________ */
			for (int k = 0; k < 2; k++) {
				Int0804DateVo x = daysList.get(daysList.size()-1);
				Int0804SummaryVo total = daysList.get(daysList.size()-1).getDay().get(x.getDay().size()-1);
				days = new Int0804DateVo();
				tdList = new ArrayList<Int0804SummaryVo>();
				for (String officeCode : keyHeadTable) {
					td = new Int0804SummaryVo();
					if(k == 0) {
						td.setSumCarryForward(total.getSumOfMonth().toString());
						td.setSumOfMonth(total.getSumOfMonth());
						td.setTotalMonths(total.getTotalMonths());
					} else {
						td.setSumCarryForward(total.getTotalMonths().toString());
					}
					tdList.add(td);
				}
				days.setDay(tdList);
				daysList.add(days);
			}
		}
		response.setDateList(daysList);
		response.setTh(headerTable);
		return response;
	}
}
