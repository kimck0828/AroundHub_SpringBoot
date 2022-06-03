package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }
    
    @GetMapping("/name1/{name}")
    public String helloName(@PathVariable String name) {
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
}


