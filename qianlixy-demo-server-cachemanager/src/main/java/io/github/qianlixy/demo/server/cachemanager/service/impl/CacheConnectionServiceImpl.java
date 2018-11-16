package io.github.qianlixy.demo.server.cachemanager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.qianlixy.demo.server.cachemanager.model.CacheConnectionEntity;
import io.github.qianlixy.demo.server.cachemanager.repository.CacheConnectionDao;
import io.github.qianlixy.demo.server.cachemanager.service.CacheConnectionService;
import io.github.qianlixy.demo.server.cachemanager.vo.CacheConnection;

@Service
public class CacheConnectionServiceImpl implements CacheConnectionService {

	@Autowired
	private CacheConnectionDao cacheConnectionDao;

	@Override
	public Long save(CacheConnection cacheConnection) {
		CacheConnectionEntity connectionEntity = new CacheConnectionEntity();
		BeanUtils.copyProperties(cacheConnection, connectionEntity);
		connectionEntity.setCreatedTime(new Date());
		connectionEntity.setModifiedTime(new Date());
		cacheConnectionDao.save(connectionEntity);
		return connectionEntity.getId();
	}

	@Override
	public CacheConnection get(Long id) {
		CacheConnectionEntity one = cacheConnectionDao.getOne(id);
		CacheConnection conn = new CacheConnection();
		BeanUtils.copyProperties(one, conn);
		return conn;
	}

	@Override
	public Page<CacheConnection> page(Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<CacheConnectionEntity> connPage = cacheConnectionDao.findAll(pageRequest);

		List<CacheConnection> content = new ArrayList<>();
		connPage.getContent().forEach((item) -> {
			CacheConnection conn = new CacheConnection();
			BeanUtils.copyProperties(item, conn);
			content.add(conn);
		});

		return new PageImpl<>(content, pageRequest, connPage.getTotalElements());
	}

	@Override
	public void update(CacheConnection cacheConnection) {
		CacheConnectionEntity entity = cacheConnectionDao.getOne(cacheConnection.getId());
		BeanUtils.copyProperties(cacheConnection, entity, "id", "version", "createdTime");
		entity.setModifiedTime(new Date());
		cacheConnectionDao.save(entity);
	}

}
