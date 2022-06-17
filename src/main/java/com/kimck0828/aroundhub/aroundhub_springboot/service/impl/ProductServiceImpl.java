package com.kimck0828.aroundhub.aroundhub_springboot.service.impl;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dao.ProductDAO;
import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ProductDto;
import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ProductEntity;
import com.kimck0828.aroundhub.aroundhub_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  ProductDAO productDAO;

  @Autowired
  public ProductServiceImpl(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @Override
  public ProductDto saveProduct(String productId, String productName, int productPrice,
      int productStock) {
    ProductEntity entity = productDAO.saveProduct(
        ProductEntity.builder()
            .productId(productId)
            .productName(productName)
            .productPrice(productPrice)
            .productStock(productStock).build()
    );
    return new ProductDto(entity);
  }

  @Override
  public ProductDto getProduct(String productId) {
    ProductEntity entity = productDAO.getProduct(productId);
    return new ProductDto(entity);
  }
}
