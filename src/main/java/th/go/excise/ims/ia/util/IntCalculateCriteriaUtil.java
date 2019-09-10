package th.go.excise.ims.ia.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Component
public class IntCalculateCriteriaUtil {
	
	@Autowired
	public static IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;
	

	public static IntCalculateCriteriaVo calculateCriteria(BigDecimal dataCal, IaRiskFactorsConfig config) {
		IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();
		if (config.getFactorsLevel() != null) {
			boolean checkNullConfig = checkNullConfig(config);
			if (3 == config.getFactorsLevel().intValue() && checkNullConfig) {

				cal = calculateRating3Level(dataCal, config);

			} else if (5 == config.getFactorsLevel().intValue() && checkNullConfig) {

				cal = calculateRating5Level(dataCal, config);

			}
		}

		return cal;

	}
	private static boolean checkNullConfig(IaRiskFactorsConfig config) {
		boolean check = false;
		if (3 == config.getFactorsLevel().intValue()) {
			if (StringUtils.isNotBlank(config.getLowStart()) 
					|| StringUtils.isNotBlank(config.getMediumStart())
					|| StringUtils.isNotBlank(config.getMediumEnd()) 
					|| StringUtils.isNotBlank(config.getHighStart())) {
				check = true;
			}
		} else if (5 == config.getFactorsLevel().intValue()) {
			if (StringUtils.isNotBlank(config.getVerylowStart()) 
					|| StringUtils.isNotBlank(config.getLowStart())
					|| StringUtils.isNotBlank(config.getVerylowEnd()) 
					|| StringUtils.isNotBlank(config.getMediumStart())
					|| StringUtils.isNotBlank(config.getMediumEnd()) 
					|| StringUtils.isNotBlank(config.getHighStart())
					|| StringUtils.isNotBlank(config.getHighEnd())
					|| StringUtils.isNotBlank(config.getVeryhighStart())) {
				check = true;
			}
		}
		return check;
	}
	public static IntCalculateCriteriaVo calculateCriteriaAndGetConfigById(BigDecimal dataCal,BigDecimal idConfig) {
		IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();
		IaRiskFactorsConfig config = new IaRiskFactorsConfig();
			if(idConfig!=null) {
				config = iaRiskFactorsConfigRepository.findById(idConfig).get();
				cal = calculateCriteria(dataCal, config);
			}

		return cal;

	}
	
	public static IntCalculateCriteriaVo calculateCriteriaAndGetConfigByIdFactors(BigDecimal dataCal,BigDecimal idFactors) {
		IntCalculateCriteriaVo cal = new IntCalculateCriteriaVo();
		IaRiskFactorsConfig config = new IaRiskFactorsConfig();
		
			if(idFactors!=null) {
				config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
				cal = calculateCriteria(dataCal, config);
			}
			

		return cal;

	}


	private static IntCalculateCriteriaVo calculateRating3Level(BigDecimal dataCal, IaRiskFactorsConfig config) {
		IntCalculateCriteriaVo value = new IntCalculateCriteriaVo();
		if (checkDataCal(dataCal.floatValue(), config.getLowCondition(), config.getLowStart(), null)) {

			value.setDataCal(dataCal);
			value.setRiskRate(config.getLowRating());
			value.setTranslatingRisk(config.getLow());
			value.setColor(config.getLowColor());
			value.setCodeColor(colorToColorCode(config.getLowColor()));
			
		} else if (checkDataCal(dataCal.floatValue(), config.getMediumCondition(), config.getMediumStart(),
				config.getMediumEnd())) {
			value.setDataCal(dataCal);
			value.setRiskRate(config.getMediumRating());
			value.setTranslatingRisk(config.getMedium());
			value.setColor(config.getMediumColor());
			value.setCodeColor(colorToColorCode(config.getMediumColor()));

		} else if (checkDataCal(dataCal.floatValue(), config.getHighCondition(), config.getHighStart(), null)) {

			value.setDataCal(dataCal);
			value.setRiskRate(config.getHighRating());
			value.setTranslatingRisk(config.getHigh());
			value.setColor(config.getHighColor());
			value.setCodeColor(colorToColorCode(config.getHighColor()));

		}
		return value;
	}

