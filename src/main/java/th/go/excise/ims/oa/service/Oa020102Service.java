package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.persistence.repository.OaLicensePlanRepository;
import th.go.excise.ims.oa.persistence.repository.OaPlanRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0201JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0201001Vo;
import th.go.excise.ims.oa.vo.Oa0201Vo;
import th.go.excise.ims.oa.vo.Oa0206FormVo;

@Service
public class Oa020102Service {
	
	@Autowired
	private OaPlanRepository oaPlanRepo;
	
	@Autowired
	private Oa0201JdbcRepository oa0201jdbc;
	
	@Autowired
	private OaLicensePlanRepository oaLicensePlanRepo;
	
	public OaLicensePlan updateById(OaLicensePlan request) {
//		BigDecimal id = new BigDecimal(request.getOaLicensePlanId());
		Optional<OaLicensePlan> licensedtl = oaLicensePlanRepo.findById(request.getOaLicensePlanId());
		OaLicensePlan license = new OaLicensePlan();
		if (licensedtl.isPresent()) {
			license = licensedtl.get();
			// TODO SET SOMETHING
			license.setAuditEnd(request.getAuditEnd());
			license.setAuditStart(request.getAuditStart());
			license.setFiscolYear(request.getFiscolYear());
			license.setOaPlanId(request.getOaPlanId());
			license.setOfficeCode(request.getOfficeCode());
			license.setStatus(request.getStatus());
			
			// TODO SAVE
			license = oaLicensePlanRepo.save(license);
		}else {
			license = oaLicensePlanRepo.save(request);
		}
		return license;
	}
	
	
	public Oa0201Vo findDetailByPlanID(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Oa0201Vo oa0201Vo = new Oa0201Vo();
		List<OaLicensePlan> licensedtl = oaLicensePlanRepo.findByoaPlanIdAndIsDeleted(id, "N");
		oa0201Vo.setListLicensePlan(licensedtl);
		return oa0201Vo;
	}
	
//	public List<Oa0201001Vo> getLicenseCustomer(Oa0206FormVo request){
//		BigDecimal id = new BigDecimal(idStr);
//		List<Oa0201001Vo> list = new ArrayList<Oa0201001Vo>();
//		list = oa0201jdbc.findLicenseById(id);
//		return list;
//	}
	
	public DataTableAjax<Oa0201001Vo> getLicenseCustomer(Oa0206FormVo request) {
		List<Oa0201001Vo> data = oa0201jdbc.findLicenseById(request);
		int count = oa0201jdbc.countLicenseById(request);
		DataTableAjax<Oa0201001Vo> dataTableAjax = new DataTableAjax<Oa0201001Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}
	

}
