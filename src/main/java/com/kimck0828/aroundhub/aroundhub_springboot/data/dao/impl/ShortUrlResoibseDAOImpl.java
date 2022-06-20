package com.kimck0828.aroundhub.aroundhub_springboot.data.dao.impl;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dao.ShortUrlResponseDAO;
import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ShortUrlEntity;
import com.kimck0828.aroundhub.aroundhub_springboot.data.repository.ShortUrlRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ShortUrlResponseDAOの実装クラス
 */
@Service
@RequiredArgsConstructor
public class ShortUrlResoibseDAOImpl implements ShortUrlResponseDAO {
  /** リポジトリ */
  final private ShortUrlRespository shortUrlRespository;

  @Override
  public void saveShortUrl(ShortUrlEntity entity) {
    shortUrlRespository.save(entity);
  }
  
  @Override
  public String getShortUrl(String originUrl) {
    ShortUrlEntity shortUrlEntity = shortUrlRespository.findByOrgUrl(originUrl);
    
    // DBから取得出来てない場合は、nullを返す
    if ( shortUrlEntity != null ) {
      return shortUrlEntity.getShortUrl();
    }
    return null;
  }

  @Override
  public Long deleteByOriginalUrl(String originalUrl) {
    return shortUrlRespository.deleteByOrgUrl(originalUrl);
  }
}
