package th.go.excise.ims.ia.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.persistence.entity.IaExpensesD1;
import th.go.excise.ims.ia.persistence.repository.IaExpensesD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaExpensesRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaChartOfAccJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesD1JdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesJdbcRepository;
import th.go.excise.ims.ia.vo.Int090101Vo;
import th.go.excise.ims.ia.vo.Int12040101FindVo;
import th.go.excise.ims.ia.vo.Int12040101SaveFormVo;
import th.go.excise.ims.ia.vo.Int12040101ValidateSearchFormVo;

@Service
public class Int12040101Service {

//	@Autowired
//	private IaChartOfAccRepository iaChartOfAccRepository;
	private static final Logger logger = LoggerFactory.getLogger(Int12040101Service.class);

	@Autowired
	private IaExpensesRepository iaExpensesRepository;

	@Autowired
	private IaExpensesJdbcRepository iaExpensesJdbcRepository;

	@Autowired
	private IaChartOfAccJdbcRepository iaChartOfAccJdbcRepository;

	@Autowired
	private IaExpensesD1Repository iaExpensesD1Repository;

	@Autowired
	private IaExpensesD1JdbcRepository iaExpensesD1JdbcRepository;

	public List<IaChartOfAcc> findAll() {
		List<IaChartOfAcc> data = new ArrayList<>();
//		data = iaChartOfAccRepository.findAll();
		data = iaChartOfAccJdbcRepository.findAll();
		for (IaChartOfAcc iaChartOfAccData : data) {
			iaChartOfAccData.setCreatedBy(null);
			iaChartOfAccData.setCreatedDate(null);
			iaChartOfAccData.setUpdatedBy(null);
			iaChartOfAccData.setUpdatedDate(null);
			iaChartOfAccData.setVersion(null);
		}
		return data;
	}

	@Transactional
	public void saveExpenses(Int12040101SaveFormVo form) {
		IaExpenses data = new IaExpenses();
		if (form.getId() != null) {
			data = iaExpensesRepository.findById(form.getId()).get();
		}
		data.setAccountId(form.getAccountId());
		data.setAccountName(form.getAccountName());
		data.setServiceReceive(form.getServiceReceive());
		data.setServiceWithdraw(form.getServiceWithdraw());
		data.setServiceBalance(form.getServiceBalance());
		data.setSuppressReceive(form.getSuppressReceive());
		data.setSuppressWithdraw(form.getSuppressWithdraw());
		data.setSuppressBalance(form.getSuppressBalance());
		data.setBudgetReceive(form.getBudgetReceive());
		data.setBudgetWithdraw(form.getBudgetWithdraw());
		data.setBudgetBalance(form.getBudgetBalance());
		data.setSumReceive(form.getSumReceive());
		data.setSumWithdraw(form.getSumWithdraw());
		data.setSumBalance(form.getSumBalance());
		data.setMoneyBudget(form.getMoneyBudget());
		data.setMoneyOut(form.getMoneyOut());
		data.setAverageCost(form.getAverageCost());
		data.setAverageGive(form.getAverageGive());
		data.setAverageFrom(form.getAverageFrom());
		data.setAverageComeCost(form.getAverageComeCost());
		data.setNote(form.getNote());
		data.setOfficeCode(form.getOfficeCode());
		data.setOfficeDesc(form.getOfficeDesc());

		data.setAverageCostOut(form.getAverageCostOut());
		data.setAverageGiveOut(form.getAverageGiveOut());
		data.setAverageFromOut(form.getAverageFromOut());
		data.setAverageComeCostOut(form.getAverageComeCostOut());

		String month = form.getExpenseDateStr().split("/")[0];
		String year = form.getExpenseDateStr().split("/")[1];
		year = Long.toString(Long.parseLong(year) - 543);
		data.setExpenseMonth(month);
		data.setExpenseYear(year);
		data.setExpenseDate(ConvertDateUtils.parseStringToDate(form.getExpenseDateStr(), ConvertDateUtils.MM_YYYY,
				ConvertDateUtils.LOCAL_TH));
		String budgetyear = year;
		if (Long.parseLong(month) > 10) {
			budgetyear = Long.toString(Long.parseLong(year) + 1);
		}
		data.setBudgetYear(budgetyear);
		iaExpensesRepository.save(data);
		IaExpensesD1 detailSave = null;

		for (String id : form.getDeleteId()) {
			iaExpensesD1Repository.deleteById(Long.valueOf(id));
		}

		Long index = 0l;
		for (IaExpensesD1 iaExpensesD1 : form.getIaExpensesD1()) {
			detailSave = new IaExpensesD1();
			if (iaExpensesD1.getId() != null) {
				detailSave = iaExpensesD1Repository.findById(iaExpensesD1.getId()).get();
			}

			// data
			detailSave.setDetailSeqNo(index);
			detailSave.setAverageCost(Double.valueOf(iaExpensesD1.getAverageCost()));
			detailSave.setAverageGive(iaExpensesD1.getAverageGive());
			detailSave.setAverageFrom(Double.valueOf(iaExpensesD1.getAverageFrom()));
			detailSave.setAverageComeCost(iaExpensesD1.getAverageComeCost());
			detailSave.setMoneyBudgetType(iaExpensesD1.getMoneyBudgetType());
//			private String note;

			// key
			detailSave.setOfficeCode(form.getOfficeCode());
			detailSave.setBudgetYear(budgetyear);
			detailSave.setExpenseYear(year);
			detailSave.setExpenseMonth(month);
			detailSave.setAccountId(form.getAccountId());
			iaExpensesD1Repository.save(detailSave);
			index++;
		}
	}

	public Int12040101FindVo findExpensesById(BigDecimal id) {
		IaExpenses entity = new IaExpenses();
		Int12040101FindVo dataRes = new Int12040101FindVo();
		entity = iaExpensesRepository.findById(id).get();
		try {
			BeanUtils.copyProperties(dataRes, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}
		List<IaExpensesD1> detail = iaExpensesD1JdbcRepository.findDetail(entity.getOfficeCode(),
				entity.getBudgetYear(), entity.getExpenseMonth(), entity.getExpenseYear(), entity.getAccountId());
		dataRes.setIaExpensesD1(detail);
		return dataRes;
	}

	public Int12040101FindVo findValidate(Int12040101ValidateSearchFormVo formReq) {
		Int090101Vo entity = new Int090101Vo();
		Date date = ConvertDateUtils.parseStringToDate(formReq.getExpenseDateStr(), ConvertDateUtils.MM_YYYY,
				ConvertDateUtils.LOCAL_TH);
		formReq.setExpenseDateStr(
				ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_EN));
		List<Int090101Vo> list = iaExpensesJdbcRepository.findValidate(formReq);
		if (list.size() > 0) {
			entity = list.get(0);
		}
		Int12040101FindVo dataRes = new Int12040101FindVo();
		try {
			BeanUtils.copyProperties(dataRes, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}
		List<IaExpensesD1> detail = iaExpensesD1JdbcRepository.findDetail(entity.getOfficeCode(),
				entity.getBudgetYear(), entity.getExpenseMonth(), entity.getExpenseYear(), entity.getAccountId());
		dataRes.setIaExpensesD1(detail);
		return dataRes;
	}
}
