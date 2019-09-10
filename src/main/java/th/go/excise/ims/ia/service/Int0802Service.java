package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.persistence.repository.IaGftrialBalanceRepository;
import th.go.excise.ims.ia.vo.Int0802SearchVo;
import th.go.excise.ims.ia.vo.Int0802Vo;

@Service
public class Int0802Service {

	@Autowired
	private IaGftrialBalanceRepository iaGftrialBalanceRepositoryl;

	public List<IaGftrialBalance> getRangePeriod(String gfDisburseUnit) {
		return iaGftrialBalanceRepositoryl.findByGfDisburseUnit(gfDisburseUnit);
	}

	public List<Int0802Vo> getResultByConditionTab1(Int0802SearchVo reqeust) {
		List<Int0802Vo> response = new ArrayList<Int0802Vo>();
		if ("Y".equals(reqeust.getFlagSearch())) {
			/* _______________ initial value _______________ */
			BigDecimal sumDebit = BigDecimal.ZERO;
			BigDecimal sumCredit = BigDecimal.ZERO;
			BigDecimal sumDebit2 = BigDecimal.ZERO;
			BigDecimal sumCredit2 = BigDecimal.ZERO;
			BigDecimal sumDiffDebit = BigDecimal.ZERO;
			BigDecimal sumDiffCredit = BigDecimal.ZERO;
			BigDecimal sumBringForward = BigDecimal.ZERO;
			BigDecimal sumCarryForward = BigDecimal.ZERO;

			/* _______________ year concat period _______________ */
			reqeust.setPeriodFromYear((ConvertDateUtils.formatDateToString(
					ConvertDateUtils.parseStringToDate(reqeust.getFromYear(), ConvertDateUtils.YYYY),
					ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN)).concat(reqeust.getPeriodFrom()));
			reqeust.setPeriodToYear((ConvertDateUtils.formatDateToString(
					ConvertDateUtils.parseStringToDate(reqeust.getToYear(), ConvertDateUtils.YYYY),
					ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN)).concat(reqeust.getPeriodTo()));

			/* _______________ get response _______________ */
			response = iaGftrialBalanceRepositoryl.findDiferrenceByConditionTab1(reqeust);
			for (Int0802Vo int0802Vo : response) {
				/* _______________ check pk40 = debit and pk50 = credit _______________ */
				if (int0802Vo.getCurrAmt() != null) {
					if ("40".equals(int0802Vo.getPkCode())) {
						int0802Vo.setDebit2(int0802Vo.getCurrAmt());
						sumDebit2 = sumDebit2.add(int0802Vo.getCurrAmt());
					} else if ("50".equals(int0802Vo.getPkCode())) {
						int0802Vo.setCredit2(int0802Vo.getCurrAmt());
						sumCredit2 = sumCredit2.add(int0802Vo.getCurrAmt());
					}
				}

				/* _______________ difference credit-credit2 and debit-debit2 _______________ */
				int0802Vo.setDiffCredit(NumberUtils.nullToZero(int0802Vo.getCredit())
						.subtract(NumberUtils.nullToZero(int0802Vo.getCredit2())));
				int0802Vo.setDiffDebit(NumberUtils.nullToZero(int0802Vo.getDebit())
						.subtract(NumberUtils.nullToZero(int0802Vo.getDebit2())));

//				/* _______________ summary credit and debit _______________ */
//				sumDebit = NumberUtils.nullToZero(sumDebit.add(NumberUtils.nullToZero(int0802Vo.getDebit())));
//				sumCredit = NumberUtils.nullToZero(sumCredit.add(NumberUtils.nullToZero(int0802Vo.getCredit())));
//
//				/* _______________ summary difference credit and debit _______________ */
//				sumDiffCredit = sumDiffCredit.add(int0802Vo.getDiffCredit());
//				sumDiffDebit = sumDiffDebit.add(int0802Vo.getDiffDebit());
//
//				/* ___________ summary sumBringForward and sumCarryForward ___________ */
//				sumBringForward = sumBringForward.add(NumberUtils.nullToZero(int0802Vo.getBringForward()));
//				sumCarryForward = sumCarryForward.add(NumberUtils.nullToZero(int0802Vo.getCarryForward()));
			}

			/* _______________ footer _______________ */
//			Int0802Vo footer = new Int0802Vo();
//			footer.setAccName("รวม");
//			footer.setDebit(sumDebit);
//			footer.setCredit(sumCredit);
//			footer.setCredit2(sumCredit2);
//			footer.setDebit2(sumDebit2);
//			footer.setBringForward(sumBringForward);
//			footer.setCarryForward(sumCarryForward);
//			footer.setDiffCredit(sumDiffCredit);
//			footer.setDiffDebit(sumDiffDebit);
//			response.add(footer);
		}
		return response;
	}

