package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendDtl;
import th.go.excise.ims.ia.persistence.entity.IaFollowRecommendHdr;
import th.go.excise.ims.ia.persistence.repository.IaFollowRecommendDtlRepository;
import th.go.excise.ims.ia.persistence.repository.IaFollowRecommendHdrRepository;
import th.go.excise.ims.ia.vo.Int110401DtlVo;
import th.go.excise.ims.ia.vo.Int1104Vo;

@Service
public class Int110401Service {
	
	@Autowired
	private IaFollowRecommendHdrRepository iaFollowRecommendHdrRepository;
	
	@Autowired
	private IaFollowRecommendDtlRepository iaFollowRecommendDtlRepository;

	public Int1104Vo findByIdHdr(BigDecimal idHdr) {
		IaFollowRecommendHdr dataHdr = iaFollowRecommendHdrRepository.findById(idHdr).get();
		
		Int1104Vo response = new Int1104Vo();
		response.setId(dataHdr.getId());
		response.setApproveDate(dataHdr.getApproveDate());
		response.setArea(dataHdr.getArea());
		response.setBudgetYear(dataHdr.getBudgetYear());
		response.setCheckType(dataHdr.getCheckType());
		response.setDateClosedWork(dataHdr.getDateClosedWork());
		response.setExciseCode(dataHdr.getExciseCode());
		response.setInspectionWork(dataHdr.getInspectionWork());
		response.setNoteClosedWork(dataHdr.getNoteClosedWork());
		response.setProjectCode(dataHdr.getProjectCode());
		response.setSector(dataHdr.getSector());
		response.setProjectName(dataHdr.getProjectName());
//		response.setReportDate();
		response.setSystemCode(dataHdr.getSystemCode());
		response.setSystemName(dataHdr.getSystemName());
		response.setStatus(dataHdr.getStatus());
		
		/* find dtls */
		List<Int110401DtlVo> dtls = findDtlByIdHdr(idHdr);
		response.setDetails(dtls);
		
		return response;
	}
	
	private List<Int110401DtlVo> findDtlByIdHdr(BigDecimal idHdr) {
		List<Int110401DtlVo> responseDtl = new ArrayList<Int110401DtlVo>();
		Int110401DtlVo vo = null;
		List<IaFollowRecommendDtl> dataDtls = iaFollowRecommendDtlRepository.findByIdFollowRecommendHdrAndIsDeleted(idHdr, "N");
		for (IaFollowRecommendDtl iaFollowRecommendDtl : dataDtls) {
			vo = new Int110401DtlVo();
			vo.setId(iaFollowRecommendDtl.getId());
			vo.setIdFollowRecommendHdr(iaFollowRecommendDtl.getIdFollowRecommendHdr());
			vo.setFollowNotifyBookNumber(iaFollowRecommendDtl.getFollowNotifyBookNumber());
			vo.setResultNotifyBookNumber(iaFollowRecommendDtl.getResultNotifyBookNumber());
			vo.setFollowReportBookNumber(iaFollowRecommendDtl.getFollowReportBookNumber());
			vo.setTimeNotify(iaFollowRecommendDtl.getTimeNotify());
			vo.setDaedlinesStartStr(ConvertDateUtils.formatDateToString(iaFollowRecommendDtl.getDaedlinesStart(), ConvertDateUtils.DD_MM_YYYY));
			vo.setDaedlinesEndStr(ConvertDateUtils.formatDateToString(iaFollowRecommendDtl.getDaedlinesEnd(), ConvertDateUtils.DD_MM_YYYY));
			vo.setFollowNotifyDateStr(ConvertDateUtils.formatDateToString(iaFollowRecommendDtl.getFollowNotifyDate(), ConvertDateUtils.DD_MM_YYYY));
			vo.setFollowReportDateStr(ConvertDateUtils.formatDateToString(iaFollowRecommendDtl.getFollowReportDate(), ConvertDateUtils.DD_MM_YYYY));
			vo.setResultNotifyDateStr(ConvertDateUtils.formatDateToString(iaFollowRecommendDtl.getResultNotifyDate(), ConvertDateUtils.DD_MM_YYYY));
			vo.setFlagUpdate(true);
			responseDtl.add(vo);
		}
		return responseDtl;
	}

	public void savefollowRecommendDtl(List<Int110401DtlVo> request) {
		IaFollowRecommendDtl entity = null;
		for (Int110401DtlVo int110401Vo : request) {
			entity = new IaFollowRecommendDtl();
			if(!int110401Vo.getFlagUpdate() || int110401Vo.getId() == null) {
				entity.setIdFollowRecommendHdr(int110401Vo.getIdFollowRecommendHdr());
				entity.setFollowNotifyBookNumber(int110401Vo.getFollowNotifyBookNumber());
				entity.setResultNotifyBookNumber(int110401Vo.getResultNotifyBookNumber());
				entity.setFollowReportBookNumber(int110401Vo.getFollowReportBookNumber());
				entity.setTimeNotify(int110401Vo.getTimeNotify());
				entity.setDaedlinesStart(ConvertDateUtils.parseStringToDate(int110401Vo.getDaedlinesStartStr(), ConvertDateUtils.DD_MM_YYYY));
				entity.setDaedlinesEnd(ConvertDateUtils.parseStringToDate(int110401Vo.getDaedlinesEndStr(), ConvertDateUtils.DD_MM_YYYY));
				entity.setFollowNotifyDate(ConvertDateUtils.parseStringToDate(int110401Vo.getFollowNotifyDateStr(), ConvertDateUtils.DD_MM_YYYY));
				entity.setFollowReportDate(ConvertDateUtils.parseStringToDate(int110401Vo.getFollowReportDateStr(), ConvertDateUtils.DD_MM_YYYY));
				entity.setResultNotifyDate(ConvertDateUtils.parseStringToDate(int110401Vo.getResultNotifyDateStr(), ConvertDateUtils.DD_MM_YYYY));
				iaFollowRecommendDtlRepository.save(entity);
				
			}
		}
	}

}
