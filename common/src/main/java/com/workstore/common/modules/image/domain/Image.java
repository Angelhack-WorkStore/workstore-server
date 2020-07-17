package com.workstore.common.modules.image.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Getter @Setter @Builder
@EqualsAndHashCode
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fileName;
	private long size;
	private String mimeType;
	@Enumerated(EnumType.STRING)
	private ImageType imageType;
	private LocalDateTime createAt;
}