	public List<Int0802Vo> getResultByConditionTab2(Int0802SearchVo reqeust) {
		List<Int0802Vo> response = new ArrayList<Int0802Vo>();
		if ("Y".equals(reqeust.getFlagSearch())) {
			/* _______________ initial value _______________ */
			BigDecimal sumDebit = BigDecimal.ZERO;
			BigDecimal sumCredit = BigDecimal.ZERO;
			BigDecimal sumDebit2 = BigDecimal.ZERO;
			BigDecimal sumCredit2 = BigDecimal.ZERO;
			BigDecimal sumDebit3 = BigDecimal.ZERO;
			BigDecimal sumCredit3 = BigDecimal.ZERO;
			BigDecimal sumDiffDebit = BigDecimal.ZERO;
			BigDecimal sumDiffCredit = BigDecimal.ZERO;
			BigDecimal sumBringForward = BigDecimal.ZERO;
			BigDecimal sumCarryForward = BigDecimal.ZERO;

			/* _______________ year concat period _______________ */
			reqeust.setPeriodFromYear((ConvertDateUtils.formatDateToString(
					ConvertDateUtils.parseStringToDate(reqeust.getFromYear(), ConvertDateUtils.YYYY),
					ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN)).concat(reqeust.getPeriodFrom()));
			reqeust.setPeriodToYear((ConvertDateUtils.formatDateToString(
					ConvertDateUtils.parseStringToDate(reqeust.getToYear(), ConvertDateUtils.YYYY),
					ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN)).concat(reqeust.getPeriodTo()));

			/* _______________ get response _______________ */
			response = iaGftrialBalanceRepositoryl.findDiferrenceByConditionTab2(reqeust);
			for (Int0802Vo int0802Vo : response) {
				/* _______________ check pk40 = debit and pk50 = credit _______________ */
				if (int0802Vo.getCurrAmt() != null) {
					if ("40".equals(int0802Vo.getPkCode())) {
						int0802Vo.setDebit2(int0802Vo.getCurrAmt());
						sumDebit2 = sumDebit2.add(int0802Vo.getCurrAmt());
					} else if ("50".equals(int0802Vo.getPkCode())) {
						int0802Vo.setCredit2(int0802Vo.getCurrAmt());
						sumCredit2 = sumCredit2.add(int0802Vo.getCurrAmt());
					}
				}

				/* _______________ difference credit-credit3 and debit-debit3 _______________ */
				int0802Vo.setDiffCredit(NumberUtils.nullToZero(int0802Vo.getCredit())
						.subtract(NumberUtils.nullToZero(int0802Vo.getCredit3())));
				int0802Vo.setDiffDebit(NumberUtils.nullToZero(int0802Vo.getDebit())
						.subtract(NumberUtils.nullToZero(int0802Vo.getDebit3())));

//				/* _______________ summary credit, debit, credit3, debit3 _______________ */
//				sumDebit = NumberUtils.nullToZero(sumDebit.add(NumberUtils.nullToZero(int0802Vo.getDebit())));
//				sumCredit = NumberUtils.nullToZero(sumCredit.add(NumberUtils.nullToZero(int0802Vo.getCredit())));
//				sumDebit3 = NumberUtils.nullToZero(sumDebit3.add(NumberUtils.nullToZero(int0802Vo.getDebit3())));
//				sumDebit3 = NumberUtils.nullToZero(sumCredit3.add(NumberUtils.nullToZero(int0802Vo.getCredit3())));
//
//				/* _______________ summary difference credit2 and debit2 _______________ */
//				sumDiffCredit = sumDiffCredit.add(int0802Vo.getDiffCredit());
//				sumDiffDebit = sumDiffDebit.add(int0802Vo.getDiffDebit());
//
//				/* ___________ summary sumBringForward and sumCarryForward ___________ */
//				sumBringForward = sumBringForward.add(NumberUtils.nullToZero(int0802Vo.getBringForward()));
//				sumCarryForward = sumCarryForward.add(NumberUtils.nullToZero(int0802Vo.getCarryForward()));
			}

			/* _______________ footer _______________ */
//			Int0802Vo footer = new Int0802Vo();
//			footer.setAccName("รวม");
//			footer.setDebit(sumDebit);
//			footer.setCredit(sumCredit);
//			footer.setCredit2(sumCredit2);
//			footer.setDebit2(sumDebit2);
//			footer.setCredit3(sumCredit3);
//			footer.setDebit3(sumDebit3);
//			footer.setBringForward(sumBringForward);
//			footer.setCarryForward(sumCarryForward);
//			footer.setDiffCredit(sumDiffCredit);
//			footer.setDiffDebit(sumDiffDebit);
//			response.add(footer);
		}
		return response;
	}

}
