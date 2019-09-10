package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.vo.ExciseDepartmentVoReq;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVoRes;
import th.go.excise.ims.ws.client.pcc.inquiryedoffice.model.EdOffice;

public interface ExciseDepartmentRepositoryCustom {

	public void batchMerge(List<EdOffice> edOfficeList);

	public Integer countByDepartmentFlag(ExciseDepartmentVoReq request);

	public List<ExciseDepartmentVoRes> findDepartmentFlag(ExciseDepartmentVoReq request);

}
