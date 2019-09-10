package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaCheckStatisticPaintJdbcRepository;
import th.go.excise.ims.ia.vo.Int0613FormVo;
import th.go.excise.ims.ia.vo.Int0613Vo;

@Service
public class Int0613Service {
	@Autowired
	private IaCheckStatisticPaintJdbcRepository iaCheckStatisticPaintJdbcRepository;
	
	public DataTableAjax<Int0613Vo> filterQtnHdr(Int0613FormVo request) {

		List<Int0613Vo> data = iaCheckStatisticPaintJdbcRepository.getDataFilter(request);
		/* convert date to string */

		DataTableAjax<Int0613Vo> dataTableAjax = new DataTableAjax<Int0613Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(iaCheckStatisticPaintJdbcRepository.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(iaCheckStatisticPaintJdbcRepository.countDatafilter(request));

		return dataTableAjax;
	}
	
}
