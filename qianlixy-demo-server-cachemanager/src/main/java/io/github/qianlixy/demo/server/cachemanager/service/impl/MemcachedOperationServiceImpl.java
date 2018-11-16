package io.github.qianlixy.demo.server.cachemanager.service.impl;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.qianlixy.demo.server.cachemanager.config.bean.MemcachedPropertiesBean;
import io.github.qianlixy.demo.server.cachemanager.service.CacheOperationService;
import io.github.qianlixy.demo.server.cachemanager.vo.CacheConnection;
import net.rubyeye.xmemcached.CommandFactory;
import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedSessionLocator;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.Transcoder;

@Service
public class MemcachedOperationServiceImpl implements CacheOperationService {
	
	@Autowired
	private MemcachedPropertiesBean memcachedProperties;

	@Override
	public boolean set(CacheConnection cacheConnection, String key, Object value) {
		return this.set(cacheConnection, key, value, 0);
	}

	@Override
	public boolean set(CacheConnection cacheConnection, String key, Object value, int expired) {
		try {
			return buildClient(cacheConnection).set(key, expired, value);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean del(CacheConnection cacheConnection, String key) {
		try {
			return buildClient(cacheConnection).delete(key);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object get(CacheConnection cacheConnection, String key) {
		try {
			return buildClient(cacheConnection).get(key);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public List<String> keys(CacheConnection conn) {
		MemcachedClient client = buildClient(conn);
		List<String> result = new ArrayList<>();
		try {
			KeyIterator keyIterator = client.getKeyIterator((InetSocketAddress) client.getAvailableServers().toArray()[0]);
			while(keyIterator.hasNext()) {
				result.add(keyIterator.next());
			}
		} catch (MemcachedException | InterruptedException | TimeoutException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public MemcachedClient buildClient(CacheConnection conn) {
		List<InetSocketAddress> addressList = new ArrayList<>();
		memcachedProperties.getNodes().forEach((node) -> {
			String[] split = node.split(":");
			addressList.add(new InetSocketAddress(split[0], Integer.parseInt(split[1])));
		});
		int[] weights = new int[memcachedProperties.getWeight().size()];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = memcachedProperties.getWeight().get(i);
		}
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
