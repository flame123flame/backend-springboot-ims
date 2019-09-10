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
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaLubricants;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0208JdbcRepository;
import th.go.excise.ims.oa.vo.Oa020801ApproveVo;
import th.go.excise.ims.oa.vo.Oa020801CheckerVo;
import th.go.excise.ims.oa.vo.Oa020801FormVo;
import th.go.excise.ims.oa.vo.Oa020801Vo;
import th.go.excise.ims.oa.vo.Oa0208ApproveVo;
import th.go.excise.ims.oa.vo.Oa0208Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Oa0208Service {

	@Autowired
	private Oa0208JdbcRepository oa0208JdbcRep;

	@Autowired
	private OaPlanRepository oaPlanRep;

	@Autowired
	private OaLicensePlanRepository oaLicensePlanRep;

	@Autowired
	private OaLubricantsRepository oaLubricantsRep;

	@Autowired
	private OaLubricantsDtlRepository oaLubricantsDtlRep;

	public List<Oa0208Vo> findByBudgetYear(String budgetYear, String offCode) {
		List<Oa0208Vo> datas = oa0208JdbcRep.findByBudgetYear(budgetYear, offCode);
		for (Oa0208Vo data : datas) {
			data.setCompanies(oa0208JdbcRep.findByPlanId(data.getId()));
		}
		return datas;
	}

	public List<Oa0208ApproveVo> findApproveByBudgetYear(String budgetYear, String offCode) {
		List<Oa0208ApproveVo> datas = oa0208JdbcRep.findApproveByBudgetYear(budgetYear, offCode);
		for (Oa0208ApproveVo data : datas) {
			String officeCode = data.getSectorName();
			data.setSectorName(findSectorName(officeCode));
			data.setAreaName(findAreaName(officeCode));
			data.setCompanies(oa0208JdbcRep.findByPlanId(data.getId()));
		}
		return datas;
	}

	public Oa020801Vo findPlan(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaPlan> plan = oaPlanRep.findById(id);
		Oa020801Vo data = new Oa020801Vo();
		if (plan.isPresent()) {
			data.setStatus(plan.get().getStatus());
			data.setRemark(plan.get().getRemark());
			List<Oa020801ApproveVo> approves = oa0208JdbcRep.findApproveList(id);
			for (Oa020801ApproveVo approve : approves) {
				String officeCode = approve.getSectorName();
				approve.setSectorName(findSectorName(officeCode));
				approve.setAreaName(findAreaName(officeCode));
			}
			List<Oa020801CheckerVo> checkers = oa0208JdbcRep.findChecker(id);
			data.setCheckers(checkers);
			data.setApproves(approves);
		}
		return data;
	}

	@Transactional
	public void updatePlan(String idStr, String status, Oa020801FormVo request) {
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
					Optional<OaLubricants> hydcaOpt = oaLubricantsRep.findByLicenseIdAndOaPlanIdAndIsDeleted(
							data.getOaPlanId(), data.getLicenseId(), FLAG.N_FLAG);
					if (!hydcaOpt.isPresent()) {
						OaLubricants lubri = new OaLubricants();
						lubri.setOaPlanId(data.getOaPlanId());
						lubri.setLicenseId(data.getLicenseId());
						lubri = oaLubricantsRep.save(lubri);
						OaLubricantsDtl lubriDtl = new OaLubricantsDtl();
						lubriDtl.setOaLubricantsId(lubri.getOaLubricantsId());
						lubriDtl = oaLubricantsDtlRep.save(lubriDtl);
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
