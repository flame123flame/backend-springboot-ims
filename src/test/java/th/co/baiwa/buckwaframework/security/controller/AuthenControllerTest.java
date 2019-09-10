package th.co.baiwa.buckwaframework.security.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.URL;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles(value = PROFILE.UAT)
public class AuthenControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
			.webAppContextSetup(this.wac)
			.addFilters(springSecurityFilterChain)
			.build();
	}
	
//	@Test
	public void ldapLoginWithValidUserThenAuthenticated() throws Exception {
		System.out.println("- - - - - ldapLoginWithValidUserThenAuthenticated - - - - -");
		
		FormLoginRequestBuilder login = formLogin()
			.loginProcessingUrl(URL.LOGIN_WEB)
			.user("ben")
			.password("benspassword");
		
		mockMvc.perform(login)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(authenticated()
			.withUsername("ben")
		);
	}
	
//	@Test
	public void ldapLoginWithInvalidUserThenUnauthenticated() throws Exception {
		System.out.println("- - - - - ldapLoginWithInvalidUserThenUnauthenticated - - - - -");
		
		FormLoginRequestBuilder login = formLogin()
			.loginProcessingUrl(URL.LOGIN_WEB)
			.user("invalid")
			.password("invalidpassword");
		
		mockMvc.perform(login)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(unauthenticated()
		);
	}
	
	@Test
	public void webLoginWithValidUserThenAuthenticated() throws Exception {
		System.out.println("- - - - - webLoginWithInvalidUserThenAuthenticated - - - - -");
		
		FormLoginRequestBuilder login = formLogin()
			.loginProcessingUrl(URL.LOGIN_WEB)
			.user("ta001401")
			.password("password");
		
		mockMvc.perform(login)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(authenticated()
		);
	}
	
	//@Test
	public void webLoginWithInvalidUserThenUnauthenticated() throws Exception {
		System.out.println("- - - - - webLoginWithInvalidUserThenUnauthenticated - - - - -");
		
		FormLoginRequestBuilder login = formLogin()
			.loginProcessingUrl(URL.LOGIN_WEB)
			.user("invalid")
			.password("invalidpassword");
		
		mockMvc.perform(login)
			.andDo(MockMvcResultHandlers.print())
			.andExpect(unauthenticated()
		);
	}
	
	@Test
	public void restLoginWithValidUserThenAuthenticated() throws Exception {
		System.out.println("- - - - - restLoginWithValidUserThenAuthenticated - - - - -");
		
		MvcResult result = this.mockMvc.perform(formLogin()
			.loginProcessingUrl(URL.LOGIN_REST)
			.user("ta001401")
			.password("password"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andReturn();
		
		int status = result.getResponse().getStatus();
		System.out.println(status);
	}
	
	@Test
	public void restLoginWithInvalidUserThenUnauthenticated() throws Exception {
		System.out.println("- - - - - restLoginWithInvalidUserThenUnauthenticated - - - - -");
		
		MvcResult result = this.mockMvc.perform(formLogin()
			.loginProcessingUrl(URL.LOGIN_REST)
			.user("user")
			.password("worngpass"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isUnauthorized())
		.andReturn();
		
		int status = result.getResponse().getStatus();
		System.out.println(status);
	}
	
}
