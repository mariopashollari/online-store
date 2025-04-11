package com.individual.online_store.services;


import com.individual.online_store.dtos.ProductDto;
import com.individual.online_store.entities.Product;
import com.individual.online_store.repositories.AuthorRepository;
import com.individual.online_store.repositories.CategoryRepository;
import com.individual.online_store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorService authorService;


    public ProductDto create(ProductDto productDto) {
        if (productDto.getProductId() != null) {
            return null;
        } else {
            Product product = ProductDto.toEntity(productDto);
            product.setCategory(categoryRepository.findByName(productDto.getCategory()).orElseThrow());
            product.setAuthor(authorService.findById(productDto.getAuthorId()));
            product.setActive(true);
            product = productRepository.save(product);
            return ProductDto.toDto(product);
        }
    }

    public ProductDto update(ProductDto productDto) {
        if (productDto.getProductId() != null) {
            Product product = productRepository.findById(productDto.getProductId())
                    .orElseThrow();
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setCategory(categoryRepository.findByName(productDto.getCategory()).orElseThrow());
            product.setImageUrl(productDto.getImageUrl());
            return ProductDto.toDto(product);
        } else {
            return null;
        }
    }

    public List<ProductDto> findByTitle(String title) {
        List<Product> products = productRepository.findByTitle(title);
        return products.stream().map(ProductDto::toDto).toList();
    }

    public ProductDto getById(Long id) {
        Product product = this.findById(id);
        return ProductDto.toDto(product);
    }

    private Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    public List<ProductDto> findByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream().map(ProductDto::toDto).toList();
    }

    public List<ProductDto> findByAuthor(Long authorId) {
        List<Product> products = productRepository.findByAuthor(authorId);
        return products.stream().map(ProductDto::toDto).toList();
    }

    public void deactivateProduct(Long productId) {
        Product product = findById(productId);
        product.setActive(false);
        productRepository.save(product);
    }

    public List<ProductDto> findAllActive() {
        List<Product> products = productRepository.findAllByActiveIsTrue();
        return products.stream().map(ProductDto::toDto).toList();
    }
}
