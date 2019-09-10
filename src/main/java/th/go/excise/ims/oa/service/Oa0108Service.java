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
import th.go.excise.ims.oa.persistence.entity.OaHydrocarb;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbRepository;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0108JdbcRepository;
import th.go.excise.ims.oa.vo.Oa010801ApproveVo;
import th.go.excise.ims.oa.vo.Oa010801CheckerVo;
import th.go.excise.ims.oa.vo.Oa010801FormVo;
import th.go.excise.ims.oa.vo.Oa010801Vo;
import th.go.excise.ims.oa.vo.Oa0108ApproveVo;
import th.go.excise.ims.oa.vo.Oa0108Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Oa0108Service {

	@Autowired
	private Oa0108JdbcRepository oa0108JdbcRep;

	@Autowired
	private OaPlanRepository oaPlanRep;

	@Autowired
	private OaLicensePlanRepository oaLicensePlanRep;

	@Autowired
	private OaHydrocarbRepository oaHydrocarbRep;

	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRep;

	public List<Oa0108Vo> findByBudgetYear(String budgetYear, String offCode) {
		List<Oa0108Vo> datas = oa0108JdbcRep.findByBudgetYear(budgetYear, offCode);
		for (Oa0108Vo data : datas) {
			data.setCompanies(oa0108JdbcRep.findByPlanId(data.getId()));
		}
		return datas;
	}

	public List<Oa0108ApproveVo> findApproveByBudgetYear(String budgetYear, String offCode) {
		List<Oa0108ApproveVo> datas = oa0108JdbcRep.findApproveByBudgetYear(budgetYear, offCode);
		for (Oa0108ApproveVo data : datas) {
			String officeCode = data.getSectorName();
			data.setSectorName(findSectorName(officeCode));
			data.setAreaName(findAreaName(officeCode));
			data.setCompanies(oa0108JdbcRep.findByPlanId(data.getId()));
		}
		return datas;
	}

	public Oa010801Vo findPlan(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaPlan> plan = oaPlanRep.findById(id);
		Oa010801Vo data = new Oa010801Vo();
		if (plan.isPresent()) {
			data.setStatus(plan.get().getStatus());
			data.setRemark(plan.get().getRemark());
			List<Oa010801ApproveVo> approves = oa0108JdbcRep.findApproveList(id);
			for (Oa010801ApproveVo approve : approves) {
				String officeCode = approve.getSectorName();
				approve.setSectorName(findSectorName(officeCode));
				approve.setAreaName(findAreaName(officeCode));
			}
			List<Oa010801CheckerVo> checkers = oa0108JdbcRep.findChecker(id);
			data.setCheckers(checkers);
			data.setApproves(approves);
		}
		return data;
	}

	@Transactional
	public void updatePlan(String idStr, String status, Oa010801FormVo request) {
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
					Optional<OaHydrocarb> hydcaOpt = oaHydrocarbRep.findByLicenseIdAndOaPlanIdAndIsDeleted(data.getOaPlanId(), data.getLicenseId(), FLAG.N_FLAG);
					if (!hydcaOpt.isPresent()) {
						OaHydrocarb hydca = new OaHydrocarb();
						hydca.setOaPlanId(data.getOaPlanId());
						hydca.setLicenseId(data.getLicenseId());
						hydca = oaHydrocarbRep.save(hydca);
						OaHydrocarbDtl hydcaDtl = new OaHydrocarbDtl();
						hydcaDtl.setOaHydrocarbId(hydca.getOaHydrocarbId());
						hydcaDtl = oaHydrocarbDtlRep.save(hydcaDtl);
					}
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
