package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquiryholiday.model.Holiday;

public interface ExciseHolidayRepositoryCustom {
	
	public void batchMerge(List<Holiday> holidayList);
	
}
