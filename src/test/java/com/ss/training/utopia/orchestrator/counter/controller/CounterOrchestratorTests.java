package com.ss.training.utopia.orchestrator.counter.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.ss.training.utopia.orchestrator.controller.BeanConfig;

/**
 * @author Justin O'Brien
 */
@WebMvcTest(CounterOrchestrator.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = { BeanConfig.class })
public class CounterOrchestratorTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void verySimpleTest() throws Exception {
		mvc.perform(get("/counter/test")).andExpect(status().isOk());
	}
	
}
