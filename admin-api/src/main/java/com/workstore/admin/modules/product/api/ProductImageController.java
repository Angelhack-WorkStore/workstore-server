package com.workstore.admin.modules.product.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.workstore.admin.infra.security.CurrentUser;
import com.workstore.admin.modules.image.service.FileService;
import com.workstore.admin.modules.product.api.response.UploadResponse;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.image.domain.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductImageController {
	private final FileService fileService;

	@PostMapping(value = "/image")
	public UploadResponse upload(@RequestParam("image") MultipartFile image, @CurrentUser Account account) {
		Image dbImage = fileService.storeFile(image);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path("/api/admin/products/images/")
			.path(dbImage.getFileName())
			.toUriString();

		return new UploadResponse(dbImage.getFileName(), fileDownloadUri,
			image.getContentType(), dbImage.getImageType().name(), image.getSize());
	}

	@PostMapping(value = "/images")
	public List<UploadResponse> multipleUpload(@RequestParam("image") MultipartFile[] images, @CurrentUser Account account) {
		return Arrays.stream(images)
			.map(image -> upload(image, account))
			.collect(Collectors.toList());
	}

	@GetMapping("/images/{imageName}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName, HttpServletRequest request,
		@CurrentUser Account account) {
		Resource resource = fileService.loadFileAsResource(imageName);

		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.info("Could not determine file type.");
		}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
			.body(resource);
	}
}
