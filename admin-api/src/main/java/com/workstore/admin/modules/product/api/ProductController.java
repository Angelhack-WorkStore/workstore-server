package com.workstore.admin.modules.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.admin.infra.security.CurrentUser;
import com.workstore.admin.modules.product.api.request.ProductPayload;
import com.workstore.admin.modules.product.service.ProductService;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.product.domain.Product;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class ProductController {
	private final ProductService productService;

	@PostMapping
	public ResponseEntity register(@RequestBody ProductPayload payload, @CurrentUser Account account) {
		Product product = productService.register(account.getId(), payload);
		return ResponseEntity.ok(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody ProductPayload payload, @CurrentUser Account account) {
		Product product = productService.update(id, payload);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity findOne(@PathVariable Long id, @CurrentUser Account account) {
		Product product = productService.findOne(id);
		return ResponseEntity.ok(product);
	}
}
