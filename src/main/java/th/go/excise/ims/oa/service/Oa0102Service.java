package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0102JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0102Vo;

@Service
public class Oa0102Service {

	@Autowired
	private Oa0102JdbcRepository oa0102JdbcRep;

	@Autowired
	OaLicensePlanRepository oaLicensePlanRep;
	
	@Autowired
	OaPlanRepository oaPlanRepo;

	public List<Oa0102Vo> findAll(String offCode, int addDate) {
		return oa0102JdbcRep.findAll(OaOfficeCode.officeCodeLike(offCode), addDate);
	}

	public void updateStatus(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLicensePlan> licenOpt = oaLicensePlanRep.findById(id);
		if (licenOpt.isPresent()) {
			licenOpt.get().setStatus("5");
			oaLicensePlanRep.save(licenOpt.get());
			
			// update status plan
			Optional<OaPlan> plan = oaPlanRepo.findById(licenOpt.get().getOaPlanId());
			if (plan.isPresent()) {
				plan.get().setStatus("5");
				oaPlanRepo.save(plan.get());
			}
		}
	}

}
