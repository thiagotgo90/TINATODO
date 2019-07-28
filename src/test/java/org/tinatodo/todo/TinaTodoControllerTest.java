package org.tinatodo.todo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tinatodo.TinatodoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TinatodoApplication.class)
@WebAppConfiguration
public class TinaTodoControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		
		mockMvc = MockMvcBuilders
					.webAppContextSetup(context)
					.addFilters(springSecurityFilterChain)
					.build();
	}
	
	@Test
	public void shouldFailWhenUserIsNotAuthenticated() throws Exception {
		
		mockMvc
			.perform(get("/api/todo"))
			.andExpect(status().isUnauthorized());
	}
	
}
