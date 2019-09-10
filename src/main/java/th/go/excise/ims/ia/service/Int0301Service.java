package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskQtnConfigRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0301JdbcRepository;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Service
public class Int0301Service {
	private Logger logger = LoggerFactory.getLogger(Int0301Service.class);

	@Autowired
	private Int0301JdbcRepository int0301JdbcRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	@Autowired
	IaRiskQtnConfigRepository iaRiskQtnConfigRepository;

	@Autowired
	IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	@Autowired
	private UpdateStatusRiskFactorsService updateStatusRiskFactorsService;

	public List<Int0301Vo> list(Int0301FormVo form) {
		List<Int0301Vo> iaRiskFactorsList = new ArrayList<Int0301Vo>();
		iaRiskFactorsList = int0301JdbcRepository.list(form);
		return iaRiskFactorsList;
	}

	public List<Int0301Vo> listdynamic(Int0301FormVo form) {

		List<Int0301Vo> iaRiskFactorsList = new ArrayList<Int0301Vo>();
		List<Int0301Vo> iaRiskFactorsList2 = new ArrayList<Int0301Vo>();

		iaRiskFactorsList = int0301JdbcRepository.list(form);

		if (iaRiskFactorsList.size() != 0) {

			BigDecimal factorsLevel = iaRiskFactorsList.get(0).getIaRiskFactorsConfig().getFactorsLevel();

			if (new BigDecimal("3").equals(factorsLevel)) {

				for (Int0301Vo int0301Vo : iaRiskFactorsList) {
					List<String> listdynamic = new ArrayList<String>();
					String riskUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();
					String lowCon = int0301Vo.getIaRiskFactorsConfig().getLowCondition();
					String lowS = int0301Vo.getIaRiskFactorsConfig().getLowStart();
					String lowUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();

					listdynamic.add(convertCondition(lowCon, lowS, "", lowUnit, riskUnit));

					String mediumS = int0301Vo.getIaRiskFactorsConfig().getMediumStart();
					String mediumE = int0301Vo.getIaRiskFactorsConfig().getMediumEnd();
					String mediumCon = int0301Vo.getIaRiskFactorsConfig().getMediumCondition();
					String mediumUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(mediumCon, mediumS, mediumE, mediumUnit, riskUnit));

					String highS = int0301Vo.getIaRiskFactorsConfig().getHighStart();
					String highCon = int0301Vo.getIaRiskFactorsConfig().getHighCondition();
					String highUnit = int0301Vo.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(highCon, highS, "", highUnit, riskUnit));

					int0301Vo.setDatalistdynamic(listdynamic);
					iaRiskFactorsList2.add(int0301Vo);
				}
			} else if (new BigDecimal("5").equals(factorsLevel)) {

				for (Int0301Vo int0301Vo2 : iaRiskFactorsList) {
					List<String> listdynamic = new ArrayList<String>();
					String riskUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					String veryLowS = int0301Vo2.getIaRiskFactorsConfig().getVerylowStart();
					String veryLowCon = int0301Vo2.getIaRiskFactorsConfig().getVerylowCondition();
					String veryLowUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(veryLowCon, veryLowS, "", veryLowUnit, riskUnit));

					String lowS = int0301Vo2.getIaRiskFactorsConfig().getLowStart();
					String lowE = int0301Vo2.getIaRiskFactorsConfig().getLowEnd();
					String lowCon = int0301Vo2.getIaRiskFactorsConfig().getLowCondition();
					String lowUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(lowCon, lowS, lowE, lowUnit, riskUnit));

