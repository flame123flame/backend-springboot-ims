package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsCust;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsCustRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Service
public class Oa02010609Service {

	@Autowired
	private OaLubricantsDtlRepository oaLubricantsDtlRep;

	@Autowired
	private OaLubricantsCustRepository oaLubricantsCustRep;

	public Oa020106DtlVo findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		List<OaLubricantsCust> dtls = new ArrayList<>();
		OaLubricantsDtl dtl = new OaLubricantsDtl();
		Oa020106DtlVo vo = new Oa020106DtlVo();
		if (oaLubricantsDtlOpt.isPresent()) {
			dtl = oaLubricantsDtlOpt.get();
			dtls = oaLubricantsCustRep.findByOaLubricantsIdAndIsDeleted(dtl.getOaLubricantsId(), "N");
			vo.setOaLubricantsDtlId(dtl.getOaLubricantsDtlId());
			vo.setOaLubricantsId(dtl.getOaLubricantsId());

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
	public OaLubricantsDtl updateById(Oa020106DtlVo dtl, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl vo = new OaLubricantsDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			vo = oaLubricantsDtlOpt.get();
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
				for (OaLubricantsCust cust : dtl.getCustomers()) {
					if (cust.getOaLubricantsCustId() != null) {
						Optional<OaLubricantsCust> customOpt = oaLubricantsCustRep
								.findById(cust.getOaLubricantsCustId());
						if (customOpt.isPresent()) {
							customOpt.get().setAddress(cust.getAddress());
							customOpt.get().setCustName(cust.getCustName());
							customOpt.get().setMobile(cust.getMobile());
							cust = oaLubricantsCustRep.save(customOpt.get());
						}
					} else {
						cust.setOaLubricantsId(id);
						cust = oaLubricantsCustRep.save(cust);
					}
				}
			}
			if (dtl.getCustdeles() != null) {
				for (OaLubricantsCust cust : dtl.getCustdeles()) {
					oaLubricantsCustRep.deleteById(cust.getOaLubricantsCustId());
				}
			}
			// TODO SAVE
			vo = oaLubricantsDtlRep.save(vo);
		}
		return vo;
	}

}
