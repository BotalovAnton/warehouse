package ru.mfua.botalov.warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mfua.botalov.warehouse.exception.ProductException;
import ru.mfua.botalov.warehouse.model.ProductDto;
import ru.mfua.botalov.warehouse.repository.ProductRepository;
import ru.mfua.botalov.warehouse.service.mapper.ProductMapper;
import ru.mfua.botalov.warehouse.service.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final Utils utils;

    public String addProduct(ProductDto productDto) {
        var savedId = productRepository.save(productMapper.fromDTO(productDto)).getId();
        return String.valueOf(savedId);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        var oldProductEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Продукт не найден"));

        var newProductEntity = productMapper.fromDTO(productDto);

        utils.merge(newProductEntity, oldProductEntity);

        return productMapper.toDTO(productRepository.save(newProductEntity));
    }

    public ProductDto getProduct(Long id) {

        var userEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Продукт не найден"));

        return productMapper.toDTO(userEntity);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> getProductsByCategory(String category) {

        return productRepository.findAllByCategory(category).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDto getProductByArticle(String article) {
        var productEntity = productRepository.findByArticle(article)
                .orElseThrow(() -> new ProductException("Продукт c указанным артикулом не найден"));

        return productMapper.toDTO(productEntity);
    }
}
