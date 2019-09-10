package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.repository.OaAchCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa040106JdbcRepository;
import th.go.excise.ims.oa.vo.Oa040106ButtonVo;
import th.go.excise.ims.oa.vo.Oa040106FormVo;

@Service
public class Oa040106Service {
	
	@Autowired
	private Oa040106JdbcRepository oa040106JdbcRep;
	
	@Autowired
	private OaAchCustomerLicenRepository oaAchCustomerLicenRep;
	
	@Autowired
	OaLicensePlanRepository oaLicensePlanRep;
	
	public Oa040106ButtonVo findButtonById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return oa040106JdbcRep.findButtonIdById(id);
	}
	
	public OaAchCustomerLicen findById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaAchCustomerLicen> optOaCustomer = oaAchCustomerLicenRep.findById(id);
		OaAchCustomerLicen  oaCustomer =  new OaAchCustomerLicen();
		if (optOaCustomer.isPresent()) {
			oaCustomer = optOaCustomer.get();
		}
		return oaCustomer;
	}
	
	public Oa040106FormVo findCustomerLicenAll(String idStr) {
		Oa040106FormVo response = new Oa040106FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaAchCustomerLicen> licenOpt = oaAchCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaAchCustomerLicen licen = licenOpt.get();
			response.setApprove(licen.getApprove());
			response.setApproveName(licen.getApproveName());
			response.setEndDate(licen.getEndDate());
			response.setLicenseDate(licen.getLicenseDate());
			response.setLicenseNo(licen.getLicenseNo());
			response.setOaCuslicenseId(licen.getOaCuslicenseId());
			response.setOffCode(licen.getOffCode());
			response.setOperateName(licen.getOperateName());
			response.setOperateRemark(licen.getOperateRemark());
			response.setReceiveDate(licen.getReceiveDate());
			response.setReceiveNo(licen.getReceiveNo());
			response.setStartDate(licen.getStartDate());
			List<OaAchCustomerLicenDtl> details = oa040106JdbcRep.findByLicenseId(response.getOaCuslicenseId());
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
		}
	}
	
}
