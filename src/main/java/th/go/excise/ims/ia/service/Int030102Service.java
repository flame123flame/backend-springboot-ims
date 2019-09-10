package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMasCon;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsStatus;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasConRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsStatusRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaRiskFactosMasterJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030102JdbcRepository;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Service
public class Int030102Service {

	@Autowired
	private Int030102JdbcRepository int030102JdbcRepository;

	@Autowired
	private UpdateStatusRiskFactorsService updateStatusRiskFactorsService;

	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;

	@Autowired
	private IaRiskFactorsStatusRepository iaRiskFactorsStatusRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;
	
	@Autowired
	private IntSetProjectAndSystemAndExciseService intSetProjectAndSystemAndExciseService;

	@Autowired
	private IaRiskFactorsMasConRepository iaRiskFactorsMasConRepository;

	@Autowired
	IaRiskFactosMasterJdbcRepository iaRiskFactosMasterJdbcRepository;
	
	public List<Int030102Vo> list(Int030102FormVo form) {
		checkAndInsertTableFactorsStatus(form);
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
		iaRiskFactorsMasterList = int030102JdbcRepository.list(form);

		intSetProjectAndSystemAndExciseService.setProjectAndSystemAndExcise();
		

		return iaRiskFactorsMasterList;
	}

	public List<Int030102Vo> listIgnoreIsDeleted(Int030102FormVo form) {
		checkAndInsertTableFactorsStatus(form);
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
//		iaRiskFactorsMasterList = int030102JdbcRepository.listIgnoreIsDeleted(form);
		iaRiskFactorsMasterList = iaRiskFactosMasterJdbcRepository.listIgnoreIsDeleted(form);
	

		intSetProjectAndSystemAndExciseService.setProjectAndSystemAndExcise();
		

		return iaRiskFactorsMasterList;
	}

	public List<Int030102FormVo> budgetYearDropdown() {
		List<Int030102FormVo> response = int030102JdbcRepository.budgetYearDropdown();
		return response;

	}


	public void checkAndInsertTableFactorsStatus(Int030102FormVo form) {
		int count = int030102JdbcRepository.checkAndInsertTableFactorsStatus(form);
		if (count == 0) {
			List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
			iaRiskFactorsMasterList = iaRiskFactorsMasterRepository.findByInspectionWork(form.getInspectionWork());

			for (IaRiskFactorsMaster iaRiskS : iaRiskFactorsMasterList) {
				IaRiskFactorsStatus data = new IaRiskFactorsStatus();

				data.setIdMaster(iaRiskS.getId());
				data.setBudgetYear(form.getBudgetYear());
				data.setStatus("N");
				data.setInspectionWork(iaRiskS.getInspectionWork());

				data.setCreatedBy(UserLoginUtils.getCurrentUsername());
				data.setCreatedDate(LocalDateTime.now());
				iaRiskFactorsStatusRepository.save(data);
			}
		}
	}

	public void delete(Int030102FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.delete(form);
	}

	public void editStatus(Int030102FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.editStatus(form);
		updateStatusRiskFactorsService.updateStatusAddIaRiskFactorsMaster(form.getBudgetYear(),
				form.getInspectionWork());
	}

