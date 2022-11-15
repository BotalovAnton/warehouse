package ru.mfua.botalov.warehouse.service;

import ru.mfua.botalov.warehouse.model.ProductDto;
import java.util.List;

public interface ProductService {

    String addProduct(ProductDto productDto);

    ProductDto updateProduct(Long id, ProductDto productDto);

    ProductDto getProduct(Long id);

    void deleteProduct(Long id);

    List<ProductDto> getProductsByCategory(String category);

    ProductDto getProductByArticle(String article);
}
