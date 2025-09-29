package com.littlelotus.controller.rest;

import com.littlelotus.model.Product;
import com.littlelotus.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product API", description = "RESTful API cho quản lý Sản phẩm")
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @Operation(summary = "Lấy danh sách sản phẩm (có phân trang/tìm kiếm)", description = "Trả về đối tượng Page<Product>.")
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword) {
        
        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);
        return ResponseEntity.ok(page);
    }


    @Operation(summary = "Lấy sản phẩm theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.getProductById(id);
        
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build(); 
    }

 
    @Operation(summary = "Tạo mới sản phẩm")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setId(null); 
        productService.saveProduct(product); 
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Operation(summary = "Cập nhật sản phẩm")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        Optional<Product> productOptional = productService.getProductById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            
            product.setCategory(productDetails.getCategory());
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            product.setImageUrl(productDetails.getImageUrl());
            
            productService.saveProduct(product); 
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Xóa sản phẩm")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        Optional<Product> productOptional = productService.getProductById(id);
        
        if (productOptional.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.notFound().build();
    }
}