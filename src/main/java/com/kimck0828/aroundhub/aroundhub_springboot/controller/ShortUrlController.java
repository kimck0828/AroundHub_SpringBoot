package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.ShortUrlResponseDto;
import com.kimck0828.aroundhub.aroundhub_springboot.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/short-url")
@RequiredArgsConstructor
@Slf4j
class ShortUrlController {
  @Value("${short.url.x.rapidapi.key}")
  private String X_RAPIDAPI_KEY;

  @Value("${short.url.x.rapidapi.host}")
  private String X_RAPIDAPI_HOST;

  // ★「Autowired」は非推薦されている。
  // 4.3からは、final変数をコンストラクタで初期化すると、DIから取得できる
  // また、Lombokを使う場合は、「@RequiredArgsConstructor」を指定することでfinal変数のDI取得が自動にできる！
  final ShortUrlService shortUrlService;
  
  /**
   * 指定URLからショットURLを生成する
   * 
   * @param originalUrl 指定するURL
   * @return ショットＵＲＬ情報
   */
  @PostMapping()
  public ShortUrlResponseDto generateShortUrl(String originalUrl) {
    log.info("X_RAPIDAPI_KEY:{}, X_RAPIDAPI_HOST:{}, originalUrl:{}",
        X_RAPIDAPI_KEY, X_RAPIDAPI_HOST, originalUrl);
    return shortUrlService.generateShortUrl(X_RAPIDAPI_KEY, X_RAPIDAPI_HOST, originalUrl);
  }

  /**
   * 指定URLからショットURLを取得する
   * 
   * ＤＢ上に存在しない場合は、登録して返す
   * @param originalUrl 指定するURL
   * @return ショットＵＲＬ情報
   */
  @GetMapping()
  public ShortUrlResponseDto getShortUrl(String originalUrl) {
    return shortUrlService.getShortUrl(X_RAPIDAPI_KEY, X_RAPIDAPI_HOST, originalUrl);
  }

  /**
   * 指定ＵＲＬから登録されているショットＵＲＬ情報を削除する
   * 
   * @param originalUrl 指定するURL
   * @return 「削除成功」 - 削除成功、「削除データなし」 - 指定するURLの情報がＤＢに存在しない
   */
  @DeleteMapping("/original_url")
  public ResponseEntity<String> deleteByOriginalUrl(@RequestParam("url") String originalUrl) {
    log.info("controller[deleteByOriginalUrl] originalUrl:{}", originalUrl);
    Long deleteCount = shortUrlService.deleteByOriginalUrl(originalUrl);
    String result;
    if ( deleteCount == 1 ) {
      result = "削除成功";
    } else {
      result = "削除データなし";
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
