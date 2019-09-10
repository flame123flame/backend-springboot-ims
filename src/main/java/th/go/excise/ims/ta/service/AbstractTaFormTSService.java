package th.go.excise.ims.ta.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.repository.CommonTaFormTsRepository;
import th.go.excise.ims.ta.vo.AuditStepFormVo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

public abstract class AbstractTaFormTSService<VO extends Object, ENTITY> {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractTaFormTSService.class);
	
	protected static final String NULL = "NULL";
	
	protected TaFormTSSequenceService taFormTSSequenceService;
	protected AuditStepService auditStepService;
	
	@Autowired
	public void setTaFormTSSequenceService(TaFormTSSequenceService taFormTSSequenceService) {
		this.taFormTSSequenceService = taFormTSSequenceService;
	}

	@Autowired
	public void setAuditStepService(AuditStepService auditStepService) {
		this.auditStepService = auditStepService;
	}

	@SuppressWarnings("unchecked")
	public Class<VO> getVoClass() {
		return ((Class<VO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	protected abstract Logger getLogger();
	
	protected abstract CommonTaFormTsRepository<?, Long> getRepository();
	
	public abstract String getReportName();
	
	public abstract byte[] processFormTS(VO vo) throws Exception;

	public abstract void saveFormTS(VO vo);
	
	public void saveAuditStep(VO vo, Class<?> voClass, String formTsCode, String formTsNumber) {
		try {
			Method methodGetAuditPlanCode = voClass.getMethod("getAuditPlanCode");
			String auditPlanCode = (String) methodGetAuditPlanCode.invoke(vo);
			
			Method methodGetAuditStepStatus = voClass.getMethod("getAuditStepStatus");
			String auditStepStatus = (String) methodGetAuditStepStatus.invoke(vo);
			
			logger.info("saveAuditStep auditPlanCode={}, auditStepStatus={}, formTsCode={}, formTsNumber={}",
					auditPlanCode, auditStepStatus, formTsCode, formTsNumber);
			if (StringUtils.isNotBlank(auditPlanCode) && StringUtils.isNotBlank(auditStepStatus)) {
				AuditStepFormVo formVo = new AuditStepFormVo();
				formVo.setAuditPlanCode(auditPlanCode);
				formVo.setAuditStepStatus(auditStepStatus);
				formVo.setFormTsNumber(formTsNumber);
				formVo.setFormTsCode(formTsCode);
				auditStepService.saveAuditStep(formVo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public abstract byte[] generateReport(VO vo) throws Exception;
	
	public List<String> getFormTsNumberList(TaFormTsFormVo formVo) {
		if (StringUtils.isEmpty(formVo.getAuditPlanCode())) {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			getLogger().info("getFormTsNumberList officeCode={}", officeCode);
			return getRepository().findFormTsNumberByOfficeCode(officeCode);
		} else {
			getLogger().info("getFormTsNumberList auditPlanCode={}", formVo.getAuditPlanCode());
			return getRepository().findFormTsNumberByAuditPlanCode(formVo.getAuditPlanCode());
		}
	}
	
	public abstract VO getFormTS(String formTsNumber);
	
	protected void toVo(VO vo, ENTITY entity) {
		try {
			BeanUtils.copyProperties(vo, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
	protected void toEntity(ENTITY entity, VO vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
}
