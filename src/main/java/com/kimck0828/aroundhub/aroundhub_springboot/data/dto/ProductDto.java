package com.kimck0828.aroundhub.aroundhub_springboot.data.dto;

import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ProductEntity;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {

  @NotNull
  @Size(min = 5, max = 100)
  private String productId;

  @NotNull
  private String productName;

  @NotNull
  @Min(value = 1)
  @Max(value = 10000000)
  private int productPrice;

  @NotNull
  @Min(value = 0)
  @Max(value = 99999)
  private int productStock;

  public ProductDto(ProductEntity entity) {
    if (entity != null) {
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
