package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.vo.Oa010106DtlVo;

@Service
public class Oa01010607Service {
	
	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRep;
	
	public OaHydrocarbDtl findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaHydrocarbDtlOpt = oaHydrocarbDtlRep.findById(id);
		OaHydrocarbDtl hydrocarbDtl = new OaHydrocarbDtl();
		if (oaHydrocarbDtlOpt.isPresent()) {
			hydrocarbDtl = oaHydrocarbDtlOpt.get();
		}
		return hydrocarbDtl;
	}
	
	public OaHydrocarbDtl updateById(Oa010106DtlVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaHydrocarbDtlOpt = oaHydrocarbDtlRep.findById(id);
		OaHydrocarbDtl hydrocarbDtl = new OaHydrocarbDtl();
		if (oaHydrocarbDtlOpt.isPresent()) {
			hydrocarbDtl = oaHydrocarbDtlOpt.get();
			// TODO SET SOMETHING
			hydrocarbDtl.setAgentStartDate(request.getAgentStartDate());
			hydrocarbDtl.setAgentEndDate(request.getAgentEndDate());
			hydrocarbDtl.setAgentOverlimit(request.getAgentOverlimit());
			
			// Buy
			hydrocarbDtl.setABuyFromIndust(request.getABuyFromIndust());
			hydrocarbDtl.setABuyIndustLicense(request.getABuyIndustLicense());
			hydrocarbDtl.setABuyFromAgent(request.getABuyFromAgent());
			hydrocarbDtl.setABuyAgentLicense(request.getABuyAgentLicense());
			// Sell
			hydrocarbDtl.setASaleToAgent(request.getASaleToAgent());
			hydrocarbDtl.setASaleAgentLicense(request.getASaleAgentLicense());
			hydrocarbDtl.setASaleToUser(request.getASaleToUser());
			hydrocarbDtl.setASaleUserLicense(request.getASaleUserLicense());
			// Sell Method
			hydrocarbDtl.setSentToAgent(request.getSentToAgent());
			hydrocarbDtl.setSentToUser(request.getSentToUser());
			hydrocarbDtl.setAImporterLicense(request.getAImporterLicense());
			hydrocarbDtl.setAgentRemark(request.getAgentRemark());
			
			// TODO SAVE
			hydrocarbDtl = oaHydrocarbDtlRep.save(hydrocarbDtl);
		}
		return hydrocarbDtl;
	}
	
}
