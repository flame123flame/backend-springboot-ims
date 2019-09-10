package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskQtnConfigRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireHdrJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int020101SideVo;
import th.go.excise.ims.ia.vo.Int020101UpdateVo;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Service
public class Int02Service {

	@Autowired
	private IaQuestionnaireHdrJdbcRepository iaQuestionnaireHdrJdbcRepository;

	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	@Autowired
	private IaQuestionnaireMadeHdrRepository iaQuestionnaireMadeHdrRepository;

	@Autowired
	private IaQuestionnaireMadeHdrJdbcRepository iaQuestionnaireMadeHdrJdbcRepository;

	@Autowired
	private IaQuestionnaireMadeRepository iaQuestionnaireMadeRepository;

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRepository;

	@Autowired
	private IaRiskQtnConfigRepository iaRiskQtnConfigRepository;

	public DataTableAjax<Int02Vo> filterQtnHdr(Int02FormVo request) {

		List<Int02Vo> data = iaQuestionnaireHdrJdbcRepository.getDataFilter(request);
		/* convert date to string */
		for (Int02Vo obj : data) {
			/* to string statusStr */
			obj.setStatusStr(ApplicationCache.getParamInfoByCode("IA_STATUS", obj.getStatus()).getValue1());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT);

			if (obj.getCreatedDate() != null) {
				Date createdDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setCreatedDateStr(ConvertDateUtils.formatDateToString(createdDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getUpdatedDate() != null) {
				Date updatedDate = ConvertDateUtils.parseStringToDate(obj.getUpdatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setUpdatedDateStr(ConvertDateUtils.formatDateToString(updatedDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getStartDate() != null) {
				Date startDate = ConvertDateUtils.parseStringToDate(obj.getStartDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setStartDateStr(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getEndDate() != null) {
				Date endDate = ConvertDateUtils.parseStringToDate(obj.getEndDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setEndDateStr(ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
		}

		DataTableAjax<Int02Vo> dataTableAjax = new DataTableAjax<Int02Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(iaQuestionnaireHdrJdbcRepository.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(iaQuestionnaireHdrJdbcRepository.countDatafilter(request));

		return dataTableAjax;
	}

	public IaQuestionnaireHdr findOne(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireHdr hdr = iaQuestionnaireHdrJdbcRepository.findOne(id);
//		hdr.setStatus(ApplicationCache.getParamInfoByCode("IA_STATUS", hdr.getStatus()).getValue1());
		return hdr;
	}

	public IaQuestionnaireHdr save(IaQuestionnaireHdr request) {
		return iaQuestionnaireHdrRepository.save(request);
	}

	public IaQuestionnaireHdr update(String idStr, Int020101UpdateVo res) {
		updateSeq(idStr, res);
		IaQuestionnaireHdr request = new IaQuestionnaireHdr();

		request.setBudgetYear(res.getBudgetYear());
		request.setId(res.getId());
		request.setQtnHeaderName(res.getQtnHeaderName());
		request.setNote(res.getNote());
		request.setStartDate(res.getStartDate());
		request.setEndDate(res.getEndDate());
		request.setStatus(res.getStatus());

		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireHdr data = iaQuestionnaireHdrJdbcRepository.findOne(id);
		data.setBudgetYear(request.getBudgetYear());
		data.setQtnHeaderName(request.getQtnHeaderName());
		data.setNote(request.getNote());
		data.setToDepartment(res.getToDepartment());
		data.setUsagePatterns(res.getUsagePatterns());
		if (IaConstants.USAGE_PATTERNS_QTN.QUESTIONNAIR_DESC.equals(res.getUsagePatterns())) {
			/* flag 'Q' */
			if(StringUtils.isAllEmpty(data.getFactorLevel())) {
				if("3".equals(res.getFactorLevel())) {
					data.setFactorLevel("5");
				}else if ("5".equals(res.getFactorLevel())) {
					data.setFactorLevel("3");
				}
			}
			if (!(data.getFactorLevel().equals(res.getFactorLevel()))) {
				updateConfig(id, res.getFactorLevel());
			}
			data.setFactorLevel(res.getFactorLevel());
		} else {
			data.setFactorLevel(IaConstants.USAGE_PATTERNS_QTN.NULL_DESC);
		}
		/* update table Questionnaire-Made-Hdr */
//		List<IaQuestionnaireMadeHdr> dataMadeHdr = iaQuestionnaireMadeHdrRepository.findByIdHdr(request.getId());
//		for (IaQuestionnaireMadeHdr objMadeHdr : dataMadeHdr) {
//			if(!"FINISH".equals(objMadeHdr.getStatus())) {
//				objMadeHdr.setQtnHeaderName(request.getQtnHeaderName());
//				objMadeHdr.setNote(request.getNote());
//				iaQuestionnaireMadeHdrRepository.save(objMadeHdr);
//			}
//		}
		List<IaQuestionnaireMadeHdr> madeHdrList = iaQuestionnaireMadeHdrJdbcRepository.findMadeHdrByIdHdr(request,
				UserLoginUtils.getCurrentUserBean().getOfficeCode());
		for (IaQuestionnaireMadeHdr madeHdr : madeHdrList) {
			madeHdr.setQtnHeaderName(request.getQtnHeaderName());
			madeHdr.setNote(request.getNote());
			iaQuestionnaireMadeHdrRepository.save(madeHdr);
		}

		return iaQuestionnaireHdrRepository.save(data);
	}

	@Transactional
	public void updateSeq(String idStr, Int020101UpdateVo res) {
		List<Int020101SideVo> side = res.getSide();
		IaQuestionnaireSide sideData = null;
		for (Int020101SideVo sideVo : side) {
			sideData = new IaQuestionnaireSide();
			sideData = iaQuestionnaireSideRepository.findById(sideVo.getId()).get();
			sideData.setSeq(new BigDecimal(sideVo.getSeq()));
			iaQuestionnaireSideRepository.save(sideData);
		}
	}

	public void updateStatus(String idStr) {
		Optional<IaQuestionnaireHdr> dataRes = iaQuestionnaireHdrRepository.findById(new BigDecimal(idStr));
		if (dataRes.isPresent()) {
			IaQuestionnaireHdr daraHdr = dataRes.get();

			if (IaConstants.IA_STATUS.STATUS_4_CODE.equals(daraHdr.getStatus())) {
				/* update status */
//				daraHdr.setStatus("FAIL_SEND_QTN");
				iaQuestionnaireHdrRepository.save(daraHdr);

				List<IaQuestionnaireMadeHdr> dataMadeHdr = iaQuestionnaireMadeHdrRepository
						.findByIdHdrAndIsDeleted(daraHdr.getId(), "N");
				for (IaQuestionnaireMadeHdr madeHdr : dataMadeHdr) {
//					madeHdr.setStatus("FAIL_SEND_QTN");
					iaQuestionnaireMadeHdrRepository.save(madeHdr);
					/* update status questionnaire made dtl */
					List<IaQuestionnaireMade> madeDtlList = iaQuestionnaireMadeRepository
							.findByIdMadeHdrAndIsDeleted(madeHdr.getId(), "N");
					for (IaQuestionnaireMade madeDtl : madeDtlList) {
//						madeDtl.setStatus("FAIL_SEND_QTN");
						iaQuestionnaireMadeRepository.save(madeDtl);
					}
				}
			} else {
				iaQuestionnaireHdrRepository.deleteById(daraHdr.getId());
			}
		}
	}

	public void updateConfig(BigDecimal id, String factorLevel) {
		IaRiskQtnConfig data = iaRiskQtnConfigRepository.findByIdQtnHdr(id);
		if ("3".equals(factorLevel)) {
			data.setVerylow(null);
			data.setVerylowStart(null);
			data.setVerylowEnd(null);
			data.setVerylowRating(null);
			data.setVerylowColor(null);
			data.setVerylowCondition(null);

			data.setLow("ต่ำ");
			data.setLowStart(new BigDecimal(50));
			data.setLowEnd(null);
			data.setLowRating(new BigDecimal(1));
			data.setLowColor("เขียว");
			data.setLowCondition("<|N");

			data.setMedium("ปานกลาง");
			data.setMediumStart(new BigDecimal(50));
			data.setMediumEnd(new BigDecimal(75));
			data.setMediumRating(new BigDecimal(2));
			data.setMediumColor("เหลือง");
			data.setMediumCondition(">=|<=");

			data.setHigh("สูง");
			data.setHighStart(new BigDecimal(75));
			data.setHighEnd(null);
			data.setHighRating(new BigDecimal(3));
			data.setHighColor("ส้ม");
			data.setHighCondition(">|N");

			data.setVeryhigh(null);
			data.setVeryhighStart(null);
			data.setVeryhighEnd(null);
			data.setVeryhighRating(null);
			data.setVeryhighColor(null);
			data.setVeryhighCondition(null);

		} else if ("5".equals(factorLevel)) {

			data.setVerylow("ต่ำมาก");
			data.setVerylowStart("5");
			data.setVerylowEnd(null);
			data.setVerylowRating(new BigDecimal(1));
			data.setVerylowColor("เขียวเข้ม");
			data.setVerylowCondition("<|N");
			data.setLow("ต่ำ");
			data.setLowStart(new BigDecimal(5));
			data.setLowEnd(new BigDecimal(25));
			data.setLowRating(new BigDecimal(2));
			data.setLowColor("เขียว");
			data.setLowCondition(">=|<");
			data.setMedium("ปานกลาง");
			data.setMediumStart(new BigDecimal(50));
			data.setMediumEnd(new BigDecimal(75));
			data.setMediumRating(new BigDecimal(3));
			data.setMediumColor("เหลือง");
			data.setMediumCondition(">=|<");
			data.setHigh("สูง");
			data.setHighStart(new BigDecimal(75));
			data.setHighEnd(new BigDecimal(85));
			data.setHighRating(new BigDecimal(4));
			data.setHighColor("ส้ม");
			data.setHighCondition(">=|<");
			data.setVeryhigh("สูงมาก");
			data.setVeryhighStart("85");
			data.setVeryhighEnd(null);
			data.setVeryhighRating(new BigDecimal(5));
			data.setVeryhighColor("แดง");
			data.setVeryhighCondition(">=|N");

		}
		iaRiskQtnConfigRepository.save(data);
	}

}
