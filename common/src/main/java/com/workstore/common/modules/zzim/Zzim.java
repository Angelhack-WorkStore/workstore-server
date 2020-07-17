package com.workstore.common.modules.zzim;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Table(name = "ZZIM")
@Entity @NoArgsConstructor
public class Zzim {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long accountId;
	private Long productId;
}

