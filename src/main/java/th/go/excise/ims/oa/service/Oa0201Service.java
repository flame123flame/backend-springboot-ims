package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaPersonApprovePlan;
import th.go.excise.ims.oa.persistence.entity.OaPersonAuditPlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaHydCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPersonApprovePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPersonAuditPlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0201JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0201001Vo;
import th.go.excise.ims.oa.vo.Oa020103Vo;
import th.go.excise.ims.oa.vo.Oa0201FromVo;

@Service
public class Oa0201Service {
	
	@Autowired
	private OaPlanRepository oaplanRepo;
	
	@Autowired
	private OaLicensePlanRepository oaLicensePlanRepo;
 	
	@Autowired
	private OaPersonAuditPlanRepository oaPersonAuditPlanRepo;
	
	@Autowired
	private OaPersonApprovePlanRepository oaPersonApprovePlanRepo;
	
	@Autowired
	private Oa0201JdbcRepository oa0201jdbcRepo;
	
	@Autowired
	private OaHydCustomerLicenRepository oaHydCustomerLicenRepo;
	
	public Oa0201FromVo saveOAPlan(Oa0201FromVo request,String officeCode) {
//		BigDecimal id = new BigDecimal();
		Oa0201FromVo resp = new Oa0201FromVo();
		OaPlan plan = new OaPlan();

		plan.setAuditStart(request.getDateFrom());
		plan.setAuditEnd(request.getDateTo());
		plan.setFiscolYear(request.getFiscolYear());
		plan.setStatus("1");
		plan.setOfficeCode(officeCode);
		plan = oaplanRepo.save(plan);
		
		List<OaLicensePlan> licenseCompany = new ArrayList<OaLicensePlan>();
		for (int i = 0; i < request.getListCompany().size(); i++) {
			OaLicensePlan license = new OaLicensePlan();
			license.setAuditStart(request.getDateFrom());
			license.setAuditEnd(request.getDateTo());
			license.setFiscolYear(request.getFiscolYear());
			license.setStatus("1");
			license.setOaPlanId(plan.getOaPlanId());
			license.setLicenseId(request.getListCompany().get(i).getOaCuslicenseId());
//			license.setOaLicensePlanId(request.getListCompany().get(i).getOaCuslicenseId());
			license.setOfficeCode(officeCode);
			licenseCompany.add(license);
		}
		

		licenseCompany = (List<OaLicensePlan>) oaLicensePlanRepo.saveAll(licenseCompany);
		
		List<OaPersonAuditPlan> listAuditer = new ArrayList<OaPersonAuditPlan>();
		for (int i = 0; i < request.getListAuditer().size(); i++) {
			OaPersonAuditPlan person = new OaPersonAuditPlan();
			person.setOaPlanId(plan.getOaPlanId());
			person.setOaPersonId(request.getListAuditer().get(i).getWsUserId());
			person.setUserId(request.getListAuditer().get(i).getUserId());
			listAuditer.add(person);
		}
		listAuditer = (List<OaPersonAuditPlan>) oaPersonAuditPlanRepo.saveAll(listAuditer);
		
		List<OaPersonApprovePlan> userApprover = new ArrayList<OaPersonApprovePlan>();
		String officeCodeStr = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa020103Vo> listApprover = oa0201jdbcRepo.findUserApprover(officeCodeStr);
		for (int i = 0; i < listApprover.size(); i++) {
			OaPersonApprovePlan person = new OaPersonApprovePlan();
			person.setOaPalnId(plan.getOaPlanId());
			person.setOaPersonId(listApprover.get(i).getWsUserId());
			person.setUserId(listApprover.get(i).getUserId());
			userApprover.add(person);
		}
		userApprover =  (List<OaPersonApprovePlan>) oaPersonApprovePlanRepo.saveAll(userApprover);
		
		return resp;
		
	}
	
