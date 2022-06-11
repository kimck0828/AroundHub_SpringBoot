package com.kimck0828.aroundhub.aroundhub_springboot.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class AroundHubCommons {

    public static ResponseEntity<Map<String, String>> getMapResponseEntity(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("error type", status.getReasonPhrase());
        map.put("code", String.valueOf(status.value()));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, httpHeaders, status);
    }
}
