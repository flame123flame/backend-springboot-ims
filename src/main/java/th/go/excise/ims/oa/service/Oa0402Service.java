package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0202JdbcRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0402JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0202Vo;

@Service
public class Oa0402Service {
	

	@Autowired
	private Oa0402JdbcRepository oa0402JdbcRep;
	
	@Autowired
	OaLicensePlanRepository oaLicensePlanRep;

	public List<Oa0202Vo> findAll(String offCode, int addDate) {
		return oa0402JdbcRep.findAll(OaOfficeCode.officeCodeLike(offCode), addDate);
	}

	public void updateStatus(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLicensePlan> licenOpt = oaLicensePlanRep.findById(id);
		if (licenOpt.isPresent()) {
			licenOpt.get().setStatus("5");
			oaLicensePlanRep.save(licenOpt.get());
		}
	}

}
