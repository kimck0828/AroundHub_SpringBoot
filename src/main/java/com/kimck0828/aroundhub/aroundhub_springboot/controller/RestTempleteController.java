package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import com.kimck0828.aroundhub.aroundhub_springboot.service.RestTempleteService;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/rest-templete")
public class RestTempleteController {

  @Autowired
  private RestTempleteService service;

  @GetMapping("/around-hub")
  public String getArounfHub() {
    return service.getArounfHub();
  }

  @GetMapping("/name")
  public String getName(@RequestParam String name) {
    return service.getName(name);
  }

  @GetMapping("/path-valiable/{name}")
  public String getName2(@PathVariable String name) {
    return service.getName2(name);
  }

  @PostMapping("/member")
  public ResponseEntity<MemberDto> getMember() {

    return service.postMemberDto();
  }

  @PostMapping("/add-header")
  public ResponseEntity<MemberDto> addHeader() {
    return service.addHeader();
  }
  
  @GetMapping("/short-url")
  public String shortUrl(@RequestParam String url) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-RapidAPI-Key", "164f928ba5mshf23a1495c0c62a1p106b49jsnb6ada11dd2ca");
    headers.add("X-RapidAPI-Host", "url-shortener-service.p.rapidapi.com");
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    RestTemplate restTemplate = new RestTemplate();
    RequestEntity<ShortUrlDto> requestEntity = RequestEntity
        .post("https://url-shortener-service.p.rapidapi.com/shorten")
        .headers(headers)
        .body(new ShortUrlDto(url));
    ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,
        String.class);
    return responseEntity.getBody();
  }
  
  @Data
  @AllArgsConstructor
  class ShortUrlDto {
    String url;
  }
  @Data
  @AllArgsConstructor
  class ShortUrlResponse{
    String resultUrl;
  }
}
