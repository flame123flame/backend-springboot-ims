package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.vo.ExcisePersonInfoVo;

public interface ExcisePersonInfoRepositoryCustom {

	public List<ExcisePersonInfoVo> findByWorkOffcode(String officeCode);
}
