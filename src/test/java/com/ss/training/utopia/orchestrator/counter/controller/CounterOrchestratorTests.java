package com.ss.training.utopia.orchestrator.counter.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.training.utopia.orchestrator.controller.BeanConfig;

// /**
//  * @author Justin O'Brien
//  */
// //@RunWith(SpringRunner.class)
// @WebMvcTest(CounterOrchestrator.class)
// @AutoConfigureMockMvc(addFilters = false)
// @ContextConfiguration(classes = { BeanConfig.class })
// public class CounterOrchestratorTests {

// 	@Autowired
// 	private MockMvc mvc;
// 	@Autowired
// 	private ObjectMapper mapper;
// 	@Autowired
// 	private RestTemplate template;

// 	private final String baseUrl = "http://localhost:8081";

// 	private MockRestServiceServer server;

// 	@BeforeEach
// 	public void before() {
// 		server = MockRestServiceServer.createServer(template);
// 	}

//	@Test
//	public void userIsTravelerTest() throws Exception {
//		String uri = "/counter/traveler/username", url = baseUrl + uri;
//		server.expect(requestTo(new URI(url))).andExpect(method(HttpMethod.HEAD))
//				.andRespond(withStatus(HttpStatus.NO_CONTENT));
//		mvc.perform(head(uri)).andExpect(status().isNoContent()).andExpect(content().string(""));
//	}

//}