	private static IntCalculateCriteriaVo calculateRating5Level(BigDecimal dataCal, IaRiskFactorsConfig config) {
		IntCalculateCriteriaVo value = new IntCalculateCriteriaVo();
		if (checkDataCal(dataCal.floatValue(), config.getVerylowCondition(), config.getVerylowStart(), null)) {

			value.setDataCal(dataCal);
			value.setRiskRate(config.getVerylowRating());
			value.setTranslatingRisk(config.getVerylow());
			value.setColor(config.getVerylowColor());
			value.setCodeColor(colorToColorCode(config.getVerylowColor()));

		} else if (checkDataCal(dataCal.floatValue(), config.getLowCondition(), config.getLowStart(),
				config.getLowEnd())) {

			value.setDataCal(dataCal);
			value.setRiskRate(config.getLowRating());
			value.setTranslatingRisk(config.getLow());
			value.setColor(config.getLowColor());
			value.setCodeColor(colorToColorCode(config.getLowColor()));

		} else if (checkDataCal(dataCal.floatValue(), config.getMediumCondition(), config.getMediumStart(),
				config.getMediumEnd())) {

			value.setDataCal(dataCal);
			value.setRiskRate(config.getMediumRating());
			value.setTranslatingRisk(config.getMedium());
			value.setColor(config.getMediumColor());
			value.setCodeColor(colorToColorCode(config.getMediumColor()));

		} else if (checkDataCal(dataCal.floatValue(), config.getHighCondition(), config.getHighStart(),
				config.getHighEnd())) {

			value.setDataCal(dataCal);
			value.setRiskRate(config.getHighRating());
			value.setTranslatingRisk(config.getHigh());
			value.setColor(config.getHighColor());
			value.setCodeColor(colorToColorCode(config.getHighColor()));

		} else if (checkDataCal(dataCal.floatValue(), config.getVeryhighCondition(), config.getVeryhighStart(), null)) {

			value.setDataCal(dataCal);
			value.setRiskRate(config.getVeryhighRating());
			value.setTranslatingRisk(config.getVeryhigh());
			value.setColor(config.getVeryhighColor());
			value.setCodeColor(colorToColorCode(config.getVeryhighColor()));
		}
		return value;
	}

//	public static Boolean checkDataCal(Float dataCal, String condition, String start, String end) {
//		Boolean res = false;
//		Float startB = (start != null) ? Float.valueOf(start) : null;
//		Float endB = (end != null) ? Float.valueOf(end) : null;
//
//		if (!"".equals(condition) && condition != null) {
//
//			if (("<".equals(condition)) && (dataCal < startB)) {
//
//				res = true;
//
//			} else if ("<>".equals(condition) && ((startB <= dataCal) && (dataCal <= endB))) {;
//
//				res = true;
//
//			} else if (">".equals(condition) && (dataCal > startB)) {
//
//				res = true;
//			}
//		}
//		return res;
//	}

	public static Boolean checkDataCal(Float dataCal, String condition, String start, String end) {
		Boolean res = false;
		Float startB = (start != null) ? Float.valueOf(start) : null;
		Float endB = (end != null) ? Float.valueOf(end) : null;
		String condition1 = (condition!=null)?condition.split("\\|")[0]:null;
		String condition2 =  (condition!=null)?condition.split("\\|")[1]:null;
		if (!"".equals(condition1) && condition1 != null && !"".equals(condition2) && condition2 != null) {

			if (("<".equals(condition1)) && (dataCal < startB)) {

				res = checkDataCal2(dataCal, condition2, end);

			} else if (">=".equals(condition1) && (dataCal >= startB)) {
				
				res = checkDataCal2(dataCal, condition2, end);

			}  else if ("<=".equals(condition1) && (dataCal <= startB)) {

				res = checkDataCal2(dataCal, condition2, end);

			} else if (">".equals(condition1) && (dataCal > startB)) {
	
				res = checkDataCal2(dataCal, condition2, end);
				
			} else if ("=".equals(condition1) && (dataCal == startB)) {;

				res = checkDataCal2(dataCal, condition2, end);

			}
		}
		return res;
	}
	
	public static Boolean checkDataCal2(Float dataCal, String condition, String end) {
		Boolean res = false;
		Float endB = (end != null) ? Float.valueOf(end) : null;
		if (!"".equals(condition) && condition != null && !"".equals(condition) && condition != null) {

			if (("<".equals(condition)) && (dataCal < endB)) {

				res = true;

			} else if (">=".equals(condition) && (dataCal >= endB)) {

				res = true;

			}  else if ("<=".equals(condition) && (dataCal <= endB)) {

				res = true;

			} else if (">".equals(condition) && (dataCal > endB)) {
	
				res = true;
				
			} else if ("=".equals(condition) && (dataCal == endB)) {

				res = true;

			} else if ("N".equals(condition)) {
	
				res = true;
			}
		}
		return res;
	}
	
	public static String colorToColorCode(String color) {
		String colorCode = "";
		if (IaConstants.IA_RISK_COLOR.COLOR1.equals(color)) {
			colorCode = IaConstants.IA_RISK_COLOR.COLOR1_CODE;
			
		} else if (IaConstants.IA_RISK_COLOR.COLOR2.equals(color)) {
			colorCode = IaConstants.IA_RISK_COLOR.COLOR2_CODE;
			
		} else if (IaConstants.IA_RISK_COLOR.COLOR3.equals(color)) {
			colorCode = IaConstants.IA_RISK_COLOR.COLOR3_CODE;
			
		} else if (IaConstants.IA_RISK_COLOR.COLOR4.equals(color)) {
			colorCode = IaConstants.IA_RISK_COLOR.COLOR4_CODE;
			
		} else if (IaConstants.IA_RISK_COLOR.COLOR5.equals(color)) {
			colorCode = IaConstants.IA_RISK_COLOR.COLOR5_CODE;
			
		}
		return colorCode;

	}
	

}
