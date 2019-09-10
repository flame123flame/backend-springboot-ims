package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.vo.Oa010106DtlVo;

@Service
public class Oa01010602Service {
	
	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRepo;
	
	public OaHydrocarbDtl findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaLubricantsDtlOpt = oaHydrocarbDtlRepo.findById(id);
		OaHydrocarbDtl lubricantsDtl = new OaHydrocarbDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			lubricantsDtl = oaLubricantsDtlOpt.get();
		}
		return lubricantsDtl;
	}
	
	
	public OaHydrocarbDtl updateById(Oa010106DtlVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydrocarbDtl> oaLubricantsDtlOpt = oaHydrocarbDtlRepo.findById(id);
		OaHydrocarbDtl hydrocarbDtl = new OaHydrocarbDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			hydrocarbDtl = oaLubricantsDtlOpt.get();
			// TODO SET SOMETHING
			hydrocarbDtl.setOfficePlaceOwner(request.getOfficePlaceOwner());
			hydrocarbDtl.setOfficeRentAmount(request.getOfficeRentAmount());
			hydrocarbDtl.setEmployeePermanent(request.getEmployeePermanent());
			hydrocarbDtl.setEmployeeTemporary(request.getEmployeeTemporary());
			hydrocarbDtl.setWorkingStartDate(request.getWorkingStartDate());
			hydrocarbDtl.setWorkingEndDate(request.getWorkingEndDate());
			hydrocarbDtl.setOfficeRentAmount(request.getOfficeRentAmount());
			hydrocarbDtl.setOrderType(request.getOrderType());
			hydrocarbDtl.setOrderPayMethod(request.getOrderPayMethod());
			hydrocarbDtl.setWorkdayPermonth(request.getWorkdayPermonth());
			hydrocarbDtl.setNumberOfTank(request.getNumberOfTank());
			hydrocarbDtl.setTankCapacity(request.getTankCapacity());
			hydrocarbDtl.setNumberUtility(request.getNumberUtility());
			hydrocarbDtl.setPayMethodOther(request.getPayMethodOther());
			
			// TODO SAVE
			hydrocarbDtl = oaHydrocarbDtlRepo.save(hydrocarbDtl);
		}
		return hydrocarbDtl;
	}

}
