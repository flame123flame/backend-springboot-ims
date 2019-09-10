package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeHdrJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeJdbcRepository;
import th.go.excise.ims.ia.vo.Int020201ConcludeVo;
import th.go.excise.ims.ia.vo.Int020201DtlVo;
import th.go.excise.ims.ia.vo.Int020201JoinVo;
import th.go.excise.ims.ia.vo.Int020201SidesFormVo;
import th.go.excise.ims.ia.vo.Int020201Vo;

@Service
public class Int020201Service {

	@Autowired
	private IaQuestionnaireMadeJdbcRepository iaQuestionnaireMadeJdbcRepository;

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRepository;

	@Autowired
	private IaQuestionnaireMadeRepository iaQuestionnaireMadeRepository;

	@Autowired
	private IaQuestionnaireMadeHdrRepository iaQuestionnaireMadeHdrRepository;

	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	@Autowired
	private UpdateStatusQuestionnaireService questionnaireService;
	
	@Autowired
	private IaQuestionnaireMadeHdrJdbcRepository iaQuestionnaireMadeHdrJdbcRepository;

	public List<IaQuestionnaireSide> findQtnSideById(Int020201SidesFormVo request) {
		return iaQuestionnaireSideRepository.findByidHeadAndIsDeletedOrderBySeqAsc(request.getIdSide(), "N");
	}

	public IaQuestionnaireMadeHdr findQtnMadeHdrById(BigDecimal id) {
		return iaQuestionnaireMadeHdrRepository.findById(id).get();
	}

	public Int020201Vo findQtnSideDtlById(Int020201Vo dataQtn) {
		List<Int020201JoinVo> dataLVL1 = null;
		List<Int020201JoinVo> dataLVL2 = null;
		List<Int020201JoinVo> dataLVL3 = null;

		for (Int020201SidesFormVo requestSide : dataQtn.getHeader()) {
			/* initial variable 'checkNull' */
			Integer checkNull = 0;
			dataLVL1 = new ArrayList<Int020201JoinVo>();
			dataLVL1 = iaQuestionnaireMadeJdbcRepository.findLvl1ByIdMadeHdr(requestSide);

			for (Int020201JoinVo objLVL1 : dataLVL1) {
				dataLVL2 = new ArrayList<Int020201JoinVo>();
				dataLVL2 = iaQuestionnaireMadeJdbcRepository.findLvl2ByIdMadeHdr(objLVL1);
				objLVL1.setChildren(dataLVL2);
				/* check children level-1 */
				if (dataLVL2.size() > 0) {
					/* level 1 have children */
					for (Int020201JoinVo objLVL2 : dataLVL2) {
						dataLVL3 = new ArrayList<Int020201JoinVo>();
						dataLVL3 = iaQuestionnaireMadeJdbcRepository.findLvl3ByIdMadeHdr(objLVL2);
						objLVL2.setChildren(dataLVL3);

						if (dataLVL3.size() == 0) {
							/* level-2 not have children */
							checkNull += iaQuestionnaireMadeJdbcRepository.countCheckNull(objLVL2, 2);
						} else {
							/* check null level-3 */
							for (Int020201JoinVo objLVL3 : dataLVL3) {
								checkNull += iaQuestionnaireMadeJdbcRepository.countCheckNull(objLVL3, 3);
							}
						}
					}
				} else {
					/* level-1 not have children */
					checkNull += iaQuestionnaireMadeJdbcRepository.countCheckNull(objLVL1, 1);
				}
			}
			requestSide.setSides(dataLVL1);
			if (checkNull > 0) {
				requestSide.setStatusSides(false);
			} else {
				requestSide.setStatusSides(true);
			}
		}
		return dataQtn;
	}

	public void updateQtnMadeByRequest(Int020201DtlVo request) throws Exception {
		if (request.getQtnMadeList().size() > 0) {
			IaQuestionnaireMade resMadeDtl = null;
			for (IaQuestionnaireMade objMadeDtl : request.getQtnMadeList()) {
				/* check data */
				resMadeDtl = iaQuestionnaireMadeRepository.findById(objMadeDtl.getId()).get();
				resMadeDtl.setNote(objMadeDtl.getNote());
				resMadeDtl.setCheckFlag(objMadeDtl.getCheckFlag());
				iaQuestionnaireMadeRepository.save(resMadeDtl);
					
//					iaQuestionnaireMadeJdbcRepository.updateStatusMade(objMadeDtl);
			}
			updateStatusQtnMadeHdr(request);
		}
	}
	
	@Transactional
	public void updateConclude(Int020201ConcludeVo request) throws Exception {
		IaQuestionnaireMadeHdr iaMadeHdrData = iaQuestionnaireMadeHdrRepository.findById(request.getIdMadeHdr()).get();
		iaMadeHdrData.setConclude(request.getConclude());
		iaQuestionnaireMadeHdrRepository.save(iaMadeHdrData);
	}
	
	private void updateStatusQtnMadeHdr(Int020201DtlVo request) {
		if (IaConstants.IA_STATUS_REPLY_QTN.STATUS_1_CODE.equals(request.getStatus()) && !request.getFlagConfirm()) {
			/* update status madeHdr */
			questionnaireService.updateStatusIaQuestionnaireMadeHdrAndDTL(request.getIdMadeHdr(), Integer.toString(Integer.parseInt(request.getStatus()) + 1));
		}

		/* confirm send questionnaire form */
		if (request.getFlagConfirm()) {
			/* find id Hdr */
			Optional<IaQuestionnaireMadeHdr> resMadeHdr = iaQuestionnaireMadeHdrRepository.findById(request.getIdMadeHdr());
			if (resMadeHdr.isPresent()) {
				IaQuestionnaireMadeHdr madeHdr = resMadeHdr.get();
				/* find status Hdr */
				Optional<IaQuestionnaireHdr> resHdr = iaQuestionnaireHdrRepository.findById(madeHdr.getIdHdr());
				if (resHdr.isPresent()) {
					IaQuestionnaireHdr hdr = resHdr.get();
					/* update status madeHdr */
					questionnaireService.updateStatusIaQuestionnaireMadeHdrAndDTL(request.getIdMadeHdr(), Integer.toString(Integer.parseInt(request.getStatus()) + 1));
					/* update status Hdr */
					questionnaireService.updateStatusIaQuestionnaire(hdr.getId(), Integer.toString(Integer.parseInt(hdr.getStatus()) + 1));
				}
			}
		}
	}

	public Boolean countCheckQtn(BigDecimal idHdr) {
		Boolean checkQtn = false;
		Integer count = iaQuestionnaireMadeHdrJdbcRepository.checkCountMadeHdrStatus3(idHdr);
		Integer countAll = iaQuestionnaireMadeHdrJdbcRepository.checkCountMadeHdrAll(idHdr);
		if(count==countAll) {
			checkQtn = true;
		}
		
		return checkQtn;
	}

	public void updateStatusReplyQtn(BigDecimal idMadeHdr, String status) {
		questionnaireService.updateStatusIaQuestionnaireMadeHdrAndDTL(idMadeHdr, IaConstants.IA_STATUS_REPLY_QTN.STATUS_2_CODE);
	}

}
