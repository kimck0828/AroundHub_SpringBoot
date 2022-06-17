package com.kimck0828.aroundhub.aroundhub_springboot.common.exception;

import com.kimck0828.aroundhub.aroundhub_springboot.common.AroundHubCommons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class AroundHubExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Map<String, String>> exceptionHandler(Exception e) {
    log.warn("AroundHubException#exceptionHandlerで実行!");

    return AroundHubCommons.getMapResponseEntity(HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler(value = ArrayStoreException.class)
  public ResponseEntity<Map<String, String>> exceptionHandler(AroundHubException e) {
    log.warn("AroundHubException#exceptionHandlerで実行!");

    HttpStatus status = e.getHttpStatus();
    HttpHeaders httpHeaders = new HttpHeaders();
    Map<String, String> map = new LinkedHashMap<>();
    map.put("error type", e.getHttpStatusType());
    map.put("code", String.valueOf(e.getHttpStatusCode()));
    map.put("message", e.getMessage());

    return new ResponseEntity<>(map, httpHeaders, status);
  }
}
