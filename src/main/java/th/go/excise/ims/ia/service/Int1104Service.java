package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendDtl;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendHdr;
import th.go.excise.ims.ia.persistence.repository.IaFollowRecommendHdrRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaFollowRecommendDtlJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaFollowRecommendHdrJdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.Int1104Vo;

@Service
public class Int1104Service {
	private static final Logger logger = LoggerFactory.getLogger(Int1104Service.class);
	
	@Autowired
	private IaFollowRecommendHdrRepository iaFollowRecommendHdrRepository;
	
	@Autowired
	private IaFollowRecommendHdrJdbcRepository iaFollowRecommendHdrJdbcRepository;
	
	@Autowired
	private IaFollowRecommendDtlJdbcRepository iaFollowRecommendDtlJdbcRepository;
	
	public List<Int1104Vo> findByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaFollowRecommendHdr> dataFilter = iaFollowRecommendHdrJdbcRepository.getDataFilter(budgetYear, inspectionWork);
		
		List<Int1104Vo> response = new ArrayList<>();
		Int1104Vo int1104Vo = null;
		for (IaFollowRecommendHdr iaFollowRecommendHdr : dataFilter) {
			int1104Vo = new Int1104Vo();
			int1104Vo.setId(iaFollowRecommendHdr.getId());
			int1104Vo.setApproveDate(iaFollowRecommendHdr.getApproveDate());
			int1104Vo.setArea(iaFollowRecommendHdr.getArea());
			int1104Vo.setBudgetYear(iaFollowRecommendHdr.getBudgetYear());
			int1104Vo.setCheckType(iaFollowRecommendHdr.getCheckType());
			int1104Vo.setDateClosedWork(iaFollowRecommendHdr.getDateClosedWork());
			int1104Vo.setExciseCode(iaFollowRecommendHdr.getExciseCode());
			int1104Vo.setInspectionWork(iaFollowRecommendHdr.getInspectionWork());
			int1104Vo.setNoteClosedWork(iaFollowRecommendHdr.getNoteClosedWork());
//			int1104Vo.setNotifyDateFrom(iaFollowRecommendHdr.getNotifyDateFrom());
//			int1104Vo.setNotifyDateTo(iaFollowRecommendHdr.getNotifyDateTo());
//			int1104Vo.setNotifyNo(iaFollowRecommendHdr.getNotifyNo());
			int1104Vo.setProjectCode(iaFollowRecommendHdr.getProjectCode());
			int1104Vo.setSector(iaFollowRecommendHdr.getSector());
			int1104Vo.setProjectName(iaFollowRecommendHdr.getProjectName());
//			int1104Vo.setReportDate();
			int1104Vo.setSystemCode(iaFollowRecommendHdr.getSystemCode());
			int1104Vo.setSystemName(iaFollowRecommendHdr.getSystemName());
			int1104Vo.setStatus(iaFollowRecommendHdr.getStatus());
			
			/* find details */
			List<IaFollowRecommendDtl> dataDtl = iaFollowRecommendDtlJdbcRepository.getDataInDeadline(iaFollowRecommendHdr.getId());
			for (IaFollowRecommendDtl iaFollowRecommendDtl : dataDtl) {
				int1104Vo.setFollowNotifyDate(iaFollowRecommendDtl.getFollowNotifyDate());
				int1104Vo.setResultNotifyDate(iaFollowRecommendDtl.getResultNotifyDate());
				int1104Vo.setFollowNotifyDateStr(ConvertDateUtils.formatDateToString(iaFollowRecommendDtl.getFollowNotifyDate(), ConvertDateUtils.DD_MM_YY));
				int1104Vo.setResultNotifyDateStr(ConvertDateUtils.formatDateToString(iaFollowRecommendDtl.getResultNotifyDate(), ConvertDateUtils.DD_MM_YY));
				int1104Vo.setTimeNotify(iaFollowRecommendDtl.getTimeNotify());
			}
			
			/* set ExciseDepartmentVo */
			logger.info(iaFollowRecommendHdr.getExciseCode());
			if(iaFollowRecommendHdr.getExciseCode() != null) {
				int1104Vo.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(int1104Vo.getExciseCode()));
			}
			response.add(int1104Vo);
		}
		
		return response;
	}

}
