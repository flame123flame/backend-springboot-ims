package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionScheduler;
import net.bytebuddy.build.Plugin.Engine.Summary;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCompare;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsSummary;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsCompareRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsSummaryRepository;
import th.go.excise.ims.oa.vo.Oa02010604FormVo;

@Service
public class Oa02010604Service {
	
	@Autowired
	private OaLubricantsDtlRepository oaLubricantsDtlRep;
	
	@Autowired
	private OaLubricantsCompareRepository oaLubricantsCompareRepo;
	
	@Autowired
	private OaLubricantsSummaryRepository oaLubricantsSummaryRepository;
	
	public Oa02010604FormVo findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		List<OaLubricantsCompare> oaLubricantsCom = oaLubricantsCompareRepo.findByOaLubricantsIdAndIsDeleted(id,"N");
		List<OaLubricantsSummary> oaLubricantsSum = oaLubricantsSummaryRepository.findByOaLubricantsIdAndIsDeleted(id,"N");
		Oa02010604FormVo lubricats = new Oa02010604FormVo();
		OaLubricantsDtl oaLubricants = oaLubricantsDtlOpt.get();
		lubricats.setResult(oaLubricants.getAuditResult());
		if (oaLubricantsCom.size()>0) {
			lubricats.setListLubricantsCompare(oaLubricantsCom);
		}
		if (oaLubricantsSum.size()>0) {
			lubricats.setListOaLubricantsSummary(oaLubricantsSum);
		}
		
		return lubricats;
	}
	
	@Transactional
	public OaLubricantsDtl updateById(Oa02010604FormVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
//		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl lubricantsDtl = new OaLubricantsDtl();
		List<OaLubricantsCompare> listLubricantsCompare = request.getListLubricantsCompare();
		List<OaLubricantsSummary> listOaLubricantsSummary = request.getListOaLubricantsSummary();
		
		List<OaLubricantsCompare> oaLubricantsCom = oaLubricantsCompareRepo.findByOaLubricantsIdAndIsDeleted(id,"N");
		List<OaLubricantsSummary> oaLubricantsSum = oaLubricantsSummaryRepository.findByOaLubricantsIdAndIsDeleted(id,"N");
		
		for (OaLubricantsSummary summary : oaLubricantsSum) {
			if (summary.getOaLubSumaryId() != null ) {
				oaLubricantsSummaryRepository.deleteById(summary.getOaLubSumaryId());
			}

		}
		
		for (OaLubricantsCompare compaer : oaLubricantsCom) {
			if (compaer.getOaLubCompareId() != null ) {
				oaLubricantsCompareRepo.deleteById(compaer.getOaLubCompareId());
			}
		}
		
		if (listLubricantsCompare.size() > 0) {
			oaLubricantsCompareRepo.saveAll(listLubricantsCompare);
		}
		if (listOaLubricantsSummary.size() >0 ) {
			oaLubricantsSummaryRepository.saveAll(listOaLubricantsSummary);
		}
		
		return lubricantsDtl;
	}
	
	@Transactional
	public OaLubricantsDtl updateLubircantsDtlById(Oa02010604FormVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Oa02010604FormVo lubricats = new Oa02010604FormVo();
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl lubricantsDtl = new OaLubricantsDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			lubricantsDtl = oaLubricantsDtlOpt.get();
			lubricantsDtl.setAuditResult(request.getResult());
			lubricantsDtl = oaLubricantsDtlRep.save(lubricantsDtl);
		}
		return lubricantsDtl;
		
	}

}