	public Oa0201FromVo updateOAPlan(Oa0201FromVo request,String officeCode) {
//		BigDecimal id = new BigDecimal();
		Oa0201FromVo resp = new Oa0201FromVo();
		
		List<OaLicensePlan> compareCompany = oaLicensePlanRepo.findByoaPlanIdAndIsDeleted(new BigDecimal(request.getPlanId()), "N");
		List<OaLicensePlan> listCompanyDelete = new ArrayList<>();
		int flag = 0;
		for (int i = 0; i < compareCompany.size(); i++) {
			for (int j = 0; j < request.getListCompany().size(); j++) {
				flag = 0;
				if (request.getListCompany().get(j).getLicensePlanId() != null ) {
					if (compareCompany.get(i).getOaLicensePlanId().equals(request.getListCompany().get(j).getLicensePlanId())) {
//						listCompanyDelete.add(compareCompany.get(i));
						flag =1;
						break;
					}
				}else {
					flag =1;
				}
				
			}
			if (flag ==0 ) {
				compareCompany.get(i).setIsDeleted("Y");
				listCompanyDelete.add(compareCompany.get(i));
			}
		}

		List<OaLicensePlan> licenseCompany = new ArrayList<OaLicensePlan>();
		for (int i = 0; i < request.getListCompany().size(); i++) {
			OaLicensePlan license = new OaLicensePlan();
			license.setAuditStart(request.getDateFrom());
			license.setAuditEnd(request.getDateTo());
			license.setFiscolYear(request.getFiscolYear());
			license.setStatus("1");
			license.setLicenseId(request.getListCompany().get(i).getOaCuslicenseId());
			license.setOfficeCode(officeCode);
			license.setOaPlanId(new BigDecimal(request.getPlanId()));
			if(request.getListCompany().get(i).getLicensePlanId() == null) {
//				license.setOaLicensePlanId(request.getListCompany().get(i).getLicensePlanId());
				licenseCompany.add(license);
			}

		}
	
		licenseCompany = (List<OaLicensePlan>) oaLicensePlanRepo.saveAll(licenseCompany);
		licenseCompany = (List<OaLicensePlan>) oaLicensePlanRepo.saveAll(listCompanyDelete);
		
		
		List<OaPersonAuditPlan> compareAuditer = oaPersonAuditPlanRepo.findByoaPlanIdAndIsDeleted(new BigDecimal(request.getPlanId()), "N");
		List<OaPersonAuditPlan> listAuditerDelete = new ArrayList<>();
		
		for (int i = 0; i < compareAuditer.size(); i++) {
			for (int j = 0; j < request.getListAuditer().size(); j++) {
				flag = 0;
				if (request.getListAuditer().get(j).getOaPersonAuditPlanId() != null ) {
					if (compareAuditer.get(i).getOaPersonAuditPlanId().equals(request.getListAuditer().get(j).getOaPersonAuditPlanId())) {
						flag =1;
						break;
					}
				}else {
					flag =1;
				}
			}
			if (flag ==0 ) {
				compareAuditer.get(i).setIsDeleted("Y");
				listAuditerDelete.add(compareAuditer.get(i));
			}
		}

		
		List<OaPersonAuditPlan> listAuditer = new ArrayList<OaPersonAuditPlan>();
		for (int i = 0; i < request.getListAuditer().size(); i++) {
			OaPersonAuditPlan person = new OaPersonAuditPlan();
			person.setOaPlanId(new BigDecimal(request.getPlanId()));
			person.setOaPersonId(request.getListAuditer().get(i).getWsUserId());
			person.setUserId(request.getListAuditer().get(i).getUserId());
			if (request.getListAuditer().get(i).getOaPersonAuditPlanId() == null ) {
				listAuditer.add(person);
			}

		}
		listAuditer = (List<OaPersonAuditPlan>) oaPersonAuditPlanRepo.saveAll(listAuditer);
		listAuditer = (List<OaPersonAuditPlan>) oaPersonAuditPlanRepo.saveAll(listAuditerDelete);
		
		/*			List<OaPersonApprovePlan> userApprover = new ArrayList<OaPersonApprovePlan>();
		String officeCodeStr = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa020103Vo> listApprover = oa0201jdbcRepo.findUserApprover(officeCodeStr);
		for (int i = 0; i < listApprover.size(); i++) {
			OaPersonApprovePlan person = new OaPersonApprovePlan();
			person.setOaPalnId(new BigDecimal(request.getPlanId()));
			person.setOaPersonId(listApprover.get(i).getWsUserId());
			person.setUserId(listApprover.get(i).getUserId());
			userApprover.add(person);
		}
		userApprover =  (List<OaPersonApprovePlan>) oaPersonApprovePlanRepo.saveAll(userApprover);*/
		
		return resp;
		
	}
	
