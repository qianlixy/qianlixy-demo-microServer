package io.github.qianlixy.demo.server.cachemanager.enums;

public enum CacheTypeEnum {

	REDIS("redis"), MEMCACHED("memcached");

	private String name;

	private CacheTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
