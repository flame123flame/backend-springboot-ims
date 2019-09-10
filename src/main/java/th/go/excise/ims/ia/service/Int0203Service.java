package th.go.excise.ims.ia.service;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0203JdbcRepository;
import th.go.excise.ims.ia.vo.Int0203FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Service
public class Int0203Service {

	@Autowired
	private Int0203JdbcRepository int0203JdbcRepository;
	
	public DataTableAjax<Int02Vo> filterQtnHdr(Int0203FormVo request) {

		List<Int02Vo> data = int0203JdbcRepository.getDataFilter(request);
		/* convert date to string */
		for (Int02Vo obj : data) {
			/* to string status */
			obj.setStatus(ApplicationCache.getParamInfoByCode(IaConstants.IA_STATUS.PARAM_GROUP_CODE, obj.getStatus()).getValue1());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT);

			if (obj.getCreatedDate() != null) {
				Date createdDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setCreatedDateStr(ConvertDateUtils.formatDateToString(createdDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getUpdatedDate() != null) {
				Date updatedDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setUpdatedDateStr(ConvertDateUtils.formatDateToString(updatedDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getStartDate() != null) {
				Date startDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setStartDateStr(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getEndDate() != null) {
				Date endDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setEndDateStr(ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
		}

		DataTableAjax<Int02Vo> dataTableAjax = new DataTableAjax<Int02Vo>();
//		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(data.size());
		dataTableAjax.setRecordsFiltered(data.size());
		return dataTableAjax;
	}
}
