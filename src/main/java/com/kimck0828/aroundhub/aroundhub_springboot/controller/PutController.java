package com.kimck0828.aroundhub.aroundhub_springboot.controller;

import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/put")
public class PutController {

  @PutMapping("/default")
  public String putDefault() {
    return "Put method  - default";
  }

  @PutMapping("/member")
  public String putMember(@RequestBody Map<String, String> putData) {
    List<String> newList = putData.keySet().stream()
        .map(key -> key + "=" + putData.get(key))
        .collect(Collectors.toList());
    return String.join(",", newList);
  }

  @PutMapping("/member1")
  public String putMemberDto1(@RequestBody MemberDto dto) {
    return dto.toString();
  }

  @PutMapping("/member2")
  public MemberDto putMemberDto2(@RequestBody MemberDto dto) {
    return dto;
  }

  @PutMapping("/member3")
  public ResponseEntity<MemberDto> putMemberDto3(@RequestBody MemberDto dto) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
  }
}
