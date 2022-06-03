package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @PostMapping("/default")
    public String postMethod() {
        return "post method";
    }
    
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, String> postData) {
        StringBuilder sb = new StringBuilder();
        
        postData.keySet().forEach(key -> 
                sb.append(key).append(":").append(postData.get(key)).append(","));
        
        return sb.toString();
    }

    @PostMapping("/member/dto")
    public String postMemberDto(@RequestBody MemberDto dto) {
        return dto.toString();
    }
    
}