					String mediumS = int0301Vo2.getIaRiskFactorsConfig().getMediumStart();
					String mediumE = int0301Vo2.getIaRiskFactorsConfig().getMediumEnd();
					String mediumCon = int0301Vo2.getIaRiskFactorsConfig().getMediumCondition();
					String mediumUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(mediumCon, mediumS, mediumE, mediumUnit, riskUnit));

					String highS = int0301Vo2.getIaRiskFactorsConfig().getHighStart();
					String highE = int0301Vo2.getIaRiskFactorsConfig().getHighEnd();
					String highCon = int0301Vo2.getIaRiskFactorsConfig().getHighCondition();
					String highUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(highCon, highS, highE, highUnit, riskUnit));

					String veryHighS = int0301Vo2.getIaRiskFactorsConfig().getVeryhighStart();
					String veryHighCon = int0301Vo2.getIaRiskFactorsConfig().getVeryhighCondition();
					String veryHighUnit = int0301Vo2.getIaRiskFactorsConfig().getRiskUnit();
					listdynamic.add(convertCondition(veryHighCon, veryHighS, "", veryHighUnit, riskUnit));

					int0301Vo2.setDatalistdynamic(listdynamic);
					iaRiskFactorsList2.add(int0301Vo2);
				}
			}

		}

		return iaRiskFactorsList2;

	}

	public String convertCondition(String condition, String start, String end, String unit, String riskUnit) {
//		if("บาท".equals(riskUnit)) {
		if (StringUtils.isNotBlank(start)) {
			start = String.format("%,.0f", new BigDecimal(start));
		}
		if (StringUtils.isNotBlank(end)) {
			end = String.format("%,.0f", new BigDecimal(end));
		}
//		}
		String res = "";
		if (StringUtils.isNotBlank(condition)) {
			String condition1 = condition.split("\\|")[0];
			String condition2 = condition.split("\\|")[1];
			if (StringUtils.isAllEmpty(start)) {
				start = "-";
			}
			if (StringUtils.isAllEmpty(end)) {
				end = "-";
			}
			if ("=".equals(condition1)) {
				res = " เท่ากับ  " + start + " " + unit;
			} else if (">=".equals(condition1)) {
				res = " มากกว่าเท่ากับ  " + start + checkCondition(condition2, end, unit);
			} else if ("<=".equals(condition1)) {
				res = " น้อยกว่าเท่ากับ  " + start + checkCondition(condition2, end, unit);
			} else if (">".equals(condition1)) {
				res = " มากกว่า  " + start + checkCondition(condition2, end, unit);
			} else if ("<".equals(condition1)) {
				res = " น้อยกว่า  " + start + checkCondition(condition2, end, unit);
			}
		}
		return res;
	}

	private String checkCondition(String condition, String end, String unit) {
		String res = "";
		if ("<".equals(condition)) {
			res = " น้อยกว่า " + end + " " + unit;
		} else if (">".equals(condition)) {
			res = " มากกว่า " + end + " " + unit;
		} else if ("<=".equals(condition)) {
			res = " น้อยกว่าเท่ากับ " + end + " " + unit;
		} else if (">=".equals(condition)) {
			res = " มากกว่าเท่ากับ " + end + " " + unit;
		} else if ("=".equals(condition)) {
			res = " เท่ากับ " + end + " " + unit;
		} else if ("N".equals(condition)) {
			res = " " + unit;
		}
		return res;
	}

	public void saveRiskFactorsLevel(Int0301FormVo form) {
		int0301JdbcRepository.saveRiskFactorsLevel(form);
		int0301JdbcRepository.claerDateCir(form);

	}

	@Transactional
	public void saveRiskFactorsConfig(Int0301FormVo form) {
		IaRiskFactorsConfig entity = new IaRiskFactorsConfig();
		IaRiskFactorsConfig formConfig = form.getIaRiskFactorsConfig();
		entity = iaRiskFactorsConfigRepository.findById(formConfig.getId()).get();
		entity.setIdFactors(formConfig.getIdFactors());
		entity.setInfoUsedRisk(formConfig.getInfoUsedRisk());

		entity.setVerylow(formConfig.getVerylow());
		entity.setVerylowStart(formConfig.getVerylowStart());
		entity.setVerylowEnd(formConfig.getVerylowEnd());
		entity.setVerylowRating(formConfig.getVerylowRating());
		entity.setVerylowColor(formConfig.getVerylowColor());
		entity.setVerylowCondition(formConfig.getVerylowCondition());

		entity.setLow(formConfig.getLow());
		entity.setLowStart(formConfig.getLowStart());
		entity.setLowEnd(formConfig.getLowEnd());
		entity.setLowRating(formConfig.getLowRating());
		entity.setLowColor(formConfig.getLowColor());
		entity.setLowCondition(formConfig.getLowCondition());

		entity.setMedium(formConfig.getMedium());
		entity.setMediumStart(formConfig.getMediumStart());
		entity.setMediumEnd(formConfig.getMediumEnd());
		entity.setMediumRating(formConfig.getMediumRating());
		entity.setMediumColor(formConfig.getMediumColor());
		entity.setMediumCondition(formConfig.getMediumCondition());

		entity.setHigh(formConfig.getHigh());
		entity.setHighStart(formConfig.getHighStart());
		entity.setHighEnd(formConfig.getHighEnd());
		entity.setHighRating(formConfig.getHighRating());
		entity.setHighColor(formConfig.getHighColor());
		entity.setHighCondition(formConfig.getHighCondition());

		entity.setVeryhigh(formConfig.getVeryhigh());
		entity.setVeryhighStart(formConfig.getVeryhighStart());
		entity.setVeryhighEnd(formConfig.getVeryhighEnd());
		entity.setVeryhighRating(formConfig.getVeryhighRating());
		entity.setVeryhighColor(formConfig.getVeryhighColor());
		entity.setVeryhighCondition(formConfig.getVeryhighCondition());

		Date startDate = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY);
		entity.setStartDate(startDate);

		Date endDate = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY);
		entity.setEndDate(endDate);
		iaRiskFactorsConfigRepository.save(entity);
		if (StringUtils.isNoneEmpty(entity.getInfoUsedRisk())) {
			updateConfigQtn(entity);
		}

		updateStatusRiskFactorsService.updateStatusIaRiskFactors(formConfig.getIdFactors(),
				IaConstants.IA_STATUS_RISK_FACTORS.STATUS_2_CODE);

	}

	public void updateConfigQtn(IaRiskFactorsConfig dataFactorConfig) {
		IaQuestionnaireHdr dataHdr = iaQuestionnaireHdrRepository
				.findById(new BigDecimal(dataFactorConfig.getInfoUsedRisk())).get();
		dataHdr.setFactorLevel(dataFactorConfig.getFactorsLevel().toString());
		iaQuestionnaireHdrRepository.save(dataHdr);

		IaRiskQtnConfig entity = new IaRiskQtnConfig();
//		entity.setIdFactors(dataFactorConfig.getIdFactors());
//		entity.setInfoUsedRisk(dataFactorConfig.getInfoUsedRisk());
		entity = iaRiskQtnConfigRepository.findByIdQtnHdr(new BigDecimal(dataFactorConfig.getInfoUsedRisk()));
//		entity = iaRiskQtnConfigRepository.findById(dataFactorConfig.getId()).get();
		
		if (StringUtils.isNoneEmpty(dataFactorConfig.getVerylow())) {
			entity.setVerylow(dataFactorConfig.getVerylow());
			entity.setVerylowStart(dataFactorConfig.getVerylowStart());
			entity.setVerylowEnd(dataFactorConfig.getVerylowEnd());
			entity.setVerylowRating(dataFactorConfig.getVerylowRating());
			entity.setVerylowColor(dataFactorConfig.getVerylowColor());
			entity.setVerylowCondition(dataFactorConfig.getVerylowCondition());
		}

		entity.setLow(dataFactorConfig.getLow());
		entity.setLowStart(new BigDecimal(dataFactorConfig.getLowStart()));
		if (StringUtils.isNotEmpty(dataFactorConfig.getLowEnd())) {
			entity.setLowEnd(new BigDecimal(dataFactorConfig.getLowEnd()));
		}
		entity.setLowRating(dataFactorConfig.getLowRating());
		entity.setLowColor(dataFactorConfig.getLowColor());
		entity.setLowCondition(dataFactorConfig.getLowCondition());

		entity.setMedium(dataFactorConfig.getMedium());
		entity.setMediumStart(new BigDecimal(dataFactorConfig.getMediumStart()));
		entity.setMediumEnd(new BigDecimal(dataFactorConfig.getMediumEnd()));
		entity.setMediumRating(dataFactorConfig.getMediumRating());
		entity.setMediumColor(dataFactorConfig.getMediumColor());
		entity.setMediumCondition(dataFactorConfig.getMediumCondition());

		entity.setHigh(dataFactorConfig.getHigh());
		entity.setHighStart(new BigDecimal(dataFactorConfig.getHighStart()));
		if (StringUtils.isNotEmpty(dataFactorConfig.getHighEnd())) {
			entity.setHighEnd(new BigDecimal(dataFactorConfig.getHighEnd()));
		}
		entity.setHighRating(dataFactorConfig.getHighRating());
		entity.setHighColor(dataFactorConfig.getHighColor());
		entity.setHighCondition(dataFactorConfig.getHighCondition());

		if (StringUtils.isNotEmpty(dataFactorConfig.getHighEnd())) {
			entity.setVeryhigh(dataFactorConfig.getVeryhigh());
			entity.setVeryhighStart(dataFactorConfig.getVeryhighStart());
			entity.setVeryhighEnd(dataFactorConfig.getVeryhighEnd());
			entity.setVeryhighRating(dataFactorConfig.getVeryhighRating());
			entity.setVeryhighColor(dataFactorConfig.getVeryhighColor());
			entity.setVeryhighCondition(dataFactorConfig.getVeryhighCondition());
		}

//		Date startDate = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY);
//		entity.setStartDate(startDate);
//
//		Date endDate = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY);
//		entity.setEndDate(endDate);
		iaRiskQtnConfigRepository.save(entity);
	}

}
