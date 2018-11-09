package io.github.qianlixy.demo.microservice.fileupload.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.qianlixy.demo.microservice.fileupload.service.StorageService;
import io.github.qianlixy.demo.microservice.fileupload.vo.UploadResult;

@RestController
@RequestMapping("/files")
public class FileUploadController {
	
	@Autowired
	private StorageService storageService;
	
	@PostMapping("")
	public ResponseEntity<UploadResult> upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
		UploadResult result = null;
		try {
			result = storageService.store(file);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UploadResult(false, "上传失败，请稍后再试"));
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{filepath:\\d+}/{filename:.+}")
	public ResponseEntity<Resource> filename(@PathVariable String filepath, @PathVariable String filename) {
		Resource file = storageService.loadAsResource("/" + filepath + "/" +filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
}
