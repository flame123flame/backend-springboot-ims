package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.repository.OaAchCustomerLicenDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaAchCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0412JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0107CodeVo;
import th.go.excise.ims.oa.vo.Oa020106FormVo;
import th.go.excise.ims.oa.vo.Oa040106FormVo;
import th.go.excise.ims.oa.vo.Oa0412Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Oa0412Service {

	@Autowired
	private Oa0412JdbcRepository oa0412JdbcRep;

	@Autowired
	OaAchCustomerLicenRepository oaAchCustomerLicenRep;

	@Autowired
	OaAchCustomerLicenDtlRepository oaAchCustomerLicenDtlRep;

	public DataTableAjax<Oa020106FormVo> filterByCriteria(Oa0412Vo request,String offCode) {
		List<Oa0107CodeVo> data = oa0412JdbcRep.getDataFilter(request, offCode);
		List<OaAchCustomerLicen> oldData = oa0412JdbcRep.licenseAll(request, offCode);
		List<OaAchCustomerLicen> realData = new ArrayList<OaAchCustomerLicen>();
		for (OaAchCustomerLicen old : oldData) {
			for (Oa0107CodeVo da : data) {
				if (da.getOffCode().equals(old.getOffCode()) && da.getIdentifyNo().equals(old.getIdentifyNo())) {
					realData.add(old);
				}
			}
		}
		List<ExciseDepartment> exciseDepts = ApplicationCache.getExciseSectorList();
		List<Oa020106FormVo> realDataAgain = new ArrayList<Oa020106FormVo>();
		for (OaAchCustomerLicen real : realData) {
			Oa020106FormVo realD = new Oa020106FormVo();
			realD.setOaCuslicenseId(real.getOaCuslicenseId());
			
			realD.setReceiveNo(real.getReceiveNo());
			realD.setLicenseNo(real.getLicenseNo());
			realD.setName(real.getName());
			realD.setCompanyName(real.getCompanyName());
			realD.setIdentifyNo(real.getIdentifyNo());
			realD.setStartDate(real.getStartDate());
			realD.setEndDate(real.getEndDate());
//			realD.setLicenseType(real.getLicenseType());
			
			realD.setIdentifyType(real.getIdentifyType());
			realD.setAddress(real.getLicenseAddress());
			realD.setOffCode(real.getOffCode());
			for (ExciseDepartment exciseDept : exciseDepts) {
				if (exciseDept.getOfficeCode().substring(0, 2).equals(real.getOffCode().substring(0, 2))) {
					realD.setSectorName(exciseDept.getDeptName());
				}
			}
			ExciseDepartment area = ApplicationCache.getExciseDepartment(real.getOffCode());
			if (!"0000".equals(area.getOfficeCode().substring(2, 6))) {
				realD.setAreaName(area.getDeptName());
			} else {
				realD.setAreaName("");
			}
			realDataAgain.add(realD);
		}
		DataTableAjax<Oa020106FormVo> dataTableAjax = new DataTableAjax<Oa020106FormVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(realDataAgain);
		dataTableAjax.setRecordsTotal(oa0412JdbcRep.countDatafilter(request, offCode));
		dataTableAjax.setRecordsFiltered(oa0412JdbcRep.countDatafilter(request, offCode));
		return dataTableAjax;
	}

	public List<Oa040106FormVo> findCustomerLicenList(String customerIdStr) {
		List<Oa040106FormVo> response = new ArrayList<Oa040106FormVo>();
		BigDecimal customerId = new BigDecimal(customerIdStr);
		response = oa0412JdbcRep.findByCustomerIdAndLicenseType(customerId);
		return response;
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
			response.setCreatedFactTime(licen.getCreatedFactTime());
			response.setUsedDate(licen.getUsedDate());
			response.setLicenseAddress(licen.getLicenseAddress());
			response.setMoney(licen.getMoney());
			response.setName(licen.getName());
			response.setCompanyName(licen.getCompanyName());
			response.setLicenseTypeDesp(licen.getLicenseTypeDesp());
			response.setIdentifyNo(licen.getIdentifyNo());
			response.setAddress(licen.getAddress());
			response.setMobile(licen.getMobile());
//			response.setWarehouseAddress(licen.geta);
			response.setIdentifyType(licen.getIdentifyType());
			response.setLicenseTypeFor(licen.getLicenseTypeFor());
			List<OaAchCustomerLicenDtl> details = oa0412JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}

	@Transactional
	public Oa040106FormVo saveCustomerLicenAll(Oa040106FormVo request) {
		OaAchCustomerLicen licen = new OaAchCustomerLicen();
		licen.setApprove(request.getApprove());
		licen.setApproveName(request.getApproveName());
		licen.setEndDate(request.getEndDate());
		licen.setLicenseDate(request.getLicenseDate());
		licen.setLicenseNo(request.getLicenseNo());
		licen.setOffCode(request.getOffCode());
		licen.setOperateName(request.getOperateName());
		licen.setOperateRemark(request.getOperateRemark());
		licen.setReceiveDate(request.getReceiveDate());
		licen.setReceiveNo(request.getReceiveNo());
		licen.setStartDate(request.getStartDate());
		licen.setCreatedFactTime(request.getCreatedFactTime());
		licen.setUsedDate(request.getUsedDate());
//		licen.setLicenseAddress(request.getLicenseAddress());
		licen.setMoney(request.getMoney());
		licen.setName(request.getName());
		licen.setCompanyName(request.getCompanyName());
		licen.setIdentifyNo(request.getIdentifyNo());
		licen.setIdentifyType(request.getIdentifyType());
		licen.setLicenseAddress(request.getWarehouseAddress());
		licen.setAddress(request.getAddress());
		licen.setMobile(request.getMobile());
		licen.setLicenseTypeDesp(request.getLicenseTypeDesp());
		licen.setLicenseTypeFor(request.getLicenseTypeFor());
		licen = oaAchCustomerLicenRep.save(licen);
//		List<OaAchCustomerLicenDtl> details = request.getDetails();
//		if (details != null) {
//			int seq = 0;
//			for (OaAchCustomerLicenDtl detail : details) {
//				detail.setLicenseNo(request.getLicenseNo());
//				detail.setOaCuslicenseId(licen.getOaCuslicenseId());
//				detail.setSeq(new BigDecimal(seq++));
//			}
//			details = (List<OaAchCustomerLicenDtl>) oaAchCustomerLicenDtlRep.saveAll(details);
//		}
//		request.setOaCuslicenseId(licen.getOaCuslicenseId());
//		request.setDetails(details);
		return request;
	}

	@Transactional
	public Oa040106FormVo updateCustomerLicenAll(Oa040106FormVo request) {
		Optional<OaAchCustomerLicen> licenOpt = oaAchCustomerLicenRep.findById(request.getOaCuslicenseId());
		if (licenOpt.isPresent()) {
			OaAchCustomerLicen licen = licenOpt.get();
			licen.setApprove(request.getApprove());
			licen.setApproveName(request.getApproveName());
			licen.setEndDate(request.getEndDate());
			licen.setLicenseDate(request.getLicenseDate());
			licen.setLicenseNo(request.getLicenseNo());
			licen.setOffCode(request.getOffCode());
			licen.setOperateName(request.getOperateName());
			licen.setOperateRemark(request.getOperateRemark());
			licen.setReceiveDate(request.getReceiveDate());
			licen.setReceiveNo(request.getReceiveNo());
			licen.setStartDate(request.getStartDate());
			licen.setCreatedFactTime(request.getCreatedFactTime());
			licen.setUsedDate(request.getUsedDate());
			licen.setLicenseAddress(request.getWarehouseAddress());
			licen.setMoney(request.getMoney());
			licen.setName(request.getName());
			licen.setCompanyName(request.getCompanyName());
			licen.setIdentifyNo(request.getIdentifyNo());
			licen.setIdentifyType(request.getIdentifyType());
			licen.setAddress(request.getAddress());
			licen.setMobile(request.getMobile());
			licen.setLicenseTypeDesp(request.getLicenseTypeDesp());
			licen.setLicenseTypeFor(request.getLicenseTypeFor());
			licen = oaAchCustomerLicenRep.save(licen);
			List<OaAchCustomerLicenDtl> details = request.getDetails();
			List<OaAchCustomerLicenDtl> detailsOld = new ArrayList<>();
			if (details != null) {
				int seq = 0;
				for (OaAchCustomerLicenDtl detail : details) {
					if (detail.getOaCuslicenseDtlId() != null) {
						Optional<OaAchCustomerLicenDtl> detailOpt = oaAchCustomerLicenDtlRep
								.findById(detail.getOaCuslicenseDtlId());
						if (detailOpt.isPresent()) {
							OaAchCustomerLicenDtl dtl = detailOpt.get();
							dtl.setName(detail.getName());
							dtl.setType(detail.getType());
							dtl.setAmount(detail.getAmount());
							dtl.setLicenseNo(detail.getLicenseNo());
							dtl.setOaCuslicenseId(detail.getOaCuslicenseId());
							dtl.setSeq(new BigDecimal(seq++));
							detailsOld.add(dtl);
						}
					} else {
						detail.setLicenseNo(request.getLicenseNo());
						detail.setOaCuslicenseId(licen.getOaCuslicenseId());
						detail.setSeq(new BigDecimal(seq++));
						detailsOld.add(detail);
					}
				}
				details = (List<OaAchCustomerLicenDtl>) oaAchCustomerLicenDtlRep.saveAll(detailsOld);
				if (request.getDeletes() != null && request.getDeletes().size() > 0) {
					for (OaAchCustomerLicenDtl delete : request.getDeletes()) {
						oaAchCustomerLicenDtlRep.deleteById(delete.getOaCuslicenseDtlId());
					}
				}

			}
			request.setDeletes(new ArrayList<>());
			request.setDetails(details);
		}
		return request;
	}

}
