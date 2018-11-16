package io.github.qianlixy.demo.server.cachemanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import io.github.qianlixy.demo.server.cachemanager.enums.CacheTypeEnum;

@Entity
@Table(name = "t_cache_connection")
public class CacheConnectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Version
	private Long version;

	@Enumerated(EnumType.STRING)
	@Column(name = "cache_type", length = 50, nullable = false)
	private CacheTypeEnum cacheType;

	@Column(name = "cluster_identity", length = 128, nullable = false)
	private String clusterIdentity;

	@Column(name = "host", length = 20, nullable = false)
	private String host;

	@Column(nullable = false)
	private int port;

	@Column(length = 128)
	private String password;

	@Column(name = "created_time")
	private Date createdTime;

	@Column(name = "modified_time")
	private Date modifiedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public CacheTypeEnum getCacheType() {
		return cacheType;
	}

	public void setCacheType(CacheTypeEnum cacheType) {
		this.cacheType = cacheType;
	}

	public String getClusterIdentity() {
		return clusterIdentity;
	}

	public void setClusterIdentity(String clusterIdentity) {
		this.clusterIdentity = clusterIdentity;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
