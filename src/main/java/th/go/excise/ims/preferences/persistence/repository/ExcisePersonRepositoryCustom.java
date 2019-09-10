package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;

public interface ExcisePersonRepositoryCustom {

	public List<ExcisePersonVoSelect> findByName(String officeCode, String name);
	
	public List<ExcisePersonVoSelect> findByOfficeCode(String officeCode,String subDeptCode);

	public List<ExcisePersonVoSelect> findByEdLogin(String edLogin);
	
	public List<ExcisePersonVoSelect> findAllForAssign(DataTableRequest quest);
	
	public Integer countAllForAssign(DataTableRequest quest);

}
