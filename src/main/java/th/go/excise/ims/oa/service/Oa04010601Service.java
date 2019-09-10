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
public class Oa04010601Service {

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
			achDtl.setLicenseMenufac(request.getLicenseMenufac());
			achDtl.setLicenseMenufacRemark(request.getLicenseMenufacRemark());
			achDtl.setLicenseType2(request.getLicenseType2());
			achDtl.setLicenseType2Remark(request.getLicenseType2Remark());
			achDtl.setAchDegree(request.getAchDegree());
			achDtl.setAchDegreeRemark(request.getAchDegreeRemark());
			achDtl.setAchCapacity(request.getAchCapacity());
			achDtl.setAchCapacityRemark(request.getAchCapacityRemark());
			achDtl.setAchApprove(request.getAchApprove());
			achDtl.setAchApproveRemark(request.getAchApproveRemark());
			achDtl.setAchPrice(request.getAchPrice());
			achDtl.setAchPriceRemark(request.getAchPriceRemark());
			achDtl.setAchStamp(request.getAchStamp());
			achDtl.setAchStampRemark(request.getAchStampRemark());
			
			// TODO SAVE
			achDtl = oaAlcoholDtlRepo.save(achDtl);
		}
		return achDtl;
	}
	
	
}
