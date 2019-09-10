package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideDtlRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101FormVo;
import th.go.excise.ims.ia.vo.Int02010101Vo;

@Service
public class Int02010101Service {

	private static final Logger logger = LoggerFactory.getLogger(Int02010101Service.class);

	@Autowired
	private IaQuestionnaireSideDtlJdbcRepository iaQuestionnaireSideDtlJdbcRepository;

	@Autowired
	private IaQuestionnaireSideDtlRepository iaQuestionnaireSideDtlRepository;
	
	@Autowired
	private UpdateStatusQuestionnaireService questionnaireService;

	public List<Int02010101Vo> findByIdSide(String idSideStr) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		List<Int02010101Vo> mains = iaQuestionnaireSideDtlJdbcRepository.findByIdSide(idSide);
		List<Int02010101Vo> details = new ArrayList<>();
		List<Int02010101Vo> detailDtls = new ArrayList<>();
		for (Int02010101Vo main : mains) {
			BigDecimal seq = main.getSeq();
			details = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(idSide, seq, main.getId());
			for (Int02010101Vo detail : details) {
				BigDecimal seqDtl = detail.getSeqDtl();
				detailDtls = iaQuestionnaireSideDtlJdbcRepository.findDtlsByIdSide(idSide, seqDtl, detail.getId());
				detail.setChildren(detailDtls);
			}
			main.setChildren(details);
		}
		return mains;
	}

	public List<IaQuestionnaireSideDtl> save(List<IaQuestionnaireSideDtl> request) {
		return (List<IaQuestionnaireSideDtl>) iaQuestionnaireSideDtlRepository.saveAll(request);
	}

	@Transactional
	public Int02010101FormVo saveAll(Int02010101FormVo form) {
		// VARIABLES
		BigDecimal idLevel1 = null;
		BigDecimal idLevel2 = null;
		
		// DELETE
		if (form.getDelete().toArray().length > 0) {
			List<IaQuestionnaireSideDtl> delete = form.getDelete();
			for (IaQuestionnaireSideDtl iaQuestionnaireSideDtl : delete) {
				iaQuestionnaireSideDtlRepository.deleteById(iaQuestionnaireSideDtl.getId());
			}
		}
		
		// SAVE
		if (form.getSave().toArray().length > 0) {
			for (IaQuestionnaireSideDtl save : form.getSave()) {
				IaQuestionnaireSideDtl item = save;
				if (item.getId() != null) {
					BigDecimal id = item.getId();
					save = iaQuestionnaireSideDtlJdbcRepository.findById(id);
					save.setQtnLevel(item.getQtnLevel());
					save.setSideDtl(item.getSideDtl());
					save.setSeq(item.getSeq());
					save.setSeqDtl(item.getSeqDtl());
					save.setIdHeading(item.getIdHeading());
				} else {
					save = item;
					if (save.getQtnLevel().compareTo(new BigDecimal(2)) == 0) {
						save.setIdHeading(idLevel1);
					}
					if (save.getQtnLevel().compareTo(new BigDecimal(3)) == 0) {
						save.setIdHeading(idLevel2);
					}
				}
				save = iaQuestionnaireSideDtlRepository.save(save);
				if (save.getQtnLevel().compareTo(new BigDecimal(1)) == 0) {
					idLevel1 = save.getId();
				}
				if (save.getQtnLevel().compareTo(new BigDecimal(2)) == 0) {
					idLevel2 = save.getId();
				}
			}
		}
		return form;
	}
	
	//UPDATE STATUS
	public void updateStatusIaQuestionnaireAutomatic(BigDecimal idHead) {
		questionnaireService.updateStatusIaQuestionnaireAutomatic(idHead);
	}
}
