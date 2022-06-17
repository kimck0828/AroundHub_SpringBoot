package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  final String SERVER_BOX_URI = "http://localhost:8080";

  private UriComponentsBuilder getBaseUriComponentsBuilder(String path) {
    return UriComponentsBuilder
        .fromUriString(SERVER_BOX_URI)
        .path("/api/hello/" + (path.startsWith("/") ? path.substring(1) : path));
  }

  @Test
  void helloTest() {
    URI uri = getBaseUriComponentsBuilder("/")
        .encode()
        .build()
        .toUri();
    ResponseEntity<String> responseEntity =
        new RestTemplate().getForEntity(uri, String.class);

    Assertions.assertEquals(responseEntity.getBody(), "hello");
  }

  @Test
  void helloNameTest() {
    URI uri = getBaseUriComponentsBuilder("/name1/{name}")
        .encode()
        .build()
        .expand("XXXX")
        .toUri();
    ResponseEntity<String> responseEntity =
        new RestTemplate().getForEntity(uri, String.class);

    Assertions.assertEquals(responseEntity.getBody(), "hello XXXX");
  }

  @Test
  void helloRequestParams() {
    URI uri = getBaseUriComponentsBuilder("/params1")
        .queryParam("name", "test-name")
        .queryParam("id", "test-id")
        .encode()
        .build()
        .toUri();
    ResponseEntity<String> responseEntity =
        new RestTemplate().getForEntity(uri, String.class);

    Assertions.assertEquals(responseEntity.getBody(), "id:test-id, name:test-name");
  }
}
