package th.go.excise.ims.ws.client.pcc.common.util;

import th.go.excise.ims.ws.client.pcc.common.PccServiceProperties;

public class PccServiceTestUtils {

	public static PccServiceProperties getPccServiceProperties() {
		PccServiceProperties properties = new PccServiceProperties();
		properties.setSystemId("REG");
		properties.setUsername("wss001");
		properties.setPassword("123456");
		properties.setIpAddress("192.168.160.67");
		return properties;
	}

}
