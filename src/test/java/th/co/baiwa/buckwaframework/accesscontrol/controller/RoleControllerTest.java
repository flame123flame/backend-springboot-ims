package th.co.baiwa.buckwaframework.accesscontrol.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = PROFILE.UNITTEST)
public class RoleControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void test_getAll() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/api/accesscontrol/role")
				.param("draw", "1")
				.param("start", "1")
				.param("length", "1")
				.accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			).andExpect(status().isOk())
				.andExpect(jsonPath("$.draw").value("1")
		).andReturn();
		
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
	}
	
}
