package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaPlanDayActivity;
import th.go.excise.ims.ia.persistence.entity.IaPlanDtl;
import th.go.excise.ims.ia.persistence.repository.IaPlanDayActivityRepository;
import th.go.excise.ims.ia.persistence.repository.IaPlanDtlRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanDayActivityJdbcRepository;
import th.go.excise.ims.ia.vo.Int0101FormVo;
import th.go.excise.ims.ia.vo.Int0101PlanDayVo;

@Service
public class Int0101Service {
	
	@Autowired
	private IaPlanDayActivityRepository iaPlanDayActivityRepository;
	
	@Autowired
	private IaPlanDtlRepository iaPlanDtlRepository;
	
	@Autowired
	private IaPlanDayActivityJdbcRepository iaPlanDayActivityJdbcRepository;
	
	public void save(Int0101FormVo request) {
		/* update plan-detail */
		IaPlanDtl dataFilterDtl = iaPlanDtlRepository.findById(request.getPlanDtlId()).get();
		dataFilterDtl.setResponsiblePerson(request.getResponsiblePerson());
		dataFilterDtl.setPosition(request.getPosition());
		iaPlanDtlRepository.save(dataFilterDtl);
		
		if (request.getFlagUpdate()) {
			/* update plan-day */
			if(request.getPlanVo().size() > 0) {
				List<IaPlanDayActivity> dataAct = iaPlanDayActivityRepository.findByPlanDtlIdAndIsDeleted(request.getPlanDtlId(), "N");
				for (int i = 0; i < dataAct.size(); i++) {
					if(IaConstants.PLAN_DAY_WORDING.AUDIT_FULL.equals(dataAct.get(i).getActivity())) {
						dataAct.get(i).setDateStartActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateStartActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						dataAct.get(i).setDateEndActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateEndActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					} else if (IaConstants.PLAN_DAY_WORDING.ENGAGEMENT_FULL.equals(dataAct.get(i).getActivity())) {
						dataAct.get(i).setDateStartActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateStartActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						dataAct.get(i).setDateEndActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateEndActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					} else if (IaConstants.PLAN_DAY_WORDING.MONITORING_FULL.equals(dataAct.get(i).getActivity())) {
						dataAct.get(i).setDateStartActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateStartActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						dataAct.get(i).setDateEndActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateEndActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					} else if (IaConstants.PLAN_DAY_WORDING.REPORT_FULL.equals(dataAct.get(i).getActivity())) {
						dataAct.get(i).setDateStartActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateStartActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						dataAct.get(i).setDateEndActivity(ConvertDateUtils.parseStringToDate(request.getPlanVo().get(i).getDateEndActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					}
					
					iaPlanDayActivityRepository.save(dataAct.get(i));
				}
			}
		} else {
			/* save plan-day */
			IaPlanDayActivity entityPlanDay = null;
			if(request.getPlanVo().size() > 0) {
				for (Int0101PlanDayVo planDay : request.getPlanVo()) {
					entityPlanDay = new IaPlanDayActivity();
					entityPlanDay.setPlanHdrId(dataFilterDtl.getPlanHdrId());
					entityPlanDay.setPlanDtlId(dataFilterDtl.getPlanDtlId());
					entityPlanDay.setActivity(planDay.getActivity());
					entityPlanDay.setActivityStatus(IaConstants.PLAN_DAY_ACTIVITY_STATUS.PARAM_CODE_I);
					if(planDay.getActivity().equals(IaConstants.PLAN_DAY_WORDING.ENGAGEMENT_FULL)) {
						entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.ENGAGEMENT_ABBREVIATION);
					}else if(planDay.getActivity().equals(IaConstants.PLAN_DAY_WORDING.AUDIT_FULL)) {
						entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.AUDIT_ABBREVIATION);
					}else if(planDay.getActivity().equals(IaConstants.PLAN_DAY_WORDING.REPORT_FULL)) {
						entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.REPORT_ABBREVIATION);
					}else {
						entityPlanDay.setActivityShort(IaConstants.PLAN_DAY_WORDING.MONITORING_ABBREVIATION);
					}
					entityPlanDay.setDateStartActivity(ConvertDateUtils.parseStringToDate(planDay.getDateStartActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					entityPlanDay.setDateEndActivity(ConvertDateUtils.parseStringToDate(planDay.getDateEndActivityStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					
					iaPlanDayActivityRepository.save(entityPlanDay);
				}
			}
		}
		
	}
	
	public List<Int0101PlanDayVo> findDataDtlAndAct(BigDecimal idDtl) {
		List<Int0101PlanDayVo> joinData = new ArrayList<>();
		
		if(idDtl != null) {
			joinData = iaPlanDayActivityJdbcRepository.getDataDtlAndAtc(idDtl);
			/* set date-str */
			for (Int0101PlanDayVo obj : joinData) {
				obj.setDateStartActivityStr(ConvertDateUtils.formatDateToString(obj.getDateStartActivity(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				obj.setDateEndActivityStr(ConvertDateUtils.formatDateToString(obj.getDateEndActivity(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			}
		}
		return joinData;
	}

}
