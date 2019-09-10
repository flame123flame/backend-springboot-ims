package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.security.domain.UserBean;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.common.constant.ProjectConstants.TA_AUDIT_STATUS;
import th.go.excise.ims.common.constant.ProjectConstants.TA_WORKSHEET_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.preferences.persistence.entity.ExciseSubdept;
import th.go.excise.ims.preferences.persistence.repository.ExciseSubdeptRepository;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSelect;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSelectRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetSendRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.vo.PersonAssignForm;
import th.go.excise.ims.ta.vo.PlanWorksheetAssignVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlCusVo;
import th.go.excise.ims.ta.vo.PlanWorksheetSendTableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetStatus;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.TaxDraftVo;

@Service
public class PlanWorksheetService {

	private static final Logger logger = LoggerFactory.getLogger(PlanWorksheetService.class);

	@Autowired
	private WorksheetSequenceService worksheetSequenceService;

	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;

	@Autowired
	private TaPlanWorksheetHdrRepository taPlanWorksheetHdrRepository;
	@Autowired
	private TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;

	@Autowired
	private TaPlanWorksheetSendRepository taPlanWorksheetSendRepository;
	@Autowired
	private TaPlanWorksheetSelectRepository taPlanWorksheetSelectRepository;

	@Autowired
	private TaWsReg4000Repository reg4000Repository;

	@Autowired
	private ExciseSubdeptRepository exciseSubdeptRepository;

