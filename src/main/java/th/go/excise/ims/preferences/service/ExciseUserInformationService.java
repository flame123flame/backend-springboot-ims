package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.exciseuserinformation.model.RequestExciseUserInformation;
import th.go.excise.ims.ws.client.pcc.exciseuserinformation.model.ResponseExciseUserInformation;
import th.go.excise.ims.ws.client.pcc.exciseuserinformation.service.ExciseUserInformationWsService;

@Service
public class ExciseUserInformationService {
	
	@Autowired
	private ExciseUserInformationWsService exciseUserInformationWsService;
	
	
	public List<ResponseExciseUserInformation> webServiceGetExciseUserInformationByUserId(String userId) throws PccRestfulException {
		RequestExciseUserInformation requestData = new RequestExciseUserInformation();
		requestData.setUserId(userId);
		return exciseUserInformationWsService.execute(requestData);
	}
}
