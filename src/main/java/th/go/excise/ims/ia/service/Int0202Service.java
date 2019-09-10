package th.go.excise.ims.ia.service;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.MessageContants;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int0202FormVo;
import th.go.excise.ims.ia.vo.Int0202Vo;

@Service
public class Int0202Service {

	@Autowired
	private IaQuestionnaireMadeHdrJdbcRepository iaQuestionnaireMadeHdrJdbcRepository;

	public DataTableAjax<Int0202Vo> findByRequest(Int0202FormVo request) {

		request.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode()); // set office code
		List<Int0202Vo> data = iaQuestionnaireMadeHdrJdbcRepository.getDataFilter(request);
		/* convert date to string */
		for (Int0202Vo obj : data) {
			/* to string status */
			obj.setStatusStr(ApplicationCache.getParamInfoByCode("IA_STATUS_REPLY_QTN", obj.getStatus()).getValue1());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT);

			if (obj.getCreatedDate() != null) {
				Date createdDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setCreatedDateStr(ConvertDateUtils.formatDateToString(createdDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getUpdatedDate() != null) {
				Date updatedDate = ConvertDateUtils.parseStringToDate(obj.getUpdatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setUpdatedDateStr(ConvertDateUtils.formatDateToString(updatedDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getStartDate() != null) {
				Date startDate = ConvertDateUtils.parseStringToDate(obj.getStartDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setStartDateStr(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getEndDate() != null) {
				Date endDate = ConvertDateUtils.parseStringToDate(obj.getEndDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setEndDateStr(ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
		}

		DataTableAjax<Int0202Vo> dataTableAjax = new DataTableAjax<Int0202Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(iaQuestionnaireMadeHdrJdbcRepository.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(iaQuestionnaireMadeHdrJdbcRepository.countDatafilter(request));

		return dataTableAjax;
	}

}
