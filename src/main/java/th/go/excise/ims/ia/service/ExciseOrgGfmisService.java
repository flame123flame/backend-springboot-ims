package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.ExciseOrgGfDisburseUnitVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDisb;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgGfmis;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgDisbRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseOrgGfmisRepository;

@Service
public class ExciseOrgGfmisService {
	@Autowired
	private ExciseOrgGfmisRepository exciseOrgGfmisRepository;

	@Autowired
	private ExciseOrgDisbRepository exciseOrgDisbRepository;

	public List<ExciseOrgGfDisburseUnitVo> findGfDisburseUnitAndName() {
		List<ExciseOrgGfmis> exciseOrgGfmisList = exciseOrgGfmisRepository.findGfDisburseUnitAndName();
		List<ExciseOrgGfDisburseUnitVo> resData = new ArrayList<>();
		ExciseOrgGfDisburseUnitVo dat = null;
		for (ExciseOrgGfmis exciseOrgGfmis : exciseOrgGfmisList) {
			dat = new ExciseOrgGfDisburseUnitVo();
			dat.setGfExciseName(exciseOrgGfmis.getGfExciseName());
			dat.setGfExciseNameAbbr(exciseOrgGfmis.getGfExciseNameAbbr());
			dat.setGfDisburseUnit(exciseOrgGfmis.getGfDisburseUnit());
			resData.add(dat);
		}
		return resData;
	}

	public ExciseOrgDisb findExciseOrgGfmisByGfDisburseUnit(String gfDisburseUnit) {
		return exciseOrgDisbRepository.findExciseOrgGfmisByGfDisburseUnit(gfDisburseUnit);
	}
}
