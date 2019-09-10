package th.go.excise.ims.oa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa02JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa02Vo;

@Service
public class Oa02Service {
	
	@Autowired
	private Oa02JdbcRepository o02Repo;
	
	public Oa02Vo findOaPlanByYear(String fiscolYear,String offCode) {
		Oa02Vo plan = new Oa02Vo();
		List<OaPlan> listPlan  = new ArrayList<>();
//		listPlan = oaPlanRepo.findByfiscolYearAndIsDeleted(fiscolYear, "N");
		listPlan = o02Repo.findLubricantPlan(fiscolYear,OaOfficeCode.officeCodeLike(offCode));
		plan.setListPlan(listPlan);
		return plan;
	}
	
	public Oa02Vo findOaPlanHydroByYear(String fiscolYear,String offCode) {
		Oa02Vo plan = new Oa02Vo();
		List<OaPlan> listPlan  = new ArrayList<>();
//		listPlan = oaPlanRepo.findByfiscolYearAndIsDeleted(fiscolYear, "N");
		listPlan = o02Repo.findHydrocarbonPlan(fiscolYear,OaOfficeCode.officeCodeLike(offCode));
		plan.setListPlan(listPlan);
		return plan;
	}
	public Oa02Vo findOaPlanACHByYear(String fiscolYear,String offCode) {
		Oa02Vo plan = new Oa02Vo();
		List<OaPlan> listPlan  = new ArrayList<>();
		
//		listPlan = oaPlanRepo.findByfiscolYearAndIsDeleted(fiscolYear, "N");
		listPlan = o02Repo.findACHPlan(fiscolYear,OaOfficeCode.officeCodeLike(offCode));
		plan.setListPlan(listPlan);
		return plan;
	}
}
