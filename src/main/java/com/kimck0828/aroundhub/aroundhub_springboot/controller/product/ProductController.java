package com.kimck0828.aroundhub.aroundhub_springboot.controller.product;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ProductDto;
import com.kimck0828.aroundhub.aroundhub_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getProductPrice(),
                productDto.getProductStock()
        ).toString();
    }
}
