package com.lumeris.example.customerservice;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lumeris.example.customerservice.controller.CustomerController;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=Application.class)
public class IgnoreCaseTest {

	private MockMvc mockMvc;

	@Autowired
	private CustomerController customerController;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void customerCountTest() throws Exception {
		ResultActions results = mockMvc.perform(get("/customers/count"));
		
		results.andExpect(status().isOk())
			.andExpect(content().string("5"));
	}

	@Test
	public void ignoreCaseTest() throws Exception {
		ResultActions results = mockMvc.perform(get("/customers/{lastName}?ignoreCase=true", "bear"));
		
		results.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].firstName", containsString("Baloo")))
			.andExpect(jsonPath("$[0].lastName", containsString("Bear")));
	}
}
