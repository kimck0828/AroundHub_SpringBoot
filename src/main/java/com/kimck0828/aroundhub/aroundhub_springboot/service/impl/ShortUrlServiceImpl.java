package com.kimck0828.aroundhub.aroundhub_springboot.service.impl;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dao.ShortUrlResponseDAO;
import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.RapidApiDto;
import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ShortUrlResponseDto;
import com.kimck0828.aroundhub.aroundhub_springboot.data.entity.ShortUrlEntity;
import com.kimck0828.aroundhub.aroundhub_springboot.service.ShortUrlService;
import java.net.URI;
import java.util.Collections;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * ショットＵＲＬ処理を行うサービスＩＦの実装クラス
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ShortUrlServiceImpl implements ShortUrlService {

  @Value("${short.url.x.rapidapi.access.point}")
  private String accessPoint;
  @Value("${short.url.x.rapidapi.access.path}")
  private String path;


  // ★「Autowired」は非推薦されている。
  // 4.3からは、final変数をコンストラクタで初期化すると、DIから取得できる
  // また、Lombokを使う場合は、「@RequiredArgsConstructor」を指定することでfinal変数のDI取得が自動にできる！
  final ShortUrlResponseDAO shortUrlResponseDAO;

  @Override
  public ShortUrlResponseDto generateShortUrl(String x_rapidapi_key, String x_rapidapi_host,
      String originalUrl) {

    log.info("service[generateShortUrl] originalUrl:{}", originalUrl);

    // 指定ＵＲＬからショットＵＲＬ取得通信を実行
    ResponseEntity<RapidApiDto> responseEntity = requestShortUrl(x_rapidapi_key, x_rapidapi_host,
        originalUrl);

    // DB保存
    ShortUrlEntity shortUrlEntity = ShortUrlEntity.builder()
        .shortUrl(Objects.requireNonNull(responseEntity.getBody()).getResult_url())
        .orgUrl(originalUrl)
        .build();
    shortUrlResponseDAO.saveShortUrl(shortUrlEntity);

    // コントローラーへ返すためにShortUrlResponseDtoインスタンスへ詰め直し
    ShortUrlResponseDto dto = getShortUrlResponseDto(
        originalUrl, Objects.requireNonNull(responseEntity.getBody()).getResult_url());

    log.info("service[generateShortUrl] Response Dto:{}", dto);

    return dto;
  }


  @Override
  public ShortUrlResponseDto getShortUrl(String x_rapidapi_key, String x_rapidapi_host,
      String originalUrl) {

    log.info("service[getShortUrl] originalUrl:{}", originalUrl);

    // ＤＢから指定ＵＲＬを元に情報を取得
    String shortUrl = shortUrlResponseDAO.getShortUrl(originalUrl);
    // ＤＢ上に存在しない場合、ショットＵＲＬを取得する処理を実行
    if (shortUrl == null) {
      ResponseEntity<RapidApiDto> responseEntity = requestShortUrl(x_rapidapi_key, x_rapidapi_host,
          originalUrl);
      shortUrl = Objects.requireNonNull(responseEntity.getBody()).getResult_url();
    }

    ShortUrlResponseDto dto = getShortUrlResponseDto(originalUrl, shortUrl);

    log.info("service[getShortUrl] Response Dto:{}", dto);

    return dto;
  }


  // ★削除処理では「@Transactional」を指定しないとエラーになる
  @Transactional
  public Long deleteByOriginalUrl(String originalUrl) {
    return shortUrlResponseDAO.deleteByOriginalUrl(originalUrl);
  }


  private ResponseEntity<RapidApiDto> requestShortUrl(String x_rapidapi_key, String x_rapidapi_host,
      String originalUrl) {
    log.info("[requestShortUrl] original URL:{}", originalUrl);

    URI uri = UriComponentsBuilder
        .fromUriString(accessPoint)
        .path(path)
        .encode()
        .build()
        .toUri();

    log.info("[requestShortUrl] make header");

    // 方法１
    // MIMEタイプを「application/json」で行う方法」
    // bodyをマッピング方法で行う場合は「application/json」を指定
//    HttpHeaders headers = new HttpHeaders();
//    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//    headers.setContentType(MediaType.APPLICATION_JSON);
//    headers.add("X-RapidAPI-Key", x_rapidapi_key);
//    headers.add("X-RapidAPI-Host", x_rapidapi_host);
//    Map<String, String> bodyMap = new HashMap<>();
//    bodyMap.put("url", originalUrl);
//
//    HttpEntity<String> entity = new HttpEntity<>(new Gson().toJson(bodyMap), headers);
//
//    ResponseEntity<RapidApiDto> responseEntity =
//        new RestTemplate().exchange(uri, HttpMethod.POST, entity, RapidApiDto.class);

    // 方法２
    // MIMEタイプを「application/x-www-form-urlencoded」で行う方法
    // bodyを「key=value&・・・」式で行う場合は「aapplication/x-www-form-urlencoded」を指定
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.add("X-RapidAPI-Key", x_rapidapi_key);
    headers.add("X-RapidAPI-Host", x_rapidapi_host);

    RequestEntity<String> requestEntity = RequestEntity
        .post(uri)
        .headers(headers)
        .body("url=" + originalUrl);

    ResponseEntity<RapidApiDto> responseEntity =
        new RestTemplate().exchange(requestEntity, RapidApiDto.class);

    log.info("[requestShortUrl] request has been successfully complete.");

    return responseEntity;
  }

  private ShortUrlResponseDto getShortUrlResponseDto(String originalUrl, String shortUrl) {
    return ShortUrlResponseDto.builder()
        .shortUrl(shortUrl)
        .originalUrl(originalUrl)
        .build();
  }
}
