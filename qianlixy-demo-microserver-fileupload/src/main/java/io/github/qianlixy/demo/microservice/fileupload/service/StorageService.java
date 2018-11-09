package io.github.qianlixy.demo.microservice.fileupload.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import io.github.qianlixy.demo.microservice.fileupload.vo.UploadResult;

public interface StorageService {

	Resource loadAsResource(String filename);

	UploadResult store(MultipartFile file) throws IOException;

}