	public TaPlanWorksheetHdr getPlanWorksheetHdr(PlanWorksheetVo formVo) {
		logger.info("getPlanWorksheetHdr budgetYear={}", formVo.getBudgetYear());
		return taPlanWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());
	}

	@Transactional(rollbackFor = { Exception.class })
	public void savePlanWorksheetHdr(PlanWorksheetVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String planNumber = worksheetSequenceService.getPlanNumber(officeCode, formVo.getBudgetYear());
		logger.info("savePlanWorksheetHdr officeCode={}, budgetYear={}, analysisNumber={}, planNumber={}", officeCode,
			formVo.getBudgetYear(), formVo.getAnalysisNumber(), planNumber);

		// ==> Update WorksheetHdr
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		worksheetHdr.setWorksheetStatus(TA_WORKSHEET_STATUS.SELECTION);
		taWorksheetHdrRepository.save(worksheetHdr);

		// ==> Save PlanWorksheetHdr
		TaPlanWorksheetHdr planHdr = new TaPlanWorksheetHdr();
		planHdr.setAnalysisNumber(formVo.getAnalysisNumber());
		planHdr.setBudgetYear(formVo.getBudgetYear());
		planHdr.setPlanNumber(planNumber);
		planHdr.setSendAllFlag(formVo.getSendAllFlag());
		taPlanWorksheetHdrRepository.save(planHdr);

		// Add more logic for support Send All and Send Hierarchy
		List<TaPlanWorksheetSend> planSendList = new ArrayList<>();
		TaPlanWorksheetSend planSend = null;
		if (FLAG.Y_FLAG.equals(formVo.getSendAllFlag())) {
			assignCentralOfficeCode(planSendList, formVo.getBudgetYear(), planNumber);

			List<ExciseDepartment> sectorList = ApplicationCache.getExciseSectorList();
			List<ExciseDepartment> areaList = null;
			// Sector
			for (ExciseDepartment sector : sectorList) {
				if (!ExciseUtils.isCentral(sector.getOfficeCode())) {
					planSend = new TaPlanWorksheetSend();
					planSend.setBudgetYear(formVo.getBudgetYear());
					planSend.setPlanNumber(planNumber);
					planSend.setOfficeCode(sector.getOfficeCode());
					planSend.setSendDate(LocalDate.now());
					planSendList.add(planSend);
					// Area
					areaList = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
					for (ExciseDepartment area : areaList) {
						planSend = new TaPlanWorksheetSend();
						planSend.setBudgetYear(formVo.getBudgetYear());
						planSend.setPlanNumber(planNumber);
						planSend.setOfficeCode(area.getOfficeCode());
						planSend.setSendDate(LocalDate.now());
						planSendList.add(planSend);
					}
				}
			}
		} else {
			assignCentralOfficeCode(planSendList, formVo.getBudgetYear(), planNumber);
		}
		taPlanWorksheetSendRepository.saveAll(planSendList);

		// Initial Data for PlanWorksheetSelect
		List<TaxDraftVo> taxDraftVoList = taWorksheetDtlRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		taPlanWorksheetSelectRepository.batchInsert(formVo.getBudgetYear(), taxDraftVoList);
	}

	private void assignCentralOfficeCode(List<TaPlanWorksheetSend> planSendList, String budgetYear, String planNumber) {
		List<ExciseSubdept> deptSelect = exciseSubdeptRepository.findByAuditSelectFlag();

		TaPlanWorksheetSend planSend = null;
		for (ExciseSubdept centralOfficeCode : deptSelect) {
			planSend = new TaPlanWorksheetSend();
			planSend.setBudgetYear(budgetYear);
			planSend.setPlanNumber(planNumber);
			planSend.setOfficeCode(centralOfficeCode.getOffCode());
			planSend.setSendDate(LocalDate.now());
			planSendList.add(planSend);
		}
	}

	@Transactional
	public void savePlanWorksheetDtl(PlanWorksheetVo formVo) {
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		String officeCode = userBean.getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		logger.info("savePlanWorkSheetDtl officeCode={}, budgetYear={}, planNumber={}, analysisNumber={}, newRegIds={}",
			officeCode, budgetYear, formVo.getPlanNumber(), formVo.getAnalysisNumber(),
			org.springframework.util.StringUtils.collectionToCommaDelimitedString(formVo.getIds()));
		
		List<String> planNewRegIdList = taPlanWorksheetDtlRepository.findNewRegIdByOfficeCodeAndPlanNumber(officeCode, formVo.getPlanNumber());
		List<String> planNewRegIdFlagNList = taPlanWorksheetDtlRepository.findNewRegIdByOfficeCodeAndPlanNumberAndIsDeletedFlagN(officeCode, formVo.getPlanNumber());
		List<String> selectNewRegIdList = formVo.getIds();
		
		// Keep New and Delete newRegId list
		TaPlanWorksheetDtl planDtl = null;
		List<String> insertNewRegIdList = new ArrayList<>();
		List<String> updateNewRegIdList = new ArrayList<>();
		
		// Loop selectNewRegIdList for Insert NewRegId
		for (String selNewRegId : selectNewRegIdList) {
			if (!planNewRegIdList.contains(selNewRegId)) {
				insertNewRegIdList.add(selNewRegId);
			}
		}
		
		// Loop planWorksheetDtlNewRegIdList for Update NewRegId
		for (String planRewRegId : planNewRegIdList) {
			if (!selectNewRegIdList.contains(planRewRegId)) {
				updateNewRegIdList.add(planRewRegId);
			}
		}
		
		// Insert newRegId to planWorksheetDtl
		if (CollectionUtils.isNotEmpty(insertNewRegIdList)) {
			for (String newRegId : insertNewRegIdList) {
				planDtl = new TaPlanWorksheetDtl();
				planDtl.setPlanNumber(formVo.getPlanNumber());
				planDtl.setAnalysisNumber(formVo.getAnalysisNumber());
				planDtl.setOfficeCode(officeCode);
				planDtl.setNewRegId(newRegId);
				planDtl.setAuditStatus(TA_AUDIT_STATUS.CODE_0100);
				planDtl.setAuSubdeptCode(userBean.getSubdeptCode());
				planDtl.setAuditPlanCode(worksheetSequenceService.getAuditPlanCode(officeCode, budgetYear));
				planDtl.setBudgetYear(formVo.getBudgetYear());
				taPlanWorksheetDtlRepository.save(planDtl);
				
				updateFlagWorksheetSelect(budgetYear, newRegId, officeCode, FLAG.Y_FLAG, LocalDate.now());
			}
		}
		
		// Update newRegId from planWorksheetDtl
		String isDeletedPlanDtl = null;
		String selFlag = null;
		LocalDate selDate = null;
		if (CollectionUtils.isNotEmpty(updateNewRegIdList)) {
			for (String newRegId : updateNewRegIdList) {
				if (selectNewRegIdList.contains(newRegId)) {
					isDeletedPlanDtl = FLAG.N_FLAG;
					selFlag = FLAG.Y_FLAG;
					selDate = LocalDate.now();
				} else {
					isDeletedPlanDtl = FLAG.Y_FLAG;
					selFlag = null;
					selDate = null;
				}
				taPlanWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, formVo.getPlanNumber(), newRegId);
				updateFlagWorksheetSelect(budgetYear, newRegId, officeCode, selFlag, selDate);
			}
		} else {
			for (String selecNewRegId : selectNewRegIdList) {
				if (!planNewRegIdFlagNList.contains(selecNewRegId)) {
					isDeletedPlanDtl = FLAG.N_FLAG;
					selFlag = FLAG.Y_FLAG;
					selDate = LocalDate.now();
					taPlanWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, formVo.getPlanNumber(), selecNewRegId);
					updateFlagWorksheetSelect(budgetYear, selecNewRegId, officeCode, selFlag, selDate);
				}
			}
		}
	}

	private void updateFlagWorksheetSelect(String budgetYear, String newRegId, String officeCode, String selFlag, LocalDate selDate) {
		logger.info("updateFlagWorksheetSelect budgetYear={}, newRegId={}, officeCode={}, selFlag={}, selDate={}", budgetYear, newRegId, officeCode, selFlag, selDate);
		List<TaPlanWorksheetSelect> planWorksheetSelList = taPlanWorksheetSelectRepository.findByBudgetYearAndNewRegId(budgetYear, newRegId);
		for (TaPlanWorksheetSelect planWorksheetSel : planWorksheetSelList) {
			if (ExciseUtils.isCentral(officeCode)) {
				planWorksheetSel.setCentralSelFlag(selFlag);
				planWorksheetSel.setCentralSelDate(selDate);
				planWorksheetSel.setCentralSelOfficeCode(officeCode);
			} else if (ExciseUtils.isSector(officeCode)) {
				planWorksheetSel.setSectorSelFlag(selFlag);
				planWorksheetSel.setSectorSelDate(selDate);
				planWorksheetSel.setSectorSelOfficeCode(officeCode);
			} else if (ExciseUtils.isArea(officeCode)) {
				planWorksheetSel.setAreaSelFlag(selFlag);
				planWorksheetSel.setAreaSelDate(selDate);
				planWorksheetSel.setAreaSelOfficeCode(officeCode);
			}
			taPlanWorksheetSelectRepository.save(planWorksheetSel);
		}
	}

	public List<TaPlanWorksheetDtl> findPlanWorksheetDtl(PlanWorksheetVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("findPlanWorkSheetDtl officeCode={}, planNumber={}", officeCode, formVo.getPlanNumber());
		
		List<TaPlanWorksheetDtl> list = null;
		if (FLAG.N_FLAG.equals(formVo.getSendAllFlag())) {
			if (ExciseUtils.isCentral(officeCode)) {
				list = taPlanWorksheetDtlRepository.findByOfficeCodeAndPlanNumberForCentral(formVo.getPlanNumber(), officeCode);
			} else {
				list = taPlanWorksheetDtlRepository.findByOfficeCodeAndPlanNumber(officeCode, formVo.getPlanNumber());
			}
		} else {
			list = taPlanWorksheetDtlRepository.findByPlanNumber(formVo.getPlanNumber());
		}
		
		return list;
	}

	public DataTableAjax<PlanWorksheetDatatableVo> planDtlDatatable(PlanWorksheetVo formVo) {
		logger.info("planDtlDatatable budgetYear={}, officeCOde={}", formVo.getBudgetYear(), formVo.getOfficeCode());
		
		DataTableAjax<PlanWorksheetDatatableVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(taPlanWorksheetDtlRepository.findByCriteria(formVo));
		dataTableAjax.setDraw(formVo.getDraw() + 1);
		int count = taPlanWorksheetDtlRepository.countByCriteria(formVo).intValue();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);
		
		return dataTableAjax;
	}

	public List<PlanWorksheetDatatableVo> planDtlDatatableAll(PlanWorksheetVo formVo) {
		formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		return taPlanWorksheetDtlRepository.findAllByCriteria(formVo);
	}

	public Boolean checkSubmitDatePlanWorksheetSend(PlanWorksheetVo formVo) {
		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCodeAndSubmitDateIsNull(formVo.getPlanNumber(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
		if (planSend != null) {
			return false;
		}
		return true;
	}

	@Transactional
	public void deletePlanWorksheetDlt(String id,String budgetYear) {
		logger.info("deletePlanWorksheetDlt by ID : {}", id);
//		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		TaPlanWorksheetHdr planHdr = taPlanWorksheetHdrRepository.findByBudgetYear(budgetYear);
		if (planHdr != null) {
			taPlanWorksheetDtlRepository.deleteByPlanNumberAndNewRegId(planHdr.getPlanNumber(), id);
		}
	}

	@Transactional
	public void deletePlanWorksheetAssingDlt(String id, String officeCode) {
		logger.info("deletePlanWorksheetDlt by ID : {}", id);
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		TaPlanWorksheetHdr planHdr = taPlanWorksheetHdrRepository.findByBudgetYear(budgetYear);
		
		if (planHdr != null) {
			taPlanWorksheetDtlRepository.deleteByPlanNumberAndNewRegId(planHdr.getPlanNumber(), id);
			TaPlanWorksheetSend planSendStampDate = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(planHdr.getPlanNumber(), officeCode);
			List<String> PlanDtlList = taPlanWorksheetDtlRepository.findNewRegIdByOfficeCodeAndPlanNumberAndIsDeletedFlagN(officeCode, planHdr.getPlanNumber());
			if (planSendStampDate != null) {
				planSendStampDate.setFacInNum(PlanDtlList.size());
				taPlanWorksheetSendRepository.save(planSendStampDate);
			}
		}
	}

	public void savePlanWorksheetSend(PlanWorksheetVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		formVo.setOfficeCode(officeCode);
		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
		Long count = taPlanWorksheetDtlRepository.countByCriteria(formVo);
		planSend.setFacInNum(new Integer(count.intValue()));
		planSend.setSubmitDate(LocalDate.now());
		taPlanWorksheetSendRepository.save(planSend);
		
		TaPlanWorksheetHdr planHdr = this.taPlanWorksheetHdrRepository.findByPlanNumber(formVo.getPlanNumber());
		
		if (FLAG.N_FLAG.equalsIgnoreCase(planHdr.getSendAllFlag())) {
			if (ExciseUtils.isCentral(officeCode)) {
				List<ExciseDepartment> sectorList = ApplicationCache.getExciseSectorList();
				for (ExciseDepartment sector : sectorList) {
					if (ExciseUtils.isCentral(sector.getOfficeCode())) {
						continue;
					}
					saveObjectTaPlanWorksheetSend(formVo, sector.getOfficeCode());
				}
			}
			if (ExciseUtils.isSector(officeCode)) {
				List<ExciseDepartment> areaList = ApplicationCache.getExciseAreaList(officeCode);
				for (ExciseDepartment area : areaList) {
					saveObjectTaPlanWorksheetSend(formVo, area.getOfficeCode());
				}
			}
		}
		
		List<TaPlanWorksheetDtl> planDtl = this.taPlanWorksheetDtlRepository.findByOfficeCodeAndPlanNumberLike(ExciseUtils.whereInLocalOfficeCode(officeCode), formVo.getPlanNumber());
		for (TaPlanWorksheetDtl dtl : planDtl) {
			dtl.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0200);
			this.taPlanWorksheetDtlRepository.save(dtl);
		}
		
	}

	public void savePlanWorksheetSendToArea(PlanWorksheetVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		formVo.setOfficeCode(officeCode);
/*		if (!ExciseUtils.isSector(officeCode)) {
			TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository
					.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
//			Long count = taPlanWorksheetDtlRepository.countByCriteria(formVo);
			formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
			Long countInNum = taPlanWorksheetDtlRepository.countByCriteria(formVo);
			formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
			Long countReNum = taPlanWorksheetDtlRepository.countByCriteria(formVo);
			
			planSend.setFacInNum(new Integer(countInNum.intValue()));
			planSend.setFacRsNum(new Integer(countReNum.intValue()));
			planSend.setSubmitDate(LocalDate.now());
			taPlanWorksheetSendRepository.save(planSend);
		}*/

		TaPlanWorksheetHdr planHdr = this.taPlanWorksheetHdrRepository.findByPlanNumber(formVo.getPlanNumber());

		if (FLAG.N_FLAG.equalsIgnoreCase(planHdr.getSendAllFlag())) {
			if (ExciseUtils.isCentral(officeCode)) {
				List<ExciseDepartment> sectorList = ApplicationCache.getExciseSectorList();
				for (ExciseDepartment sector : sectorList) {
					if (ExciseUtils.isCentral(sector.getOfficeCode())) {
						continue;
					}
//					saveObjectTaPlanWorksheetSendToArea(formVo, sector.getOfficeCode());
					saveObjectTaPlanWorksheetSendToSector(formVo, sector.getOfficeCode());
				}
			}
			if (ExciseUtils.isSector(officeCode)) {
				List<ExciseDepartment> areaList = ApplicationCache.getExciseAreaList(officeCode);
				for (ExciseDepartment area : areaList) {
					saveObjectTaPlanWorksheetSendToArea(formVo, area.getOfficeCode());
				}
			}
		}

		List<TaPlanWorksheetDtl> planDtl = this.taPlanWorksheetDtlRepository.findByOfficeCodeAndPlanNumberLike(ExciseUtils.whereInLocalOfficeCode(officeCode), formVo.getPlanNumber());
		for (TaPlanWorksheetDtl dtl : planDtl) {
			if (ExciseUtils.isCentral(officeCode)) {
				dtl.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0200);
			} else {
				dtl.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0101);
			}

			if (formVo.getIds().contains(dtl.getPlanWorksheetDtlId().toString())) {
				dtl.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
			} else {
				dtl.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
			}
			this.taPlanWorksheetDtlRepository.save(dtl);
		}
		
		
		if (!ExciseUtils.isSector(officeCode)) {
			TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
//			Long count = taPlanWorksheetDtlRepository.countByCriteria(formVo);
			formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
			Long countInNum = taPlanWorksheetDtlRepository.countByCriteria(formVo);
			formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
			Long countReNum = taPlanWorksheetDtlRepository.countByCriteria(formVo);
			
			planSend.setFacInNum(new Integer(countInNum.intValue()));
			planSend.setFacRsNum(new Integer(countReNum.intValue()));
			planSend.setSubmitDate(LocalDate.now());
			taPlanWorksheetSendRepository.save(planSend);
		}
	}

	private void saveObjectTaPlanWorksheetSendToArea(PlanWorksheetVo formVo, String officeCode) {
		TaPlanWorksheetSend planSendStampDate = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
		if (planSendStampDate == null) {
			planSendStampDate = new TaPlanWorksheetSend();
			planSendStampDate.setBudgetYear(formVo.getBudgetYear());
			planSendStampDate.setPlanNumber(formVo.getPlanNumber());
			planSendStampDate.setOfficeCode(officeCode);
			planSendStampDate.setSendDate(LocalDate.now());
			taPlanWorksheetSendRepository.save(planSendStampDate);
		}
	}

	private void saveObjectTaPlanWorksheetSendToSector(PlanWorksheetVo formVo, String officeCode) {
		Integer countCentalSend = taPlanWorksheetSelectRepository.findCentalAllSend();
		if (countCentalSend <= 0) {
			TaPlanWorksheetSend planSendStampDate = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
			if (planSendStampDate == null) {
				planSendStampDate = new TaPlanWorksheetSend();
				planSendStampDate.setBudgetYear(formVo.getBudgetYear());
				planSendStampDate.setPlanNumber(formVo.getPlanNumber());
				planSendStampDate.setOfficeCode(officeCode);
				planSendStampDate.setSendDate(LocalDate.now());
				taPlanWorksheetSendRepository.save(planSendStampDate);
			}
		}
	}

	private void saveObjectTaPlanWorksheetSend(PlanWorksheetVo formVo, String officeCode) {
		TaPlanWorksheetSend planSendStampDate = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), officeCode);
		
		formVo.setOfficeCode(officeCode);
		List<PlanWorksheetSendTableVo> planCount = taPlanWorksheetDtlRepository.findPlanWorksheetByDtl(formVo);
		planSendStampDate.setBudgetYear(formVo.getBudgetYear());
		planSendStampDate.setPlanNumber(formVo.getPlanNumber());
		planSendStampDate.setOfficeCode(officeCode);
		planSendStampDate.setSubmitDate(LocalDate.now());
		planSendStampDate.setFacInNum(planCount.get(0).getCountPlan());
		taPlanWorksheetSendRepository.save(planSendStampDate);
