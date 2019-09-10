package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaHydrocarbCompare;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbSummary;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbCompareRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbSummaryRepository;
import th.go.excise.ims.oa.vo.Oa01010604FromVo;

@Service
public class Oa01010604Service {
	
	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRepo;
	
	@Autowired
	private OaHydrocarbCompareRepository oaHydrocarbCompareRepo;
	
	@Autowired
	private OaHydrocarbSummaryRepository oaHydrocarbSummaryRepo;
	
	public Oa01010604FromVo findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaLubricantsDtlOpt = oaHydrocarbDtlRepo.findById(id);
		List<OaHydrocarbCompare> oaLubricantsCom = oaHydrocarbCompareRepo.findByoaHydrocarbIdAndIsDeleted(id, "N");
		List<OaHydrocarbSummary> oaLubricantsSum = oaHydrocarbSummaryRepo.findByoaHydrocarbIdAndIsDeleted(id, "N");
		Oa01010604FromVo lubricats = new Oa01010604FromVo();
		OaHydrocarbDtl oaLubricants = oaLubricantsDtlOpt.get();
		lubricats.setResult(oaLubricants.getAuditResult());
		if (oaLubricantsCom.size()>0) {
			lubricats.setListOaHydrocarbCompare(oaLubricantsCom);
		}
		if (oaLubricantsSum.size()>0) {
			lubricats.setListOaHydrocarbSummary(oaLubricantsSum);
		}
		
		return lubricats;
	}
	
	@Transactional
	public OaHydrocarbDtl updateById(Oa01010604FromVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
//		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaHydrocarbDtl hydrocarbDtl = new OaHydrocarbDtl();
		List<OaHydrocarbCompare> listLubricantsCompare = request.getListOaHydrocarbCompare();
		List<OaHydrocarbSummary> listOaLubricantsSummary = request.getListOaHydrocarbSummary();
		
		List<OaHydrocarbCompare> oaLubricantsCom = oaHydrocarbCompareRepo.findByoaHydrocarbIdAndIsDeleted(id, "N");
		List<OaHydrocarbSummary> oaLubricantsSum = oaHydrocarbSummaryRepo.findByoaHydrocarbIdAndIsDeleted(id, "N");
		
		for (OaHydrocarbSummary summary : oaLubricantsSum) {
			if (summary.getOaHydSumaryId() != null ) {
				oaHydrocarbSummaryRepo.deleteById(summary.getOaHydSumaryId());
			}

		}
		
		for (OaHydrocarbCompare compaer : oaLubricantsCom) {
			if (compaer.getOaHydCompareId() != null ) {
				oaHydrocarbCompareRepo.deleteById(compaer.getOaHydCompareId());
			}
		}
		
		if (listLubricantsCompare.size() > 0) {
			oaHydrocarbCompareRepo.saveAll(listLubricantsCompare);
		}
		if (listOaLubricantsSummary.size() >0 ) {
			oaHydrocarbSummaryRepo.saveAll(listOaLubricantsSummary);
		}
		
		return hydrocarbDtl;
	}
	
	@Transactional
	public OaHydrocarbDtl updateLubircantsDtlById(Oa01010604FromVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Oa01010604FromVo lubricats = new Oa01010604FromVo();
		Optional<OaHydrocarbDtl> oaLubricantsDtlOpt = oaHydrocarbDtlRepo.findById(id);
		OaHydrocarbDtl lubricantsDtl = new OaHydrocarbDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			lubricantsDtl = oaLubricantsDtlOpt.get();
			lubricantsDtl.setAuditResult(request.getResult());
			lubricantsDtl = oaHydrocarbDtlRepo.save(lubricantsDtl);
		}
		return lubricantsDtl;
		
	}
	

}
