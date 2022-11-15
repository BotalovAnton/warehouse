package ru.mfua.botalov.warehouse.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mfua.botalov.warehouse.model.ProductDto;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void addProductShouldReturnNewId() {
        var productDto = ProductDto.builder()
                .category("Пиломатериалы")
                .article("1qwerty")
                .count(67L)
                .storagePlace("r23p24")
                .name("Брус 25 мм")
                .build();

        var result = productService.addProduct(productDto);

        Assertions.assertEquals("2", result);
    }

    @Test
    void updateProductShouldReturnUpdatedProduct() {
        var productDto = ProductDto.builder()
                .id(1L)
                .category("Лес")
                .article("qwerty")
                .count(67L)
                .storagePlace("r3m25")
                .name("брус 25мм")
                .build();

        var result = productService.updateProduct(1l, productDto);
        Assertions.assertEquals("Лес", result.getCategory());
    }
}
