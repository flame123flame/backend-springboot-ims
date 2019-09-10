package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaAlcoholDtl;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaAlcoholDtlRepository;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Service
public class Oa04010603Service {

	@Autowired
	private OaAlcoholDtlRepository oaAlcoholDtlRepo;
	
	public OaAlcoholDtl findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaAlcoholDtl> oaAchDtlOpt = oaAlcoholDtlRepo.findById(id);
		OaAlcoholDtl achDtl = new OaAlcoholDtl();
		if (oaAchDtlOpt.isPresent()) {
			achDtl = oaAchDtlOpt.get();
		}
		return achDtl;
	}
	
	public OaAlcoholDtl updateById(OaAlcoholDtl request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaAlcoholDtl> oaAchDtlOpt = oaAlcoholDtlRepo.findById(id);
		OaAlcoholDtl achDtl = new OaAlcoholDtl();
		if (oaAchDtlOpt.isPresent()) {
			achDtl = oaAchDtlOpt.get();
			// TODO SET SOMETHING
			achDtl.setPlaceStatus(request.getPlaceStatus());
			achDtl.setPlaceStatusRemark(request.getPlaceStatusRemark());
			achDtl.setEqmTank(request.getEqmTank());
			achDtl.setEqmTankNum(request.getEqmTankNum());
			achDtl.setEqmTankStatus(request.getEqmTankStatus());
			achDtl.setEqmDistil(request.getEqmDistil());
			achDtl.setEqmDistilNum(request.getEqmDistilNum());
			achDtl.setEqmDistilStatus(request.getEqmDistilStatus());
			achDtl.setEqmPacking(request.getEqmPacking());
			achDtl.setEqmPackingNum(request.getEqmPackingNum());
			achDtl.setEqmPackingStatus(request.getEqmPackingStatus());
			achDtl.setAudit0701(request.getAudit0701());
			achDtl.setAudit0701Remark(request.getAudit0701Remark());
			achDtl.setAudit07021(request.getAudit07021());
			achDtl.setAudit07021Remark(request.getAudit07021Remark());
			achDtl.setAudit07022(request.getAudit07022());
			achDtl.setAudit07022Remark(request.getAudit07022Remark());
			achDtl.setEquipmentUsed(request.getEquipmentUsed());
			achDtl.setEquipmentUsedRemark(request.getEquipmentUsedRemark());
			achDtl.setAuditOther(request.getAuditOther());
			achDtl.setAuditSuggestion(request.getAuditSuggestion());
			
			// TODO SAVE
			achDtl = oaAlcoholDtlRepo.save(achDtl);
		}
		return achDtl;
	}
	
	
}
