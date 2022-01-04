package com.github.uyt.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.ProductRepository;
import com.github.uyt.model.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> fetchALLProducts() {
        return productRepository.getAllProducts();
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
