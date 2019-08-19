package org.tinatodo.user;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tinatodo.TinatodoApplication;
import org.tinatodo.user.repository.TodoUser;
import org.tinatodo.user.repository.TodoUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TinatodoApplication.class)
@WebAppConfiguration
public class UserControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@MockBean
	private TodoUserRepository userRepository;
	
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
	
	
	@Test
	public void shouldFailWhenDoesNotExist() throws Exception {
		
		mockMvc
			.perform(get("/api/todo").with(httpBasic("thiago", "123")))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void shouldFailWhenPasswordDoesNotMatch() throws Exception {
		
		TodoUser persistedUser = new TodoUser();
		persistedUser.setName("thiago");
		persistedUser.setEmail("thiago");
		persistedUser.setPassword(encoder.encode("persistedPassword"));
		
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(persistedUser);
		
		mockMvc
			.perform(get("/api/todo").with(httpBasic("thiago", "123")))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void shouldFailWhenUserDoesNotHaveRoles() throws Exception {
		
		TodoUser persistedUser = new TodoUser();
		persistedUser.setName("thiago");
		persistedUser.setEmail("thiago");
		persistedUser.setPassword(encoder.encode("123"));
		
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(persistedUser);
		
		mockMvc
			.perform(get("/api/todo").with(httpBasic("thiago", "123")))
			.andExpect(status().isOk());
	}
	
	@Test
	public void shouldPassWhenPasswordMatchesAndUserHasRoles() throws Exception {
		
		TodoUser persistedUser = new TodoUser();
		persistedUser.setName("thiago");
		persistedUser.setEmail("thiago");
		persistedUser.setPassword(encoder.encode("123"));
		
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(persistedUser);
		
		mockMvc
			.perform(get("/api/todo").with(httpBasic("thiago", "123")))
			.andExpect(status().isOk());
	}
	

}
