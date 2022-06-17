package com.kimck0828.aroundhub.aroundhub_springboot.service.impl;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dao.ProductDAO;
import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ProductDto;
import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
// テストを行う時に必要なクラス（テストクラス、そのクラスでAutowiredされるメソッドの中でMOCKになる変数）
@Import({ProductServiceImpl.class, ProductDAO.class})
public class PriductServiceImplTest {
    
    @MockBean
    ProductDAO productDAO;
    
    @Autowired
    ProductServiceImpl productService;
    
    @Test
    @DisplayName("getProduct(String productId)メソッド")
    public void getProductTest() {

        final ProductEntity ENTITY = ProductEntity.builder()
                .productId("12345")
                .productName("prdName")
                .productPrice(10000)
                .productStock(12)
                .build();
        given(productDAO.getProduct(ENTITY.getProductId()))
                .willReturn(ENTITY);

        // 実行
        ProductDto rtnProductDto = productService.getProduct(ENTITY.getProductId());
        
        // 確認
        assertEquals(ENTITY.getProductId(), rtnProductDto.getProductId());
        assertEquals(ENTITY.getProductName(), rtnProductDto.getProductName());
        assertEquals(ENTITY.getProductPrice(), rtnProductDto.getProductPrice());
        assertEquals(ENTITY.getProductStock(), rtnProductDto.getProductStock());
        
        verify(productDAO, times(1)).getProduct(ENTITY.getProductId());
        
    }

    @Test
    @DisplayName("saveProduct(String productId, String productName, int productPrice, int productStock) テスト")
    public void saveProduct() {
        final ProductEntity ENTITY = ProductEntity.builder()
                .productId("12345")
                .productName("prdName")
                .productPrice(10000)
                .productStock(12)
                .build();
        given(productDAO.saveProduct(ENTITY))
                .willReturn(ENTITY);

        // 実行
        ProductDto rtnProductDto = 
                productService.saveProduct(ENTITY.getProductId(), ENTITY.getProductName(), ENTITY.getProductPrice(), ENTITY.getProductStock());

        // 確認
        assertEquals(ENTITY.getProductId(), rtnProductDto.getProductId());
        assertEquals(ENTITY.getProductName(), rtnProductDto.getProductName());
        assertEquals(ENTITY.getProductPrice(), rtnProductDto.getProductPrice());
        assertEquals(ENTITY.getProductStock(), rtnProductDto.getProductStock());

        verify(productDAO, times(1)).saveProduct(ENTITY);
    }
}
