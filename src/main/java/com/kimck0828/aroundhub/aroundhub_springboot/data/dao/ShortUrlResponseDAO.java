package com.kimck0828.aroundhub.aroundhub_springboot.data.dao;

import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ShortUrlEntity;

public interface ShortUrlResponseDAO {
  void saveShortUrl(ShortUrlEntity entity);
  String getShortUrl(String originUrl);

  Long deleteByOriginalUrl(String originalUrl);
}
