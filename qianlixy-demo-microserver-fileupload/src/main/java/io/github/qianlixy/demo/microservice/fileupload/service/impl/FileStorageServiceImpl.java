package io.github.qianlixy.demo.microservice.fileupload.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.qianlixy.demo.microservice.fileupload.config.bean.FileUploadProperties;
import io.github.qianlixy.demo.microservice.fileupload.service.StorageService;
import io.github.qianlixy.demo.microservice.fileupload.vo.UploadResult;

@Service
public class FileStorageServiceImpl implements StorageService {

	@Autowired
	private FileUploadProperties fileUploadProperties;

	@Override
	public Resource loadAsResource(String filename) {
		return new FileSystemResource(new File(fileUploadProperties.getRootPath() + fileUploadProperties.getFileSavePath() + filename));
	}

	@Override
	public UploadResult store(MultipartFile file) throws IOException {
		String path = "/" + new Date().getTime() + "/" + UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
		File saveFile = new File(fileUploadProperties.getRootPath() + fileUploadProperties.getFileSavePath() + path);
		File parentFile = saveFile.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		
		saveFile.deleteOnExit();
		saveFile.createNewFile();
		
		FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
		
		UploadResult uploadResult = new UploadResult(true);
		uploadResult.setFileUrl(fileUploadProperties.getDomain() + fileUploadProperties.getFileSavePath() + path);
		return uploadResult;
	}

}
