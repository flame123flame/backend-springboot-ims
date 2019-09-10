package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaAuditPmcommitH;
import th.go.excise.ims.ia.persistence.repository.IaAuditPmcommitHRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.IaAuditPmcommitHVo;

@Service
public class Int1305Service {
	private static final Logger logger = LoggerFactory.getLogger(Int1305Service.class);

	@Autowired
	private IaAuditPmcommitHRepository iaAuditPmcommitHRepository;

	@Autowired
	private IaCommonService iaCommonService;

	public IaAuditPmcommitHVo save(IaAuditPmcommitHVo vo) {
		IaAuditPmcommitH h = null;
		try {

			if (StringUtils.isNotBlank(vo.getAuditPmcommitNo())) {
				h = iaAuditPmcommitHRepository.findByAuditPmcommitNo(vo.getAuditPmcommitNo());
				h.setAuditFlag(vo.getAuditFlag());
				h.setConditionText(vo.getConditionText());
				h.setCriteriaText(vo.getCriteriaText());
				h = iaAuditPmcommitHRepository.save(h);
				vo.setAuditPmcommitId(h.getAuditPmcommitId());
				vo.setAuditPmcommitNo(h.getAuditPmcommitNo());
			} else {
				h = new IaAuditPmcommitH();
				h.setOfficeCode(vo.getOfficeCode());
				h.setBudgetYear(vo.getBudgetYear());
				h.setAuditPmcommitNo(iaCommonService.autoGetRunAuditNoBySeqName("PMC", vo.getOfficeCode(), "AUDIT_PMCOMMIT_NO_SEQ", 8));
				h.setUrlLink(vo.getUrlLink());
				h.setAuditFlag(vo.getAuditFlag());
				h.setConditionText(vo.getConditionText());
				h.setCriteriaText(vo.getCriteriaText());
				h = iaAuditPmcommitHRepository.save(h);
				vo.setAuditPmcommitId(h.getAuditPmcommitId());
				vo.setAuditPmcommitNo(h.getAuditPmcommitNo());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return vo;

	}

	public List<IaAuditPmcommitHVo> findAuditPmcommitHList() {
		List<IaAuditPmcommitH> iaAuditPmcommitHList = iaAuditPmcommitHRepository.findIaAuditPmcommitHAllDataActive();
		IaAuditPmcommitHVo hVo = null;
		List<IaAuditPmcommitHVo> auditPmcommitHVoList = new ArrayList<>();
		for (IaAuditPmcommitH data : iaAuditPmcommitHList) {
			hVo = new IaAuditPmcommitHVo();
			try {
				hVo.setAuditPmcommitId(data.getAuditPmcommitId());
				hVo.setOfficeCode(data.getOfficeCode());
				hVo.setBudgetYear(data.getBudgetYear());
				hVo.setAuditPmcommitNo(data.getAuditPmcommitNo());
				hVo.setUrlLink(data.getUrlLink());
				hVo.setAuditFlag(data.getAuditFlag());
				hVo.setConditionText(data.getConditionText());
				hVo.setCriteriaText(data.getCriteriaText());
				auditPmcommitHVoList.add(hVo);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		return auditPmcommitHVoList;
	}

	public IaAuditPmcommitHVo findByAuditPmcommitNo(String auditPmcommitNo) {
		IaAuditPmcommitHVo data = null;
		IaAuditPmcommitH h = null;
		ExciseDepartmentVo excise = null;
		h = iaAuditPmcommitHRepository.findByAuditPmcommitNo(auditPmcommitNo);
		try {
			data = new IaAuditPmcommitHVo();
			data.setAuditPmcommitId(h.getAuditPmcommitId());
			data.setAuditPmcommitNo(h.getAuditPmcommitNo());
			data.setBudgetYear(h.getBudgetYear());
			data.setUrlLink(h.getUrlLink());
			data.setOfficeCode(h.getOfficeCode());
			data.setAuditFlag(h.getAuditFlag());
			data.setConditionText(h.getConditionText());
			data.setCriteriaText(h.getCriteriaText());

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(h.getOfficeCode());
			data.setArea(excise.getArea());
			data.setSector(excise.getSector());
			data.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return data;
	}

}
