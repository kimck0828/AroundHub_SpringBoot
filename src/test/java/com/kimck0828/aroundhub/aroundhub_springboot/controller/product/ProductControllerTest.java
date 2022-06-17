package com.kimck0828.aroundhub.aroundhub_springboot.controller.product;

import com.google.gson.Gson;
import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ProductDto;
import com.kimck0828.aroundhub.aroundhub_springboot.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    // ProductControllerクラス内でAutowiredで設定されている
    // ProductService変数のモック変数。
    @MockBean
    ProductService productService;
    
    @Test
    @DisplayName("getProductTestメソッド")
    public void getProductTest() throws Exception {
        // MOCK生成
        // productService.getProductメソッドにパラメータ値「12345」を渡すと
        // willReturnメソッド中のProductDtoインスタンスを返すように設定
        given(productService.getProduct("12345")).willReturn(
                ProductDto.builder()
                        .productId("prdid")
                        .productName("prdName")
                        .productPrice(1000)
                        .productStock(100)
                        .build()
        );
        
        // 上記MOCKで設定した内容が実行できるように同じパラメータ設定
        String productId = "12345";
        
        // 実行
        mockMvc
                // リクエスト実行
                .perform(
                        // GETメソッ
                        get("/api/product/" + productId ))
                // 確認項目
                // 200なのか
                .andExpect(status().isOk())
                // BODY（json）の値チェック
                .andExpect(jsonPath("$.productId").value("prdid"))
                .andExpect(jsonPath("$.productName").value("prdName"))
                .andExpect(jsonPath("$.productPrice").value(1000))
                .andExpect(jsonPath("$.productStock").value(100))
                .andDo(print())
        ;
        // 上記まで以下のメソッドが何回実行されたのか
        verify(productService, times(1)).getProduct(productId);
    }
    
    @Test
    @DisplayName("saveProductメソッド")
    public void saveProductTest() throws Exception {

        ProductDto productDto = ProductDto.builder()
                .productId("prdid")
                .productName("prdName")
                .productPrice(1000)
                .productStock(100)
                .build();
        
        given(productService.saveProduct(productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock()))
        .willReturn(productDto);
        
        String requestBody = new Gson().toJson(
                ProductDto.builder()
                        .productId("prdid")
                        .productName("prdName")
                        .productPrice(1000)
                        .productStock(100)
                        .build());
        
        mockMvc
                .perform(
                        // POST通信
                        post("/api/product/")
                                // BODYにJSONデータ
                                .content(requestBody)
                                // BODYタイプがJSONであることを指定
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("prdid"))
                .andExpect(jsonPath("$.productName").value("prdName"))
                .andExpect(jsonPath("$.productPrice").value(1000))
                .andExpect(jsonPath("$.productStock").value(100))
                .andDo(print())
        ;
        
        verify(productService).saveProduct(
                productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock());
    }
    
}
