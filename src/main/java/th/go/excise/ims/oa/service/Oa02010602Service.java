package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Service
public class Oa02010602Service {
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
			lubricantsDtl.setOfficePlaceOwner(request.getOfficePlaceOwner());
			lubricantsDtl.setOfficeRentAmount(request.getOfficeRentAmount());
			lubricantsDtl.setEmployeePermanent(request.getEmployeePermanent());
			lubricantsDtl.setEmployeeTemporary(request.getEmployeeTemporary());
			lubricantsDtl.setWorkingStartDate(request.getWorkingStartDate());
			lubricantsDtl.setWorkingEndDate(request.getWorkingEndDate());
			lubricantsDtl.setOfficeRentAmount(request.getOfficeRentAmount());
			lubricantsDtl.setOrderType(request.getOrderType());
			lubricantsDtl.setOrderPayMethod(request.getOrderPayMethod());
			lubricantsDtl.setWorkdayPermonth(request.getWorkdayPermonth());
			lubricantsDtl.setNumberOfTank(request.getNumberOfTank());
			lubricantsDtl.setTankCapacity(request.getTankCapacity());
			lubricantsDtl.setNumberUtility(request.getNumberUtility());
			lubricantsDtl.setPayMethodOther(request.getPayMethodOther());
			
			// TODO SAVE
			lubricantsDtl = oaLubricantsDtlRep.save(lubricantsDtl);
		}
		return lubricantsDtl;
	}

}
