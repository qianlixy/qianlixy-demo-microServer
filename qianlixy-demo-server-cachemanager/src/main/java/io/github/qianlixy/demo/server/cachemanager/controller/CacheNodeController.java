package io.github.qianlixy.demo.server.cachemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.qianlixy.demo.server.cachemanager.service.CacheConnectionService;
import io.github.qianlixy.demo.server.cachemanager.service.CacheOperationService;
import io.github.qianlixy.demo.server.cachemanager.vo.Result;

@RestController
@RequestMapping("/node")
public class CacheNodeController {
	
	@Autowired
	private CacheConnectionService connectionService;
	
	@Autowired
	private CacheOperationService memcachedOperationService;
	
	@GetMapping("/{id:\\d+}/{key:\\w+}")
	public ResponseEntity<Object> get(@PathVariable Long id, @PathVariable String key) {
		return ResponseEntity.ok(memcachedOperationService.get(connectionService.get(id), key));
	}
	
	@GetMapping("/{id:\\d+}/keys")
	public ResponseEntity<List<String>> keys(@PathVariable Long id) {
		return ResponseEntity.ok(memcachedOperationService.keys(connectionService.get(id)));
	}
	
	@PostMapping("/{id:\\d+}/{key:\\w+}")
	public ResponseEntity<Result> set(@PathVariable Long id, @PathVariable String key, @RequestBody String value) {
		memcachedOperationService.set(connectionService.get(id), key, value);
		return ResponseEntity.ok(Result.success());
	}
}
