package com.kimck0828.aroundhub.aroundhub_springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * コントローラーでの共通エラー処理クラス
 */
@RestControllerAdvice
@Slf4j
public class AroundHubException {
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String,String>> exceptionHandler(Exception e) {
        log.warn("AroundHubException#exceptionHandlerで実行!");

        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("error type", status.getReasonPhrase());
        map.put("code", String.valueOf(status.value()));
        map.put("message", e.getMessage());
        
        return new ResponseEntity<>(map, httpHeaders, status);
    }
}
