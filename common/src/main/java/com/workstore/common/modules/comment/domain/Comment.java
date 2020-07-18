package com.workstore.common.modules.comment.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Table(name = "COMMENTS")
@Entity @NoArgsConstructor
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;			// 구매한 상품 ID
	private Long reservationId;		// 예약 ID
	private String content;			// 한줄평
	private double score;			// 평점
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	@PrePersist
	public void setCreateAt() {
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void setModifiedAt() {
		this.modifiedAt = LocalDateTime.now();
	}
}