	@Transactional
	public void save(Int030102FormVo form) {

		List<IaRiskFactorsMaster> iaRiskFactorsMasterList = int030102JdbcRepository.listSaveFactors(form);

		List<IaRiskFactors> iaRiskFactorsList = iaRiskFactorsRepository
				.findByInspectionWorkByBudgetYear(form.getInspectionWork(), form.getBudgetYear());

		int030102JdbcRepository.updateFactorsIsDeleteY(form);

		for (IaRiskFactorsMaster iaRiskFactorsMaster : iaRiskFactorsMasterList) {
			IaRiskFactors data = new IaRiskFactors();
			String check = "Add";
			BigDecimal id = new BigDecimal(0);
			for (IaRiskFactors checkFactors : iaRiskFactorsList) {
				if (checkFactors.getIdMaster().equals(iaRiskFactorsMaster.getId())) {
					id = checkFactors.getId();
					check = "Update";
					break;
				}
			}

			if ("Add".equals(check)) {
				data.setIdMaster(iaRiskFactorsMaster.getId());
				data.setBudgetYear(iaRiskFactorsMaster.getBudgetYear());
				data.setRiskFactors(iaRiskFactorsMaster.getRiskFactorsMaster());
				data.setInspectionWork(iaRiskFactorsMaster.getInspectionWork());
				data.setSide(iaRiskFactorsMaster.getSide());
				data.setDataEvaluate(iaRiskFactorsMaster.getDataEvaluate());
				data.setStatusScreen(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_1_CODE);
				data.setCreatedBy(UserLoginUtils.getCurrentUsername());
				data.setCreatedDate(LocalDateTime.now());
				IaRiskFactors resultIdFactors = iaRiskFactorsRepository.save(data);

				IaRiskFactorsMasCon dataCon = new IaRiskFactorsMasCon();
				dataCon = iaRiskFactorsMasConRepository.findByIdMaster(iaRiskFactorsMaster.getId());

				IaRiskFactorsConfig dataConfig = new IaRiskFactorsConfig();
				dataConfig.setIdFactors(resultIdFactors.getId());
				dataConfig.setFactorsLevel(dataCon.getFactorsLevel());
				dataConfig.setRiskUnit(dataCon.getRiskUnit());
				dataConfig.setRiskIndicators(dataCon.getRiskIndicators());
				dataConfig.setInfoUsedRiskDesc(dataCon.getInfoUsedRiskDesc());
				dataConfig.setInfoUsedRisk(dataCon.getInfoUsedRisk());
				dataConfig.setVerylow(dataCon.getVerylow());
				dataConfig.setVerylowStart(dataCon.getVerylowStart());
				dataConfig.setVerylowEnd(dataCon.getVerylowEnd());
				dataConfig.setVerylowRating(dataCon.getVerylowRating());
				dataConfig.setVerylowColor(dataCon.getVerylowColor());
				dataConfig.setVerylowCondition(dataCon.getVerylowCondition());

				dataConfig.setLow(dataCon.getLow());
				dataConfig.setLowStart(dataCon.getLowStart());
				dataConfig.setLowEnd(dataCon.getLowEnd());
				dataConfig.setLowRating(dataCon.getLowRating());
				dataConfig.setLowColor(dataCon.getLowColor());
				dataConfig.setLowCondition(dataCon.getLowCondition());

				dataConfig.setMedium(dataCon.getMedium());
				dataConfig.setMediumStart(dataCon.getMediumStart());
				dataConfig.setMediumEnd(dataCon.getMediumEnd());
				dataConfig.setMediumRating(dataCon.getMediumRating());
				dataConfig.setMediumColor(dataCon.getMediumColor());
				dataConfig.setMediumCondition(dataCon.getMediumCondition());

				dataConfig.setHigh(dataCon.getHigh());
				dataConfig.setHighStart(dataCon.getHighStart());
				dataConfig.setHighEnd(dataCon.getHighEnd());
				dataConfig.setHighRating(dataCon.getHighRating());
				dataConfig.setHighColor(dataCon.getHighColor());
				dataConfig.setHighCondition(dataCon.getHighCondition());

				dataConfig.setVeryhigh(dataCon.getVeryhigh());
				dataConfig.setVeryhighStart(dataCon.getVeryhighStart());
				dataConfig.setVeryhighEnd(dataCon.getVeryhighEnd());
				dataConfig.setVeryhighRating(dataCon.getVeryhighRating());
				dataConfig.setVeryhighColor(dataCon.getVeryhighColor());
				dataConfig.setVeryhighCondition(dataCon.getVeryhighCondition());

				dataConfig.setCreatedBy(UserLoginUtils.getCurrentUsername());
				dataConfig.setCreatedDate(LocalDateTime.now());
				iaRiskFactorsConfigRepository.save(dataConfig);

			} else if ("Update".equals(check)) {
				int030102JdbcRepository.updateIsDelete(id);
				// iaRiskFactorsRepository.rrrrrr(id);
			}

		}

	}

	public void saveRiskFactorsLevel(Int030102FormVo form) {
		int030102JdbcRepository.saveRiskFactorsLevel(form);
		int030102JdbcRepository.claerDateCir(form);
		int030102JdbcRepository.saveRiskFactClerAll(form);
		updateStatusRiskFactorsService.updateStatusSaveRiskFactorsLevel(form.getBudgetYear(), form.getInspectionWork());
	}

	public void updateStatus(Int030102FormVo form) {
		int030102JdbcRepository.listUpdateStatus(form);
		save(form);
	}

	public IaRiskFactors factorList(IaRiskFactorsMaster form) {
		IaRiskFactors factor = new IaRiskFactors();
		List<IaRiskFactors> factorsList = iaRiskFactorsRepository.findByIdMaster(form.getId());
		for (IaRiskFactors iaRiskFactors : factorsList) {
			factor.setId(iaRiskFactors.getId());
			factor.setDataEvaluate(iaRiskFactors.getDataEvaluate());
		}
		return factor;
	}
}
