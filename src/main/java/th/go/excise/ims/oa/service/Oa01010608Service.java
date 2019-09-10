package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.vo.Oa010106DtlVo;

@Service
public class Oa01010608Service {
	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRep;
	
	public OaHydrocarbDtl findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaHydrocarbDtlOpt = oaHydrocarbDtlRep.findById(id);
		OaHydrocarbDtl HydrocarbDtl = new OaHydrocarbDtl();
		if (oaHydrocarbDtlOpt.isPresent()) {
			HydrocarbDtl = oaHydrocarbDtlOpt.get();
		}
		return HydrocarbDtl;
	}
	
	public OaHydrocarbDtl updateById(Oa010106DtlVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaHydrocarbDtlOpt = oaHydrocarbDtlRep.findById(id);
		OaHydrocarbDtl hydrocarbDtl = new OaHydrocarbDtl();
		if (oaHydrocarbDtlOpt.isPresent()) {
			hydrocarbDtl = oaHydrocarbDtlOpt.get();
			// TODO SET SOMETHING
			hydrocarbDtl.setMaterail(request.getMaterail());
			hydrocarbDtl.setDocument(request.getDocument());
			hydrocarbDtl.setProductProcess(request.getProductProcess());
			hydrocarbDtl.setProductNextime(request.getProductNextime());
			// TODO SAVE
			hydrocarbDtl = oaHydrocarbDtlRep.save(hydrocarbDtl);
		}
		return hydrocarbDtl;
	}
}
