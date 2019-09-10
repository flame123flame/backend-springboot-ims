package th.go.excise.ims.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.controller.Int11Controller;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaConcludeFollowHdrJdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Service
public class Int11Service {
	
	private Logger logger = LoggerFactory.getLogger(Int11Service.class);

	@Autowired
	private IaConcludeFollowHdrJdbcRepository iaConcludeFollowHdrJdbcRepository;

	public DataTableAjax<Int11Vo> list(Int11FormVo form) {
		List<Int11Vo> dataList = iaConcludeFollowHdrJdbcRepository.getData(form);
		for (Int11Vo int11Vo : dataList) {
			int11Vo.setApproveDateString(ConvertDateUtils.formatDateToString(int11Vo.getApproveDate(),ConvertDateUtils.DD_MM_YYYY));
			int11Vo.setDateFromString(ConvertDateUtils.formatDateToString(int11Vo.getDateFrom(), ConvertDateUtils.DD_MM_YYYY));
			int11Vo.setDateToString(ConvertDateUtils.formatDateToString(int11Vo.getDateTo(), ConvertDateUtils.DD_MM_YYYY));
			
			/* set ExciseDepartmentVo */
			logger.info(int11Vo.getExciseCode());
			if(int11Vo.getExciseCode() != null) {
				int11Vo.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(int11Vo.getExciseCode()));
			}
		}
		
		DataTableAjax<Int11Vo> dataTableAjax = new DataTableAjax<Int11Vo>();
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}

}
