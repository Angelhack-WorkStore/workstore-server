package com.workstore.admin.modules.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workstore.admin.modules.product.api.request.ProductPayload;
import com.workstore.admin.modules.product.service.factory.ProductMaker;
import com.workstore.common.modules.product.domain.Product;
import com.workstore.common.modules.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductMaker productMaker;

	public Product register(Long ownerId, ProductPayload payload) {
		Product product = productMaker.make(ownerId, payload);
		return productRepository.save(product);
	}

	public Product update(Long productId, ProductPayload payload) {
		Product dbProduct = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
		Product request = productMaker.make(payload);
		dbProduct.update(request);
		return dbProduct;
	}

	@Transactional(readOnly = true)
	public Product findOne(Long productId) {
		return productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
	}
}
