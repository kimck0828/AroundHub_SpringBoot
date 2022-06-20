package com.kimck0828.aroundhub.aroundhub_springboot.service;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ShortUrlResponseDto;

/**
 * サービスIF
 */
public interface ShortUrlService {
  ShortUrlResponseDto generateShortUrl(String x_rapidapi_key, String x_rapidapi_host, String originalUrl);

  ShortUrlResponseDto getShortUrl(String x_rapidapi_key, String x_rapidapi_host, String originalUrl);
  Long deleteByOriginalUrl(String originalUrl);
}
