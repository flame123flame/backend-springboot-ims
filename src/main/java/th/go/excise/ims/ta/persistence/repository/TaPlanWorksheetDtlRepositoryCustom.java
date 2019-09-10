package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.PersonAssignForm;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetSendTableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.TaPlanMasVo;

public interface TaPlanWorksheetDtlRepositoryCustom {

	public List<PlanWorksheetDatatableVo> findByCriteria(PlanWorksheetVo formVo);
	
	public List<PlanWorksheetDatatableVo> findAllByCriteria(PlanWorksheetVo formVo);

	public Long countByCriteria(PlanWorksheetVo formVo);
	
	public List<PlanWorksheetDtlVo> findByCriteria(AuditCalendarCriteriaFormVo formVo);
	
	public void updateStatusPlanWorksheetDtl(ExcisePerson formVo,String status);
	
	public void updateStatusPlanWorksheetDtlByList(PersonAssignForm formVo);
	
	public List<PlanWorksheetSendTableVo> findPlanWorksheetByDtl(PlanWorksheetVo formVo);
	
	public List<TaPlanWorksheetDtl> findByOfficeCodeAndPlanNumberForCentral(String planNumber, String officeCode);
	
	public List<PlanWorksheetDatatableVo> findOutPlanDtl(PlanWorksheetVo formVo);
	
	public Long countOutPlanDtl(PlanWorksheetVo formVo);
	
	public int countPlanTypeByOfficeCodeAndPlanType(PlanWorksheetVo formVo);
	
	public List<PlanWorksheetDtlVo> findByPlanDtlByAssingPerson(AuditCalendarCriteriaFormVo formVo);
	
	public List<PlanWorksheetDtlVo> countPlanDtlAndAreaByOfficeCode(String officeCode,String budgerYear);
	
	public List<TaPlanMasVo> countPlanDtlMonthByOfficeCode(String officeCode,String budgerYear);
	
	public List<PlanWorksheetDatatableVo> countPlanDtlStatusByOfficeCode(String officeCode,String budgetYear);
	
}
