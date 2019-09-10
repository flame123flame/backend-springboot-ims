package th.go.excise.ims.preferences.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import th.go.excise.ims.preferences.persistence.entity.ExciseDuedatePs0112;

public interface ExciseDuedatePs0112RepositoryCustom {

	public void batchMerge(List<ExciseDuedatePs0112> exciseDuedatePs0112List);

	public Map<String, LocalDate> findByMonthRange(String startMonth, String endMonth);

}
