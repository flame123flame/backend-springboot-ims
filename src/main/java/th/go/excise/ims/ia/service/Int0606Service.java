package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaCheckLicense;
import th.go.excise.ims.ia.persistence.entity.IaConcludeFollowDetail;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.repository.IaConcludeFollowDetailRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0606JdbcRepository;
import th.go.excise.ims.ia.vo.Int0606FormDetail;
import th.go.excise.ims.ia.vo.Int0606FormHdrId;
import th.go.excise.ims.ia.vo.Int0606FormVo;
import th.go.excise.ims.ia.vo.Int0606Vo;

@Service
public class Int0606Service {
	
	@Autowired
	Int0606JdbcRepository int0606JdbcRepository;
	
	@Autowired
	IaConcludeFollowDetailRepository iaConcludeFollowDetailRepository;
	
	public List<IaCheckLicense> list(Int0606FormVo form) {
		List<IaCheckLicense> iaCheckLicenseList = new ArrayList<IaCheckLicense>();
		iaCheckLicenseList = int0606JdbcRepository.list(form);
		return iaCheckLicenseList;
	}
	
	public List<Int0606Vo> FindIdHdr(Int0606FormHdrId form) {
		List<Int0606Vo> iaFindIdHdr = new ArrayList<Int0606Vo>();
		iaFindIdHdr = int0606JdbcRepository.FindIdHdr(form);
		return iaFindIdHdr;
	}
	
	@Transactional
	public void saveConcludeFollow(Int0606FormDetail form) {	
		IaConcludeFollowDetail entity = new IaConcludeFollowDetail();
		entity.setIdHdr(form.getId());
		entity.setDetectedObserved(form.getDetectedObserved());
		entity.setWhatShouldBe(form.getWhatShouldBe());
		entity.setIssues(form.getIssues());
		iaConcludeFollowDetailRepository.save(entity);	
	}

}