//		if (planSendStampDate == null) {
//			planSendStampDate = new TaPlanWorksheetSend();
//			planSendStampDate.setBudgetYear(formVo.getBudgetYear());
//			planSendStampDate.setPlanNumber(formVo.getPlanNumber());
//			planSendStampDate.setOfficeCode(officeCode);
//			planSendStampDate.setSubmitDate(LocalDate.now());
//			planSendStampDate.setFacInNum(planCount.get(0).getCountPlan());
//			taPlanWorksheetSendRepository.save(planSendStampDate);
//		}
	}

	public PlanWorksheetStatus getPlanHeaderStatus(PlanWorksheetVo formVo) {
		PlanWorksheetStatus status = new PlanWorksheetStatus();
		TaPlanWorksheetHdr planWsHdr = taPlanWorksheetHdrRepository.findByBudgetYear(formVo.getBudgetYear());
		
		if (planWsHdr != null) {
			ParamInfo statusDesc = ApplicationCache.getParamInfoByCode("TA_PLAN_STATUS", planWsHdr.getPlanStatus());
			if (statusDesc != null) {
				status.setPlanStatus(statusDesc.getParamCode());
				status.setApprovalComment(planWsHdr.getApprovalComment());
				status.setApprovedComment(planWsHdr.getApprovedComment());
				status.setPlanStatusDesc(statusDesc.getValue1());
			}
		}
		
		return status;
	}

	@Transactional(rollbackFor = { Exception.class })
	public void insertComment(TaPlanWorksheetHdr form) {
		TaPlanWorksheetHdr planHdr = taPlanWorksheetHdrRepository.findByBudgetYear(form.getBudgetYear());
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		if (StringUtils.isEmpty(form.getApprovedComment())) {
			planHdr.setApprovalBy(UserLoginUtils.getCurrentUsername());
			planHdr.setApprovalDate(LocalDateTime.now());
			planHdr.setApprovalComment(form.getApprovalComment());
			planHdr.setDocApproveNo(form.getDocApproveNo());
			planHdr.setDocApproveDate(form.getDocApproveDate());
			planHdr.setPlanStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0300);
		} else {
			planHdr.setApprovedBy(UserLoginUtils.getCurrentUsername());
			planHdr.setApprovedDate(LocalDateTime.now());
			planHdr.setApprovedComment(form.getApprovedComment());
			planHdr.setDocApproveNo(form.getDocApproveNo());
			planHdr.setDocApproveDate(form.getDocApproveDate());
			planHdr.setPlanStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0300);
		}
		taPlanWorksheetHdrRepository.save(planHdr);
		
		List<TaPlanWorksheetDtl> planDtlList = taPlanWorksheetDtlRepository.findByPlanNumber(planHdr.getPlanNumber());
		for (TaPlanWorksheetDtl planDtl : planDtlList) {
			planDtl.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0300);
			planDtl.setAuditPlanCode(worksheetSequenceService.getAuditPlanCode(officeCode, planHdr.getBudgetYear()));
			taPlanWorksheetDtlRepository.save(planDtl);
		}
	}

	@Transactional(rollbackFor = { Exception.class })
	public void clearDataByBudgetYear(String budgetYear) {
		logger.info("clearDataByBudgetYear budgetYear={}", budgetYear);
		taPlanWorksheetDtlRepository.forceDeleteByBudgetYear(budgetYear);
		taPlanWorksheetHdrRepository.forceDeleteByBudgetYear(budgetYear);
		taPlanWorksheetSendRepository.forceDeleteByBudgetYear(budgetYear);
		taPlanWorksheetSelectRepository.forceDeleteByBudgetYear(budgetYear);
		taWorksheetHdrRepository.updateWorksheetStatusByBudgetYear(TA_WORKSHEET_STATUS.CONDITION, budgetYear);
	}

	public PlanWorksheetDtlCusVo findByNewRegId(PlanWorksheetDtlCusVo formVo) {
		PlanWorksheetDtlCusVo dtlCus = new PlanWorksheetDtlCusVo();
		TaWsReg4000 reg4000 = new TaWsReg4000();
		reg4000 = reg4000Repository.getByNewRegId(formVo.getNewRegId());
		dtlCus.setWsReg4000Id(reg4000.getWsReg4000Id());
		dtlCus.setNewRegId(reg4000.getNewRegId());
		dtlCus.setCusId(reg4000.getCusId());
		dtlCus.setCusFullname(reg4000.getCusFullname());
		dtlCus.setCusAddress(reg4000.getCusAddress());
		dtlCus.setCusTelno(reg4000.getCusTelno());
		dtlCus.setCusEmail(reg4000.getCusEmail());
		dtlCus.setCusUrl(reg4000.getCusUrl());
		dtlCus.setFacId(reg4000.getFacId());
		dtlCus.setFacFullname(reg4000.getFacFullname());
		dtlCus.setFacAddress(reg4000.getFacAddress());
		dtlCus.setFacTelno(reg4000.getFacTelno());
		dtlCus.setFacEmail(reg4000.getFacEmail());
		dtlCus.setFacUrl(reg4000.getFacUrl());
		dtlCus.setOfficeCode(reg4000.getOfficeCode());
		dtlCus.setActiveFlag(reg4000.getActiveFlag());
		dtlCus.setDutyCode(reg4000.getDutyCode());
		dtlCus.setFacType(reg4000.getFacType());
		dtlCus.setRegStatus(reg4000.getRegStatus());
		dtlCus.setRegDate(reg4000.getRegDate().toString());
		dtlCus.setRegCapital(reg4000.getRegCapital());
		dtlCus.setDutyCodeDesc(ApplicationCache.getParamInfoByCode(PARAM_GROUP.EXCISE_PRODUCT_TYPE, reg4000.getDutyCode()).getValue1());
		return dtlCus;
	}

	@Transactional
	public TaPlanWorksheetDtl savePlanWorksheetDtlByAssing(PlanWorksheetDatatableVo formVo) {
		Optional<TaPlanWorksheetDtl> taPlanOpt = taPlanWorksheetDtlRepository.findById(formVo.getPlanWorksheetDtlId());
		TaPlanWorksheetDtl planDtl = new TaPlanWorksheetDtl();
		if (taPlanOpt.isPresent()) {
			planDtl = taPlanOpt.get();
			// TODO SET SOMETHING
			planDtl.setAuJobResp(formVo.getAuJobResp());
			planDtl.setAuSubdeptCode(formVo.getAuSubdeptCode());
			planDtl.setOfficeCode(formVo.getOfficeCode());
			planDtl.setAssignedOfficerBy(UserLoginUtils.getCurrentUserBean().getUsername());
			planDtl.setAssignedOfficerDate(LocalDateTime.now());
			planDtl.setAuditStatus(formVo.getAuditStatus());
			if (ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE.equals(formVo.getPlanType()) && StringUtils.isNotEmpty(formVo.getReplaceRegId())) {
				planDtl.setReplaceReason(formVo.getReplaceReason());
				planDtl.setReplaceRegId(formVo.getReplaceRegId());
				planDtl.setPlanReplaceId(formVo.getPlanReplaceId());
			}
			planDtl = taPlanWorksheetDtlRepository.save(planDtl);
		}
		
		if (ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE.equals(formVo.getPlanType()) && StringUtils.isNotEmpty(formVo.getReplaceRegId())) {
			Optional<TaPlanWorksheetDtl> taPlanResOpt = taPlanWorksheetDtlRepository.findById(formVo.getPlanReplaceId());
			TaPlanWorksheetDtl planResDtl = new TaPlanWorksheetDtl();
			if (taPlanOpt.isPresent()) {
				planResDtl = taPlanResOpt.get();
				planResDtl.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0900);
				taPlanWorksheetDtlRepository.save(planResDtl);
			}
		}
		
		return planDtl;
	}

	@Transactional
	public void saveStatusPlanWorksheetDtlByAssing(ExcisePerson formVo, String status) {
		taPlanWorksheetDtlRepository.updateStatusPlanWorksheetDtl(formVo, status);
	}

	public void savePlanWorksheetDtlByAssingList(PersonAssignForm formVo) {
		taPlanWorksheetDtlRepository.updateStatusPlanWorksheetDtlByList(formVo);
	}

	@Transactional
	public void savePlanWorksheetDtlAndAssign(PlanWorksheetAssignVo formVo) {
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		String officeCode = userBean.getOfficeCode();
		String budgetYear = formVo.getBudgetYear();

		logger.info("savePlanWorkSheetDtl officeCode={}, budgetYear={}, planNumber={}, analysisNumber={}, newRegIds={}",
			officeCode, budgetYear, formVo.getPlanNumber(), formVo.getAnalysisNumber(),
			org.springframework.util.StringUtils.collectionToCommaDelimitedString(formVo.getIds()));

		List<String> planNewRegIdList = taPlanWorksheetDtlRepository.findNewRegIdByPlanNumber(formVo.getPlanNumber());
		List<String> planNewRegIdFlagNList = taPlanWorksheetDtlRepository.findNewRegIdByPlanNumberAndIsDeletedFlagN(formVo.getPlanNumber());
		List<String> selectNewRegIdList = formVo.getIds();

		// Keep New and Delete newRegId list
		TaPlanWorksheetDtl planDtl = null;
		List<String> insertNewRegIdList = new ArrayList<>();
		List<String> updateNewRegIdList = new ArrayList<>();

		// Loop selectNewRegIdList for Insert NewRegId
		for (String selNewRegId : selectNewRegIdList) {
			if (!planNewRegIdList.contains(selNewRegId)) {
				insertNewRegIdList.add(selNewRegId);
			}
		}

		// Loop planWorksheetDtlNewRegIdList for Update NewRegId
		for (String planRewRegId : planNewRegIdList) {
			if (!selectNewRegIdList.contains(planRewRegId)) {
				updateNewRegIdList.add(planRewRegId);
			}
		}
		String assingOfficeCode = formVo.getSector();
		if (ExciseUtils.isCentral(formVo.getSector())) {
			assingOfficeCode = formVo.getSector();
		} else if (ExciseUtils.isArea(formVo.getArea())) {
			assingOfficeCode = formVo.getArea();
		}

		// Insert newRegId to planWorksheetDtl
		if (CollectionUtils.isNotEmpty(insertNewRegIdList)) {
			for (String newRegId : insertNewRegIdList) {
				planDtl = new TaPlanWorksheetDtl();
				planDtl.setPlanNumber(formVo.getPlanNumber());
				planDtl.setAnalysisNumber(formVo.getAnalysisNumber());
				planDtl.setOfficeCode(assingOfficeCode);
				planDtl.setNewRegId(newRegId);
				planDtl.setAuditStatus(TA_AUDIT_STATUS.CODE_0300);
				planDtl.setAuditPlanCode(worksheetSequenceService.getAuditPlanCode(officeCode, budgetYear));
//				planDtl.setAuSubdeptCode(userBean.getSubdeptCode());
				planDtl.setBudgetYear(formVo.getBudgetYear());
				taPlanWorksheetDtlRepository.save(planDtl);

				updateFlagWorksheetSelect(budgetYear, newRegId, officeCode, FLAG.Y_FLAG, LocalDate.now());
			}
		}

		// Update newRegId from planWorksheetDtl
		String isDeletedPlanDtl = null;
		String selFlag = null;
		LocalDate selDate = null;
		if (CollectionUtils.isNotEmpty(updateNewRegIdList)) {
			for (String newRegId : updateNewRegIdList) {
				if (selectNewRegIdList.contains(newRegId)) {
					isDeletedPlanDtl = FLAG.N_FLAG;
					selFlag = FLAG.Y_FLAG;
					selDate = LocalDate.now();
				} else {
					isDeletedPlanDtl = FLAG.Y_FLAG;
					selFlag = null;
					selDate = null;
				}
				taPlanWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, formVo.getPlanNumber(), newRegId);
				updateFlagWorksheetSelect(budgetYear, newRegId, officeCode, selFlag, selDate);
			}
		} else {
			for (String selecNewRegId : selectNewRegIdList) {
				if (!planNewRegIdFlagNList.contains(selecNewRegId)) {
					isDeletedPlanDtl = FLAG.N_FLAG;
					selFlag = FLAG.Y_FLAG;
					selDate = LocalDate.now();
					taPlanWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, formVo.getPlanNumber(), selecNewRegId);
					updateFlagWorksheetSelect(budgetYear, selecNewRegId, officeCode, selFlag, selDate);
				}
			}
		}

