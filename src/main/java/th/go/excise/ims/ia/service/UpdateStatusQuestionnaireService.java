package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireHdrJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeHdrJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;

@Service
public class UpdateStatusQuestionnaireService {

	private static final Logger logger = LoggerFactory.getLogger(UpdateStatusQuestionnaireService.class);

//	****************** JDBC ****************** 
	@Autowired
	private IaQuestionnaireHdrJdbcRepository iaQuestionnaireHdrJdbcRepository;
	
	@Autowired
	private IaQuestionnaireMadeHdrJdbcRepository iaQuestionnaireMadeHdrJdbcRepository;
	
	@Autowired
	private IaQuestionnaireMadeJdbcRepository iaQuestionnaireMadeJdbcRepository;
	
	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQuestionnaireSideJdbcRepository;


//	****************** JPA ****************** 
	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;
	
	@Autowired
	private IaQuestionnaireMadeHdrRepository iaQuestionnaireMadeHdrRepository;
	
	@Autowired
	private IaQuestionnaireMadeRepository iaQuestionnaireMadeRepository;

	
	public BigDecimal updateStatusIaQuestionnaire(BigDecimal idHdr,String status) {
		
		
		if(IaConstants.IA_STATUS.STATUS_5_CODE.equals(status)||IaConstants.IA_STATUS.STATUS_6_CODE.equals(status)) {
			Integer count = iaQuestionnaireMadeHdrJdbcRepository.checkCountMadeHdrStatus3(idHdr);
			Integer countAll = iaQuestionnaireMadeHdrJdbcRepository.checkCountMadeHdrAll(idHdr);
			if(count==countAll) {
				iaQuestionnaireHdrJdbcRepository.updateStatus(idHdr, status);
			}
		}else {
			iaQuestionnaireHdrJdbcRepository.updateStatus(idHdr, status);
		}
		
		
		return idHdr;
	}
	
	public BigDecimal downStatusIaQuestionnaire(BigDecimal idHdr) {
		
		List<ParamInfo> paramInfoList = ApplicationCache.getParamInfoListByGroupCode(IaConstants.IA_STATUS.PARAM_GROUP_CODE);
		IaQuestionnaireHdr iaQuestionnaireHdr = iaQuestionnaireHdrRepository.findById(idHdr).get();
	
		for (ParamInfo paramInfo : paramInfoList) {
			
			if(paramInfo.getParamCode().equals(iaQuestionnaireHdr.getStatus())) {
				
				for (ParamInfo paramInfo2 : paramInfoList) {
					
					if(paramInfo.getSortingOrder()-1 == paramInfo2.getSortingOrder()) {
						
						

							if(IaConstants.IA_STATUS.STATUS_4_CODE.equals(paramInfo.getSortingOrder().toString())) {
//								Check Table MadeHdr Count Finish == 0 Up
								Integer count = iaQuestionnaireMadeHdrJdbcRepository.checkCountMadeHdrStatus3(idHdr);
								if(count==0) {
									iaQuestionnaireHdrJdbcRepository.updateStatus(idHdr, paramInfo2.getParamCode());
									iaQuestionnaireMadeJdbcRepository.deleteByIdHdr(idHdr);
									iaQuestionnaireMadeHdrJdbcRepository.deleteByIdHdr(idHdr);
								}
							}else {
								iaQuestionnaireHdrJdbcRepository.updateStatus(idHdr, paramInfo2.getParamCode());
							}
							
					}
				}
			}
		}
		
		
		return idHdr;
	}
	
	public BigDecimal updateStatusIaQuestionnaireAutomatic(BigDecimal idHdr) {
	
		Integer countSide = iaQuestionnaireSideJdbcRepository.checkCountSide(idHdr);
		Integer countSideDtl = iaQuestionnaireSideJdbcRepository.checkCountSideDtl(idHdr);
		
//		*********** ID_HDR Check Table Side !=0 And Table ID Side => SideDTL Count != 0 *********** 
		if(countSideDtl==0&&countSide>0) {
			String status = IaConstants.IA_STATUS.STATUS_2_CODE;
			updateStatusIaQuestionnaire(idHdr, status);
		}else {
			String status = IaConstants.IA_STATUS.STATUS_1_CODE;
			updateStatusIaQuestionnaire(idHdr, status);
		}
		
		return idHdr;
	}
	
	
	public BigDecimal updateStatusIaQuestionnaireMadeHdrAndDTL(BigDecimal idMadeHdr,String status) {
		String username = UserLoginUtils.getCurrentUserBean().getUserThaiName();
		iaQuestionnaireMadeHdrJdbcRepository.updateStatus(idMadeHdr, status,username);
		iaQuestionnaireMadeJdbcRepository.updateStatus(idMadeHdr, status,username);
		
		
		return idMadeHdr;
	}
	
	

}
