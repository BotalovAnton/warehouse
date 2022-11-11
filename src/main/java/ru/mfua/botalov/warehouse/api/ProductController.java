package ru.mfua.botalov.warehouse.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mfua.botalov.warehouse.model.ProductDto;
import ru.mfua.botalov.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestParam(name = "id") Long id, ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @GetMapping
    public ResponseEntity<ProductDto> getProduct(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam(name = "id") long id) {
        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable(name = "category") String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/article/{article}")
    public ResponseEntity<ProductDto> getProductByArticle(@PathVariable(name = "article") String article) {
        return ResponseEntity.ok(productService.getProductByArticle(article));
    }

}
