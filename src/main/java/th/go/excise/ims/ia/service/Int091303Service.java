package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.ia.persistence.entity.IaUtilityBudget;
import th.go.excise.ims.ia.persistence.repository.IaUtilityBudgetRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0913JdbcRepository;
import th.go.excise.ims.ia.vo.Int091301ResultSearchVo;
import th.go.excise.ims.ia.vo.Int091301SearchVo;
import th.go.excise.ims.ia.vo.Int091303ResultVo;
import th.go.excise.ims.ia.vo.Int091303SaveVo;
import th.go.excise.ims.ia.vo.Int091303SearchVo;

@Service
public class Int091303Service {

	private static final Logger logger = LoggerFactory.getLogger(Int091303Service.class);
	
	@Autowired
	private Int0913JdbcRepository int0913JdbcRepository;
	
	@Autowired
	private IaUtilityBudgetRepository iaUtilityBudgetRepository;

	public List<Int091303ResultVo> int091303SearchVo(Int091303SearchVo vo) {
		logger.info("int091303SearchVo ==> quarter = {} && budgetYear = {}" , vo.getQuarter() , vo.getBudgetYear());
		List<Int091303ResultVo> voView =  new ArrayList<>();
		Long quarter = vo.getQuarter();
		String from = null;
		String to = null;
		if (quarter.longValue() == 1) {
			from = vo.getBudgetYear() + ProjectConstants.QUARTER.Q1[0];
			to = vo.getBudgetYear() + ProjectConstants.QUARTER.Q1[2];
		} else if (quarter.longValue() == 2) {
			from = vo.getBudgetYear() + ProjectConstants.QUARTER.Q2[0];
			to = vo.getBudgetYear() + ProjectConstants.QUARTER.Q2[2];
		} else if (quarter.longValue() == 3) {
			from = vo.getBudgetYear() + ProjectConstants.QUARTER.Q3[0];
			to = vo.getBudgetYear() + ProjectConstants.QUARTER.Q3[2];
		} else if (quarter.longValue() == 4) {
			from = vo.getBudgetYear() + ProjectConstants.QUARTER.Q4[0];
			to = vo.getBudgetYear() + ProjectConstants.QUARTER.Q4[2];
		}
		Int091301SearchVo searchVo = new Int091301SearchVo();
		searchVo.setMonthWdPayFrom(from);
		searchVo.setMonthWdPayTo(to);
		List<Int091301ResultSearchVo> int091301ResultSearchVoList = int0913JdbcRepository.findIaUtilityBillByCriteria(searchVo);
		Map<String, Int091303ResultVo> map = new HashMap<>();
		Set<String> listOfKey = new HashSet<String>();
		Int091303ResultVo iVo = null;
		for (Int091301ResultSearchVo int091301ResultSearchVo : int091301ResultSearchVoList) {
			iVo = new Int091303ResultVo();
			listOfKey.add(int091301ResultSearchVo.getMonthWdPay());
			iVo = map.get(int091301ResultSearchVo.getMonthWdPay());
			if (iVo == null) {
				iVo = new Int091303ResultVo();
				iVo.setBillType1Amt(BigDecimal.ZERO);
				iVo.setBillType2Amt(BigDecimal.ZERO);
				iVo.setBillType3Amt(BigDecimal.ZERO);
				iVo.setBillType4Amt(BigDecimal.ZERO);
				iVo.setBillType5Amt(BigDecimal.ZERO);
				iVo.setBillType6Amt(BigDecimal.ZERO);
				iVo.setBillType7Amt(BigDecimal.ZERO);
				iVo.setSumBillAmt(BigDecimal.ZERO);
			}
			BigDecimal netAmtValue = int091301ResultSearchVo.getReqNetAmt() != null ? int091301ResultSearchVo.getReqNetAmt() : BigDecimal.ZERO;
			if ("1".equals(int091301ResultSearchVo.getUbillType())) {
				iVo.setBillType1Amt(iVo.getBillType1Amt().add(netAmtValue));
			} else if ("2".equals(int091301ResultSearchVo.getUbillType())) {
				iVo.setBillType2Amt(iVo.getBillType2Amt().add(netAmtValue));
			} else if ("3".equals(int091301ResultSearchVo.getUbillType())) {
				iVo.setBillType2Amt(iVo.getBillType3Amt().add(netAmtValue));
			} else if ("4".equals(int091301ResultSearchVo.getUbillType())) {
				iVo.setBillType2Amt(iVo.getBillType4Amt().add(netAmtValue));
			} else if ("5".equals(int091301ResultSearchVo.getUbillType())) {
				iVo.setBillType2Amt(iVo.getBillType5Amt().add(netAmtValue));
			} else if ("6".equals(int091301ResultSearchVo.getUbillType())) {
				iVo.setBillType2Amt(iVo.getBillType6Amt().add(netAmtValue));
			} else if ("7".equals(int091301ResultSearchVo.getUbillType())) {
				iVo.setBillType2Amt(iVo.getBillType7Amt().add(netAmtValue));
			}
			iVo.setSumBillAmt(int091301ResultSearchVo.getReqNetAmt());
			map.put(int091301ResultSearchVo.getMonthWdPay(), iVo);
		}
		
		for (String key : listOfKey) {
			voView.add(map.get(key));
		}
		return voView;
 	}
	
	
	public IaUtilityBudget save(Int091303SaveVo int091303SaveVo) {
		IaUtilityBudget iaUtilityBudget = null;
		if(int091303SaveVo.getUtilityBudgetSeq() != null) {
			iaUtilityBudget = iaUtilityBudgetRepository.findById(int091303SaveVo.getUtilityBudgetSeq()).get();
		}
		
		if(iaUtilityBudget == null ) {
			iaUtilityBudget = new IaUtilityBudget();
		}
		iaUtilityBudget.setExciseCode(int091303SaveVo.getExciseCode());
		iaUtilityBudget.setUbudgetQ(int091303SaveVo.getUbudgetQ());
		iaUtilityBudget.setBudgetAmt(int091303SaveVo.getBudgetAmt());
		iaUtilityBudget.setNonBudgetAmt(int091303SaveVo.getNonBudgetAmt());
		return iaUtilityBudgetRepository.save(iaUtilityBudget);
	}
	
	
}
