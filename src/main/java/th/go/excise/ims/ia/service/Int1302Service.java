package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1D;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1H;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy1DRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy1HRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.Int1302FormVo;
import th.go.excise.ims.ia.vo.Int1302Py1NoVo;
import th.go.excise.ims.ia.vo.Int1302SaveDtlFormVo;
import th.go.excise.ims.ia.vo.Int1302Vo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1DRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1HRepository;

@Service
public class Int1302Service {
	@Autowired
	private WsPmPy1HRepository wsPmPy1HRepository;

	@Autowired
	private WsPmPy1DRepository wsPmPy1DRepository;

	@Autowired
	private IaAuditPy1HRepository iaAuditPy1HRepository;

	@Autowired
	private IaAuditPy1DRepository iaAuditPy1DRepository;

	@Autowired
	private IaCommonService iaCommonService;

	public ResponseData<Int1302Vo> list(Int1302FormVo form) {
		ResponseData<Int1302Vo> response = new ResponseData<Int1302Vo>();

//		form.setArea("010000");
		Int1302Vo int1302Vo = new Int1302Vo();
		WsPmPy1H wsPmPy1H = wsPmPy1HRepository.findByOffCodeByFormYear(form.getOfficeCode(), form.getBudgetYear());
		List<WsPmPy1D> wsPmPy1DList = null;
		if (wsPmPy1H == null) {
			response.setMessage("ไม่พบข้อมูลที่ค้นหา");
			response.setStatus(RESPONSE_STATUS.FAILED);
		} else if (wsPmPy1H != null) {
			wsPmPy1DList = wsPmPy1DRepository.findByFormCodeByOffCode(wsPmPy1H.getFormCode(), form.getOfficeCode());
			int1302Vo.setWsPmPy1H(wsPmPy1H);
			int1302Vo.setWsPmPy1DList(wsPmPy1DList);
			response.setData(int1302Vo);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		}

		return response;
	}

	public String saveData(Int1302SaveDtlFormVo form) {
		IaAuditPy1H dataHdrSave = new IaAuditPy1H();
		IaAuditPy1D dataDtlSave = null;
//		String py1No = "PY1 ";
		String py1No = iaCommonService.autoGetRunAuditNoBySeqName("PY1", form.getOfficeCode(), "AUDIT_PM_PY1_NO_SEQ", 8);

		dataHdrSave.setAuditPy1No(py1No);
		dataHdrSave.setBuggetYear(form.getBuggetYear());
		dataHdrSave.setOfficeCode(form.getOfficeCode());
		dataHdrSave.setOverallResules(form.getOverallResules());
		dataHdrSave.setAuditResult(form.getAuditResult());
		dataHdrSave.setConditionText(form.getConditionText());
		dataHdrSave.setCriteriaText(form.getCriteriaText());
		iaAuditPy1HRepository.save(dataHdrSave);
		for (IaAuditPy1D dataDtl : form.getIaAuditPy1DList()) {
			dataDtlSave = new IaAuditPy1D();
			dataDtlSave.setAuditPy1No(py1No);
			dataDtlSave.setTopicDesc(dataDtl.getTopicDesc());
			dataDtlSave.setTopicAnswer(dataDtl.getTopicAnswer());
			dataDtlSave.setAuditResult(dataDtl.getAuditResult());
			iaAuditPy1DRepository.save(dataDtlSave);
		}
		return py1No;
	}

	public Int1302Py1NoVo findByPy1No(String py1No) {
		Int1302Py1NoVo dataRes = new Int1302Py1NoVo();
		IaAuditPy1H py1Hdr = iaAuditPy1HRepository.findByAuditPy1No(py1No);
		List<IaAuditPy1D> py1DtlList = iaAuditPy1DRepository.findByAuditPy1No(py1No);
		dataRes.setIaAuditPy1H(py1Hdr);
		dataRes.setIaAuditPy1DList(py1DtlList);
		
		if(dataRes.getIaAuditPy1H().getOfficeCode() != null) {
			dataRes.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(dataRes.getIaAuditPy1H().getOfficeCode()));
		}
		return dataRes;
	}

	public List<IaAuditPy1H> getPy1NoList() {
		return iaAuditPy1HRepository.getAuditPy1NoList();
	}

	public void editData(Int1302SaveDtlFormVo form) {
		// get data to edit
		IaAuditPy1D dataDtlEdit = null;
		IaAuditPy1H dataHdrEdit = iaAuditPy1HRepository.findByAuditPy1No(form.getAuditPy1No());
		dataHdrEdit.setAuditResult(form.getAuditResult());
		dataHdrEdit.setConditionText(form.getConditionText());
		dataHdrEdit.setCriteriaText(form.getCriteriaText());
		iaAuditPy1HRepository.save(dataHdrEdit);
		// loop for edit Detail
		for (IaAuditPy1D dataDtl : form.getIaAuditPy1DList()) {
			dataDtlEdit = iaAuditPy1DRepository.findById(dataDtl.getAuditPy1DId()).get();
			dataDtlEdit.setAuditResult(dataDtl.getAuditResult());
			iaAuditPy1DRepository.save(dataDtlEdit);
		}
	}
}
