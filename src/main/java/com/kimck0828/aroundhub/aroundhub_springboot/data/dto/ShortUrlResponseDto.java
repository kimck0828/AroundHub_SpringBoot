package com.kimck0828.aroundhub.aroundhub_springboot.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrlResponseDto {
  private String originalUrl;
  private String shortUrl;
}
