package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskBudgetProject;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaRiskSelectCaseRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaRiskBudgetProjectJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030405JdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0401JdbcRepository;
import th.go.excise.ims.ia.vo.Int030405Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class IntSetProjectAndSystemAndExciseService {

	@Autowired
	private Int030405JdbcRepository int030405JdbcRepository;

	@Autowired
	private IaRiskBudgetProjectJdbcRepository iaRiskBudgetProjectJdbcRepository;

	@Autowired
	private IaRiskSelectCaseRepository iaRiskSelectCaseRep;

	@Autowired
	private Int0401JdbcRepository int0401JdbcRepository;

	public List<IaRiskSelectCase> setProjectAndSystemAndExcise() {
		List<IaRiskSelectCase> selectCases = new ArrayList<>();
		for (int i = 3; i <= 5; i++) {
			long count = int0401JdbcRepository.findCountRowWithoutStatus(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(i));
			if (count == 0) {
				selectCases = saveDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(i));
			}

		}

		return selectCases;
	}

	private List<IaRiskSelectCase> saveDataList(String budgetYear, BigDecimal inspectionWork) {

		List<IaRiskSelectCase> selectCases = new ArrayList<>();
		if (inspectionWork.compareTo(new BigDecimal(3)) == 0) {
			selectCases = new ArrayList<>();
			IaRiskSelectCase selectCase = new IaRiskSelectCase();

			List<IaRiskBudgetProject> project = iaRiskBudgetProjectJdbcRepository.getProjectByYear(new Date());

			for (IaRiskBudgetProject vo : project) {
				selectCase = new IaRiskSelectCase();
				selectCase.setProjectCode(vo.getProjectid());
				selectCase.setProject(vo.getProjectname());
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
			}
			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		} else if (inspectionWork.compareTo(new BigDecimal(4)) == 0) {
			selectCases = new ArrayList<>();
			IaRiskSelectCase selectCase = new IaRiskSelectCase();

			List<Int030405Vo> system = int030405JdbcRepository.getSystemByYear(new Date());

			for (Int030405Vo int030405Vo : system) {
				selectCase = new IaRiskSelectCase();
				selectCase.setSystemCode(int030405Vo.getSystemCode());
				selectCase.setSystemName(int030405Vo.getSystemName());
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
			}

			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		} else if (inspectionWork.compareTo(new BigDecimal(5)) == 0) {
			selectCases = new ArrayList<>();
			List<ExciseDepartment> exciseSectorList = ApplicationCache.getExciseSectorList();
			IaRiskSelectCase selectCase = new IaRiskSelectCase();

			for (ExciseDepartment exciseDept : exciseSectorList) {

				// ************* Insert Sector *************
				selectCase = new IaRiskSelectCase();
				ExciseDepartment sector = ApplicationCache.getExciseDepartment(exciseDept.getOfficeCode().substring(0, 2) + "0000");
				selectCase.setExciseCode(exciseDept.getOfficeCode());
				selectCase.setSector(sector.getDeptName());

				if (!"0000".equals(exciseDept.getOfficeCode().substring(2, 6))) {
					ExciseDepartment area = ApplicationCache.getExciseDepartment(exciseDept.getOfficeCode());
					selectCase.setArea(area.getDeptName());
				} else {
					selectCase.setArea("");
				}
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);

				// ************* Insert Area *************
				List<ExciseDepartment> exciseAreaList = ApplicationCache.getExciseAreaList(exciseDept.getOfficeCode());
				for (ExciseDepartment exciseDeptArea : exciseAreaList) {
					selectCase = new IaRiskSelectCase();
					ExciseDepartment sectorArea = ApplicationCache.getExciseDepartment(exciseDeptArea.getOfficeCode().substring(0, 2) + "0000");
					selectCase.setExciseCode(exciseDeptArea.getOfficeCode());
					selectCase.setSector(sectorArea.getDeptName());

					if (!"0000".equals(exciseDeptArea.getOfficeCode().substring(2, 6))) {
						ExciseDepartment area2 = ApplicationCache.getExciseDepartment(exciseDeptArea.getOfficeCode());
						selectCase.setArea(area2.getDeptName());
					} else {
						selectCase.setArea("");
					}
					selectCase.setBudgetYear(budgetYear);
					selectCase.setInspectionWork(inspectionWork);
					selectCase.setStatus("C");
					selectCases.add(selectCase);
				}
			}

			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		}

		return selectCases;
	}

}
