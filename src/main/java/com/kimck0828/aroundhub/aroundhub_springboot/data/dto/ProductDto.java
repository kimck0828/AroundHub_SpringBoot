package com.kimck0828.aroundhub.aroundhub_springboot.data.dto;

import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ProductEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {
    private String productId;
    private String productName;
    private int productPrice;
    private int productStock;
    
    public ProductDto(ProductEntity entity) {
        if ( entity != null ) {
            this.productId = entity.getProductId();
            this.productName = entity.getProductName();
            this.productPrice = entity.getProductPrice();
            this.productStock = entity.getProductStock();
        }
    }
    
    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}
