package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.ExciseOrgDepaccVo;
import th.go.excise.ims.ia.vo.ExciseOrgGfmisVo;
import th.go.excise.ims.ia.vo.Int1504FormVo;
import th.go.excise.ims.ia.vo.Int1504OrgFormVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDepacc;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgGfmis;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgDepaccRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgGfmisRepository;

@Service
public class Int1504Service {
	
	@Autowired
	private ExciseOrgGfmisRepository exciseOrgGfmisRepository;
	
	@Autowired
	private ExciseOrgDepaccRepository exciseOrgDepaccRepository;
	
	
	public List<ExciseOrgGfmis> listData() {
		List<ExciseOrgGfmis> dataList = new ArrayList<ExciseOrgGfmis>();
		dataList = exciseOrgGfmisRepository.listData();
		return dataList;
	}
	
	public List<ExciseOrgDepacc> listOrg(Int1504OrgFormVo form) {
		List<ExciseOrgDepacc> dataList = new ArrayList<ExciseOrgDepacc>();
		dataList = exciseOrgDepaccRepository.listOrg(form.getOfficeCode());
		return dataList;
	}
	
	
	
	public ExciseOrgGfmisVo saveOrgGfmis(Int1504FormVo vo) {
		ExciseOrgGfmis exciseOrgGfmis = null;
		ExciseDepartmentVo excise = null;
		try {
			if (StringUtils.isNotBlank(vo.getExciseOrgGfmisVo().getGfExciseCode())) {
				exciseOrgGfmis = new ExciseOrgGfmis();
				exciseOrgGfmis = exciseOrgGfmisRepository.findByGfExciseCode(vo.getExciseOrgGfmisVo().getGfExciseCode());			
				excise = ExciseDepartmentUtil.getExciseDepartmentFull(vo.getExciseOrgGfmisVo().getGfExciseCode());
				exciseOrgGfmis.setGfExciseName(excise.getArea());
				exciseOrgGfmis.setGfExciseNameAbbr(excise.getOffShortName());
				exciseOrgGfmis.setGfCostCenter(vo.getExciseOrgGfmisVo().getGfCostCenter());
				exciseOrgGfmis.setGfDisburseUnit(vo.getExciseOrgGfmisVo().getGfDisburseUnit());
				exciseOrgGfmis.setGfOwnerDeposit(vo.getExciseOrgGfmisVo().getGfOwnerDeposit());
				exciseOrgGfmis.setGfRecBudget(vo.getExciseOrgGfmisVo().getGfRecBudget());
				exciseOrgGfmis.setGfDeptCode(vo.getExciseOrgGfmisVo().getGfDeptCode());
				exciseOrgGfmis.setGfAreaCode(vo.getExciseOrgGfmisVo().getGfAreaCode());			
				exciseOrgGfmis = exciseOrgGfmisRepository.save(exciseOrgGfmis);
			} else {
				exciseOrgGfmis = new ExciseOrgGfmis();
				excise = ExciseDepartmentUtil.getExciseDepartmentFull(vo.getExciseOrgGfmisVo().getGfExciseCode());
				exciseOrgGfmis.setGfExciseName(excise.getArea());
				exciseOrgGfmis.setGfExciseNameAbbr(excise.getOffShortName());
				exciseOrgGfmis.setGfCostCenter(vo.getExciseOrgGfmisVo().getGfCostCenter());
				exciseOrgGfmis.setGfDisburseUnit(vo.getExciseOrgGfmisVo().getGfDisburseUnit());
				exciseOrgGfmis.setGfOwnerDeposit(vo.getExciseOrgGfmisVo().getGfOwnerDeposit());
				exciseOrgGfmis.setGfRecBudget(vo.getExciseOrgGfmisVo().getGfRecBudget());
				exciseOrgGfmis.setGfDeptCode(vo.getExciseOrgGfmisVo().getGfDeptCode());
				exciseOrgGfmis.setGfAreaCode(vo.getExciseOrgGfmisVo().getGfAreaCode());			
				exciseOrgGfmis = exciseOrgGfmisRepository.save(exciseOrgGfmis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// D1
		if (vo.getExciseOrgDepaccVos() != null && vo.getExciseOrgDepaccVos().size() > 0) {
			ExciseOrgDepacc val1 = null;
			List<ExciseOrgDepacc> exciseOrgDepacc = new ArrayList<>();
			for (ExciseOrgDepaccVo data1 : vo.getExciseOrgDepaccVos()) {
				val1 = new ExciseOrgDepacc();
				if (data1.getOrgDepaccId() != null) {
					val1 = exciseOrgDepaccRepository.findById(data1.getOrgDepaccId()).get();
					try {
						val1.setOfficeCode(data1.getOfficeCode());
						val1.setGfOwnerDeposit(data1.getGfOwnerDeposit());
						val1.setGfDepositCode(data1.getGfDepositCode());
						val1 = exciseOrgDepaccRepository.save(val1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						val1.setOfficeCode(data1.getOfficeCode());
						val1.setGfOwnerDeposit(data1.getGfOwnerDeposit());
						val1.setGfDepositCode(data1.getGfDepositCode());
					} catch (Exception e) {
						e.printStackTrace();
					}
					exciseOrgDepacc.add(val1);
				}
			}
			exciseOrgDepaccRepository.saveAll(exciseOrgDepacc);
		}
		return vo.getExciseOrgGfmisVo();
	}

}
