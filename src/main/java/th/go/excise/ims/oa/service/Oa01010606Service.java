package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.vo.Oa010106DtlVo;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Service
public class Oa01010606Service {
	
	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRep;
	
	public OaHydrocarbDtl findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaLubricantsDtlOpt = oaHydrocarbDtlRep.findById(id);
		OaHydrocarbDtl lubricantsDtl = new OaHydrocarbDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			lubricantsDtl = oaLubricantsDtlOpt.get();
		}
		return lubricantsDtl;
	}
	
	
	public OaHydrocarbDtl updateById(Oa010106DtlVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaLubricantsDtlOpt = oaHydrocarbDtlRep.findById(id);
		OaHydrocarbDtl hydrocarbDtl = new OaHydrocarbDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			hydrocarbDtl = oaLubricantsDtlOpt.get();
			hydrocarbDtl.setDailyAcc(request.getDailyAcc());
			hydrocarbDtl.setDailyAccDoc(request.getDailyAccDoc());
			hydrocarbDtl.setDailyAuditRemark(request.getDailyAuditRemark());
			hydrocarbDtl.setMonthlyAcc(request.getMonthlyAcc());
			hydrocarbDtl.setMonthlyAccDoc(request.getMonthlyAccDoc());
			hydrocarbDtl.setMonthlyAuditRemark(request.getMonthlyAuditRemark());
			hydrocarbDtl.setMonthlyAcc04(request.getMonthlyAcc04());
			hydrocarbDtl.setMonthlyAccDoc04(request.getMonthlyAccDoc04());
			hydrocarbDtl.setMonthlyAuditRemark04(request.getMonthlyAuditRemark04());

			hydrocarbDtl = oaHydrocarbDtlRep.save(hydrocarbDtl);
		}
		return hydrocarbDtl;
	}

}
