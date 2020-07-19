package com.workstore.user.modules.image.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.workstore.user.infra.config.UserAppProperties;

@Component
public class FileService {
	private final Path fileLocation;

	@Autowired
	public FileService(UserAppProperties prop) {
		this.fileLocation = Paths.get(prop.getFile().getUploadDir())
			.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileLocation);
		}catch(Exception e) {
			throw new IllegalStateException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if(resource.exists()) {
				return resource;
			}else {
				throw new IllegalStateException(fileName + " 파일을 찾을 수 없습니다.");
			}
		}catch(MalformedURLException e) {
			throw new IllegalStateException(fileName + " 파일을 찾을 수 없습니다.", e);
		}
	}
}
