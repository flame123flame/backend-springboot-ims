package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101Vo;
import th.go.excise.ims.ia.vo.Int0201FormVo;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Int0201Service {
	private static final Logger logger = LoggerFactory.getLogger(Int0201Service.class);

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRepository;

	@Autowired
	private IaQuestionnaireSideDtlJdbcRepository iaQuestionnaireSideDtlJdbcRepository;

	@Autowired
	private IaQuestionnaireMadeRepository iaQuestionnaireMadeRepository;

	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	@Autowired
	private IaQuestionnaireMadeHdrRepository iaQuestionnaireMadeHdrRepository;

	@Autowired
	private IaRiskQtnConfigRepository iaRiskQtnConfigRepository;

	@Autowired
	private Int02010101Service int02010101Service;
	
	@Autowired
	private UpdateStatusQuestionnaireService questionnaireService;
	
	@Autowired
	private IaQuestionnaireMadeJdbcRepository iaQuestionnaireMadeJdbcRepository;

	public List<IaQuestionnaireSide> findQtnSideById(Int0201FormVo request) {
		return iaQuestionnaireSideRepository.findByidHeadAndIsDeletedOrderBySeqAsc(request.getId(), "N");
	}

	public IaQuestionnaireHdr findQtnHdrById(BigDecimal id) {
		return iaQuestionnaireHdrRepository.findById(id).get();
	}

	public Int0201Vo findQtnSideDtlById(Int0201FormVo2 request) {
		List<Int02010101Vo> dataHdr = null;
		List<Int02010101Vo> dataDtl = null;
		List<Int02010101Vo> dataDtls = null;
		List<List<Int02010101Vo>> dataRes = new ArrayList<List<Int02010101Vo>>();

		Int0201Vo response = new Int0201Vo();
		for (Int0201FormVo dataRequest : request.getRequest()) {
			dataHdr = new ArrayList<Int02010101Vo>();
			dataHdr = iaQuestionnaireSideDtlJdbcRepository.findByIdSide(dataRequest.getId());

			for (Int02010101Vo objHdr : dataHdr) {
				dataDtl = new ArrayList<Int02010101Vo>();
				dataDtl = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(objHdr.getIdSide(), objHdr.getSeq(),
						objHdr.getId());
				for (Int02010101Vo objDtl : dataDtl) {
					dataDtls = new ArrayList<Int02010101Vo>();
					dataDtls = iaQuestionnaireSideDtlJdbcRepository.findDtlsByIdSide(objDtl.getIdSide(),
							objDtl.getSeqDtl(), objDtl.getId());
					objDtl.setChildren(dataDtls);
				}
				objHdr.setChildren(dataDtl);
			}
			dataRes.add(dataHdr);
		}
		response.setData(dataRes);

		return response;
	}

	@Transactional
	public void sendQtnform(Int0201FormVo request) {
		/* update Questionnaire Header */
		if (request.getIdHead() != null) {
			Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdHead());
			if (hdrRes.isPresent()) {
				IaQuestionnaireHdr dataHdr = hdrRes.get();
				dataHdr.setStartDate(ConvertDateUtils.parseStringToLocalDate(request.getStartDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setEndDate(ConvertDateUtils.parseStringToLocalDate(request.getEndDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setStatus(IaConstants.IA_STATUS.STATUS_4_CODE);
				dataHdr = iaQuestionnaireHdrRepository.save(dataHdr);
				
				/* save Questionnaire Made HDR from office code */
				if (request.getIdHead() != null) {
					IaQuestionnaireMadeHdr dataMadeHdr = null;
					for (String officeCode : request.getExciseCodes()) {
						dataMadeHdr = new IaQuestionnaireMadeHdr();
						dataMadeHdr.setIdHdr(dataHdr.getId());
						dataMadeHdr.setBudgetYear(dataHdr.getBudgetYear());
						dataMadeHdr.setNote(dataHdr.getNote());
						dataMadeHdr.setQtnHeaderName(dataHdr.getQtnHeaderName());
						dataMadeHdr.setStartDate(ConvertDateUtils.parseStringToLocalDate(request.getStartDateSend(),
								ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setEndDate(ConvertDateUtils.parseStringToLocalDate(request.getEndDateSend(),
								ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setStatus(IaConstants.IA_STATUS_REPLY_QTN.STATUS_1_CODE);
						dataMadeHdr.setOfficeCode(officeCode);
						IaQuestionnaireMadeHdr resMadeHdr = iaQuestionnaireMadeHdrRepository.save(dataMadeHdr);
						
						// find sides
						List<IaQuestionnaireSide> sides = iaQuestionnaireSideRepository
								.findByidHeadAndIsDeletedOrderBySeqAsc(dataHdr.getId(), "N");
						
						for (IaQuestionnaireSide side : sides) {
							
							// find side dtls
							List<Int02010101Vo> dtls = int02010101Service.findByIdSide(side.getId().toString());
							
							/* save Questionnaire Made DTL */
							IaQuestionnaireMade qtnMade = null;
							List<IaQuestionnaireMade> qtnMades = new ArrayList<>();
							for (Int02010101Vo dtl : dtls) {
								qtnMade = new IaQuestionnaireMade();
								qtnMade.setIdSideDtl(dtl.getId());
								qtnMade.setQtnLevel(dtl.getQtnLevel());
								qtnMade.setStatus(IaConstants.IA_STATUS_REPLY_QTN.STATUS_1_CODE);
								qtnMade.setOfficeCode(officeCode);
								qtnMade.setIdMadeHdr(resMadeHdr.getId());
								qtnMades.add(qtnMade);
								for (Int02010101Vo dt : dtl.getChildren()) {
									qtnMade = new IaQuestionnaireMade();
									qtnMade.setIdSideDtl(dt.getId());
									qtnMade.setQtnLevel(dt.getQtnLevel());
									qtnMade.setStatus(IaConstants.IA_STATUS_REPLY_QTN.STATUS_1_CODE);
									qtnMade.setOfficeCode(officeCode);
									qtnMade.setIdMadeHdr(resMadeHdr.getId());
									qtnMades.add(qtnMade);
									for (Int02010101Vo d : dt.getChildren()) {
										qtnMade = new IaQuestionnaireMade();
										qtnMade.setIdSideDtl(d.getId());
										qtnMade.setQtnLevel(d.getQtnLevel());
										qtnMade.setStatus(IaConstants.IA_STATUS_REPLY_QTN.STATUS_1_CODE);
										qtnMade.setOfficeCode(officeCode);
										qtnMade.setIdMadeHdr(resMadeHdr.getId());
										qtnMades.add(qtnMade);
									} // end loop 5
								} // end loop 4
							} // end loop 3
							if (qtnMades.size() > 0) {
//								iaQuestionnaireMadeRepository.saveAll(qtnMades);
								iaQuestionnaireMadeJdbcRepository.saveQtnMadeList(qtnMades);
							}
						} // end loop 2
					} // end loop 1
				}
			}
		}
	}

	@Transactional
	public void sendQtnForm(Int0201FormVo request) {
		/* update Questionnaire Header */
		if (request.getIdHead() != null) {
			Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdHead());
			if (hdrRes.isPresent()) {
				IaQuestionnaireHdr dataHdr = hdrRes.get();
				dataHdr.setStartDate(ConvertDateUtils.parseStringToLocalDate(request.getStartDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setEndDate(ConvertDateUtils.parseStringToLocalDate(request.getEndDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setStatus(IaConstants.IA_STATUS.STATUS_4_CODE);
				iaQuestionnaireHdrRepository.save(dataHdr);
			}
		}

		/* check status for save or update or delete */
		if (IaConstants.IA_STATUS.STATUS_4_CODE.equals(request.getStatus())) {
			logger.info("delete QtnMade by idSideDtl");
			/* find id made header from request */
			List<IaQuestionnaireMade> filterQtnMade = iaQuestionnaireMadeRepository
					.findByIdSideDtl(request.getQtnMadeList().get(0).getIdSideDtl());

			if (request.getQtnMadeList().size() > 0) {
				for (IaQuestionnaireMade sideDtl : request.getQtnMadeList()) {
					iaQuestionnaireMadeRepository.deleteByIdSideDtl(sideDtl.getIdSideDtl());
				}
			}
//			updateQtnMadeHdr(request);

			/* save Questionnaire Made */
			if (filterQtnMade.size() > 0) {
				for (IaQuestionnaireMade filter : filterQtnMade) {
					saveQtnMade(request, filter.getOfficeCode(), filter.getIdMadeHdr());
				}
			}
		} else {
			logger.info("save QtnMadeHdr");

			/* find id of Questionnaire Header */
			if (request.getIdHead() != null) {
				Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdHead());
				List<ExciseDepartment> sectors = ApplicationCache.getExciseSectorList();
				List<ExciseDepartment> areas = new ArrayList<>();
				List<String> officeCodes = new ArrayList<>();
				for (ExciseDepartment sector : sectors) {
					areas = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
					officeCodes.add(sector.getOfficeCode());
					for (ExciseDepartment area : areas) {
						officeCodes.add(area.getOfficeCode());
					}
				}
				if (hdrRes.isPresent()) {
					IaQuestionnaireHdr dataHdr = hdrRes.get();
					IaQuestionnaireMadeHdr dataMadeHdr = null;
					for (String officeCode : officeCodes) {
						dataMadeHdr = new IaQuestionnaireMadeHdr();
						dataMadeHdr.setIdHdr(dataHdr.getId());
						dataMadeHdr.setBudgetYear(dataHdr.getBudgetYear());
						dataMadeHdr.setNote(dataHdr.getNote());
						dataMadeHdr.setQtnHeaderName(dataHdr.getQtnHeaderName());
						dataMadeHdr.setStartDate(ConvertDateUtils.parseStringToLocalDate(request.getStartDateSend(),
								ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setEndDate(ConvertDateUtils.parseStringToLocalDate(request.getEndDateSend(),
								ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setStatus(IaConstants.IA_STATUS_REPLY_QTN.STATUS_1_CODE);
						dataMadeHdr.setOfficeCode(officeCode);
						IaQuestionnaireMadeHdr resMadeHdr = iaQuestionnaireMadeHdrRepository.save(dataMadeHdr);

						/* save Questionnaire Made */
						saveQtnMade(request, officeCode, resMadeHdr.getId());
					} // end loop 1
				}
			}
		}
	}

	/* update Questionnaire Made Header */
	@Transactional
	private void updateQtnMadeHdr(Int0201FormVo request) {
		logger.info("update QtnMadeHdr");

		/* find id of Questionnaire Made Header */
		if (request.getIdHead() != null) {
			List<IaQuestionnaireMadeHdr> filterQtnMadeHdr = iaQuestionnaireMadeHdrRepository
					.findByIdHdrAndIsDeleted(request.getIdHead(), "N");
			if (filterQtnMadeHdr.size() > 0) {
				/* find id of Questionnaire Header */
				Optional<IaQuestionnaireHdr> filterQtnHdr = iaQuestionnaireHdrRepository.findById(request.getIdHead());
				if (filterQtnHdr.isPresent()) {
					IaQuestionnaireHdr dataHdr = filterQtnHdr.get();
					int loopCount = 0;

					/* compare data QtnMadeHdr and QtnHdr */
					for (IaQuestionnaireMadeHdr objMadeHdr : filterQtnMadeHdr) {
						objMadeHdr.setBudgetYear(dataHdr.getBudgetYear());
						objMadeHdr.setNote(dataHdr.getNote());
						objMadeHdr.setQtnHeaderName(dataHdr.getQtnHeaderName());
						objMadeHdr.setStartDate(dataHdr.getStartDate());
						objMadeHdr.setEndDate(dataHdr.getEndDate());
						objMadeHdr.setUpdatedBy(dataHdr.getUpdatedBy());
						objMadeHdr.setUpdatedDate(dataHdr.getUpdatedDate());
						objMadeHdr.setOfficeCode("0" + loopCount + "0000");
						iaQuestionnaireMadeHdrRepository.save(objMadeHdr);
						loopCount++;

						/* update Questionnaire Made Detail */
						if (request.getQtnMadeList().size() > 0) {
							for (IaQuestionnaireMade madeDtl : request.getQtnMadeList()) {
								List<IaQuestionnaireMade> filterQtnMadeDtl = iaQuestionnaireMadeRepository
										.findByIdSideDtl(madeDtl.getIdSideDtl());
								if (filterQtnMadeDtl.size() > 0) {
									for (IaQuestionnaireMade objQtnMadeDtl : filterQtnMadeDtl) {
										objQtnMadeDtl.setUpdatedBy(UserLoginUtils.getCurrentUsername());
										iaQuestionnaireMadeRepository.save(objQtnMadeDtl);
									} // end loop 3
								}
							} // end loop 2
						}
					} // end loop 1
				}
			}
		}
	}

	/* save Questionnaire Made */
	@Transactional
	private void saveQtnMade(Int0201FormVo request, String officeCode, BigDecimal idMadeHdr) {
		logger.info("save QtnMadeHdr");
		IaQuestionnaireMade qtnMade = null;
		if (request.getQtnMadeList().size() > 0) {
			for (IaQuestionnaireMade objMade : request.getQtnMadeList()) {
				qtnMade = new IaQuestionnaireMade();
				qtnMade.setIdSideDtl(objMade.getIdSideDtl());
				qtnMade.setQtnLevel(objMade.getQtnLevel());
				qtnMade.setStatus(IaConstants.IA_STATUS_REPLY_QTN.STATUS_1_CODE);
				qtnMade.setOfficeCode(officeCode);
				qtnMade.setIdMadeHdr(idMadeHdr);
				iaQuestionnaireMadeRepository.save(qtnMade);
			} // end loop 2
		}
	}

	public IaQuestionnaireHdr updateStatus(Int0201FormVo request) {
		IaQuestionnaireHdr response = new IaQuestionnaireHdr();

		Optional<IaQuestionnaireHdr> resQtnHdr = iaQuestionnaireHdrRepository.findById(request.getId());
		if (resQtnHdr.isPresent()) {
			IaQuestionnaireHdr dataQtnHdr = resQtnHdr.get();
			if ("1".equals(request.getStatus())) {
				dataQtnHdr.setStatus("2");
				response = iaQuestionnaireHdrRepository.save(dataQtnHdr);
			}else if("2".equals(request.getStatus()) && "PASS".equals(request.getFlagStr())) {
				dataQtnHdr.setStatus("3");
				response = iaQuestionnaireHdrRepository.save(dataQtnHdr);
			}else if("2".equals(request.getStatus()) && "NOT".equals(request.getFlagStr())) {
				dataQtnHdr.setStatus("1");
				response = iaQuestionnaireHdrRepository.save(dataQtnHdr);
			}
		}
		return response;
	}

	/*
	 * ==================== == CONFIGS `START`== ====================
	 */
	public IaRiskQtnConfig findConfigByIdQtnHdr(String idQtnHdrStr) {
		BigDecimal idQtnHdr = new BigDecimal(idQtnHdrStr);
		// Find
		IaRiskQtnConfig risk = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(idQtnHdr, "N");
		return risk;
	}

	public IaRiskQtnConfig saveConfig(IaRiskQtnConfig request) throws Exception {
		// Save
		IaRiskQtnConfig risk = iaRiskQtnConfigRepository.save(request);
		if (risk != null) {
			// Response here
			return risk;
		}
		throw new Exception();
	}

	public IaRiskQtnConfig updateConfig(IaRiskQtnConfig request) throws Exception {
		// Find
		IaRiskQtnConfig risk = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(request.getIdQtnHdr(), "N");
		// Update here
		risk.setLow(request.getLow());
		risk.setLowStart(request.getLowStart());
		risk.setLowEnd(request.getLowEnd());
		risk.setLowRating(request.getLowRating());
		risk.setLowColor(request.getLowColor());
		risk.setLowCondition(request.getLowCondition());
		
		risk.setMedium(request.getMedium());
		risk.setMediumStart(request.getMediumStart());
		risk.setMediumEnd(request.getMediumEnd());
		risk.setMediumRating(request.getMediumRating());
		risk.setMediumColor(request.getMediumColor());
		risk.setMediumCondition(request.getMediumCondition());

		risk.setHigh(request.getHigh());
		risk.setHighStart(request.getHighStart());
		risk.setHighEnd(request.getHighEnd());
		risk.setHighRating(request.getHighRating());
		risk.setHighColor(request.getHighColor());
		risk.setHighCondition(request.getHighCondition());
		
		risk.setVeryhigh(request.getVeryhigh());
		risk.setVeryhighStart(request.getVeryhighStart());
		risk.setVeryhighEnd(request.getVeryhighEnd());
		risk.setVeryhighRating(request.getVeryhighRating());
		risk.setVeryhighColor(request.getVeryhighColor());
		risk.setVeryhighCondition(request.getVeryhighCondition());
		
		risk.setVerylow(request.getVerylow());
		risk.setVerylowStart(request.getVerylowStart());
		risk.setVerylowEnd(request.getVerylowEnd());
		risk.setVerylowRating(request.getVerylowRating());
		risk.setVerylowColor(request.getVerylowColor());
		risk.setVerylowCondition(request.getVerylowCondition());

		risk = iaRiskQtnConfigRepository.save(risk);

		if (risk != null) {
			return risk;
		}
		throw new Exception();
	}
	
	public BigDecimal canceledQtn(BigDecimal idHdrStr) throws Exception {
		
		// Update here
		BigDecimal downStatus =  questionnaireService.downStatusIaQuestionnaire(idHdrStr);
		if (downStatus != null) {
			return downStatus;
		}
		throw new Exception();
	}
	
	/*
	 * ==================== == CONFIGS `END`== ====================
	 */

}
