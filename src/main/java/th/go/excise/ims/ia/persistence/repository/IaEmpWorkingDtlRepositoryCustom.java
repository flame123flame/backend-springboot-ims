package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.preferences.persistence.entity.ExciseHoliday;

public interface IaEmpWorkingDtlRepositoryCustom {

	public List<IaEmpWorkingDtl> findByMonth(String workingDate);
	public List<ExciseHoliday> getHoliday(String workingDate);
}
