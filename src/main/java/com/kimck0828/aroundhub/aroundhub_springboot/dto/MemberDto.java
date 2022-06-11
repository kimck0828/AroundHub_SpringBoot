package com.kimck0828.aroundhub.aroundhub_springboot.dto;

import lombok.*;

@Data
@Builder
public class MemberDto {
    private String id;
    private String name;
    private String email;
    private String organization;
}
