package th.go.excise.ims.scheduler.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsLicfri6010ServiceTest {
	
	@Autowired
	private SyncWsLicfri6010Service syncWsLicfri6010Service;
	
//	@Test
	public void test_syncData() throws PccRestfulException {
		List<String> offCodeList = new ArrayList<>();
		offCodeList.add("010100");
		offCodeList.add("010200");
		offCodeList.add("010300");
		offCodeList.add("010400");
		offCodeList.add("010500");
		offCodeList.add("010600");
		offCodeList.add("010700");
		offCodeList.add("010800");
		offCodeList.add("010900");
		offCodeList.add("011000");
		offCodeList.add("020100");
		offCodeList.add("020200");
		offCodeList.add("020300");
		offCodeList.add("020400");
		offCodeList.add("020500");
		offCodeList.add("020600");
		offCodeList.add("020700");
		offCodeList.add("020800");
		offCodeList.add("020900");
		offCodeList.add("021000");
		offCodeList.add("021100");
		offCodeList.add("021200");
		offCodeList.add("030100");
		offCodeList.add("030200");
		offCodeList.add("030300");
		offCodeList.add("030400");
		offCodeList.add("030500");
		offCodeList.add("030600");
		offCodeList.add("030700");
		offCodeList.add("030800");
		offCodeList.add("030900");
		offCodeList.add("040100");
		offCodeList.add("040200");
		offCodeList.add("040300");
		offCodeList.add("040400");
		offCodeList.add("040500");
		offCodeList.add("040600");
		offCodeList.add("040800");
		offCodeList.add("040900");
		offCodeList.add("041000");
		offCodeList.add("041100");
		offCodeList.add("041200");
		offCodeList.add("050100");
		offCodeList.add("050200");
		offCodeList.add("050300");
		offCodeList.add("050400");
		offCodeList.add("050500");
		offCodeList.add("050600");
		offCodeList.add("050700");
		offCodeList.add("050800");
		offCodeList.add("050900");
		offCodeList.add("060100");
		offCodeList.add("060200");
		offCodeList.add("060300");
		offCodeList.add("060400");
		offCodeList.add("060500");
		offCodeList.add("060600");
		offCodeList.add("060700");
		offCodeList.add("060800");
		offCodeList.add("070100");
		offCodeList.add("070200");
		offCodeList.add("070300");
		offCodeList.add("070400");
		offCodeList.add("070500");
		offCodeList.add("070600");
		offCodeList.add("070700");
		offCodeList.add("070800");
		offCodeList.add("071000");
		offCodeList.add("080100");
		offCodeList.add("080200");
		offCodeList.add("080300");
		offCodeList.add("080400");
		offCodeList.add("080500");
		offCodeList.add("080600");
		offCodeList.add("080700");
		offCodeList.add("090100");
		offCodeList.add("090200");
		offCodeList.add("090300");
		offCodeList.add("090400");
		offCodeList.add("090500");
		offCodeList.add("090600");
		offCodeList.add("090700");
		offCodeList.add("100100");
		offCodeList.add("100200");
		offCodeList.add("100300");
		offCodeList.add("100400");
		RequestData requestData = new RequestData();
			for (String offCode : offCodeList) {
				try {
					requestData.setOffcode(offCode);
					requestData.setYearMonthFrom("201401");
					requestData.setYearMonthTo("201912");
					syncWsLicfri6010Service.syncData(requestData);
				} catch (Exception e) {
					System.out.println(offCode);
				}
			}
	}
	
	@Test 
	public void syncWs6010ToIaWs6010() {
		RequestData requestData = new RequestData();
		requestData.setYearMonthFrom("201803");
		requestData.setYearMonthTo("201907");
		
		syncWsLicfri6010Service.syncWs6010ToIaWs6010(requestData);
	}
	
}
