package th.go.excise.ims.ta.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetStep;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetStepRepository;
import th.go.excise.ims.ta.vo.AuditStepFormVo;
import th.go.excise.ims.ta.vo.AuditStepVo;

@Service
public class AuditStepService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuditStepService.class);
	
	@Autowired
	private TaPlanWorksheetStepRepository taPlanWorksheetStepRepository;
	
	public List<AuditStepVo> getAuditStepVoList(AuditStepFormVo formVo) {
		logger.info("getAuditStepVoList auditPlanCode={}", formVo.getAuditPlanCode());
		return taPlanWorksheetStepRepository.findByAuditPlanCode(formVo.getAuditPlanCode());
	}
	
	public void saveAuditStep(AuditStepFormVo formVo) {
		logger.info("saveAuditStep auditPlanCode={}, auditStepStatus={}, auditStepFlag={}, formTsCode={}, formTsNumber={}",
				formVo.getAuditPlanCode(), formVo.getAuditStepStatus(), formVo.getAuditStepFlag(),
				formVo.getFormTsCode(), formVo.getFormTsNumber());
		
		TaPlanWorksheetStep entity = taPlanWorksheetStepRepository.findByFormTsNumber(formVo.getFormTsNumber());
		if (entity != null) {
			entity.setProcessDate(LocalDateTime.now());
		} else {
			entity = new TaPlanWorksheetStep();
			entity.setAuditPlanCode(formVo.getAuditPlanCode());
			entity.setAuditStepStatus(formVo.getAuditStepStatus());
			entity.setAuditStepSubStatus(formVo.getAuditStepSubStatus());
			entity.setAuditStepFlag(formVo.getAuditStepFlag());
			entity.setFormTsCode(formVo.getFormTsCode());
			entity.setFormTsNumber(formVo.getFormTsNumber());
			entity.setProcessDate(LocalDateTime.now());
		}
		
		taPlanWorksheetStepRepository.save(entity);
	}
	
}
