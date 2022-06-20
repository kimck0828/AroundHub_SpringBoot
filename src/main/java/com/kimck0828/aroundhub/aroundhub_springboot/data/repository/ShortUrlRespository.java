package com.kimck0828.aroundhub.aroundhub_springboot.data.repository;

import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRespository extends JpaRepository<ShortUrlEntity, String>  {
    ShortUrlEntity findByOrgUrl(String ornUrl);
//    ShortUrlEntity findByShortUrl(String shortUrl);
    Long deleteByOrgUrl(String originalUrl);
}
