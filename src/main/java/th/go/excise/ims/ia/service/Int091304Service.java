package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaUtilityBill;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaUtilityBillJdbcRepository;
import th.go.excise.ims.ia.vo.Int091304Quarter;
import th.go.excise.ims.ia.vo.Int091304SearchVo;
import th.go.excise.ims.ia.vo.Int091304Vo;

@Service
public class Int091304Service {

	@Autowired
	private IaUtilityBillJdbcRepository iaUtilityBillJdbcRepository;

	public Int091304Vo filterByBudgetYear(Int091304SearchVo formVo) {
		List<Int091304Quarter> dataList = new ArrayList<Int091304Quarter>();
		Int091304Quarter obj = null;
		/* __________ default variable __________ */
		/* Q1 */
		BigDecimal sumOct = BigDecimal.ZERO;
		BigDecimal sumNov = BigDecimal.ZERO;
		BigDecimal sumDec = BigDecimal.ZERO;
		/* Q2 */
		BigDecimal sumJan = BigDecimal.ZERO;
		BigDecimal sumFeb = BigDecimal.ZERO;
		BigDecimal sumMar = BigDecimal.ZERO;
		/* Q3 */
		BigDecimal sumApr = BigDecimal.ZERO;
		BigDecimal sumMay = BigDecimal.ZERO;
		BigDecimal sumJun = BigDecimal.ZERO;
		/* Q4 */
		BigDecimal sumJul = BigDecimal.ZERO;
		BigDecimal sumAug = BigDecimal.ZERO;
		BigDecimal sumSep = BigDecimal.ZERO;
		
		
		List<ParamInfo> ubillTypeList = ApplicationCache.getParamInfoListByGroupCode(IaConstants.UTILITY_BILL_TYPE.PARAM_GROUP_CODE);

		formVo.setMonthWdPayFrom((Integer.parseInt(formVo.getBudgetYear()) - 1) + ProjectConstants.QUARTER.Q1[0]);
		formVo.setMonthWdPayTo(formVo.getBudgetYear() + ProjectConstants.QUARTER.Q4[2]);
		List<IaUtilityBill> dataFind = iaUtilityBillJdbcRepository.findQuarter(formVo);

		if(dataFind.size() > 0) {
			Map<String, BigDecimal> mapValue = new HashMap<>();
			for (IaUtilityBill value : dataFind) {
				mapValue.put(value.getUbillType() + value.getMonthWdPay(), value.getReqWdAmt());
			}

			for (ParamInfo paramGroup : ubillTypeList) {
				obj = new Int091304Quarter();
				/* ______ set type ______ */
				obj.setUbillType(paramGroup.getParamCode());
				obj.setUbillTypeStr(paramGroup.getValue1());
				/* ________________ Q1 ________________ */
				obj.setQ1Oct(
						mapValue.get(paramGroup.getParamCode() + (Integer.parseInt(formVo.getBudgetYear()) - 1) + ProjectConstants.QUARTER.Q1[0]));
				obj.setQ1Nov(
						mapValue.get(paramGroup.getParamCode() + (Integer.parseInt(formVo.getBudgetYear()) - 1) + ProjectConstants.QUARTER.Q1[1]));
				obj.setQ1Dec(
						mapValue.get(paramGroup.getParamCode() + (Integer.parseInt(formVo.getBudgetYear()) - 1) + ProjectConstants.QUARTER.Q1[2]));
				obj.setQ1Total(calculateNull(obj.getQ1Oct(), obj.getQ1Nov(), obj.getQ1Dec()));
				/* total sum month Q1 */
				sumOct = sumOct.add(calculateNull(obj.getQ1Oct(), null, null));
				sumNov = sumNov.add(calculateNull(obj.getQ1Nov(), null, null));
				sumDec = sumDec.add(calculateNull(obj.getQ1Dec(), null, null));

				/* ________________ Q2 ________________ */
				obj.setQ2Jan(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q2[0]));
				obj.setQ2Feb(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q2[1]));
				obj.setQ2Mar(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q2[2]));
				obj.setQ2Total(calculateNull(obj.getQ2Jan(), obj.getQ2Feb(), obj.getQ2Mar()));
				/* total sum month Q2 */
				sumJan = sumJan.add(calculateNull(obj.getQ2Jan(), null, null));
				sumFeb = sumFeb.add(calculateNull(obj.getQ2Feb(), null, null));
				sumMar = sumMar.add(calculateNull(obj.getQ2Mar(), null, null));

				/* ________________ Q3 ________________ */
				obj.setQ3Apr(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q3[0]));
				obj.setQ3May(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q3[1]));
				obj.setQ3Jun(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q3[2]));
				obj.setQ3Total(calculateNull(obj.getQ3Apr(), obj.getQ3May(), obj.getQ3Jun()));
				/* total sum month Q3 */
				sumApr = sumApr.add(calculateNull(obj.getQ3Apr(), null, null));
				sumMay = sumMay.add(calculateNull(obj.getQ3May(), null, null));
				sumJun = sumJun.add(calculateNull(obj.getQ3Jun(), null, null));
				
				/* ________________ Q4 ________________ */
				obj.setQ4Jul(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q4[0]));
				obj.setQ4Aug(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q4[1]));
				obj.setQ4Sep(mapValue.get(paramGroup.getParamCode() + formVo.getBudgetYear() + ProjectConstants.QUARTER.Q4[2]));
				obj.setQ4Total(calculateNull(obj.getQ4Jul(), obj.getQ4Aug(), obj.getQ4Sep()));
				/* total sum month Q4 */
				sumJul= sumJul.add(calculateNull(obj.getQ4Jul(), null, null));
				sumAug = sumAug.add(calculateNull(obj.getQ4Aug(), null, null));
				sumSep = sumSep.add(calculateNull(obj.getQ4Sep(), null, null));

				dataList.add(obj);
			}
		}
		
//		footer summary month
		obj = new Int091304Quarter();
		 obj.setUbillTypeStr("รวม");
		 /* footer Q1 */
		 obj.setQ1Oct(sumOct);
		 obj.setQ1Nov(sumNov);
		 obj.setQ1Dec(sumDec);
		 obj.setQ1Total(sumOct.add(sumNov).add(sumDec));
		 /* footer Q2 */
		 obj.setQ2Jan(sumJan);
		 obj.setQ2Feb(sumFeb);
		 obj.setQ2Mar(sumMar);
		 obj.setQ2Total(sumJan.add(sumFeb).add(sumMar));
		 /* footer Q3 */
		 obj.setQ3Apr(sumApr);
		 obj.setQ3May(sumMay);
		 obj.setQ3Jun(sumJun);
		 obj.setQ3Total(sumApr.add(sumMay).add(sumJun));
		 /* footer Q2 */
		 obj.setQ4Jul(sumJul);
		 obj.setQ4Aug(sumAug);
		 obj.setQ4Sep(sumSep);
		 obj.setQ4Total(sumJul.add(sumAug).add(sumSep));
		
		 dataList.add(obj);

		Int091304Vo response = new Int091304Vo();
		response.setQuarter(dataList);
		return response;
	}

	private BigDecimal calculateNull(BigDecimal value1, BigDecimal value2, BigDecimal value3) {
		if (value1 == null) {
			value1 = BigDecimal.ZERO;
		}
		if (value2 == null) {
			value2 = BigDecimal.ZERO;
		}
		if (value3 == null) {
			value3 = BigDecimal.ZERO;
		}
		return value1.add(value2).add(value3);
	}

}
