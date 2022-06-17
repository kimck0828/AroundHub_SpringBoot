package com.kimck0828.aroundhub.aroundhub_springboot.service;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(String productId, String ProductName, int ProductPrice, int ProductStock);

    ProductDto getProduct(String productId);
}
