package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hello")
@Slf4j
public class HelloController {

    @GetMapping("/")
    public String hello() {
        log.info("★HELLO");
        log.error("★HELLO");
        return "hello";
    }
    
    @GetMapping("/name1/{name}")
    public String helloName(@PathVariable String name) {
        log.info("★helloName param:{}", name);
        return "hello " + name; 
    }

    @GetMapping("/name2/{name}")
    public String helloName2(@PathVariable("name") String value) {
        return "hello " + value;
    }
    
    @GetMapping("/params1")
    public String helloRequestParams(@RequestParam String name,
                                     @RequestParam String id) {
        return "id:" + id + ", name:" + name;
    }
    @GetMapping("/params2")
    public String helloRequestParamsMap(@RequestParam Map<String, String> params) {
        return "id:" + params.get("id") + ", name:" + params.get("name");
    }
    
    @GetMapping("/params/dto")
    public String helloRequestParamsDto(MemberDto dto) {
        return dto.toString();
    }
    
    
    @GetMapping("/exception")
    public void exception() throws Exception{
        throw new Exception("わざとです");
    }

    /**
     * このクラス内で例外が発生した時に処理するメソッド。
     * このメソッドがなかったら、
     * {@link com.kimck0828.aroundhub.aroundhub_springboot.exception.AroundHubException#exceptionHandler(Exception)}で処理される
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String,String>> exceptionHandler(Exception e) {
        log.warn("HelloController#exceptionHandlerで実行!");

        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("error type", status.getReasonPhrase());
        map.put("code", String.valueOf(status.value()));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, httpHeaders, status);
    }
}


