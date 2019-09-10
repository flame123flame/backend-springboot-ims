package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaHydrocarbCust;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCust;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbCustRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.vo.Oa010106DtlVo;

@Service
public class Oa01010609Service {

	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRep;

	@Autowired
	private OaHydrocarbCustRepository oaHydrocarbCustRep;

	public Oa010106DtlVo findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaHydrocarbDtlOpt = oaHydrocarbDtlRep.findById(id);
		List<OaHydrocarbCust> dtls = new ArrayList<>();
		OaHydrocarbDtl dtl = new OaHydrocarbDtl();
		Oa010106DtlVo vo = new Oa010106DtlVo();
		if (oaHydrocarbDtlOpt.isPresent()) {
			dtl = oaHydrocarbDtlOpt.get();
			dtls = oaHydrocarbCustRep.findByOaHydrocarbIdAndIsDeleted(dtl.getOaHydrocarbId(), "N");
			vo.setOaHydrocarbDtlId(dtl.getOaHydrocarbDtlId());
			vo.setOaHydrocarbId(dtl.getOaHydrocarbId());

			vo.setUseStartDate(dtl.getUseStartDate());
			vo.setUseEndDate(dtl.getUseEndDate());
			vo.setBuyOverlimit(dtl.getBuyOverlimit());

			vo.setBuyFromAgent(dtl.getBuyFromAgent());
			vo.setBuyFromImporter(dtl.getBuyFromImporter());
			vo.setBuyFromIndust(dtl.getBuyFromIndust());
			vo.setBuyAgentLicense(dtl.getBuyAgentLicense());
			vo.setBuyIndustLicense(dtl.getBuyIndustLicense());
			vo.setBuyImporterLicense(dtl.getBuyImporterLicense());

			vo.setUsedType(dtl.getUsedType());
			vo.setUsedRemark(dtl.getUsedRemark());
			vo.setSalerType(dtl.getSalerType());
			vo.setSalerCapacity(dtl.getSalerCapacity());
			vo.setNumOfCust(dtl.getNumOfCust());

			vo.setGoodQuality(dtl.getGoodQuality());
			vo.setOtherRemark(dtl.getOtherRemark());
			vo.setCustomers(dtls);
		}
		return vo;
	}

	@Transactional
	public OaHydrocarbDtl updateById(Oa010106DtlVo dtl, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaHydrocarbDtlOpt = oaHydrocarbDtlRep.findById(id);
		OaHydrocarbDtl vo = new OaHydrocarbDtl();
		if (oaHydrocarbDtlOpt.isPresent()) {
			vo = oaHydrocarbDtlOpt.get();
			// TODO SET SOMETHING
			vo.setUseStartDate(dtl.getUseStartDate());
			vo.setUseEndDate(dtl.getUseEndDate());
			vo.setBuyOverlimit(dtl.getBuyOverlimit());

			vo.setBuyFromAgent(dtl.getBuyFromAgent());
			vo.setBuyFromImporter(dtl.getBuyFromImporter());
			vo.setBuyFromIndust(dtl.getBuyFromIndust());
			vo.setBuyAgentLicense(dtl.getBuyAgentLicense());
			vo.setBuyIndustLicense(dtl.getBuyIndustLicense());
			vo.setBuyImporterLicense(dtl.getBuyImporterLicense());

			vo.setUsedType(dtl.getUsedType());
			vo.setUsedRemark(dtl.getUsedRemark());
			vo.setSalerType(dtl.getSalerType());
			vo.setSalerCapacity(dtl.getSalerCapacity());
			vo.setNumOfCust(dtl.getNumOfCust());

			vo.setGoodQuality(dtl.getGoodQuality());
			vo.setOtherRemark(dtl.getOtherRemark());
			if (dtl.getCustomers() != null) {
				for (OaHydrocarbCust cust : dtl.getCustomers()) {
					if (cust.getOaHydrocarbCustId() != null) {
						Optional<OaHydrocarbCust> customOpt = oaHydrocarbCustRep
								.findById(cust.getOaHydrocarbCustId());
						if (customOpt.isPresent()) {
							customOpt.get().setAddress(cust.getAddress());
							customOpt.get().setCustName(cust.getCustName());
							customOpt.get().setMobile(cust.getMobile());
							cust = oaHydrocarbCustRep.save(customOpt.get());
						}
					} else {
						cust.setOaHydrocarbId(id);
						cust = oaHydrocarbCustRep.save(cust);
					}
				}
			}
			if (dtl.getCustdeles() != null) {
				for (OaHydrocarbCust cust : dtl.getCustdeles()) {
					oaHydrocarbCustRep.deleteById(cust.getOaHydrocarbCustId());
				}
			}
			// TODO SAVE
			vo = oaHydrocarbDtlRep.save(vo);
		}
		return vo;
	}

}
