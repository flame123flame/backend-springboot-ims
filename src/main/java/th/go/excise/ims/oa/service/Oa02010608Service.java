package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Service
public class Oa02010608Service {
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
			lubricantsDtl.setMaterail(request.getMaterail());
			lubricantsDtl.setDocument(request.getDocument());
			lubricantsDtl.setProductProcess(request.getProductProcess());
			lubricantsDtl.setProductNextime(request.getProductNextime());
			// TODO SAVE
			lubricantsDtl = oaLubricantsDtlRep.save(lubricantsDtl);
		}
		return lubricantsDtl;
	}
}