	public Oa0201FromVo findOaplan(String  planId,String officeCode) {
		BigDecimal id = new BigDecimal(planId);
		Oa0201FromVo resp = new Oa0201FromVo();
		OaPlan plan = new OaPlan();
		Optional<OaPlan> planOpt = oaplanRepo.findById(id);
		if (planOpt.isPresent()) {
			plan = planOpt.get();
			
			resp.setDateFrom(plan.getAuditStart());
			resp.setDateTo(plan.getAuditEnd());
			resp.setFiscolYear(plan.getFiscolYear());
			resp.setPlanId(planId);
			
			List<OaPersonAuditPlan> listAudit = oaPersonAuditPlanRepo.findByoaPlanIdAndIsDeleted(id, "N");
			
			List<Oa0201001Vo> listLicense = oa0201jdbcRepo.findLicenseByPlanId(id);
			List<Oa020103Vo> userAuditer=  oa0201jdbcRepo.findUserAuditerByPlanId(plan.getOfficeCode(),id);
		
			resp.setListAuditer(userAuditer);
			resp.setListCompany(listLicense);
//			String officeCodeStr = OaOfficeCode.officeCodeLike(plan.getOfficeCode());
		}
		return resp;
	}
	
	public Oa0201FromVo findOaplanHydro(String  planId,String officeCode) {
		BigDecimal id = new BigDecimal(planId);
		Oa0201FromVo resp = new Oa0201FromVo();
		OaPlan plan = new OaPlan();
		Optional<OaPlan> planOpt = oaplanRepo.findById(id);
		if (planOpt.isPresent()) {
			plan = planOpt.get();
			
			resp.setDateFrom(plan.getAuditStart());
			resp.setDateTo(plan.getAuditEnd());
			resp.setFiscolYear(plan.getFiscolYear());
			resp.setPlanId(planId);
			
			List<OaPersonAuditPlan> listAudit = oaPersonAuditPlanRepo.findByoaPlanIdAndIsDeleted(id, "N");
			
//			List<Oa0201001Vo> listLicense = oa0201jdbcRepo.findLicenseByPlanId(id);
			List<Oa0201001Vo> listLicense = oa0201jdbcRepo.findLicenseHydroByPlanId(id);
			List<Oa020103Vo> userAuditer=  oa0201jdbcRepo.findUserAuditerByPlanId(plan.getOfficeCode(),id);
		
			resp.setListAuditer(userAuditer);
			resp.setListCompany(listLicense);
//			String officeCodeStr = OaOfficeCode.officeCodeLike(plan.getOfficeCode());
		}
		return resp;
	}
	
	public List<Oa020103Vo> findApprover(String officeCode){
		List<Oa020103Vo> listApprover = new ArrayList<>();
		String officeCodeStr = OaOfficeCode.officeCodeLike(officeCode);
		listApprover = oa0201jdbcRepo.findUserApprover(officeCodeStr);
		return listApprover;
	}
	
	public Oa0201FromVo sendApprove(String planId) {
		BigDecimal id = new BigDecimal(planId);
		Oa0201FromVo resp = new Oa0201FromVo();
		OaPlan plan = new OaPlan();
		Optional<OaPlan> planOpt = oaplanRepo.findById(id);
		if (planOpt.isPresent()) {
			plan = planOpt.get();
			plan.setStatus("2");
			
			plan = oaplanRepo.save(plan);
			
			List<OaLicensePlan> licensePlans = oaLicensePlanRepo.findByoaPlanIdAndIsDeleted(id, "N");
			
			for (OaLicensePlan oaLicensePlan : licensePlans) {
				oaLicensePlan.setStatus("2");
			}
			licensePlans = (List<OaLicensePlan>) oaLicensePlanRepo.saveAll(licensePlans);
			
		}
		
		return resp;
		
		
	}

}
