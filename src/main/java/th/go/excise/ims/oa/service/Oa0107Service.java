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
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicen;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydCustomerLicenDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaHydCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0107JdbcRepository;
import th.go.excise.ims.oa.vo.Oa010106FormVo;
import th.go.excise.ims.oa.vo.Oa0107CodeVo;
import th.go.excise.ims.oa.vo.Oa0107CustomerVo;
import th.go.excise.ims.oa.vo.Oa0107Vo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Service
public class Oa0107Service {

	@Autowired
	private Oa0107JdbcRepository oa0107JdbcRep;

	@Autowired
	OaHydCustomerLicenRepository oaHydCustomerLicenRep;

	@Autowired
	OaHydCustomerLicenDtlRepository oaHydCustomerLicenDtlRep;

	public DataTableAjax<Oa010106FormVo> filterByCriteria(Oa0107Vo request, String offCode) {
		List<Oa0107CodeVo> data = oa0107JdbcRep.getDataFilter(request, offCode);
		List<OaHydCustomerLicen> oldData = oa0107JdbcRep.licenseAll(request, offCode);
		List<OaHydCustomerLicen> realData = new ArrayList<OaHydCustomerLicen>();
		for (OaHydCustomerLicen old : oldData) {
			for (Oa0107CodeVo da : data) {
				if (da.getOffCode().equals(old.getOffCode()) && da.getIdentifyNo().equals(old.getIdentifyNo())
						&& da.getLicenseType().equals(old.getLicenseType())) {
					realData.add(old);
				}
			}
		}
		List<ExciseDepartment> exciseDepts = ApplicationCache.getExciseSectorList();
		List<Oa010106FormVo> realDataAgain = new ArrayList<Oa010106FormVo>();
		for (OaHydCustomerLicen real : realData) {
			Oa010106FormVo realD = new Oa010106FormVo();
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
		DataTableAjax<Oa010106FormVo> dataTableAjax = new DataTableAjax<Oa010106FormVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(realDataAgain);
		dataTableAjax.setRecordsTotal(oa0107JdbcRep.countDatafilter(request, offCode));
		dataTableAjax.setRecordsFiltered(oa0107JdbcRep.countDatafilter(request, offCode));
		return dataTableAjax;
	}

	public List<Oa010106FormVo> findCustomerLicenList(String customerIdStr, String licenseType) {
		List<Oa010106FormVo> response = new ArrayList<Oa010106FormVo>();
		BigDecimal customerId = new BigDecimal(customerIdStr);
		response = oa0107JdbcRep.findByCustomerIdAndLicenseType(customerId, licenseType);
		return response;
	}

	public Oa010106FormVo findCustomerLicenAll(String idStr) {
		Oa010106FormVo response = new Oa010106FormVo();
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaHydCustomerLicen> licenOpt = oaHydCustomerLicenRep.findById(id);
		if (licenOpt.isPresent()) {
			OaHydCustomerLicen licen = licenOpt.get();
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
			response.setLicenseTypeUsr(licen.getLicenseTypeUsr());
			response.setLicenseTypeUsrATxt(licen.getLicenseTypeUsrATxt());
			response.setLicenseTypeUsrBTxt(licen.getLicenseTypeUsrBTxt());
			response.setBankGuaranteeTxt(licen.getBankGuaranteeTxt());
			response.setEmail(licen.getEmail());
			List<OaHydCustomerLicenDtl> details = oa0107JdbcRep.findByLicenseId(response.getOaCuslicenseId());
			response.setDetails(details);
		}
		return response;
	}

	@Transactional
	public Oa010106FormVo saveCustomerLicenAll(Oa010106FormVo request) {
		OaHydCustomerLicen licen = new OaHydCustomerLicen();
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
		licen.setLicenseTypeUsr(request.getLicenseTypeUsr());
		licen.setLicenseTypeUsrATxt(request.getLicenseTypeUsrATxt());
		licen.setLicenseTypeUsrBTxt(request.getLicenseTypeUsrBTxt());
		licen.setBankGuaranteeTxt(request.getBankGuaranteeTxt());
		licen.setEmail(request.getEmail());
		licen = oaHydCustomerLicenRep.save(licen);
		List<OaHydCustomerLicenDtl> details = request.getDetails();
		if (details != null) {
			int seq = 0;
			for (OaHydCustomerLicenDtl detail : details) {
				detail.setLicenseNo(request.getLicenseNo());
				detail.setOaCuslicenseId(licen.getOaCuslicenseId());
				detail.setSeq(new BigDecimal(seq++));
			}
			details = (List<OaHydCustomerLicenDtl>) oaHydCustomerLicenDtlRep.saveAll(details);
		}
		request.setOaCuslicenseId(licen.getOaCuslicenseId());
		request.setDetails(details);
		return request;
	}

	@Transactional
	public Oa010106FormVo updateCustomerLicenAll(Oa010106FormVo request) {
		Optional<OaHydCustomerLicen> licenOpt = oaHydCustomerLicenRep.findById(request.getOaCuslicenseId());
		if (licenOpt.isPresent()) {
			OaHydCustomerLicen licen = licenOpt.get();
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
			licen.setLicenseTypeUsr(request.getLicenseTypeUsr());
			licen.setLicenseTypeUsrATxt(request.getLicenseTypeUsrATxt());
			licen.setLicenseTypeUsrBTxt(request.getLicenseTypeUsrBTxt());
			licen.setBankGuaranteeTxt(request.getBankGuaranteeTxt());
			licen.setEmail(request.getEmail());
			licen = oaHydCustomerLicenRep.save(licen);
			List<OaHydCustomerLicenDtl> details = request.getDetails();
			List<OaHydCustomerLicenDtl> detailsOld = new ArrayList<>();
			if (details != null) {
				int seq = 0;
				for (OaHydCustomerLicenDtl detail : details) {
					if (detail.getOaCuslicenseDtlId() != null) {
						Optional<OaHydCustomerLicenDtl> detailOpt = oaHydCustomerLicenDtlRep
								.findById(detail.getOaCuslicenseDtlId());
						if (detailOpt.isPresent()) {
							OaHydCustomerLicenDtl dtl = detailOpt.get();
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
				details = (List<OaHydCustomerLicenDtl>) oaHydCustomerLicenDtlRep.saveAll(detailsOld);
				if (request.getDeletes() != null && request.getDeletes().size() > 0) {
					for (OaHydCustomerLicenDtl delete : request.getDeletes()) {
						oaHydCustomerLicenDtlRep.deleteById(delete.getOaCuslicenseDtlId());
					}
				}

			}
			request.setDeletes(new ArrayList<>());
			request.setDetails(details);
		}
		return request;
	}

	public List<Oa0107CustomerVo> findCustomers(String offCode) {
		return oa0107JdbcRep.findCustomers(offCode);
	}

	public Oa010106FormVo findCustomer(String customerNo, String offCode) {
		return oa0107JdbcRep.findCustomer(offCode, customerNo);
	}

}
