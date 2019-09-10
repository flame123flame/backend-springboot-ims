package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaHydCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa010106JdbcRepository;
import th.go.excise.ims.oa.vo.Oa010106ButtonVo;
import th.go.excise.ims.oa.vo.Oa010106FormVo;

@Service
public class Oa010106Service {
	
	@Autowired
	private Oa010106JdbcRepository oa010106JdbcRep;
	
	@Autowired
	private OaHydCustomerLicenRepository oaHydCustomerLicenRep;
	
	@Autowired
	OaLicensePlanRepository oaLicensePlanRep;
	
	@Autowired
	OaPlanRepository oaPlanRepo;
	
	public Oa010106ButtonVo findButtonById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return oa010106JdbcRep.findButtonIdById(id);
	}
	
	public OaHydCustomerLicen findById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydCustomerLicen> optOaCustomer = oaHydCustomerLicenRep.findById(id);
		OaHydCustomerLicen  oaCustomer =  new OaHydCustomerLicen();
		if (optOaCustomer.isPresent()) {
			oaCustomer = optOaCustomer.get();
		}
		return oaCustomer;
	}
	
	public Oa010106FormVo findCustomerLicenAll(String idStr) {
		Oa010106FormVo response = new Oa010106FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydCustomerLicen> licenOpt = oaHydCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaHydCustomerLicen licen = licenOpt.get();
			response.setApprove(licen.getApprove());
			response.setApproveName(licen.getApproveName());
			response.setBankGuarantee(licen.getBankGuarantee());
			response.setBankGuaranteeDate(licen.getBankGuaranteeDate());
			response.setBankGuaranteeNo(licen.getBankGuaranteeNo());
			response.setEndDate(licen.getEndDate());
			response.setLicenseDate(licen.getLicenseDate());
			response.setLicenseNo(licen.getLicenseNo());
			response.setLicenseType(licen.getLicenseType());
			response.setOaCuslicenseId(licen.getOaCuslicenseId());
			response.setOffCode(licen.getOffCode());
			response.setOldLicenseYear(licen.getOldLicenseYear());
			response.setOperateName(licen.getOperateName());
			response.setOperateRemark(licen.getOperateRemark());
			response.setReceiveDate(licen.getReceiveDate());
			response.setReceiveNo(licen.getReceiveNo());
			response.setStartDate(licen.getStartDate());
			List<OaHydCustomerLicenDtl> details = oa010106JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}
	
	public void completeLicense(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLicensePlan> licenOpt = oaLicensePlanRep.findById(id);
		if (licenOpt.isPresent()) {
			licenOpt.get().setStatus("6");
			oaLicensePlanRep.save(licenOpt.get());
			
			// update status plan
			Optional<OaPlan> plan = oaPlanRepo.findById(licenOpt.get().getOaPlanId());
			if (plan.isPresent()) {
				plan.get().setStatus("6");
				oaPlanRepo.save(plan.get());
			}
		}
	}
	
}
