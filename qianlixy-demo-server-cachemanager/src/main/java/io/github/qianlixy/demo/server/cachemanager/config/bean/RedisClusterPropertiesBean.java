package io.github.qianlixy.demo.server.cachemanager.config.bean;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Primary
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisClusterPropertiesBean extends RedisProperties {

	/**
	 * 连接超时时间
	 */
	private int connectionTimeout = 1000;

	/**
	 * 读取数据时间
	 */
	private int soTimeout = 1000;

	/**
	 * 重试次数
	 */
	private int maxAttempts = 3;

	/**
	 * 获取connectionTimeout
	 *
	 * @return connectionTimeout
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * 设置connectionTimeout
	 *
	 * @param connectionTimeout
	 *            connectionTimeout
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * 获取soTimeout
	 *
	 * @return soTimeout
	 */
	public int getSoTimeout() {
		return soTimeout;
	}

	/**
	 * 设置soTimeout
	 *
	 * @param soTimeout
	 *            soTimeout
	 */
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	/**
	 * 获取maxAttempts
	 *
	 * @return maxAttempts
	 */
	public int getMaxAttempts() {
		return maxAttempts;
	}

	/**
	 * 设置maxAttempts
	 *
	 * @param maxAttempts
	 *            maxAttempts
	 */
	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}
}
