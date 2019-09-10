package th.co.baiwa.ws.userldap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleRequest;
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleResponse;
import th.go.excise.dexsrvint.wsdl.ldapgateway.ldpagauthenandgetuserrole.LDPAGAuthenAndGetUserRolePortType;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class LoginLdapProxyTest {
	
	@Autowired
	private LDPAGAuthenAndGetUserRolePortType loginLdapProxy;
	
	@Test
	public void test_login() {
		String user = "admin";
		String pass = "password";
		AuthenAndGetUserRoleRequest ldap = new AuthenAndGetUserRoleRequest();
		ldap.setUserId("pcc020100");
		ldap.setPassword("pcc020100");
		AuthenAndGetUserRoleResponse response = loginLdapProxy.ldpagAuthenAndGetUserRoleOperation(ldap);
		
		System.out.println(ToStringBuilder.reflectionToString(response, ToStringStyle.JSON_STYLE));
	}
	
}
