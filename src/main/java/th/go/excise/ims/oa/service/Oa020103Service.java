package th.go.excise.ims.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0201JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa020103Vo;
import th.go.excise.ims.oa.vo.Oa0206FormVo;

@Service
public class Oa020103Service {
	
	@Autowired
	private Oa0201JdbcRepository oa0201jdbc;
	
	
	public DataTableAjax<Oa020103Vo> getUserAuditer(String officeCode , Oa0206FormVo request) {

		String officeCon = OaOfficeCode.officeCodeLike(officeCode);
		
		List<Oa020103Vo> data = oa0201jdbc.findUserAuditer(officeCon ,request);
		int count = oa0201jdbc.countUserAuditer(officeCon, request);
		DataTableAjax<Oa020103Vo> dataTableAjax = new DataTableAjax<Oa020103Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}
	
	

}
