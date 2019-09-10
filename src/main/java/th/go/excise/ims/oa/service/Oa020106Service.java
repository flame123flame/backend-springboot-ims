package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa020106JdbcRepository;
import th.go.excise.ims.oa.vo.Oa020106ButtonVo;
import th.go.excise.ims.oa.vo.Oa020106FormVo;

@Service
public class Oa020106Service {
	
	@Autowired
	private Oa020106JdbcRepository oa020106JdbcRep;
	
	@Autowired
	OaCustomerLicenRepository oaCustomerLicenRep;
	
	@Autowired
	OaLicensePlanRepository oaLicensePlanRep;
	
	@Autowired
	OaPlanRepository oaPlanRepo;
	
	public Oa020106ButtonVo findButtonById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return oa020106JdbcRep.findButtonIdById(id);
	}
	
	public OaCustomerLicen findById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaCustomerLicen> optOaCustomer = oaCustomerLicenRep.findById(id);
		OaCustomerLicen  oaCustomer =  new OaCustomerLicen();
		if (optOaCustomer.isPresent()) {
			oaCustomer = optOaCustomer.get();
		}
		return oaCustomer;
	}
	
	public Oa020106FormVo findCustomerLicenAll(String idStr) {
		Oa020106FormVo response = new Oa020106FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaCustomerLicen> licenOpt = oaCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaCustomerLicen licen = licenOpt.get();
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
			List<OaCustomerLicenDetail> details = oa020106JdbcRep.findByLicenseId(response.getOaCuslicenseId());
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
