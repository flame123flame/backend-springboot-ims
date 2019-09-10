package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.repository.IaChartOfAccRepository;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.vo.Int0803Footer;
import th.go.excise.ims.ia.vo.Int0803Search;
import th.go.excise.ims.ia.vo.Int0803TableVo;
import th.go.excise.ims.ia.vo.Int0803Vo;

@Service
public class Int0803Service {
	@Autowired
	private IaChartOfAccRepository iaChartOfAccRepository;

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepository;

	public List<IaChartOfAcc> getDropdown() {
		return iaChartOfAccRepository.getDropdown();
	}

	public Int0803Vo getExperimentalBudget(Int0803Search request) {
		Int0803Vo response = new Int0803Vo();
		/* ___________ set request ___________ */
		String fromYearEN = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getFromYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN);
		String toYearEN = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getToYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN);
		request.setPeriodDateFromStr(fromYearEN.concat(request.getPeriodFrom()));
		request.setPeriodDateToStr(toYearEN.concat(request.getPeriodTo()));
//		request.setPeriodDateFrom(ExciseUtils.firstDateOfPeriod(request.getPeriodFrom(), fromYearEN));
//		request.setPeriodDateTo(ExciseUtils.lastDateOfPeriod(request.getPeriodTo(), toYearEN, false));

		/* ___________ set response ___________ */
		List<Int0803TableVo> resExperimentalBudget = iaGftrialBalanceRepository.findExperimentalBudgetByRequest(request);
		List<Int0803TableVo> resDeposits = iaGftrialBalanceRepository.findDepositsReportByRequest(request);
		response.setExperimentalBudgetList(experimentalBudget(resExperimentalBudget).getExperimentalBudgetList());
		response.setSumExperimentalBudget(experimentalBudget(resExperimentalBudget).getSumExperimentalBudget());
		response.setDepositsReportList(depositsReport(resDeposits).getDepositsReportList());
		response.setSumDepositsReport(depositsReport(resDeposits).getSumDepositsReport());
		response.setDifference(calculateDiffValue(response.getSumExperimentalBudget(), response.getSumDepositsReport()));
		return response;
	}

	private Int0803Vo experimentalBudget(List<Int0803TableVo> dataExperimentalBudget) {
		Int0803Vo response = new Int0803Vo();
		BigDecimal sumCarryForward = BigDecimal.ZERO;
		BigDecimal sumBringForward = BigDecimal.ZERO;
		BigDecimal sumCredit = BigDecimal.ZERO;
		BigDecimal sumDebit = BigDecimal.ZERO;

		for (Int0803TableVo vo : dataExperimentalBudget) {
			if (vo.getOfficeCode() != null) {
				vo.setOfficeCodeStr(ApplicationCache.getExciseDepartment(vo.getOfficeCode()).getDeptName());
				sumCarryForward = sumCarryForward.add(NumberUtils.nullToZero(vo.getCarryForward()));
				sumBringForward = sumBringForward.add(NumberUtils.nullToZero(vo.getBringForward()));
				sumCredit = sumCredit.add(NumberUtils.nullToZero(vo.getCredit()));
				sumDebit = sumDebit.add(NumberUtils.nullToZero(vo.getDebit()));
			}
		}

		response.setSumExperimentalBudget(setSummary(sumCarryForward, sumBringForward, sumCredit, sumDebit));
		response.setExperimentalBudgetList(dataExperimentalBudget);
		return response;
	}

	private Int0803Vo depositsReport(List<Int0803TableVo> dataDepositsReport) {
		Int0803Vo response = new Int0803Vo();
		BigDecimal sumCarryForward = BigDecimal.ZERO;
		BigDecimal sumBringForward = BigDecimal.ZERO;
		BigDecimal sumCredit = BigDecimal.ZERO;
		BigDecimal sumDebit = BigDecimal.ZERO;

		for (Int0803TableVo vo : dataDepositsReport) {
			if (vo.getOfficeCode() != null) {
				vo.setOfficeCodeStr(ApplicationCache.getExciseDepartment(vo.getOfficeCode()).getDeptName());
				sumCarryForward = sumCarryForward.add(NumberUtils.nullToZero(vo.getCarryForward()));
				sumBringForward = sumBringForward.add(NumberUtils.nullToZero(vo.getBringForward()));
				sumCredit = sumCredit.add(NumberUtils.nullToZero(vo.getCredit()));
				sumDebit = sumDebit.add(NumberUtils.nullToZero(vo.getDebit()));
			}
		}

		response.setSumDepositsReport(setSummary(sumCarryForward, sumBringForward, sumCredit, sumDebit));
		response.setDepositsReportList(dataDepositsReport);
		return response;
	}

	private List<Int0803Footer> setSummary(BigDecimal sumCarryForward, BigDecimal sumBringForward, BigDecimal sumCredit, BigDecimal sumDebit) {
		List<Int0803Footer> footerList = new ArrayList<Int0803Footer>();
		Int0803Footer footer = new Int0803Footer();
		footer.setSumCarryForward(sumCarryForward);
		footer.setSumBringForward(sumBringForward);
		footer.setSumCredit(sumCredit);
		footer.setSumDebit(sumDebit);
		footerList.add(footer);
		return footerList;

	}

	private List<Int0803Footer> calculateDiffValue(List<Int0803Footer> sumExperimentalBudget, List<Int0803Footer> sumDepositsReport) {
		List<Int0803Footer> differenceList = new ArrayList<Int0803Footer>();
		Int0803Footer diffValue = null;

		if (sumExperimentalBudget.size() > 0 && sumDepositsReport.size() > 0) {
			/* both have 1 loop */
			for (Int0803Footer e : sumExperimentalBudget) {
				for (Int0803Footer d : sumDepositsReport) {
					diffValue = new Int0803Footer();
					diffValue.setDiffBringForward(e.getSumBringForward().subtract(d.getSumBringForward()));
					diffValue.setDiffCarryForward(e.getSumCarryForward().subtract(d.getSumCarryForward()));
					diffValue.setDiffCredit(e.getSumCredit().subtract(d.getSumCredit()));
					diffValue.setDiffDebit(e.getSumDebit().subtract(d.getSumDebit()));
					differenceList.add(diffValue);
				}
			}
		}
		return differenceList;
	}
}
