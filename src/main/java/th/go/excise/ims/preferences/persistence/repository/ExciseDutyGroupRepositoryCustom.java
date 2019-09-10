package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExciseDutyGroup;

public interface ExciseDutyGroupRepositoryCustom {
	
	public void batchMerge(List<ExciseDutyGroup> dutyGroupList);
	
}
