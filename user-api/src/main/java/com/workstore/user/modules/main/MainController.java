package com.workstore.user.modules.main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.common.modules.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
	private final ProductRepository productRepository;

	@GetMapping
	public ResponseEntity findAll() {
		return ResponseEntity.ok(productRepository.findAll());
	}
}
