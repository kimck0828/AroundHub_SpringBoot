package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.common.AroundHubCommons;
import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hello")
@Slf4j
public class HelloController {

    @GetMapping("")
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

    /**
     * helloRequestParamsメソッド
     * @param name 名前
     * @param id ID
     * @return id:{id}, name:{name}
     */
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
     * 
     * @param e 例外インスタンス
     * @return レスポンスエンティティ
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String,String>> exceptionHandler(Exception e) {
        log.warn("HelloController#exceptionHandlerで実行!");

        return AroundHubCommons.getMapResponseEntity(HttpStatus.BAD_REQUEST, e);
    }
}


