package io.github.qianlixy.demo.server.cachemanager.config.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.memcached")
public class MemcachedPropertiesBean {

	/**
	 * 节点IP地址和端口信息
	 */
	private List<String> nodes = new ArrayList<>();

	/**
	 * 每个节点的权重
	 */
	private List<Integer> weight = new ArrayList<>();

	/**
	 * 操作缓存超时时长
	 */
	private long opTimeout = 5000L;

	/**
	 * 连接超时时长
	 */
	private long connectTimeout = 5000L;

	/**
	 * 连接池大小
	 */
	private int connectionPoolSize = 1;

	/**
	 * 通信协议
	 */
	private String commandFactory;

	/**
	 * 缓存分布算法
	 */
	private String sessionLocator;

	/**
	 * 序列化
	 */
	private String transcoder;

	/**
	 * 压缩阈值，默认16KB
	 */
	private int compressionThreshold = 16384;

	/**
	 * 获取nodes
	 *
	 * @return nodes
	 */
	public List<String> getNodes() {
		return nodes;
	}

	/**
	 * 设置nodes
	 *
	 * @param nodes
	 *            nodes
	 */
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}

	/**
	 * 获取weight
	 *
	 * @return weight
	 */
	public List<Integer> getWeight() {
		return weight;
	}

	/**
	 * 设置weight
	 *
	 * @param weight
	 *            weight
	 */
	public void setWeight(List<Integer> weight) {
		this.weight = weight;
	}

	/**
	 * 获取opTimeout
	 *
	 * @return opTimeout
	 */
	public long getOpTimeout() {
		return opTimeout;
	}

	/**
	 * 设置opTimeout
	 *
	 * @param opTimeout
	 *            opTimeout
	 */
	public void setOpTimeout(long opTimeout) {
		this.opTimeout = opTimeout;
	}

	/**
	 * 获取connectTimeout
	 *
	 * @return connectTimeout
	 */
	public long getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * 设置connectTimeout
	 *
	 * @param connectTimeout
	 *            connectTimeout
	 */
	public void setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * 获取connectionPoolSize
	 *
	 * @return connectionPoolSize
	 */
	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

	/**
	 * 设置connectionPoolSize
	 *
	 * @param connectionPoolSize
	 *            connectionPoolSize
	 */
	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	/**
	 * 获取commandFactory
	 *
	 * @return commandFactory
	 */
	public String getCommandFactory() {
		return commandFactory;
	}

	/**
	 * 设置commandFactory
	 *
	 * @param commandFactory
	 *            commandFactory
	 */
	public void setCommandFactory(String commandFactory) {
		this.commandFactory = commandFactory;
	}

	/**
	 * 获取sessionLocator
	 *
	 * @return sessionLocator
	 */
	public String getSessionLocator() {
		return sessionLocator;
	}

	/**
	 * 设置sessionLocator
	 *
	 * @param sessionLocator
	 *            sessionLocator
	 */
	public void setSessionLocator(String sessionLocator) {
		this.sessionLocator = sessionLocator;
	}

	/**
	 * 获取transcoder
	 *
	 * @return transcoder
	 */
	public String getTranscoder() {
		return transcoder;
	}

	/**
	 * 设置transcoder
	 *
	 * @param transcoder
	 *            transcoder
	 */
	public void setTranscoder(String transcoder) {
		this.transcoder = transcoder;
	}

	/**
	 * 获取compressionThreshold
	 *
	 * @return compressionThreshold
	 */
	public int getCompressionThreshold() {
		return compressionThreshold;
	}

	/**
	 * 设置compressionThreshold
	 *
	 * @param compressionThreshold
	 *            compressionThreshold
	 */
	public void setCompressionThreshold(int compressionThreshold) {
		this.compressionThreshold = compressionThreshold;
	}
}
