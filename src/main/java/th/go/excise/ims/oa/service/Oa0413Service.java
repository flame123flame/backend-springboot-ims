package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaAlcohol;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholDtl;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaAlcoholDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaAlcoholRepository;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0413JdbcRepository;
import th.go.excise.ims.oa.vo.Oa041301ApproveVo;
import th.go.excise.ims.oa.vo.Oa041301CheckerVo;
import th.go.excise.ims.oa.vo.Oa041301FormVo;
import th.go.excise.ims.oa.vo.Oa041301Vo;
import th.go.excise.ims.oa.vo.Oa0413ApproveVo;
import th.go.excise.ims.oa.vo.Oa0413Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Oa0413Service {

	@Autowired
	private Oa0413JdbcRepository oa0413JdbcRep;

	@Autowired
	private OaPlanRepository oaPlanRep;

	@Autowired
	private OaLicensePlanRepository oaLicensePlanRep;
	
	@Autowired
	private OaAlcoholRepository oaAlcoholRep;
	
	@Autowired
	private OaAlcoholDtlRepository oaAlcoholDtlRep;

	public List<Oa0413Vo> findByBudgetYear(String budgetYear, String offCode) {
		List<Oa0413Vo> datas = oa0413JdbcRep.findByBudgetYear(budgetYear, offCode);
		for (Oa0413Vo data : datas) {
			data.setCompanies(oa0413JdbcRep.findByPlanId(data.getId()));
		}
		return datas;
	}

	public List<Oa0413ApproveVo> findApproveByBudgetYear(String budgetYear, String offCode) {
		List<Oa0413ApproveVo> datas = oa0413JdbcRep.findApproveByBudgetYear(budgetYear, offCode);
		for (Oa0413ApproveVo data : datas) {
			String officeCode = data.getSectorName();
			data.setSectorName(findSectorName(officeCode));
			data.setAreaName(findAreaName(officeCode));
			data.setCompanies(oa0413JdbcRep.findByPlanId(data.getId()));
		}
		return datas;
	}

	public Oa041301Vo findPlan(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaPlan> plan = oaPlanRep.findById(id);
		Oa041301Vo data = new Oa041301Vo();
		if (plan.isPresent()) {
			data.setStatus(plan.get().getStatus());
			data.setRemark(plan.get().getRemark());
			List<Oa041301ApproveVo> approves = oa0413JdbcRep.findApproveList(id);
			for (Oa041301ApproveVo approve : approves) {
				String officeCode = approve.getSectorName();
				approve.setSectorName(findSectorName(officeCode));
				approve.setAreaName(findAreaName(officeCode));
			}
			List<Oa041301CheckerVo> checkers = oa0413JdbcRep.findChecker(id);
			data.setCheckers(checkers);
			data.setApproves(approves);
		}
		return data;
	}

	@Transactional
	public void updatePlan(String idStr, String status, Oa041301FormVo request) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaPlan> plan = oaPlanRep.findById(id);
		if (plan.isPresent()) {
			plan.get().setRemark(request.getRemark());
			plan.get().setStatus(status);
			oaPlanRep.save(plan.get());
			List<OaLicensePlan> datas = oaLicensePlanRep.findByoaPlanIdAndIsDeleted(plan.get().getOaPlanId(),
					FLAG.N_FLAG);
			for (OaLicensePlan data : datas) {
				if ("3".equalsIgnoreCase(status.trim())) { // IF STATUS == '3'
					// TODO
					OaAlcohol alcoho = new OaAlcohol();
					alcoho.setOaPlanId(data.getOaPlanId());
					alcoho.setLicenseId(data.getLicenseId());
					alcoho = oaAlcoholRep.save(alcoho);
					OaAlcoholDtl alcohoDtl = new OaAlcoholDtl();
					alcohoDtl.setOaAlcoholId(alcoho.getOaAlcoholId());
					alcohoDtl = oaAlcoholDtlRep.save(alcohoDtl);
				}
				data.setStatus(status);
			}
			oaLicensePlanRep.saveAll(datas);
		}
	}

	private static String findSectorName(String officeCode) {
		List<ExciseDepartment> sectors = ApplicationCache.getExciseSectorList();
		for (ExciseDepartment sector : sectors) {
			if (StringUtils.isNotBlank(sector.getOfficeCode())) {
				if (sector.getOfficeCode().trim().substring(0, 2).equals(officeCode.trim().substring(0, 2))) {
					return sector.getDeptName();
				}
			}
		}
		return "";
	}

	private static String findAreaName(String officeCode) {
		return ApplicationCache.getExciseDepartment(officeCode).getDeptName();
	}

}
