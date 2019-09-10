package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideDtlRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;
import th.go.excise.ims.ia.vo.Int020101NameVo;
import th.go.excise.ims.ia.vo.Int020101Vo;
import th.go.excise.ims.ia.vo.Int020101YearVo;

@Service
public class Int020101Service {

	private static final Logger logger = LoggerFactory.getLogger(Int020101Service.class);

	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQtnSideJdbcRep;

	@Autowired
	private UpdateStatusQuestionnaireService questionnaireService;

	@Autowired
	private IaQuestionnaireSideRepository iaQtnSideRep;

	@Autowired
	private IaQuestionnaireSideDtlRepository iaQtnSideDtlRep;

	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	public List<Int020101Vo> findAll() {
		return iaQtnSideJdbcRep.findAll();
	}

	public List<Int020101Vo> findByIdHead(String idHeadStr) {
		BigDecimal idHead = new BigDecimal(idHeadStr);
		return iaQtnSideJdbcRep.findByIdHead(idHead);
	}

	public List<Int020101YearVo> findByUsername(String username) {
		return iaQtnSideJdbcRep.findByUsername(username);
	}

	public List<Int020101YearVo> findByStatus() {
		return iaQtnSideJdbcRep.findByStatus();
	}

	public List<Int020101NameVo> findByYearAndUsername(String year, String username) {
		return iaQtnSideJdbcRep.findByYearAndUsername(year, username);
	}

	public List<Int020101NameVo> findByYearAndStatus(String year, String id) {
		return iaQtnSideJdbcRep.findByYearAndStatus(year, id);
	}

	public IaQuestionnaireSide save(IaQuestionnaireSide request) {
		IaQuestionnaireSide req = iaQtnSideRep.save(request);
		questionnaireService.updateStatusIaQuestionnaireAutomatic(req.getIdHead());
		return req;
	}

	@Transactional
	public List<IaQuestionnaireSide> saveAll(List<IaQuestionnaireSide> request) {
		// array of old id
		List<BigDecimal> ids = new ArrayList<>();
		for (IaQuestionnaireSide req : request) {
			ids.add(req.getId()); // add idSide
			req.setId(null); // remove old id
		}

		// Saved
		List<IaQuestionnaireSide> newSides = (List<IaQuestionnaireSide>) iaQtnSideRep.saveAll(request);
		logger.debug("Int020101Service::saveAll => SAVED");

		// array of new id
		List<BigDecimal> idsNew = new ArrayList<>();
		for (IaQuestionnaireSide newSide : newSides) {
			idsNew.add(newSide.getId()); // add idSide
		}
		List<IaQuestionnaireSideDtl> qtnDtls = iaQtnSideJdbcRep.findBySideIds(ids);
		for (int i = 0; i < qtnDtls.toArray().length; i++) {
			for (int j = 0; j < ids.toArray().length; j++) {
				if (ids.get(j).compareTo(qtnDtls.get(i).getIdSide()) == 0) {
					qtnDtls.get(i).setIdSide(idsNew.get(j));
				}
			}
		}

		// On Saved Details
		qtnDtls = (List<IaQuestionnaireSideDtl>) iaQtnSideDtlRep.saveAll(qtnDtls);

		// VARIABLES
		BigDecimal idLevel1 = null;
		BigDecimal idLevel2 = null;
		for (IaQuestionnaireSideDtl qtnDtl : qtnDtls) {
			// On Level 1
			if (qtnDtl.getQtnLevel().compareTo(new BigDecimal(1)) == 0) {
				idLevel1 = qtnDtl.getId();
			}
			// On Level 2
			if (qtnDtl.getQtnLevel().compareTo(new BigDecimal(2)) == 0) {
				qtnDtl.setIdHeading(idLevel1);
				idLevel2 = qtnDtl.getId();
			}
			// On Level 3
			if (qtnDtl.getQtnLevel().compareTo(new BigDecimal(3)) == 0) {
				qtnDtl.setIdHeading(idLevel2);
			}
		}

		// On Saved Details after add id heading
		iaQtnSideDtlRep.saveAll(qtnDtls);

		return newSides;
	}

	@Transactional
	public IaQuestionnaireSide update(String idStr, IaQuestionnaireSide request) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireSide data = iaQtnSideJdbcRep.findOne(id);
		data.setSideName(request.getSideName());
		return iaQtnSideRep.save(data);
	}

	@Transactional
	public IaQuestionnaireSide delete(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireSide data = iaQtnSideJdbcRep.findOne(id);
		/* delete head */
		iaQtnSideRep.deleteById(id);

		/* delete children */
		List<IaQuestionnaireSideDtl> sideDtlList = iaQtnSideDtlRep.findByIdSide(id);
		for (IaQuestionnaireSideDtl sideDtl : sideDtlList) {
			sideDtl.setIsDeleted("Y");
			iaQtnSideDtlRep.save(sideDtl);
		}

		return data;
	}

	public IaQuestionnaireSide findOne(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return iaQtnSideJdbcRep.findOne(id);
	}

	@Transactional
	public IaQuestionnaireHdr deleteQtn(BigDecimal id, String choiceStr) {
		IaQuestionnaireHdr response = null;
		if ("CANCEL".equals(choiceStr)) {
			Optional<IaQuestionnaireHdr> resHdr = iaQuestionnaireHdrRepository.findById(id);
			if (resHdr.isPresent()) {
				IaQuestionnaireHdr dataHdr = resHdr.get();
				dataHdr.setStatus("7");
				response = iaQuestionnaireHdrRepository.save(dataHdr);
			}
		} else {
			/* delete from DB ignore isDeleted */
			iaQuestionnaireHdrRepository.deleteByIdAndIsDeleted(id, "N");
		}
		return response;
	}

	public void updateStatusIaQuestionnaireAutomatic(BigDecimal idHead) {
		questionnaireService.updateStatusIaQuestionnaireAutomatic(idHead);
	}

	public String checkUseQtn(String idHeadStr) {
		BigDecimal idHead = new BigDecimal(idHeadStr);
		return iaQtnSideJdbcRep.checkUseQtn(idHead);
	}
}
