package com.lumeris.example.customerservice;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class NameSortTest {
	
	private MockMvc mockMvc;

	@Autowired
	private CustomerController customerController;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void allNamesSortTest() throws Exception {
		ResultActions results = mockMvc.perform(get("/customers?sorted=true"));
		
		results.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].lastName", containsString("Bear")))
			.andExpect(jsonPath("$[1].lastName", containsString("Cloudkicker")))
			.andExpect(jsonPath("$[2].lastName", containsString("Cunningham"))).andExpect(jsonPath("$[2].firstName", containsString("Molly")))
			.andExpect(jsonPath("$[3].lastName", containsString("Cunningham"))).andExpect(jsonPath("$[3].firstName", containsString("Rebecca")))
			.andExpect(jsonPath("$[4].lastName", containsString("Khan")));
	}

	@Test
	public void singleNameSortTest() throws Exception {
		ResultActions results = mockMvc.perform(get("/customers/Cunningham?sorted=true"));
		
		results.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].firstName", containsString("Molly")))
			.andExpect(jsonPath("$[1].firstName", containsString("Rebecca")));
	}

}
