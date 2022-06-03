package com.kimck0828.aroundhub.aroundhub_springboot.controller.product;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ProductDto;
import com.kimck0828.aroundhub.aroundhub_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/{productId}")
    public String getProduct(@PathVariable String productId) {
        ProductDto productDto = productService.getProduct(productId);
        return productDto.toString();
    }
    @PostMapping("/")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto response = productService.saveProduct(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getProductPrice(),
                productDto.getProductStock()
        );
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
