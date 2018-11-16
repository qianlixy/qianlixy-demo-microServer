package io.github.qianlixy.demo.server.cachemanager.service;

import org.springframework.data.domain.Page;

import io.github.qianlixy.demo.server.cachemanager.vo.CacheConnection;

public interface CacheConnectionService {

	Long save(CacheConnection cacheConnection);

	CacheConnection get(Long id);

	Page<CacheConnection> page(Integer page, Integer size);

	void update(CacheConnection cacheConnection);
}
