package th.go.excise.ims.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.TravelEstimatorFormDtl;
import th.go.excise.ims.ia.persistence.repository.TravelEstimatorFormDtlRepository;

@Service
public class Int0501010101Serevice {
	@Autowired
	private TravelEstimatorFormDtlRepository travelEstimatorFormDtlRepository;
	
	public void saveDtl(TravelEstimatorFormDtl request) {
		if (request.getId() != null) {
			/* ___________ update ___________ */
			TravelEstimatorFormDtl data = travelEstimatorFormDtlRepository.findById(request.getId()).get();
			/* ____________ set value ______________ */
			
		} else {
			/* ___________ save ___________ */
			travelEstimatorFormDtlRepository.save(request);
		}
	}

}
