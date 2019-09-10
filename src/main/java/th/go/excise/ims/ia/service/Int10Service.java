package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaInspectionPlan;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaInspectionPlanJdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.Int10Vo;

@Service
public class Int10Service {
	private static final Logger logger = LoggerFactory.getLogger(Int10Service.class);
	
	@Autowired
	private IaInspectionPlanJdbcRepository iaInspectionPlanJdbcRepository;

	public List<Int10Vo> findByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaInspectionPlan> dataFilter = iaInspectionPlanJdbcRepository.getDataFilter(budgetYear, inspectionWork, status);
		
		List<Int10Vo> response = new ArrayList<>();
		Int10Vo int10Vo = null;
		for (IaInspectionPlan iaInspectionPlan : dataFilter) {
			int10Vo = new Int10Vo();
			int10Vo.setArea(iaInspectionPlan.getArea());
			int10Vo.setBudgetYear(iaInspectionPlan.getBudgetYear());
			int10Vo.setExciseCode(iaInspectionPlan.getExciseCode());
			int10Vo.setId(iaInspectionPlan.getId());
			int10Vo.setInspectionWork(iaInspectionPlan.getInspectionWork());
			int10Vo.setProject(iaInspectionPlan.getProject());
			int10Vo.setSector(iaInspectionPlan.getSector());
			int10Vo.setStatus(iaInspectionPlan.getStatus());

			/* set ExciseDepartmentVo */
			logger.info(iaInspectionPlan.getExciseCode());
			if(iaInspectionPlan.getExciseCode() != null) {
				int10Vo.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(int10Vo.getExciseCode()));
			}
			response.add(int10Vo);
		}
		
		return response;
	}
	
	public List<IaInspectionPlan> findByIdParams(BigDecimal id) {
		List<IaInspectionPlan> response = iaInspectionPlanJdbcRepository.getDataFilterIdParams(id);	
		return response;
	}
}
