package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Service
public class Oa02010606Service {

	@Autowired
	private OaLubricantsDtlRepository oaLubricantsDtlRep;
	
	public OaLubricantsDtl findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl lubricantsDtl = new OaLubricantsDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			lubricantsDtl = oaLubricantsDtlOpt.get();
		}
		return lubricantsDtl;
	}
	
	
	public OaLubricantsDtl updateById(Oa020106DtlVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl lubricantsDtl = new OaLubricantsDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			lubricantsDtl = oaLubricantsDtlOpt.get();
			// TODO SET SOMETHING
			lubricantsDtl.setDailyAcc(request.getDailyAcc());
			lubricantsDtl.setDailyAccDoc(request.getDailyAccDoc());
			lubricantsDtl.setDailyAuditRemark(request.getDailyAuditRemark());
			lubricantsDtl.setMonthlyAcc(request.getMonthlyAcc());
			lubricantsDtl.setMonthlyAccDoc(request.getMonthlyAccDoc());
			lubricantsDtl.setMonthlyAuditRemark(request.getMonthlyAuditRemark());
			lubricantsDtl.setMonthlyAcc04(request.getMonthlyAcc04());
			lubricantsDtl.setMonthlyAccDoc04(request.getMonthlyAccDoc04());
			lubricantsDtl.setMonthlyAuditRemark04(request.getMonthlyAuditRemark04());
			
			// TODO SAVE
			lubricantsDtl = oaLubricantsDtlRep.save(lubricantsDtl);
		}
		return lubricantsDtl;
	}
}
