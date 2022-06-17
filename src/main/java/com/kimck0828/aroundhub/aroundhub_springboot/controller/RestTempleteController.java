package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import com.kimck0828.aroundhub.aroundhub_springboot.service.RestTempleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
