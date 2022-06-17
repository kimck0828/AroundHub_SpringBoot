package com.kimck0828.aroundhub.aroundhub_springboot.service;

import com.kimck0828.aroundhub.aroundhub_springboot.dto.MemberDto;
import org.springframework.http.ResponseEntity;

public interface RestTempleteService {

  String getArounfHub();

  String getName(String name);

  String getName2(String name);

  ResponseEntity<MemberDto> postMemberDto();

  ResponseEntity<MemberDto> addHeader();
}
