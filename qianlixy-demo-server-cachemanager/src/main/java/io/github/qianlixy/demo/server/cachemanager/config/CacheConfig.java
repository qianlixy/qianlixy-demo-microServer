package io.github.qianlixy.demo.server.cachemanager.config;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.qianlixy.demo.server.cachemanager.config.bean.MemcachedPropertiesBean;
import io.github.qianlixy.demo.server.cachemanager.config.bean.RedisClusterPropertiesBean;
import net.rubyeye.xmemcached.CommandFactory;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedSessionLocator;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.transcoders.Transcoder;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class CacheConfig {

	/**
	 * Memcached配置参数
	 */
	@Autowired
	private MemcachedPropertiesBean memcachedProperties;

	/**
	 * Redis集群配置参数
	 */
	@Autowired
	private RedisClusterPropertiesBean redisClusterProperties;

	/**
	 * memcached客户端工具
	 *
	 * @return memcached客户端工具
	 * @throws Exception
	 *             构建器构建失败时抛出该异常
	 */
	@SuppressWarnings("rawtypes")
	@Bean(destroyMethod = "shutdown")
	public MemcachedClient memcachedClient() throws Exception {
		List<InetSocketAddress> addressList = new ArrayList<>();
		memcachedProperties.getNodes().forEach((node) -> {
			String[] split = node.split(":");
			addressList.add(new InetSocketAddress(split[0], Integer.parseInt(split[1])));
		});
		int[] weights = new int[memcachedProperties.getWeight().size()];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = memcachedProperties.getWeight().get(i);
		}
		XMemcachedClientBuilder clientBuilder = new XMemcachedClientBuilder(addressList, weights);
		clientBuilder.setOpTimeout(memcachedProperties.getOpTimeout());
		clientBuilder.setConnectTimeout(memcachedProperties.getConnectTimeout());
		clientBuilder.setConnectionPoolSize(memcachedProperties.getConnectionPoolSize());
		clientBuilder.setCommandFactory(
				(CommandFactory) Class.forName(memcachedProperties.getCommandFactory()).newInstance());
		clientBuilder.setSessionLocator(
				(MemcachedSessionLocator) Class.forName(memcachedProperties.getSessionLocator()).newInstance());
		clientBuilder.setTranscoder((Transcoder) Class.forName(memcachedProperties.getTranscoder()).newInstance());
		clientBuilder.getTranscoder().setCompressionThreshold(memcachedProperties.getCompressionThreshold());
		return clientBuilder.build();
	}

	/**
	 * redis集群配置
	 * 
	 * @return redis集群访问对象
	 */
//	@Bean
	public JedisCluster jedisCluster() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(redisClusterProperties.getJedis().getPool().getMaxActive());
		config.setMaxIdle(redisClusterProperties.getJedis().getPool().getMaxIdle());
		config.setMaxWaitMillis(redisClusterProperties.getJedis().getPool().getMaxWait().getSeconds());
		Set<HostAndPort> nodeSet = new HashSet<>();
		for (String node : redisClusterProperties.getCluster().getNodes()) {
			String[] address = node.split(":");
			nodeSet.add(new HostAndPort(address[0], Integer.parseInt(address[1])));
		}
		JedisCluster jedisCluster = new JedisCluster(nodeSet, redisClusterProperties.getConnectionTimeout(),
				redisClusterProperties.getSoTimeout(), redisClusterProperties.getMaxAttempts(),
				redisClusterProperties.getPassword(), config);
		return jedisCluster;
	}

}
