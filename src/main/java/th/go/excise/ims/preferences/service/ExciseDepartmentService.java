package th.go.excise.ims.preferences.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepartmentRepository;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVoReq;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVoRes;

@Service
public class ExciseDepartmentService {

	private Logger logger = LoggerFactory.getLogger(ExciseDepartmentService.class);

	@Autowired
	private ExciseDepartmentRepository departmentRepository;

	public DataTableAjax<ExciseDepartmentVoRes> findDepartmentFlag(ExciseDepartmentVoReq request) {
		logger.info("findDepartmentFlag");
		List<ExciseDepartmentVoRes> resList = new ArrayList<ExciseDepartmentVoRes>();
		DataTableAjax<ExciseDepartmentVoRes> dataTableAjax = new DataTableAjax<>();
		try {

			resList = departmentRepository.findDepartmentFlag(request);
			Integer count = departmentRepository.countByDepartmentFlag(request);

			dataTableAjax.setDraw(request.getDraw() + 1);
			dataTableAjax.setData(resList);
			dataTableAjax.setRecordsTotal(count.intValue());
			dataTableAjax.setRecordsFiltered(count.intValue());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return dataTableAjax;
	}

}
