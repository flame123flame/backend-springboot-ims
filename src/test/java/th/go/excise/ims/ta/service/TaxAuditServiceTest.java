package th.go.excise.ims.ta.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.gson.Gson;

import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;
import th.go.excise.ims.ws.client.pcc.common.util.PccServiceTestUtils;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;
import th.go.excise.ims.ws.client.service.RestfulClientService;

public class TaxAuditServiceTest {
	
	@Test
	public void test_getOperatorDetail_Manual() throws Exception {
		WsRegfri4000FormVo wsRegfri4000FormVo = new WsRegfri4000FormVo();
		wsRegfri4000FormVo.setNewRegId("01055551727011001");
		
		String url = "http://webtest.excise.go.th/EDAuditServicesUAT/reg/RegFri4000";
		RegFri4000Service regFri4000Service = new RegFri4000Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());
		
		RequestData requestData = new RequestData();
		requestData.setType("2");
		requestData.setNid("");
		requestData.setNewregId(wsRegfri4000FormVo.getNewRegId());
		requestData.setHomeOfficeId("");
		requestData.setActive("1");
		requestData.setPageNo("1");
		requestData.setDataPerPage("1");
		
		List<RegMaster60> regMaster60List = regFri4000Service.execute(requestData).getRegMaster60List();
		WsRegfri4000FormVo formVo = new WsRegfri4000FormVo();
		RegMaster60 regMaster60 = null;
		if (regMaster60List != null && regMaster60List.size() > 0) {
			regMaster60 = regMaster60List.get(0);
			BeanUtils.copyProperties(formVo, regMaster60);
			formVo.setNewRegId(regMaster60.getNewregId());
			formVo.setCustomerAddress(ExciseUtils.buildCusAddress(regMaster60));
			formVo.setFacAddress(ExciseUtils.buildFacAddress(regMaster60));
			formVo.setFactoryType(ExciseUtils.getFactoryType(formVo.getNewRegId()));
			formVo.setFactoryTypeText("");
		}
		
		System.out.println(ToStringBuilder.reflectionToString(formVo, ToStringStyle.MULTI_LINE_STYLE));
	}
	
}
