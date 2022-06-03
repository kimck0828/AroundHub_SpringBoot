package com.kimck0828.aroundhub.aroundhub_springboot.data.dao;

import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ProductEntity;

import java.util.Optional;

public interface ProductDAO {
    ProductEntity saveProduct(ProductEntity productEntity);
    ProductEntity getProduct(String productId);
}
