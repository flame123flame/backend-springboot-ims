package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030102JdbcRepository;

@Service
public class UpdateStatusRiskFactorsService {

	private static final Logger logger = LoggerFactory.getLogger(UpdateStatusRiskFactorsService.class);

//	****************** JDBC ****************** 
	@Autowired
	private Int030102JdbcRepository int030102JdbcRepository;


//	****************** JPA ****************** 
	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;


	
	public BigDecimal updateStatusIaRiskFactors(BigDecimal idFactors,String statusScreen) {
		if (idFactors != null) {
			if(false) {
			
			} else {
				
				IaRiskFactors entity = iaRiskFactorsRepository.findById(idFactors).get();
				
				entity.setStatusScreen(statusScreen);
				entity.setDateCriteria(ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.DD_MM_YYYY));
				
				iaRiskFactorsRepository.save(entity);
				
			}
		}
		
		return idFactors;
	}
	
	public BigDecimal downStatusIaRiskFactors(BigDecimal idHdr) {
	

		return idHdr;
	}
	
	public String updateStatusAddIaRiskFactorsMaster(String budgetYear,BigDecimal inspectionWork) {
		if (budgetYear != null && inspectionWork != null ) {
			int030102JdbcRepository.updateStatusAddIaRiskFactorsMaster(budgetYear, inspectionWork);	
		}
		
		return budgetYear;
	}
	
	public String updateStatusSaveRiskFactorsLevel(String budgetYear,BigDecimal inspectionWork) {
		if (budgetYear != null && inspectionWork != null ) {
			int030102JdbcRepository.updateStatusSaveRiskFactorsLevel(budgetYear, inspectionWork);	
		}
		
		return budgetYear;
	}
	

}
