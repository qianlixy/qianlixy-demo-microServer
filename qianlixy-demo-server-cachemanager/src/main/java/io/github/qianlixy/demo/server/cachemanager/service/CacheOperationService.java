package io.github.qianlixy.demo.server.cachemanager.service;

import java.util.List;

import io.github.qianlixy.demo.server.cachemanager.vo.CacheConnection;

public interface CacheOperationService {

	boolean set(CacheConnection cacheConnection, String key, Object value);
	
	boolean set(CacheConnection cacheConnection, String key, Object value, int expired);
	
	boolean del(CacheConnection cacheConnection, String key);
	
	Object get(CacheConnection cacheConnection, String key);

	List<String> keys(CacheConnection conn);
}
