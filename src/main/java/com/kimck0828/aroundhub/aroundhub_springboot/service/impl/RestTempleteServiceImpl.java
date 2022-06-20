package com.kimck0828.aroundhub.aroundhub_springboot.service.impl;

import com.kimck0828.aroundhub.aroundhub_springboot.data.dto.MemberDto;
import com.kimck0828.aroundhub.aroundhub_springboot.service.RestTempleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
public class RestTempleteServiceImpl implements RestTempleteService {

  final String SERVER_BOX_URI = "http://localhost:9909";

  private UriComponentsBuilder getBaseUriComponentsBuilder(String path) {
    return UriComponentsBuilder
        .fromUriString(SERVER_BOX_URI)
        .path("/api/server/" + (path.startsWith("/") ? path.substring(1) : path));
  }

  private void resutlLog(String mthdName, ResponseEntity responseEntity) {
    log.info("★{} code[{}]", mthdName, responseEntity.getStatusCode());
    log.info("★{} body[{}]", mthdName, responseEntity.getBody());
  }


  @Override
  public String getArounfHub() {
    URI uri = getBaseUriComponentsBuilder("/around-hub")
        .encode()
        .build()
        .toUri();
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    resutlLog("getArounfHub", responseEntity);

    return responseEntity.getBody();
  }

  @Override
  public String getName(String name) {
    URI uri = getBaseUriComponentsBuilder("/name")
        .queryParam("name", name)
        .encode()
        .build()
        .toUri();

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    resutlLog("getName", responseEntity);

    return responseEntity.getBody();
  }

  @Override
  public String getName2(String name) {
    URI uri = getBaseUriComponentsBuilder("/path-valiable/{name}")
        .encode()
        .build()
        .expand(name) // PathValiable 複数の場合は,で指定
        .toUri();

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

    resutlLog("getName2", responseEntity);

    return responseEntity.getBody();
  }

  @Override
  public ResponseEntity<MemberDto> postMemberDto() {
    URI uri = getBaseUriComponentsBuilder("/member")
        .queryParam("name", "Flature")
        .queryParam("email", "kim@test.co.jp")
        .queryParam("organization", "AroundHub")
        .encode()
        .build()
        .toUri();

    MemberDto memberDto = MemberDto.builder()
        .name("dto name")
        .email("dto email")
        .organization("dto organization")
        .build();

    RestTemplate restTemplate = new RestTemplate();

    // 方法１
//        ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);

    // 方法２
    RequestEntity<MemberDto> requestEntity = RequestEntity
        .post(uri)
        // Header設定も出来る
//                .header("xxxx", "yyy")
        .body(memberDto);
    ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity,
        MemberDto.class);

    resutlLog("postMemberDto", responseEntity);
    return responseEntity;
  }

  @Override
  public ResponseEntity<MemberDto> addHeader() {
    URI uri = getBaseUriComponentsBuilder("/add-header")
        .encode()
        .build()
        .toUri();

    MemberDto memberDto = MemberDto.builder()
        .name("dto name")
        .email("dto email")
        .organization("dto organization")
        .build();

    RestTemplate restTemplate = new RestTemplate();

    RequestEntity<MemberDto> requestEntity = RequestEntity
        .post(uri)
        .header("around-header", "AroundHeader value")
        .body(memberDto);
    ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity,
        MemberDto.class);

    resutlLog("addHeader", responseEntity);
    return responseEntity;
  }
}