//		find worksheet send for update count plan
		TaPlanWorksheetSend planSendStampDate = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), assingOfficeCode);

		List<String> PlanDtlList = taPlanWorksheetDtlRepository.findNewRegIdByOfficeCodeAndPlanNumberAndIsDeletedFlagN(assingOfficeCode, formVo.getPlanNumber());
		if (planSendStampDate != null) {
//			logger.info(" find plan exist size is {} ",Integer.toString(PlanDtlList.size()));
			planSendStampDate.setFacInNum(PlanDtlList.size());

			taPlanWorksheetSendRepository.save(planSendStampDate);
		}

	}

	@Transactional
	public void uploadAssignPlan(PlanWorksheetAssignVo formVo) throws Exception {

		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		String officeCode = userBean.getOfficeCode();
		String budgetYear = formVo.getBudgetYear();

		logger.info("savePlanWorkSheetDtl officeCode={}, budgetYear={}, planNumber={}, analysisNumber={}, newRegIds={}",
			officeCode, budgetYear, formVo.getPlanNumber(), formVo.getAnalysisNumber(),
			org.springframework.util.StringUtils.collectionToCommaDelimitedString(formVo.getIds()));

		MultipartFile file = formVo.getFile();
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		List<TaPlanWorksheetDtl> list = new ArrayList<TaPlanWorksheetDtl>();
		List<String> listCheck = new ArrayList<>();
		Sheet sheet = workbook.getSheetAt(0);
		int index = 0;
		for (Row row : sheet) {
			TaPlanWorksheetDtl vo = new TaPlanWorksheetDtl();
			if (index > 0) {
				Cell cell1 = row.getCell(1);
				Cell cell2 = row.getCell(2);
				Cell cell3 = row.getCell(3);
				vo.setNewRegId(cell1.getStringCellValue().trim());
				vo.setOfficeCode(cell2.getStringCellValue().trim());
				vo.setAuditType(cell3.getStringCellValue().trim());
				vo.setAnalysisNumber(formVo.getAnalysisNumber());
				vo.setPlanNumber(formVo.getPlanNumber());
				list.add(vo);
				listCheck.add(cell1.getStringCellValue());
			}
			index++;
		}

		List<String> planNewRegIdList = taPlanWorksheetDtlRepository.findNewRegIdByPlanNumber(formVo.getPlanNumber());
//		List<String> planNewRegIdFlagNList = taPlanWorksheetDtlRepository.findNewRegIdByPlanNumberAndIsDeletedFlagN(formVo.getPlanNumber());
//		List<String> selectNewRegIdList = formVo.getIds();

		// Keep New and Delete newRegId list
		TaPlanWorksheetDtl planDtl = null;
		List<TaPlanWorksheetDtl> insertNewRegIdList = new ArrayList<>();
		List<TaPlanWorksheetDtl> updateNewRegIdList = new ArrayList<>();

		// Loop selectNewRegIdList for Insert NewRegId
		for (TaPlanWorksheetDtl selNewRegId : list) {
			if (!planNewRegIdList.contains(selNewRegId.getNewRegId())) {
				insertNewRegIdList.add(selNewRegId);
			}
		}

		// Loop planWorksheetDtlNewRegIdList for Update NewRegId\
		for (TaPlanWorksheetDtl selNewRegId : list) {
			if (planNewRegIdList.contains(selNewRegId.getNewRegId())) {
//				insertNewRegIdList.add(selNewRegId);
				updateNewRegIdList.add(selNewRegId);
			}
		}

//		String assingOfficeCode = formVo.getSector();
//		if (ExciseUtils.isCentral(formVo.getSector())) {
//			assingOfficeCode = formVo.getSector();
//		} else if (ExciseUtils.isArea(formVo.getArea())) {
//			assingOfficeCode = formVo.getArea();
//		}

		// Insert newRegId to planWorksheetDtl
		String planCode = "";
		if (CollectionUtils.isNotEmpty(insertNewRegIdList)) {
			for (TaPlanWorksheetDtl upload : insertNewRegIdList) {
				planCode =  worksheetSequenceService.getAuditPlanCode(officeCode, budgetYear);
				planDtl = new TaPlanWorksheetDtl();
				planDtl.setPlanNumber(upload.getPlanNumber());
				planDtl.setAnalysisNumber(upload.getAnalysisNumber());
				planDtl.setOfficeCode(upload.getOfficeCode());
				planDtl.setNewRegId(upload.getNewRegId());
				planDtl.setAuditStatus(TA_AUDIT_STATUS.CODE_0100);
				planDtl.setPlanType(upload.getAuditType());
				planDtl.setAuditPlanCode(planCode);
//				planDtl.setAuditType(upload.getAuditType());
//				planDtl.setAuSubdeptCode(userBean.getSubdeptCode());
				planDtl.setBudgetYear(formVo.getBudgetYear());
				taPlanWorksheetDtlRepository.save(planDtl);

				updateFlagWorksheetSelect(budgetYear, upload.getNewRegId(), officeCode, FLAG.Y_FLAG, LocalDate.now());
			}
		}

		// Update newRegId from planWorksheetDtl
		String isDeletedPlanDtl = null;
		String selFlag = null;
		LocalDate selDate = null;
		if (CollectionUtils.isNotEmpty(updateNewRegIdList)) {
			for (TaPlanWorksheetDtl upload : updateNewRegIdList) {
				isDeletedPlanDtl = FLAG.N_FLAG;
				selFlag = FLAG.Y_FLAG;
				selDate = LocalDate.now();

				taPlanWorksheetDtlRepository.updateIsDeletedByPlanNumberAndNewRegId(isDeletedPlanDtl, upload.getPlanNumber(), upload.getNewRegId());
				updateFlagWorksheetSelect(budgetYear, upload.getNewRegId(), officeCode, selFlag, selDate);
			}
		}
	}
	
	public List<TaPlanWorksheetDtl> readFileAssignData (PlanWorksheetAssignVo formVo) throws Exception {
		
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		String officeCode = userBean.getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		

		logger.info("upload plan assign officeCode={}, budgetYear={}, planNumber={}, analysisNumber={}, newRegIds={}",
			officeCode, budgetYear, formVo.getPlanNumber(), formVo.getAnalysisNumber(),
			org.springframework.util.StringUtils.collectionToCommaDelimitedString(formVo.getIds()));

		MultipartFile file = formVo.getFile();
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		List<TaPlanWorksheetDtl> list = new ArrayList<TaPlanWorksheetDtl>();
		List<String> listCheck = new ArrayList<>();
		Sheet sheet = workbook.getSheetAt(0);
		int index = 0;
		for (Row row : sheet) {
			TaPlanWorksheetDtl vo = new TaPlanWorksheetDtl();
			if (index > 0) {
				Cell cell1 = row.getCell(1);
				Cell cell2 = row.getCell(2);
				Cell cell3 = row.getCell(3);
				vo.setNewRegId(cell1.getStringCellValue().trim());
				vo.setOfficeCode(cell2.getStringCellValue().trim());
				vo.setAuditType(cell3.getStringCellValue().trim());
				vo.setAnalysisNumber(formVo.getAnalysisNumber());
				vo.setPlanNumber(formVo.getPlanNumber());
				list.add(vo);
				listCheck.add(cell1.getStringCellValue());
			}
			index++;
		}
		
		return list;
		
	}
	

	public void savePlanWorksheetSendByAdmin(PlanWorksheetVo formVo) {
//		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
//		formVo.setOfficeCode(officeCode);
/*		TaPlanWorksheetHdr planHdr = this.taPlanWorksheetHdrRepository.findByPlanNumber(formVo.getPlanNumber());

		if (FLAG.N_FLAG.equalsIgnoreCase(planHdr.getSendAllFlag())) {
			if (ExciseUtils.isCentral(officeCode)) {
				List<ExciseDepartment> sectorList = ApplicationCache.getExciseSectorList();
				for (ExciseDepartment sector : sectorList) {
					if (ExciseUtils.isCentral(sector.getOfficeCode())) {
						continue;
					}
//					saveObjectTaPlanWorksheetSendToArea(formVo, sector.getOfficeCode());
					saveObjectTaPlanWorksheetSendToSector(formVo, sector.getOfficeCode());
				}
			}
			if (ExciseUtils.isSector(officeCode)) {
				List<ExciseDepartment> areaList = ApplicationCache.getExciseAreaList(officeCode);
				for (ExciseDepartment area : areaList) {
					saveObjectTaPlanWorksheetSendToArea(formVo, area.getOfficeCode());
				}
			}
		}*/

		List<TaPlanWorksheetDtl> planDtl = this.taPlanWorksheetDtlRepository.findByOfficeCodeAndPlanNumberLike(formVo.getOfficeCode(), formVo.getPlanNumber());
		for (TaPlanWorksheetDtl dtl : planDtl) {
			if (Integer.parseInt(dtl.getAuditStatus()) <= Integer.parseInt(ProjectConstants.TA_AUDIT_STATUS.CODE_0300)) {
				dtl.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0300);
				if (formVo.getIds().contains(dtl.getPlanWorksheetDtlId().toString())) {
					dtl.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
				} else {
					dtl.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
				}
				this.taPlanWorksheetDtlRepository.save(dtl);
			}
		}
		
		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), formVo.getOfficeCode());
		formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
		int countInNum = taPlanWorksheetDtlRepository.countPlanTypeByOfficeCodeAndPlanType(formVo);
		formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
		int countReNum = taPlanWorksheetDtlRepository.countPlanTypeByOfficeCodeAndPlanType(formVo);
		
		planSend.setFacInNum(countInNum);
		planSend.setFacRsNum(countReNum);
		planSend.setSubmitDate(LocalDate.now());
		taPlanWorksheetSendRepository.save(planSend);
		
		
