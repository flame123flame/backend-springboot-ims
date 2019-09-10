package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaConcludeFollowHdr;
import th.go.excise.ims.ia.persistence.repository.IaConcludeFollowHdrRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaConcludeFollowDetailJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaConcludeFollowHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int1101FormVo;
import th.go.excise.ims.ia.vo.Int1101Vo;
import th.go.excise.ims.ia.vo.Int1102FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Service
public class Int1101Service {
	@Autowired
	private IaConcludeFollowHdrRepository iaConcludeFollowHdrRepository;

	@Autowired
	private IaConcludeFollowDetailJdbcRepository iaConcludeFollowDetailJdbcRepository;
	
	@Autowired
	private IaConcludeFollowHdrJdbcRepository iaConcludeFollowHdrJdbcRepository;
	
	public Int11Vo list(String id) {
		IaConcludeFollowHdr dataList = iaConcludeFollowHdrRepository.findById(Long.parseLong(id)).get();
		Int11Vo int11Vo = new Int11Vo();
		int11Vo.setApproveDateString(
				ConvertDateUtils.formatDateToString(dataList.getApproveDate(), ConvertDateUtils.DD_MM_YYYY));
		int11Vo.setDateFromString(
				ConvertDateUtils.formatDateToString(dataList.getDateFrom(), ConvertDateUtils.DD_MM_YYYY));
		int11Vo.setDateToString(ConvertDateUtils.formatDateToString(dataList.getDateTo(), ConvertDateUtils.DD_MM_YYYY));
		int11Vo.setProjectName(dataList.getProjectName());
		int11Vo.setApprovers(dataList.getApprovers());
		int11Vo.setBudgetYear(dataList.getBudgetYear());
		int11Vo.setCheckType(dataList.getCheckType());
		int11Vo.setCheckStatus(dataList.getCheckStatus());
		int11Vo.setNotation(dataList.getNotation());
		int11Vo.setSystemName(dataList.getSystemName());
		int11Vo.setSector(dataList.getSector());
		int11Vo.setInspectionWork(dataList.getInspectionWork());
		int11Vo.setInspectionUnit(dataList.getInspectionUnit());
		int11Vo.setReportNumber(dataList.getReportNumber());
		int11Vo.setDateReceiving(dataList.getDateReceiving());
		int11Vo.setReportingResults(dataList.getReportingResults());
		int11Vo.setHeadInspectionUnit(dataList.getHeadInspectionUnit());
		return int11Vo;
	}
	
	public List<Int1101Vo> findConcludeFollowHdrDetailList(String id) {
		List<Int1101Vo> dataList = iaConcludeFollowDetailJdbcRepository.getDataDetailList(id);
		
		for (Int1101Vo int1101VoList : dataList) {
			int1101VoList.setUpdatedBy(null);
			int1101VoList.setUpdatedDate(null);
			int1101VoList.setCreatedBy(null);
			int1101VoList.setCreatedDate(null);
			int1101VoList.setVersion(null);
		}

		return dataList;
	}
	
	public void updateSentStatus(Int1101FormVo form) {
		iaConcludeFollowHdrJdbcRepository.updateSentStatus(form);
	}
	
	
}
