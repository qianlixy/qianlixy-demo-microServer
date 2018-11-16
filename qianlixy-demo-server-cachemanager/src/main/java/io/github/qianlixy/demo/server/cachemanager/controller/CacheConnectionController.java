package io.github.qianlixy.demo.server.cachemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.qianlixy.demo.server.cachemanager.service.CacheConnectionService;
import io.github.qianlixy.demo.server.cachemanager.vo.CacheConnection;
import io.github.qianlixy.demo.server.cachemanager.vo.Result;

@RestController
@RequestMapping("/connection")
public class CacheConnectionController {

	@Autowired
	private CacheConnectionService connectionService;

	@PostMapping("")
	public ResponseEntity<Result> add(@RequestBody CacheConnection cacheConnection) {
		Long id = connectionService.save(cacheConnection);
		return ResponseEntity.ok(Result.success().addData("id", id));
	}

	@GetMapping("/{id:\\d+}")
	public ResponseEntity<CacheConnection> get(@PathVariable Long id) {
		return ResponseEntity.ok(connectionService.get(id));
	}

	@GetMapping("/page")
	public ResponseEntity<Page<CacheConnection>> page(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size) {
		return ResponseEntity.ok(connectionService.page(page, size));
	}
	
	@PutMapping("/{id:\\d+}")
	public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody CacheConnection cacheConnection) {
		cacheConnection.setId(id);
		connectionService.update(cacheConnection);
		return ResponseEntity.ok(Result.success());
	}
}