//		if (!ExciseUtils.isSector(formVo.getOfficeCode())) {
//			TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository
//					.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), formVo.getOfficeCode());
////			Long count = taPlanWorksheetDtlRepository.countByCriteria(formVo);
//			formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
//			Long countInNum = taPlanWorksheetDtlRepository.countByCriteria(formVo);
//			formVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
//			Long countReNum = taPlanWorksheetDtlRepository.countByCriteria(formVo);
//			
//			planSend.setFacInNum(new Integer(countInNum.intValue()));
//			planSend.setFacRsNum(new Integer(countReNum.intValue()));
//			planSend.setSubmitDate(LocalDate.now());
//			taPlanWorksheetSendRepository.save(planSend);
//		}
	}
	
	public List<TaPlanWorksheetDtl> findPlanWorksheetDtlByOfficeCode(PlanWorksheetVo formVo) {
		List<TaPlanWorksheetDtl> list = null;
		if (!StringUtils.isNotBlank(formVo.getOfficeCode())) {
			formVo.setOfficeCode(null);
		} else {
			if (ProjectConstants.EXCISE_OFFICE_CODE.TA_CENTRAL.equals(formVo.getOfficeCode())) {
				formVo.setOfficeCode("0014__");
			} else if (ExciseUtils.isSector(formVo.getOfficeCode())) {
				formVo.setOfficeCode(formVo.getOfficeCode().substring(0, 2) + "____");
			}
		}

		if (FLAG.N_FLAG.equals(formVo.getSendAllFlag())) {
			list = taPlanWorksheetDtlRepository.findByOfficeCodeAndPlanNumberLike(formVo.getOfficeCode(), formVo.getPlanNumber());
		}
		
		return list;
	}
	
	
	public PlanWorksheetVo findWorksheetHdrByStatusOrderById(String status){
		PlanWorksheetVo planWorksheetVo = new PlanWorksheetVo();
		List<TaWorksheetHdr> taWorksheetHdrList = taWorksheetHdrRepository.findWorksheetHdrByStatusOrderById(status);
		if(taWorksheetHdrList != null && taWorksheetHdrList.size()> 0) {
			planWorksheetVo.setBudgetYear(taWorksheetHdrList.get(0).getBudgetYear());
			planWorksheetVo.setAnalysisNumber(taWorksheetHdrList.get(0).getAnalysisNumber());
		}
		return planWorksheetVo;
	}
	
	public DataTableAjax<PlanWorksheetDatatableVo> planDtlOutPlanDatatable(PlanWorksheetVo formVo) {
		logger.info("planDtlDatatable budgetYear={}, officeCOde={}", formVo.getBudgetYear(), formVo.getOfficeCode());
		
		
		formVo.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0900.substring(0,2)+"__");

		DataTableAjax<PlanWorksheetDatatableVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(taPlanWorksheetDtlRepository.findOutPlanDtl(formVo));
		dataTableAjax.setDraw(formVo.getDraw() + 1);
		int count = taPlanWorksheetDtlRepository.countOutPlanDtl(formVo).intValue();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);

		return dataTableAjax;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public TaPlanWorksheetDtl savePlanWorksheetDtlOutPlan(PlanWorksheetDatatableVo formVo) {
		
		TaPlanWorksheetDtl planDtlInsert  = new TaPlanWorksheetDtl();
		TaPlanWorksheetDtl planDtl = new TaPlanWorksheetDtl();
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		String officeCode = userBean.getOfficeCode();
		String budgetYear = formVo.getBudgetYear();
		
		if (ProjectConstants.TA_PLAN_WORKSHEET_STATUS.OUTPLAN.equals(formVo.getPlanType())) {

//			insert outplan to plan detail
			planDtlInsert.setPlanNumber(formVo.getPlanNumber());
			planDtlInsert.setAnalysisNumber(formVo.getAnalysisNumber());
			planDtlInsert.setOfficeCode(formVo.getOfficeCode());
			planDtlInsert.setNewRegId(formVo.getReplaceRegId());
			planDtlInsert.setAuditStatus(TA_AUDIT_STATUS.CODE_0301);
			planDtlInsert.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.OUTPLAN);
			planDtlInsert.setAuditPlanCode(worksheetSequenceService.getAuditPlanCode(officeCode, budgetYear));
			planDtlInsert.setReplaceReason(formVo.getReplaceReason());
			planDtlInsert.setBudgetYear(formVo.getBudgetYear());
			planDtlInsert = taPlanWorksheetDtlRepository.save(planDtlInsert);
		} else {
//			find plan detail by id for replace 
			Optional<TaPlanWorksheetDtl> taPlanOpt = taPlanWorksheetDtlRepository.findById(formVo.getPlanWorksheetDtlId());
			if (taPlanOpt.isPresent()) {
				planDtl = taPlanOpt.get();
				
//				insert outplan to plan detail
				planDtlInsert.setPlanNumber(planDtl.getPlanNumber());
				planDtlInsert.setAnalysisNumber(planDtl.getAnalysisNumber());
				planDtlInsert.setOfficeCode(planDtl.getOfficeCode());
				planDtlInsert.setNewRegId(formVo.getReplaceRegId());
				planDtlInsert.setAuditStatus(TA_AUDIT_STATUS.CODE_0301);
				planDtlInsert.setPlanType(formVo.getPlanType());
//				planDtlInsert.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.OUTPLAN);
				planDtlInsert.setAuditPlanCode(planDtl.getAuditPlanCode());
				planDtlInsert.setReplaceReason(formVo.getReplaceReason());
				planDtlInsert = taPlanWorksheetDtlRepository.save(planDtlInsert);
				
				planDtl.setReplaceReason(formVo.getReplaceReason());
				planDtl.setReplaceRegId(formVo.getReplaceRegId());
				planDtl.setPlanReplaceId(planDtlInsert.getPlanWorksheetDtlId());
				planDtl.setAuditStatus(ProjectConstants.TA_AUDIT_STATUS.CODE_0900);
				
				planDtl = taPlanWorksheetDtlRepository.save(planDtl);

			}
		}
		
		updateCountFacNum(formVo);
		

		return planDtl;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateCountFacNum(PlanWorksheetDatatableVo formVo) {
		
//		find for update count outside plan
		PlanWorksheetVo planVo = new PlanWorksheetVo();
		planVo.setOfficeCode(formVo.getOfficeCode());
		planVo.setPlanNumber(formVo.getPlanNumber());
		planVo.setAnalysisNumber(formVo.getAnalysisNumber());
		planVo.setBudgetYear(formVo.getBudgetYear());
		TaPlanWorksheetSend planSend = taPlanWorksheetSendRepository.findByPlanNumberAndOfficeCode(formVo.getPlanNumber(), formVo.getOfficeCode());
		planVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.OUTPLAN);
		int countOutNum = taPlanWorksheetDtlRepository.countPlanTypeByOfficeCodeAndPlanType(planVo);
		planVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
		int countInNum = taPlanWorksheetDtlRepository.countPlanTypeByOfficeCodeAndPlanType(planVo);
		planVo.setPlanType(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
		int countReNum = taPlanWorksheetDtlRepository.countPlanTypeByOfficeCodeAndPlanType(planVo);
		
		planSend.setFacInNum(countInNum);
		planSend.setFacRsNum(countReNum);
		planSend.setFacOutNum(countOutNum+1);
		taPlanWorksheetSendRepository.save(planSend);
	}

}
