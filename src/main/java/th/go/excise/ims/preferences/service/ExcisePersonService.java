package th.go.excise.ims.preferences.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonRepository;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonRepositoryCustom;
import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.exciseuserinformation.model.ResponseExciseUserInformation;

@Service
public class ExcisePersonService {

	private Logger logger = LoggerFactory.getLogger(ExcisePersonService.class);
	
	@Autowired
	private ExcisePersonRepositoryCustom excisePersonRepositoryCus;

	@Autowired
	private ExcisePersonRepository excisePersonRepository;
	
	@Autowired
	private ExciseUserInformationService exciseUserInformationService;

	@Transactional
	public List<ExcisePersonVoSelect> findPersonByName(String name) {
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByName(offCode, name);
		return resList;
	}

	public List<ExcisePersonVoSelect> findPersonByEdLogin(String edLogin) {
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByEdLogin(edLogin);
		return resList;
	}

	public DataTableAjax<ExcisePersonVoSelect> findPersonForAssign(DataTableRequest quest) {
		DataTableAjax<ExcisePersonVoSelect> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(excisePersonRepositoryCus.findAllForAssign(quest));
		dataTableAjax.setDraw(quest.getDraw() + 1);
		int count = excisePersonRepositoryCus.countAllForAssign(quest).intValue();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);
		return dataTableAjax;
	}

	public ExcisePerson savePerson(ExcisePersonVoSelect form) {
		Optional<ExcisePerson> person = excisePersonRepository.findById(form.getEdPersonSeq());
		ExcisePerson personRes = new ExcisePerson();
		if (person.isPresent()) {
			personRes = person.get();
			personRes.setAuSubdeptCode(form.getSubDeptCode());
			personRes.setAuSubdeptLevel(form.getLevel());
			excisePersonRepository.save(personRes);
		}
		return personRes;
	}

	public List<ExcisePersonVoSelect> findbyOfficeCode(String officeCode, String subDeptCode) {
		List<ExcisePersonVoSelect> resList = excisePersonRepositoryCus.findByOfficeCode(officeCode, subDeptCode);
		return resList;
	}

	@Transactional
	public ExcisePerson checkAccountByLdapLogin(UserDetails userDetails) {
		logger.info("checkAccountByLdapLogin username={}", userDetails.getUsername());
		
		List<ExcisePerson> excisePersonList = excisePersonRepository.findExcisePersonOrderbySeq(userDetails.getUsername());
		if (excisePersonList != null && excisePersonList.size() > 0) {
			ExcisePerson excisePerson = excisePersonList.get(0);
			if (!userDetails.getUsername().equals(excisePerson.getEdLogin()) ||
					!(userDetails.getUserThaiName() + (userDetails.getUserThaiSurname() != null ? " " + userDetails.getUserThaiSurname() : "")).equals(excisePerson.getEdPersonName()) ||
			        !userDetails.getOfficeCode().equals(excisePerson.getEdOffcode())) {
				excisePersonRepository.updateExcisePersonFromLdapData(excisePerson.getEdLogin());
			} else {
				return excisePerson;
			}
		}
		// case no user in excisePersion
		List<ResponseExciseUserInformation> response = new ArrayList<>();
		try {
			response = exciseUserInformationService.webServiceGetExciseUserInformationByUserId(userDetails.getUsername());
		} catch (PccRestfulException e) {
			logger.error(e.getMessage(), e);
		}
		ExcisePerson excisePerson = new ExcisePerson();
		if (response != null && response.size() > 0) {
			excisePerson.setEdPersonId(response.get(0).getNid());
		}
		excisePerson.setEdLogin(userDetails.getUsername());
		excisePerson.setEdPersonName(userDetails.getUserThaiName() + (userDetails.getUserThaiSurname() != null ? " " + userDetails.getUserThaiSurname() : ""));
		excisePerson.setEdOffcode(userDetails.getOfficeCode());
		excisePerson.setEdPositionName(userDetails.getTitle());
		excisePerson.setSeq(0);
		
		return excisePersonRepository.save(excisePerson);
	}
	
	public void addAdditionalInfo(UserDetails userDetails) {
		ExcisePerson excisePerson = excisePersonRepository.findByEdLogin(userDetails.getUsername());
		if (excisePerson != null) {
			userDetails.setSubdeptCode(excisePerson.getAuSubdeptCode());
			userDetails.setSubdeptLevel(excisePerson.getAuSubdeptLevel());
		}
	}

}
