package io.github.qianlixy.demo.server.cachemanager.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.github.qianlixy.demo.server.cachemanager.BaseSpringBootJunitTest;

public class CacheNodeControllerTest extends BaseSpringBootJunitTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testKeys() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/node/10/keys")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGet() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/node/10/aaa")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testSet() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/node/10/aaa").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"value\":\"aaa\"}")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

}
