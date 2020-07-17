package com.workstore.common.modules.account.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts", uniqueConstraints = {
	@UniqueConstraint(columnNames = "email")
})
@Getter @Setter@Builder
@DynamicUpdate @DynamicInsert
@NoArgsConstructor @AllArgsConstructor
public class Account {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	private String email;
	private String password;
	private String imageUrl;
	private boolean emailVerified = false;
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;
	private String providerId;
	private String emailCheckToken;
	private LocalDateTime emailCheckTokenGeneratedAt;
	private LocalDateTime joinedAt;
	@Enumerated(EnumType.STRING)
	private Role role;

	public void generateEmailCheckToken() {
		this.emailCheckToken = UUID.randomUUID().toString();
		this.emailCheckTokenGeneratedAt = LocalDateTime.now();
	}

	public void completeSignUp() {
		this.emailVerified = true;
		this.joinedAt = LocalDateTime.now();
	}

	public boolean isValidToken(String token) {
		return this.emailCheckToken.equals(token);
	}
}
