package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigAllRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030103JdbcRepository;
import th.go.excise.ims.ia.vo.Int030103IdFactorsVo;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Service
public class Int030103Service {

	@Autowired
	private Int030103JdbcRepository int030103JdbcRepository;

	@Autowired
	private IaRiskFactorsConfigAllRepository iaRiskFactorsConfigAllRepository;
	

	@Autowired
	private UpdateStatusRiskFactorsService updateStatusRiskFactorsService;

	public List<IaRiskFactorsConfigAll> listConfigAll(IaRiskFactorsConfigAll form) {
		List<IaRiskFactorsConfigAll> iaRiskFactorsConfigAllList = new ArrayList<IaRiskFactorsConfigAll>();
		iaRiskFactorsConfigAllList = int030103JdbcRepository.listConfigAll(form);
		return iaRiskFactorsConfigAllList;
	}

	public void updatePercent(Int0301FormVo form) {
		IaRiskFactorsConfigAll configData = new IaRiskFactorsConfigAll();
		IaRiskFactorsConfigAll formConfigAll = form.getIaRiskFactorsConfigAll();
		configData.setBudgetYear(formConfigAll.getBudgetYear());
		configData.setInspectionWork(formConfigAll.getInspectionWork());
		configData.setFactorsLevel(formConfigAll.getFactorsLevel());
		configData.setVerylow(formConfigAll.getVerylow());
		configData.setVerylowColor(formConfigAll.getVerylowColor());
		configData.setVerylowCondition(formConfigAll.getVerylowCondition());
		configData.setVerylowEnd(formConfigAll.getVerylowEnd());
		configData.setVerylowRating(formConfigAll.getVerylowRating());
		configData.setVerylowStart(formConfigAll.getVerylowStart());
		configData.setLow(formConfigAll.getLow());
		configData.setLowColor(formConfigAll.getLowColor());
		configData.setLowCondition(formConfigAll.getLowCondition());
		configData.setLowEnd(formConfigAll.getLowEnd());
		configData.setLowRating(formConfigAll.getLowRating());
		configData.setLowStart(formConfigAll.getLowStart());
		configData.setMedium(formConfigAll.getMedium());
		configData.setMediumColor(formConfigAll.getMediumColor());
		configData.setMediumCondition(formConfigAll.getMediumCondition());
		configData.setMediumEnd(formConfigAll.getMediumEnd());
		configData.setMediumRating(formConfigAll.getMediumRating());
		configData.setMediumStart(formConfigAll.getMediumStart());
		configData.setHigh(formConfigAll.getHigh());
		configData.setHighColor(formConfigAll.getHighColor());
		configData.setHighCondition(formConfigAll.getHighCondition());
		configData.setHighEnd(formConfigAll.getHighEnd());
		configData.setHighRating(formConfigAll.getHighRating());
		configData.setHighStart(formConfigAll.getHighStart());
		configData.setVeryhigh(formConfigAll.getVeryhigh());
		configData.setVeryhighColor(formConfigAll.getVeryhighColor());
		configData.setVeryhighCondition(formConfigAll.getVeryhighCondition());
		configData.setVeryhighEnd(formConfigAll.getVeryhighEnd());
		configData.setVeryhighRating(formConfigAll.getVeryhighRating());
		configData.setVeryhighStart(formConfigAll.getVeryhighStart());
		iaRiskFactorsConfigAllRepository.save(configData);

		for (IaRiskFactorsConfig irfc : form.getIaRiskFactorsConfigList()) {
			int030103JdbcRepository.listUpdatePercent(irfc);
		}
	}

	public void updataConfigAll(Int0301FormVo form) {
		IaRiskFactorsConfigAll entity = new IaRiskFactorsConfigAll();
		IaRiskFactorsConfigAll formConfigAll = form.getIaRiskFactorsConfigAll();
		entity = iaRiskFactorsConfigAllRepository.findByBudgetYearByInspectionWork(formConfigAll.getBudgetYear(), formConfigAll.getInspectionWork());
		entity.setVerylow(formConfigAll.getVerylow());
		entity.setVerylowStart(formConfigAll.getVerylowStart());
		entity.setVerylowEnd(formConfigAll.getVerylowEnd());
		entity.setVerylowRating(formConfigAll.getVerylowRating());
		entity.setVerylowColor(formConfigAll.getVerylowColor());
		entity.setVerylowCondition(formConfigAll.getVerylowCondition());

		entity.setLow(formConfigAll.getLow());
		entity.setLowStart(formConfigAll.getLowStart());
		entity.setLowEnd(formConfigAll.getLowEnd());
		entity.setLowRating(formConfigAll.getLowRating());
		entity.setLowColor(formConfigAll.getLowColor());
		entity.setLowCondition(formConfigAll.getLowCondition());

		entity.setMedium(formConfigAll.getMedium());
		entity.setMediumStart(formConfigAll.getMediumStart());
		entity.setMediumEnd(formConfigAll.getMediumEnd());
		entity.setMediumRating(formConfigAll.getMediumRating());
		entity.setMediumColor(formConfigAll.getMediumColor());
		entity.setMediumCondition(formConfigAll.getMediumCondition());

		entity.setHigh(formConfigAll.getHigh());
		entity.setHighStart(formConfigAll.getHighStart());
		entity.setHighEnd(formConfigAll.getHighEnd());
		entity.setHighRating(formConfigAll.getHighRating());
		entity.setHighColor(formConfigAll.getHighColor());
		entity.setHighCondition(formConfigAll.getHighCondition());

		entity.setVeryhigh(formConfigAll.getVeryhigh());
		entity.setVeryhighStart(formConfigAll.getVeryhighStart());
		entity.setVeryhighEnd(formConfigAll.getVeryhighEnd());
		entity.setVeryhighRating(formConfigAll.getVeryhighRating());
		entity.setVeryhighColor(formConfigAll.getVeryhighColor());
		entity.setVeryhighCondition(formConfigAll.getVeryhighCondition());

		iaRiskFactorsConfigAllRepository.save(entity);

		for (IaRiskFactorsConfig irfc : form.getIaRiskFactorsConfigList()) {
			int030103JdbcRepository.listUpdatePercent(irfc);
		}

	}

//	private Logger logger = LoggerFactory.getLogger(Int030103Controller.class);
	public void updataStatusRiskFactors(Int030103IdFactorsVo form) {
		List<String> ids = form.getIds();
		for (String id : ids) {
//			logger.info(id);
			updateStatusRiskFactorsService.updateStatusIaRiskFactors(new BigDecimal(id),IaConstants.IA_STATUS_RISK_FACTORS.STATUS_3_CODE);
		}
	}

}
