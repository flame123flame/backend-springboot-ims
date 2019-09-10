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
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenDetailRepository;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0207JdbcRepository;
import th.go.excise.ims.oa.vo.Oa020106FormVo;
import th.go.excise.ims.oa.vo.Oa0207CodeVo;
import th.go.excise.ims.oa.vo.Oa0207CustomerVo;
import th.go.excise.ims.oa.vo.Oa0207Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Oa0207Service {

	@Autowired
	private Oa0207JdbcRepository oa0207JdbcRep;

	@Autowired
	OaCustomerLicenRepository oaCustomerLicenRep;

	@Autowired
	OaCustomerLicenDetailRepository oaCustomerLicenDetailRep;

	public DataTableAjax<Oa020106FormVo> filterByCriteria(Oa0207Vo request, String offCode) {
		List<Oa0207CodeVo> data = oa0207JdbcRep.getDataFilter(request, offCode);
		List<OaCustomerLicen> oldData = oa0207JdbcRep.licenseAll(request, offCode);
		List<OaCustomerLicen> realData = new ArrayList<OaCustomerLicen>();
		for (OaCustomerLicen old : oldData) {
			for (Oa0207CodeVo da : data) {
				if (da.getOffCode().equals(old.getOffCode()) && da.getIdentifyNo().equals(old.getIdentifyNo())
						&& da.getLicenseType().equals(old.getLicenseType())) {
					realData.add(old);
				}
			}
		}
		List<ExciseDepartment> exciseDepts = ApplicationCache.getExciseSectorList();
		List<Oa020106FormVo> realDataAgain = new ArrayList<Oa020106FormVo>();
		for (OaCustomerLicen real : realData) {
			Oa020106FormVo realD = new Oa020106FormVo();
			realD.setOaCuslicenseId(real.getOaCuslicenseId());
			
			realD.setReceiveNo(real.getReceiveNo());
			realD.setLicenseNo(real.getLicenseNo());
			realD.setName(real.getName());
			realD.setCompanyName(real.getCompanyName());
			realD.setIdentifyNo(real.getIdentifyNo());
			realD.setStartDate(real.getStartDate());
			realD.setEndDate(real.getEndDate());
			realD.setLicenseType(real.getLicenseType());
			
			realD.setIdentifyType(real.getIdentifyType());
			realD.setAddress(real.getAddress());
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
		dataTableAjax.setRecordsTotal(oa0207JdbcRep.countDatafilter(request, offCode));
		dataTableAjax.setRecordsFiltered(oa0207JdbcRep.countDatafilter(request, offCode));
		return dataTableAjax;
	}

	public List<Oa020106FormVo> findCustomerLicenList(String customerIdStr, String licenseType) {
		List<Oa020106FormVo> response = new ArrayList<Oa020106FormVo>();
		BigDecimal customerId = new BigDecimal(customerIdStr);
		response = oa0207JdbcRep.findByCustomerIdAndLicenseType(customerId, licenseType);
		return response;
	}

	public Oa020106FormVo findCustomerLicenAll(String idStr) {
		Oa020106FormVo response = new Oa020106FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaCustomerLicen> licenOpt = oaCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaCustomerLicen licen = licenOpt.get();
			response.setAddress(licen.getAddress());
			response.setApprove(licen.getApprove());
			response.setApproveName(licen.getApproveName());
			response.setBankGuarantee(licen.getBankGuarantee());
			response.setBankGuaranteeDate(licen.getBankGuaranteeDate());
			response.setBankGuaranteeNo(licen.getBankGuaranteeNo());
			response.setCompanyName(licen.getCompanyName());
			response.setEndDate(licen.getEndDate());
			response.setIdentifyNo(licen.getIdentifyNo());
			response.setIdentifyType(licen.getIdentifyType());
			response.setLicenseDate(licen.getLicenseDate());
			response.setLicenseNo(licen.getLicenseNo());
			response.setLicenseType(licen.getLicenseType());
			response.setLicenseTypeDesp(licen.getLicenseTypeDesp());
			response.setLicenseTypeFor(licen.getLicenseTypeFor());
			response.setMobile(licen.getMobile());
			response.setName(licen.getName());
			response.setOaCuslicenseId(licen.getOaCuslicenseId());
			response.setOffCode(licen.getOffCode());
			response.setOldCustomer(licen.getOldCustomer());
			response.setOldLicenseYear(licen.getOldLicenseYear());
			response.setOperateName(licen.getOperateName());
			response.setOperateRemark(licen.getOperateRemark());
			response.setReceiveDate(licen.getReceiveDate());
			response.setReceiveNo(licen.getReceiveNo());
			response.setStartDate(licen.getStartDate());
			response.setWarehouseAddress(licen.getWarehouseAddress());
			List<OaCustomerLicenDetail> details = oa0207JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}

	@Transactional
	public Oa020106FormVo saveCustomerLicenAll(Oa020106FormVo request) {
		OaCustomerLicen licen = new OaCustomerLicen();
		licen.setAddress(request.getAddress());
		licen.setApprove(request.getApprove());
		licen.setApproveName(request.getApproveName());
		licen.setBankGuarantee(request.getBankGuarantee());
		licen.setBankGuaranteeDate(request.getBankGuaranteeDate());
		licen.setBankGuaranteeNo(request.getBankGuaranteeNo());
		licen.setCompanyName(request.getCompanyName());
		licen.setEndDate(request.getEndDate());
		licen.setIdentifyNo(request.getIdentifyNo());
		licen.setIdentifyType(request.getIdentifyType());
		licen.setLicenseDate(request.getLicenseDate());
		licen.setLicenseNo(request.getReceiveNo());
		licen.setLicenseType(request.getLicenseType());
		licen.setLicenseTypeDesp(request.getLicenseTypeDesp());
		licen.setLicenseTypeFor(request.getLicenseTypeFor());
		licen.setMobile(request.getMobile());
		licen.setName(request.getName());
		licen.setOffCode(request.getOffCode());
		licen.setOldCustomer(request.getOldCustomer());
		licen.setOldLicenseYear(request.getOldLicenseYear());
		licen.setOperateName(request.getOperateName());
		licen.setOperateRemark(request.getOperateRemark());
		licen.setReceiveDate(request.getReceiveDate());
		licen.setReceiveNo(request.getReceiveNo());
		licen.setStartDate(request.getStartDate());
		licen.setWarehouseAddress(request.getWarehouseAddress());
		licen = oaCustomerLicenRep.save(licen);
		List<OaCustomerLicenDetail> details = request.getDetails();
		if (details != null) {
			int seq = 0;
			for (OaCustomerLicenDetail detail : details) {
				detail.setLicenseNo(request.getLicenseNo());
				detail.setOaCuslicenseId(licen.getOaCuslicenseId());
				detail.setSeq(new BigDecimal(seq++));
			}
			details = (List<OaCustomerLicenDetail>) oaCustomerLicenDetailRep.saveAll(details);
		}
		request.setOaCuslicenseId(licen.getOaCuslicenseId());
		request.setDetails(details);
		return request;
	}

	@Transactional
	public Oa020106FormVo updateCustomerLicenAll(Oa020106FormVo request) {
		Optional<OaCustomerLicen> licenOpt = oaCustomerLicenRep.findById(request.getOaCuslicenseId());
		if (licenOpt.isPresent()) {
			OaCustomerLicen licen = licenOpt.get();
			licen.setAddress(request.getAddress());
			licen.setApprove(request.getApprove());
			licen.setApproveName(request.getApproveName());
			licen.setBankGuarantee(request.getBankGuarantee());
			licen.setBankGuaranteeDate(request.getBankGuaranteeDate());
			licen.setBankGuaranteeNo(request.getBankGuaranteeNo());
			licen.setCompanyName(request.getCompanyName());
			licen.setEndDate(request.getEndDate());
			licen.setIdentifyNo(request.getIdentifyNo());
			licen.setIdentifyType(request.getIdentifyType());
			licen.setLicenseDate(request.getLicenseDate());
			licen.setLicenseNo(request.getLicenseNo());
			licen.setLicenseType(request.getLicenseType());
			licen.setLicenseTypeDesp(request.getLicenseTypeDesp());
			licen.setLicenseTypeFor(request.getLicenseTypeFor());
			licen.setMobile(request.getMobile());
			licen.setName(request.getName());
			licen.setOffCode(request.getOffCode());
			licen.setOldCustomer(request.getOldCustomer());
			licen.setOldLicenseYear(request.getOldLicenseYear());
			licen.setOperateName(request.getOperateName());
			licen.setOperateRemark(request.getOperateRemark());
			licen.setReceiveDate(request.getReceiveDate());
			licen.setReceiveNo(request.getReceiveNo());
			licen.setStartDate(request.getStartDate());
			licen.setWarehouseAddress(request.getWarehouseAddress());
			licen = oaCustomerLicenRep.save(licen);
			List<OaCustomerLicenDetail> details = request.getDetails();
			List<OaCustomerLicenDetail> detailsOld = new ArrayList<>();
			if (details != null) {
				int seq = 0;
				for (OaCustomerLicenDetail detail : details) {
					if (detail.getOaCuslicenseDtlId() != null) {
						Optional<OaCustomerLicenDetail> detailOpt = oaCustomerLicenDetailRep
								.findById(detail.getOaCuslicenseDtlId());
						if (detailOpt.isPresent()) {
							OaCustomerLicenDetail dtl = detailOpt.get();
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
				details = (List<OaCustomerLicenDetail>) oaCustomerLicenDetailRep.saveAll(detailsOld);
				if (request.getDeletes() != null && request.getDeletes().size() > 0) {
					for (OaCustomerLicenDetail delete : request.getDeletes()) {
						oaCustomerLicenDetailRep.deleteById(delete.getOaCuslicenseDtlId());
					}
				}

			}
			request.setDeletes(new ArrayList<>());
			request.setDetails(details);
		}
		return request;
	}

	public List<Oa0207CustomerVo> findCustomers(String offCode) {
		return oa0207JdbcRep.findCustomers(offCode);
	}

	public Oa020106FormVo findCustomer(String customerNo, String offCode) {
		return oa0207JdbcRep.findCustomer(offCode, customerNo);
	}

}
