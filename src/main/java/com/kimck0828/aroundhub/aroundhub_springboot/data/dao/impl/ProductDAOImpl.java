package com.kimck0828.aroundhub.aroundhub_springboot.data.dao.impl;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dao.ProductDAO;
import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ProductEntity;
import com.kimck0828.aroundhub.aroundhub_springboot.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDAOImpl implements ProductDAO {

  ProductRepository productRepository;

  @Autowired
  public ProductDAOImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ProductEntity saveProduct(ProductEntity productEntity) {
    return productRepository.save(productEntity);
  }

  @Override
  public ProductEntity getProduct(String productId) {
    return productRepository.findById(productId).orElse(null);
  }
}
