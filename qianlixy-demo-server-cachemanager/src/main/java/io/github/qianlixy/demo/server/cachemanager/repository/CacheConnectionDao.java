package io.github.qianlixy.demo.server.cachemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.qianlixy.demo.server.cachemanager.model.CacheConnectionEntity;

public interface CacheConnectionDao extends JpaRepository<CacheConnectionEntity, Long> {

}
