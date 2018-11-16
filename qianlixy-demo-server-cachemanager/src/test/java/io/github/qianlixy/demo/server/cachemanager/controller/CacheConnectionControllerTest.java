package io.github.qianlixy.demo.server.cachemanager.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alibaba.fastjson.JSON;

import io.github.qianlixy.demo.server.cachemanager.BaseSpringBootJunitTest;
import io.github.qianlixy.demo.server.cachemanager.enums.CacheTypeEnum;
import io.github.qianlixy.demo.server.cachemanager.vo.CacheConnection;

public class CacheConnectionControllerTest extends BaseSpringBootJunitTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testAdd() throws Exception {

		CacheConnection cacheConnection = new CacheConnection();
		cacheConnection.setCacheType(CacheTypeEnum.MEMCACHED);
		cacheConnection.setClusterIdentity("1");
		cacheConnection.setHost("localhost");
		cacheConnection.setPort(11211);

		this.mvc.perform(MockMvcRequestBuilders.post("/connection").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(JSON.toJSONString(cacheConnection))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testGet() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/connection/11")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPage() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/connection/page"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testUpdate() throws Exception {

		CacheConnection cacheConnection = new CacheConnection();
		cacheConnection.setCacheType(CacheTypeEnum.REDIS);
		cacheConnection.setClusterIdentity("2");
		cacheConnection.setHost("127.0.0.1");
		cacheConnection.setPort(6379);

		this.mvc.perform(MockMvcRequestBuilders.put("/connection/11").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(JSON.toJSONString(cacheConnection))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print());
	}

}
