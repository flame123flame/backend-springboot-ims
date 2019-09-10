package th.go.excise.ims.preferences.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.preferences.persistence.entity.ExcisePersonInfo;
import th.go.excise.ims.preferences.persistence.entity.ExcisePersonInfo1;
import th.go.excise.ims.preferences.persistence.entity.ExciseTitle;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonInfo1Repository;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonInfoRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseTitleRepository;
import th.go.excise.ims.preferences.vo.Ed04FormHeadVo;
import th.go.excise.ims.preferences.vo.Ed04FormSave;
import th.go.excise.ims.preferences.vo.ExcisePersonInfo1Vo;
import th.go.excise.ims.preferences.vo.ExcisePersonInfoVo;

@Service
public class Ed04Service {

	@Autowired
	private ExciseTitleRepository exciseTitleRepository;

	@Autowired
	private ExcisePersonInfoRepository excisePersonInfoRepository;

	@Autowired
	private ExcisePersonInfo1Repository excisePersonInfo1Repository;

	public List<ExciseTitle> listPersonThTitle() {
		List<ExciseTitle> dataList = new ArrayList<ExciseTitle>();
		dataList = exciseTitleRepository.listPersonThTitle();
		return dataList;
	}

	public ExcisePersonInfoVo savePerson(Ed04FormSave vo) {
		ExcisePersonInfo excisePersonInfo = null;
		try {
			excisePersonInfo = new ExcisePersonInfo();
			excisePersonInfo.setPersonLogin(vo.getExcisePersonInfoVo().getPersonLogin());
			String personid = vo.getExcisePersonInfoVo().getPersonId().replace("-", "");
			excisePersonInfo.setPersonId(personid);
			excisePersonInfo.setPersonType(vo.getExcisePersonInfoVo().getPersonType());
			excisePersonInfo.setPersonThTitle(vo.getExcisePersonInfoVo().getPersonThTitle());
			excisePersonInfo.setPersonThName(vo.getExcisePersonInfoVo().getPersonThName());
			excisePersonInfo.setPersonThSurname(vo.getExcisePersonInfoVo().getPersonThSurname());
			excisePersonInfo.setWorkOffcode(vo.getExcisePersonInfoVo().getWorkOffcode());
			excisePersonInfo.setWorkOffname(vo.getExcisePersonInfoVo().getWorkOffname());
			excisePersonInfo.setLinePosition(vo.getExcisePersonInfoVo().getLinePosition());
			excisePersonInfo.setCoupleThTitle(vo.getExcisePersonInfoVo().getCoupleThTitle());
			excisePersonInfo.setCoupleName(vo.getExcisePersonInfoVo().getCoupleName());
			excisePersonInfo.setCoupleSurnameName(vo.getExcisePersonInfoVo().getCoupleSurnameName());
			String couplepid = vo.getExcisePersonInfoVo().getCouplePid().replace("-", "");
			excisePersonInfo.setCouplePid(couplepid);
			excisePersonInfo.setFatherThTitle(vo.getExcisePersonInfoVo().getFatherThTitle());
			excisePersonInfo.setFatherName(vo.getExcisePersonInfoVo().getFatherName());
			excisePersonInfo.setFatherSurnameName(vo.getExcisePersonInfoVo().getFatherSurnameName());
			String fatherpid = vo.getExcisePersonInfoVo().getFatherPid().replace("-", "");
			excisePersonInfo.setFatherPid(fatherpid);
			excisePersonInfo.setMotherThTitle(vo.getExcisePersonInfoVo().getMotherThTitle());
			excisePersonInfo.setMotherName(vo.getExcisePersonInfoVo().getMotherName());
			excisePersonInfo.setMotherSurnameName(vo.getExcisePersonInfoVo().getMotherSurnameName());
			String motherpid = vo.getExcisePersonInfoVo().getMotherPid().replace("-", "");
			excisePersonInfo.setMotherPid(motherpid);
			excisePersonInfo.setPersonAddrno(vo.getExcisePersonInfoVo().getPersonAddrno());
			excisePersonInfo.setPersonMoono(vo.getExcisePersonInfoVo().getPersonMoono());
			excisePersonInfo.setPersonVillagename(vo.getExcisePersonInfoVo().getPersonVillagename());
			excisePersonInfo.setPersonSoiname(vo.getExcisePersonInfoVo().getPersonSoiname());
			excisePersonInfo.setPersonRoadname(vo.getExcisePersonInfoVo().getPersonRoadname());
			excisePersonInfo.setPersonAmphurCode(vo.getExcisePersonInfoVo().getPersonAmphurCode());
			excisePersonInfo.setPersonProvinceCode(vo.getExcisePersonInfoVo().getPersonProvinceCode());
			excisePersonInfo.setPersonTabbolCode(vo.getExcisePersonInfoVo().getPersonTabbolCode());
			excisePersonInfo.setZipCode(vo.getExcisePersonInfoVo().getZipCode());
			excisePersonInfo = excisePersonInfoRepository.save(excisePersonInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// D1
		if (vo.getExcisePersonInfo1Vos() != null && vo.getExcisePersonInfo1Vos().size() > 0) {
			ExcisePersonInfo1 val1 = null;
			List<ExcisePersonInfo1> excisePersonInfo1s = new ArrayList<>();
			for (ExcisePersonInfo1Vo data1 : vo.getExcisePersonInfo1Vos()) {
				val1 = new ExcisePersonInfo1();
				try {
					val1.setPersonLogin(data1.getPersonLogin());
					val1.setChildNo(data1.getChildNo());
					val1.setChildPid(data1.getChildPid());
					val1.setChildBirthDate(ConvertDateUtils.parseStringToDate(data1.getChildBirthDate(),
							ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setInstituteDesc(data1.getInstituteDesc());
					val1.setInstituteAmphurCode(data1.getInstituteAmphurCode());
					val1.setInstituteProvinceCode(data1.getInstituteProvinceCode());
					val1.setChildThTitle(data1.getChildThTitle());
					val1.setChildName(data1.getChildName());
					val1.setChildSurnameName(data1.getChildSurnameName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				excisePersonInfo1s.add(val1);
			}
			excisePersonInfo1Repository.saveAll(excisePersonInfo1s);
		}
		return vo.getExcisePersonInfoVo();
	}

	public ExcisePersonInfoVo editPerson(Ed04FormSave vo) {
		ExcisePersonInfo excisePersonInfo = null;
		excisePersonInfo = new ExcisePersonInfo();
		excisePersonInfo = excisePersonInfoRepository.findById(vo.getExcisePersonInfoVo().getId()).get();
		excisePersonInfo.setPersonLogin(vo.getExcisePersonInfoVo().getPersonLogin());
		String personid = vo.getExcisePersonInfoVo().getPersonId().replace("-", "");
		excisePersonInfo.setPersonId(personid);
		excisePersonInfo.setPersonType(vo.getExcisePersonInfoVo().getPersonType());
		excisePersonInfo.setPersonThTitle(vo.getExcisePersonInfoVo().getPersonThTitle());
		excisePersonInfo.setPersonThName(vo.getExcisePersonInfoVo().getPersonThName());
		excisePersonInfo.setPersonThSurname(vo.getExcisePersonInfoVo().getPersonThSurname());
		excisePersonInfo.setWorkOffcode(vo.getExcisePersonInfoVo().getWorkOffcode());
		excisePersonInfo.setWorkOffname(vo.getExcisePersonInfoVo().getWorkOffname());
		excisePersonInfo.setLinePosition(vo.getExcisePersonInfoVo().getLinePosition());
		excisePersonInfo.setCoupleThTitle(vo.getExcisePersonInfoVo().getCoupleThTitle());
		excisePersonInfo.setCoupleName(vo.getExcisePersonInfoVo().getCoupleName());
		excisePersonInfo.setCoupleSurnameName(vo.getExcisePersonInfoVo().getCoupleSurnameName());
		String couplepid = vo.getExcisePersonInfoVo().getCouplePid().replace("-", "");
		excisePersonInfo.setCouplePid(couplepid);
		excisePersonInfo.setFatherThTitle(vo.getExcisePersonInfoVo().getFatherThTitle());
		excisePersonInfo.setFatherName(vo.getExcisePersonInfoVo().getFatherName());
		excisePersonInfo.setFatherSurnameName(vo.getExcisePersonInfoVo().getFatherSurnameName());
		String fatherpid = vo.getExcisePersonInfoVo().getFatherPid().replace("-", "");
		excisePersonInfo.setFatherPid(fatherpid);
		excisePersonInfo.setMotherThTitle(vo.getExcisePersonInfoVo().getMotherThTitle());
		excisePersonInfo.setMotherName(vo.getExcisePersonInfoVo().getMotherName());
		excisePersonInfo.setMotherSurnameName(vo.getExcisePersonInfoVo().getMotherSurnameName());
		String motherpid = vo.getExcisePersonInfoVo().getMotherPid().replace("-", "");
		excisePersonInfo.setMotherPid(motherpid);
		excisePersonInfo.setPersonAddrno(vo.getExcisePersonInfoVo().getPersonAddrno());
		excisePersonInfo.setPersonMoono(vo.getExcisePersonInfoVo().getPersonMoono());
		excisePersonInfo.setPersonVillagename(vo.getExcisePersonInfoVo().getPersonVillagename());
		excisePersonInfo.setPersonSoiname(vo.getExcisePersonInfoVo().getPersonSoiname());
		excisePersonInfo.setPersonRoadname(vo.getExcisePersonInfoVo().getPersonRoadname());
		excisePersonInfo.setPersonAmphurCode(vo.getExcisePersonInfoVo().getPersonAmphurCode());
		excisePersonInfo.setPersonProvinceCode(vo.getExcisePersonInfoVo().getPersonProvinceCode());
		excisePersonInfo.setPersonTabbolCode(vo.getExcisePersonInfoVo().getPersonTabbolCode());
		excisePersonInfo.setZipCode(vo.getExcisePersonInfoVo().getZipCode());
		excisePersonInfo = excisePersonInfoRepository.save(excisePersonInfo);
		// D1
		if (vo.getExcisePersonInfo1Vos() != null && vo.getExcisePersonInfo1Vos().size() > 0) {
			ExcisePersonInfo1 val1 = null;
			List<ExcisePersonInfo1> excisePersonInfo1s = new ArrayList<>();
			for (ExcisePersonInfo1Vo data1 : vo.getExcisePersonInfo1Vos()) {
				val1 = new ExcisePersonInfo1();
				if (data1.getId() != null) {
					val1 = excisePersonInfo1Repository.findById(data1.getId()).get();
					val1.setPersonLogin(data1.getPersonLogin());
					val1.setChildNo(data1.getChildNo());
					val1.setChildPid(data1.getChildPid());
					val1.setChildBirthDate(ConvertDateUtils.parseStringToDate(data1.getChildBirthDate(),
							ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setInstituteDesc(data1.getInstituteDesc());
					val1.setInstituteAmphurCode(data1.getInstituteAmphurCode());
					val1.setInstituteProvinceCode(data1.getInstituteProvinceCode());
					val1.setChildThTitle(data1.getChildThTitle());
					val1.setChildName(data1.getChildName());
					val1.setChildSurnameName(data1.getChildSurnameName());
					val1 = excisePersonInfo1Repository.save(val1);
				} else {
					val1.setPersonLogin(data1.getPersonLogin());
					val1.setChildNo(data1.getChildNo());
					String childPid = data1.getChildPid().replace("-", "");
					val1.setChildPid(childPid);
					val1.setChildBirthDate(ConvertDateUtils.parseStringToDate(data1.getChildBirthDate(),
							ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setInstituteDesc(data1.getInstituteDesc());
					val1.setInstituteAmphurCode(data1.getInstituteAmphurCode());
					val1.setInstituteProvinceCode(data1.getInstituteProvinceCode());
					val1.setChildThTitle(data1.getChildThTitle());
					val1.setChildName(data1.getChildName());
					val1.setChildSurnameName(data1.getChildSurnameName());
					excisePersonInfo1s.add(val1);
				}
			}
			excisePersonInfo1Repository.saveAll(excisePersonInfo1s);
		}
		return vo.getExcisePersonInfoVo();
	}

	public ExcisePersonInfo dataHead(Ed04FormHeadVo form) {
		ExcisePersonInfo dataList = excisePersonInfoRepository.dataHead(form.getPersonLogin());
		return dataList;
	}

	public List<ExcisePersonInfo1> listChild(Ed04FormHeadVo form) {
		List<ExcisePersonInfo1> dataList = new ArrayList<ExcisePersonInfo1>();
		dataList = excisePersonInfo1Repository.listChild(form.getPersonLogin());
		return dataList;
	}

}
