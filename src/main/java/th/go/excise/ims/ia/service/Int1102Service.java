package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.entity.IaConcludeFollowHdr;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendHdr;
import th.go.excise.ims.ia.persistence.repository.IaConcludeFollowHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaFollowRecommendHdrRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int1102JdbcRepository;
import th.go.excise.ims.ia.vo.Int1102FormVo;
import th.go.excise.ims.ia.vo.Int1102Vo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Service
public class Int1102Service {
	
	@Autowired
	private Int1102JdbcRepository int1102JdbcRepository;
	
	@Autowired
	private IaConcludeFollowHdrRepository iaConcludeFollowHdrRepository;
	
	@Autowired
	private IaFollowRecommendHdrRepository iaFollowRecommendHdrRepository;
	
	public DataTableAjax<Int1102Vo> list(Int1102FormVo form) {
		List<Int1102Vo> dataList = int1102JdbcRepository.getData(form);
		
		DataTableAjax<Int1102Vo> dataTableAjax = new DataTableAjax<Int1102Vo>();
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}
	
	public DataTableAjax<Int11Vo> listConFol(Int1102FormVo form) {
		List<Int11Vo> dataList = int1102JdbcRepository.getDataConFol(form);		
		DataTableAjax<Int11Vo> dataTableAjax = new DataTableAjax<Int11Vo>();
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}
	
	public void updateCheckStatus(Int1102FormVo form) {
		int1102JdbcRepository.updateCheckStatus(form);
		
		IaConcludeFollowHdr dataFilter = new IaConcludeFollowHdr();
		dataFilter = iaConcludeFollowHdrRepository.findById(Long.valueOf(form.getId())).get();
		/* UPDATE 'IA_CONCLUDE_FOLLOW_HDR' */
		dataFilter.setCheckStatus(form.getCheckStatus());
		dataFilter.setApproveDate(form.getApproveDate());
		dataFilter.setNotation(form.getNotation());
		iaConcludeFollowHdrRepository.save(dataFilter);
		
		/* INSERT 'IA_FOLLOW_RECOMMEND_HDR' */
		IaFollowRecommendHdr entityIaFollowRecommendHdr = new IaFollowRecommendHdr();
		entityIaFollowRecommendHdr.setApproveDate(form.getApproveDate());
		entityIaFollowRecommendHdr.setArea(dataFilter.getArea());
		entityIaFollowRecommendHdr.setBudgetYear(dataFilter.getBudgetYear());
		entityIaFollowRecommendHdr.setCheckType(dataFilter.getCheckType());
//		entityIaFollowRecommendHdr.setDateClosedWork(dateClosedWork);
		entityIaFollowRecommendHdr.setExciseCode(dataFilter.getExciseCode());
		entityIaFollowRecommendHdr.setIdConcludeFollowHdr(new BigDecimal(dataFilter.getId()));
		entityIaFollowRecommendHdr.setInspectionWork(dataFilter.getInspectionWork());
//		entityIaFollowRecommendHdr.setNoteClosedWork(noteClosedWork);
//		entityIaFollowRecommendHdr.setNotifyDateFrom(notifyDateFrom);
//		entityIaFollowRecommendHdr.setNotifyDateTo(notifyDateTo);
//		entityIaFollowRecommendHdr.setNotifyNo();
		entityIaFollowRecommendHdr.setProjectCode(dataFilter.getProjectCode());
		entityIaFollowRecommendHdr.setSector(dataFilter.getSector());
		entityIaFollowRecommendHdr.setProjectName(dataFilter.getProjectName());
//		entityIaFollowRecommendHdr.setReportDate();
		entityIaFollowRecommendHdr.setSystemCode(dataFilter.getSystemCode());
		entityIaFollowRecommendHdr.setSystemName(dataFilter.getSystemName());
//		entityIaFollowRecommendHdr.setStatus(status);
		iaFollowRecommendHdrRepository.save(entityIaFollowRecommendHdr);
	}
	
}
