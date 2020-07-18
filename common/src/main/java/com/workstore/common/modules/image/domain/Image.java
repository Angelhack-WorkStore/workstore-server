package com.workstore.common.modules.image.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "IMAGES")
@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder @Getter @Setter @EqualsAndHashCode
public class Image {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fileName;
	private String mimeType;
	private String filePath;
	private long size;
	@Enumerated(EnumType.STRING)
	private ImageType imageType;
	private LocalDateTime createdAt;

	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}

	public Image(String fileName, String mimeType, String filePath, long size, ImageType imageType) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.size = size;
		this.mimeType = mimeType;
		this.imageType = imageType;
	}
}
