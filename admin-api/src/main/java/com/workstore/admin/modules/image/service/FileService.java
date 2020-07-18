package com.workstore.admin.modules.image.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.workstore.admin.infra.config.AdminAppProperties;
import com.workstore.common.modules.image.domain.Image;
import com.workstore.common.modules.image.domain.ImageRepository;
import com.workstore.common.modules.image.domain.ImageType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {
	private final Path fileLocation;
	private final ImageRepository imageRepository;

	@Autowired
	public FileService(AdminAppProperties prop, ImageRepository imageRepository) {
		this.fileLocation = Paths.get(prop.getFile().getUploadDir())
			.toAbsolutePath().normalize();
		this.imageRepository = imageRepository;

		try {
			Files.createDirectories(this.fileLocation);
		}catch(Exception e) {
			throw new IllegalStateException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
		}
	}

	public Image storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if(fileName.contains(".."))
				throw new IllegalStateException("Sorry! Filename contains invalid path sequence " + fileName);

			Path targetLocation = this.fileLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			Image image = new Image(fileName, file.getContentType(), file.getSize(), ImageType.THUMBNAIL);
			return imageRepository.save(image);
		} catch (IOException ex) {
			throw new IllegalStateException("Could not store file " + fileName + ". Please try again!", ex);
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
